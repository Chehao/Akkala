package chehao.myscala.condition

object Match {
  def fibonacci(i: Int): Int =
    i match {
      case 0 => 0
      case 1 => 1
      case n: Int if (n > 0) => fibonacci(n - 1) + fibonacci(n - 2)
      case _ => 0
    }
  def main(args: Array[String]): Unit = {
    println(fibonacci(3))
    
    println(fibonacci(10))
  }

}