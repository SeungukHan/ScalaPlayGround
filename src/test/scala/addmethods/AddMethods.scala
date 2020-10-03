package addmethods

import org.scalatest._
import flatspec._
import matchers._

class AddMethods extends AnyFlatSpec with should.Matchers {
  implicit class StringImprovements(s: String) {
    def increase: String = s.map(c => (c + 1).toChar)
    def decrease: String = s.map(c => (c - 1).toChar)
    def hideAll: String = s.replaceAll(".", "*")
    def plusOne: Int = s.toInt + 1
    def asBoolean: Boolean = s match {
      case "0" | "zero" | "" | " " => false
      case _ => true
    }
  }

  "HAL".increase should be ("IBM")

  println("HAL".increase)
  println("HAL".decrease)
  println("HAL".hideAll)

  "100".plusOne should be (101)
  "0".asBoolean should be (false)
  "1".asBoolean should be (true)
}

