package ground

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

object IntoFuture extends App {
  /**
   * Future is a placeholder object for a value that may not yet exist.
   * Generally, the value of the Future is supplied concurrently and
   * can subsequently be used.
   * Composing concurrent tasks in this way tends to result in faster,
   * asynchronous, non-blocking parallel code.
   */
  val executorService = Executors.newFixedThreadPool(32)
  implicit val context: ExecutionContext = ExecutionContext.fromExecutorService(executorService)

  def getUser: User = {
    Thread.sleep(1000)
    println("This is getUser!! thread: " + Thread.currentThread.getId)
    User("Seungwook", 29)
  }

  def getUser(name: String): User = {
    Thread.sleep(5000)
    println("This is getUser thread: " + Thread.currentThread.getId)
    User(name, 29)
  }

  def getUserException: User = {
    Thread.sleep(1000)
    println("This is getUser thread: " + Thread.currentThread.getId)
    throw new RuntimeException("Intent exception...")
  }

  def handleResult(result: Try[User]): Unit = {
        result match {
          case Success(user) => println(user + " This is onComplete Thread: " + Thread.currentThread.getId)
          case Failure(t) => println("Failed: " + t.getMessage + " This is onComplete Thread: " + Thread.currentThread.getId)
        }
  }

  def request(trial: Int) {
    val future: Future[User] = Future { getUser }
  }

  def createFuture(name: String): Future[User] = {
    /**
     * There are two ways to create Future and force to place the result of operation at Future.
     * (1) Constructor of Future
     * (2) Call Future.apply
     */
    // Future { getUser }
    println("createFuture called")
    Future.apply(getUser)
  }

  def printThread(name: String): Unit = {
    println(s"${name} thread: ${Thread.currentThread().getName}")
  }

  def threadPassTest_failure(): Unit = {
    /**
     * Each time calling thread related task, for example create Future object and
     * map the result, current thread will be changed.
     * Let's print thread name for tracing the context
     */
    printThread("Main")

    val fail: Future[User] = Future.failed(new Exception)
    fail.filter {
      user =>
        printThread("Filter")
        user.age > 20
    } map {
      user =>
        printThread("Map")
        user.name
    } andThen {
      case Success(s) =>
        printThread("andThen")
        println(s"Success: $s")
      case Failure(f) =>
        printThread("andThen")
        println(s"Failure: $f")
    }
  }

  def threadPassTest_success(name: String): Future[String] = {
    /**
     * Each time calling thread related task, for example create Future object and
     * map the result, current thread will be changed.
     * Let's print thread name for tracing the context
     */
    printThread("Main")

    val f: Future[User] = createFuture(name)
    f.filter {
      user =>
        printThread("Filter")
        user.age > 20
    } map {
      user =>
        printThread("Map")
        user.name
    } andThen {
      case Success(s) =>
        printThread("andThen")
        println(s"Success: $s")
      case Failure(f) =>
        printThread("andThen")
        println(s"Failure: $f")
    }
  }

//  val f1 = threadPassTest_success("Seungwook1")
//  val f2 = threadPassTest_success("Seungwook2")
//  val f3 = threadPassTest_success("Seungwook3")
//
//  while(!f1.isCompleted || !f2.isCompleted || !f3.isCompleted) {}
//  executorService.shutdown()

  def recursiveCall(name: String, count: Int): Future[_] = {
    val f = if (count < 10) Future.failed(new Exception) else createFuture(name)
    Thread.sleep(1000)

    f recoverWith {
      case e: Exception =>
        if (count < 3) {
          println("Retry: " + Thread.currentThread().getName)
          recursiveCall(name, count + 1)
        } else {
          println("Fall back to failure: " + Thread.currentThread().getName)
          Future.failed(e)
        }
    }
  }

  val f = recursiveCall("MyTest", 1)
  f onComplete {
    case Success(s) =>
      println(f"Success: $s")
    case Failure(f) =>
      println(f"Failure: $f")
  }

  println("Start looping: " + Thread.currentThread().getName)
  while (!f.isCompleted) {}
  println("Start sleep: " + Thread.currentThread().getName)
  Thread.sleep(10000)
  executorService.shutdown()
}

case class User(name: String, age: Int)
