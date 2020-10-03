package mapper

import org.scalatest._
import flatspec._
import matchers._

class loop extends AnyFlatSpec with should.Matchers {

  "yield" should "same as map" in {
    val greet = "hello world!"

    val mapResult = greet.map(_.toUpper)
    val yieldResult = for (c <- greet) yield c.toUpper

    mapResult should be (yieldResult)
  }

  "yield with body" should "same as map with filter" in {
    val greet = "hello world!"

    val mapResult = greet.filter(_ != 'l').map(_.toUpper)
    val yieldResult = for {
      c <- greet
      if c != 'l'
    } yield c.toUpper

    mapResult should be (yieldResult)
  }
}
