package mapper

object ListMap {
  val li = Seq(1, 2, 3, 4, 5)

  def main(args: Array[String]): Unit = {
    li foreach {x => println(s"This is number: $x")}
    /* This is same as above. Just same as passing function as parameter explicitly. */
//    li.foreach(x => println(s"This is number: $x"))

    li map double foreach println
  }

  def double(x: Int): Int = x * 2
}
