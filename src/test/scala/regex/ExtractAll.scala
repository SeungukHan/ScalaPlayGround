package regex

import org.scalatest._
import flatspec._
import matchers._

class ExtractAll extends AnyFlatSpec with should.Matchers {
  "Pattern" should "extract numbers and words" in {
    val pattern = "([0-9]+) ([a-zA-Z]+)".r

    // This kind of assignment is really awkward, but it will be convenient when combine with match.
    val pattern(count, fruit) = "100 Bananas"

    count should be ("100")
    fruit should be ("Bananas")
  }

  "Multiple pattern" should "match given different format input" in {
    val KoreaFormat = "([0-9]{3})-([0-9]{4})-([0-9]{4})".r
    val JapanFormat = "([0-9]{2})-([0-9]{4})-([0-9]{4})".r

    val extractPartition: String => String = {
      case KoreaFormat(head, middle, tail) => s"Korea:Head<$head>,Middle<$middle>,Tail<$tail>"
      case JapanFormat(head, middle, tail) => s"Japan:Head<$head>,Middle<$middle>,Tail<$tail>"
      case _ => "Nothing..."
    }
    println(extractPartition("111-0000-0000"))
    println(extractPartition("22-0000-0000"))

    val decideCountry: String => String = {
      case KoreaFormat(_*) => "Korea"
        // below should have * after _
      case JapanFormat(_) => "Japan"
      case _ => "Nothing..."
    }

    decideCountry("000-0000-0000") should be ("Korea")
    decideCountry("00-0000-0000") should be ("Nothing...")
  }
}
