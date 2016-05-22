package chehao.myscala.func

import scala.annotation.tailrec

object TailCallFactorial {

  def main(args: Array[String]): Unit = {
    println(factorial(1,10))
  }

  @tailrec def factorial(acc: Int, n: Int): Int = {
    if (n <= 1) acc
    else factorial(n * acc, n - 1)
  }
}