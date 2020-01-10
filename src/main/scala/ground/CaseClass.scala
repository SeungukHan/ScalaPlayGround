package ground

object CaseClass extends App {
  /**
   * object for case class created without new keyword
   * object for normal class create with new keyword
   */
  val candidates = List(
    PhoneCall("010-1234-1234", "iPhone"),
    Tweeter("myAccount"),
    Line("myLine"),
    Line("yourLine"),
    new Mutant("X-Man")

  )

  val vips = List("010-1234-1234", "myLine")

  def show(notification: Notification): Unit = {
    println(
      notification match {
        case PhoneCall(numbers, deviceType) => s"This is phone call: $numbers, $deviceType"
        case Tweeter(account) => s"This is tweeter: $account"
        /* underscore will locate when variable not used, as like ignorable loop iterator */
        case Line(_) => s"This is Line: Ignore account"
        case _ => "Wrong class is coming"
      })
  }

  def caseClassTest(): Unit = {
    candidates foreach {
      x => show(x)
    }

    /**
     * Should not use map when return type is Unit
     */
    /*
    candidates map {
      x => show(x)
    }
    */
  }

  def caseClassWithGuard(): Unit = {
    candidates foreach {
      x => showOnlyVIP(x, vips)
    }
  }

  def showOnlyVIP(notification: Notification, vips: List[String]): Unit = {
    val result = notification match {
      case PhoneCall(numbers, _) if vips contains numbers => s"This is vip PhoneCall: $numbers"
      case Tweeter(account) if vips contains account => s"This is vip Tweeter: $account"
      case Line(account) if vips contains account => s"This is vip Line: $account"
      case Line(account) => s"This is normal Line: $account"
      case _ => s"Not VIP"
    }
    println(result)
  }

  def caseClassWithType(): Unit = {
    candidates foreach {
      showOnlyVIP(_, vips)
    }

    /**
     * underscore can be used as placeholder for Anonymous Functions' parameter
     */
    /*
    candidates foreach {
      x => showOnlyVIP(x, vips)
    }
     */
  }

  def showWithType(notification: Notification, vips: List[String]): Unit = {
    val result = notification match {
      // You don't need to put brace for case statement
      case phoneCall: PhoneCall if vips contains phoneCall.numbers => {
        val number = phoneCall.numbers
        s"This is vip PhoneCall: $number"
      }
      case tweeter: Tweeter =>
        val account = tweeter.account
        s"All tweeter: $account"
      case _ => "All others"
    }
    println(result)
  }

  def caseClassEquality(): Unit = {
    val myPhone = PhoneCall("010-1234-1234", "iPhone")
    val yourPhone = PhoneCall("010-1234-1234", "iPhone")

    assert(myPhone == yourPhone, "case class having identical value should be same")
  }

  def shortMatch(notification: Notification): Unit = {
    notification match {
      case _: PhoneCall => println("This is PhoneCall")
      case _: Tweeter => println("This is Tweeter")
      case _: Line => println("This is Line")
      case _ => println("This is mutant")
    }
  }

//  caseClassWithGuard()
  candidates foreach {
    e => shortMatch(e)
  }
}

abstract class Notification
/**
 * Belows are Notification types, think that case class make type for extends one.
 * Then, what should call if case class without extends?
 */
case class PhoneCall(numbers: String, deviceType: String) extends Notification
case class Tweeter(account: String) extends Notification
case class Line(account: String) extends Notification
class Mutant(name: String) extends Notification
