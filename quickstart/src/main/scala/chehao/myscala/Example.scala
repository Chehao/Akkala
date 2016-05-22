package chehao.myscala

object Example {
  val who = "chehao"
  def main(args: Array[String]): Unit = {
    
    println("Hello, " + who + "!")
    println("Goodbye!" + who)

    println("square of 8 " + square(8))
    println("square block 10 " + squareWithBlock(10))
    println("square val " + squareVal(8))
    println("addOne " + addOne(squareVal, 6))

    println("multiplay 2 x 2 = " + multiplay(2, 2))
    println("multiplay2 2 x 2 = " + multiplay2(2)(2))
    println("multiplay3 2 x 2 = " + multiplay3(2)(2))
  }

  def square(a: Int) = a * a

  def squareWithBlock(a: Int) = {
    a * a
  }

  //function(lambda) as val 
  val squareVal = (a: Int) => a * a

  //val multiplay = (a: Int, b: Int) => a * b
  def multiplay(a: Int, b: Int) = a * b
  def multiplay2(a: Int) = (b: Int) => a * b
  //currying
  def multiplay3(a: Int)(b: Int) = a * b

  def addOne(f: Int => Int, arg: Int) = f(arg) + 1

}