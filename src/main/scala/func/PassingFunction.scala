package func

object PassingFunction {
  /**
   * callback should be some function who doesn't have any parameter and return value
   * @param callback
   */
  def oncePerSecond(callback: () => Unit): Unit = {
    (1 until 10).foreach(_ => {
      callback()
      Thread.sleep(1000)
    })
  }

  /**
   * callback should be some function who has Int parameter but doesn't have return value
   * @param callback
   */
  def oncePerSecond(callback: Int => Unit): Unit = {
    (1 until 10).foreach(n => {
      callback(n)
      Thread.sleep(1000)
    })
  }

  def greet(): Unit = {
    println("Greeting!!")
  }

  def main(args: Array[String]): Unit = {
    // Function argument
    greet
    /**
     * Scala compiler treat below greet as function call. As you can see above function call, in scala we can
     * call function without Empty-paren. Instead we can depend on anonymous function argument.
     */
//    oncePerSecond(greet)
    oncePerSecond(_ => greet())

    // Passing anonymous function as function argument
    oncePerSecond(n => println(s"Greeting!! $n"))
  }
}
