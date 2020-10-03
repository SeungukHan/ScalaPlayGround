package regex

import org.scalatest._
import flatspec._
import matchers._

class FindIn extends AnyFlatSpec with should.Matchers {

  "Pattern" should "match number address" in {
    val address = "123 Main Street Suite 101"
    val numPattern = "[0-9]+".r

    val result = numPattern.findFirstIn(address)
    result.isEmpty should be (false)
  }

  "Pattern" should "Not match number address" in {
    val address = "Main Street Suite"
    val numPattern = "[0-9]+".r

    val result = numPattern.findFirstIn(address)
    // result is option, and the option could be Some or None.
    // To handler option there are three ways
    // 1. getOrElse: Extract value in Option with giving default value when the value doesn't exist in it.
    // 2. foreach: Giving function when the element exist in the option, if not exist the function won't call.
    // 3. match: Match the state of option and give different result for states

    // 1. getOrElse
    {
      result.getOrElse("Empty") should be ("Empty")
    }
    // 2. foreach
    {
      result.foreach(v => {
        // Never call below
        false should be (true)
      })
    }
    // 3. match
    {
      val extracted = result match {
        case Some(value) => value
        case None => "Empty"
      }
      extracted should be ("Empty")
    }
    println(resolveOption(result))
  }

  "Pattern" should "match multiple number address" in {
    val address = "123 Main Street Suite 101"
    val numPattern = "[0-9]+".r

    val result = numPattern.findAllIn(address)
    result.isEmpty should be (false)
    result foreach println
  }

  def resolveOption(o: Option[String]): String = o match {
    case Some(n) => s"The option has $n"
    case None => "The option is empty"
  }
}
