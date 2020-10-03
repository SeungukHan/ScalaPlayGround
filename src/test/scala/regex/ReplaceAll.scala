package regex

import org.scalatest._
import flatspec._
import matchers._

class ReplaceAll extends AnyFlatSpec with should.Matchers {

  private val pattern = "[0-9]".r
  private val address = "123 Main Street"

  "Pattern" should "replace all numbers" in {
    pattern.replaceAllIn(address, "X") should be ("XXX Main Street")
  }
}
