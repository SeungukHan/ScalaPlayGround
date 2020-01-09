package ground

object Loop extends App {
  // Will include from only and exclude to
  def testUntil(num: Int): Unit = (1 until num) foreach (println(_))
  // Will include from and to both
  def testTo(num: Int): Unit = (1 to num) foreach (println(_))

  val target = 10

  testUntil(target)
  println()
  testTo(target)
}
