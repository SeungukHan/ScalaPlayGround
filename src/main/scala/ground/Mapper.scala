package ground

object Mapper extends App {
  /**
   * map usage case
   */
  def listToList(): Unit = {
    val li = Seq(1, 2, 3, 4, 5)
    li foreach {x => println(s"This is number: $x")}
    val result = li map {
      x => x * x
    }
    result foreach {x => println(s"Result is number: $x")}
  }

  def listToMap(): Unit = {
  }
}