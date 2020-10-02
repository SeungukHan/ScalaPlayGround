package matcher

import scala.util.Random

object PatternMatch {

  def main(args: Array[String]): Unit = {
    anonymousPatternMatch()
  }

  def toString(num: Int): String = num match {
    case 1 => "one"
    case 2 => "two"
    case 3 => "three"
    /* Expression for something else */
    case _ => "something else"
  }

  /**
   * underscore usage case
   */
  def matchRandom(): String = toString(Random.nextInt(4))

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

  def anonymousPatternMatch(): Unit = {
    /**
     * toString in here, should be something like, x => x.toString()
     * So it is not passing function, it is calling function.
     */
    List(1, 2, 3) map toString() foreach println
    /**
     * Below function is same as above one.
     */
    List(1, 2, 3) map {
      toString()
    } foreach println

    /**
     * toString at here is passing function as parameter.
     */
    List(1, 2, 3) map toString foreach println

//    assert(simpleResult equals result, "Should both result same")
  }
}
