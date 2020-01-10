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
    println("This is getUser thread: " + Thread.currentThread.getId)
    User("Seungwook", 29)
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

  val resultFuture: Future[User] = Future { getUser }

  resultFuture onComplete {
    case Success(user) => println(user + " This is onComplete Thread: " + Thread.currentThread.getId)
    case Failure(t) => println("Failed: " + t.getMessage + " This is onComplete Thread: " + Thread.currentThread.getId)
  }
  println("This is main Thread: " + Thread.currentThread.getId)
}

case class User(name: String, age: Int)
