package ground

import scala.util.Random

object PatternMatch extends App {
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

  def anonymousPatternMatch(): Unit = {
    /**
     * Below two format is same.
     */
    val simpleResult = List(1, 2, 3) map {
      case 1 => "one"
      case 2 => "two"
      case 3 => "three"
      case _ => "what?"
    }

    val result = List(1, 2, 3) map {
      x => x match {
        case 1 => "one"
        case 2 => "two"
        case 3 => "three"
        case _ => "what?"
      }
    }

    assert(simpleResult equals result, "Should both result same")
  }

  anonymousPatternMatch()
}
