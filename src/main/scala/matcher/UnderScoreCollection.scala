package matcher

object UnderScoreCollection {
  val abuser = List("dog", "cat", "cow")
  val mixedAbuser = List("cat", "cow", "dog")
  val animal = Map("name" -> "dog", "address" -> "daegu")
  val animalInOrder = Map(1 -> "dog", 2 -> "cat", 3-> "cow")

  def main(args: Array[String]): Unit = {
    checkAbuser(List("dog"))
    checkAbuser(List("dog", "cat"))
    checkAbuser(List("dog", "cat", ""))
    checkAbuser(List("dog", "cat", "", ""))
    checkAbuser(List(""))
    checkAbuser(List())
  }

  def checkAbuser(abuser: List[String]): Unit = {
    val result =
    abuser match {
      case List("dog") => "Only dog is abuser"
      case List("dog", "cat") => "dog and cat are abusers"
      case List("dog", "cat", _) => "dog, cat and something else one are abusers"
      case List("dog", "cat", _*) => "dog, cat and something a few are abusers"
      case List(_*) => "Actually this is check whether inserted object is list or not"
      case _ => "Never reach due to above condition"
    }

    println(result)
  }
}
