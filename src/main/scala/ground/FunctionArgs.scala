package ground

object FunctionArgs extends App {

  def oncePerSecond(callback: () => Unit): Unit = {
    for (_ <- 1 to 2) {
      callback()
      Thread.sleep(1000)
    }
  }

  def timeFlies(): String = {
    val msg = "Time goes so fast..."
    println(msg)
    msg + "go out"
  }

  def passingValueOrFunction(): Unit = {
    /**
     * Below two format is same
     */
    oncePerSecond { timeFlies }
    oncePerSecond(timeFlies)

    /**
     * Below two format is same.
     * But putting parentheses is better even there is no argument
     */
    println { timeFlies }
    println { timeFlies() }
  }

  def passingAnonymous(): Unit = {
    oncePerSecond {() => println("Time goes so fast")}
  }

  def callingAnonymousFunction_notInvoke(): Unit = {
    /**
     * Below to two expression are not called.
     * Bracing with () or {} does not guarantee invoke.
     */
    (() => {
      println("I am in anonymous function")
    })

    {() => {
      println("I am in anonymous function")
    }}
  }

  def callingAnonymousFunction_inFunctionParameter(): Unit = {
    /**
     * Will print lambda address
     */
    println {
      (() => {
        println("I am in anonymous function in println parentheses")
        "Hello World!"
      })
    }
  }

  def callingAnonymousFunction(): Unit = {
    /**
     * This format only guarantee lambda object.
     * () => {}
     *
     * Need invoke action with parentheses at the last of lambda.
     * (() => {}) ()
     */
    ((name: String) => println(s"Hello! $name")) ("Togepi")
  }

  def assignFunctionToVariable(): Unit = {
    val greet = () => println("Hello World")
    greet()
  }

  callingAnonymousFunction()
  passingAnonymous()
  assignFunctionToVariable()

  /**
   * Functional Programming;;??
   * Final stage of recursive call;;??
   * That is secret of functional programming?
   */
  // three args are passed in:
  // 1) 'f' - a function that takes an Int and returns an Int
  // 2) 'a' - an Int
  // 3) 'b' - an Int
  def sum(f: Int => Int, a: Int, b: Int): Int = if (a > b) 0 else f(a) + sum(f, a + 1, b)

  // these three functions use the sum() function
  def sumInts(a: Int, b: Int): Int = sum(id, a, b)
  def sumSquares(a: Int, b: Int): Int = sum(square, a, b)
  def sumPowersOfTwo(a: Int, b: Int): Int = sum(powerOfTwo, a, b)

  // three functions that are passed into the sum() function
  def id(x: Int): Int = x
  def square(x: Int): Int = x * x
  def powerOfTwo(x: Int): Int = if (x == 0) 1 else 2 * powerOfTwo(x - 1)

  // this simply prints the number 10
  println("sum ints 1 to 4 = " + sumInts(1,4))
}
