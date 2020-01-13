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

//  def executeShort(body: => Unit): Unit = executionContext.execute(body)


  def concurrent(): Unit = {
    for (i <- 0 until 16) {
      execute {
        Thread.sleep(200)
        println(s"Task $i completed.")
      }
    }
    Thread.sleep(10000)
  }
}
