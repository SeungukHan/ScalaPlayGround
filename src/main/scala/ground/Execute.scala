package ground

import java.util.concurrent.{ExecutorService, Executors}

import scala.concurrent._
import scala.util.Random

object Execute extends App {

  val executorService: ExecutorService = Executors.newFixedThreadPool(16)
  val executionContext = ExecutionContext.fromExecutorService(executorService)

  def execute(body: => Unit): Unit = executionContext.execute(new Runnable {
    override def run(): Unit = body
  })

  def concurrent(): Unit = {
    for (i <- 0 until 16) execute {
      Thread.sleep(200)
      println(s"Task $i completed.")
    }
    Thread.sleep(10000)
  }

  /**
   * underscore usage case
   */
  def matchRandom(): String = Random.nextInt(4) match {
    case 1 => "one"
    case 2 => "two"
    case 3 => "three"
    /* Expression for something else */
    case _ => "something else"
  }

  def selectMultipleRandom(n: Int): Unit = {
    for(_ <- 0 until n) {
      println(matchRandom())
    }

    /**
     * i is declared but not used.
     * Above format hide that kind of variable with underscore.
     * Below format still expose unused variable
     */
    for(i <- 0 until n) {
      println(matchRandom())
    }
  }

  selectMultipleRandom(10)
}
