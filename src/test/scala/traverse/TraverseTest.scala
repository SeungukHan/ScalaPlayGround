package traverse

import org.scalatest._
import flatspec._
import matchers._

class TraverseTest extends AnyFlatSpec with should.Matchers {

  "to" should "make inclusive range" in {
    (1 to 10).toList.length should be (10)
  }

  "to with by" should "make inclusive range with specific stride" in {
    (1 to 10 by 2).toList.length should be (5)
  }

  "until" should "make [} range" in {
    (1 until 10).toList.length should be (9)
  }

  "until with if" should "make range who meet condition" in {
    (for (x <- 1 until 10 if x < 2) yield x).length should be (1)
  }

  val names = List("Moon", "Kony", "Brown")

  "list with if" should "make list who meet condition" in {
    (for (name <- names if name.length > 4) yield name) should be (List("Brown"))
  }

  "zipWithIndex" should "gives index for each element" in {
    (for ((_, idx) <- names.zipWithIndex) yield idx) should be (List(0, 1, 2))
  }

  "map" should "iterate with key and value together" in {
    val capital = Map("Korea" -> "Seoul", "Japan" -> "Tokyo")
    (for ((_ , city) <- capital) yield city) should be (List("Seoul", "Tokyo"))
  }

  "block for condition" should "iterate each element who meet condition" in {
    val selected = for {
      name <- names
      if name.endsWith("n")
      if name.length > 4
    } yield {
      val upper = name.toUpperCase
      s"${upper.length}, ${upper}"
    }

    selected should be (List("5, BROWN"))
  }
}
