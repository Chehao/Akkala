package chehao.myscala

object Main {
  trait TraitExample[A] {
    def iterator: java.util.Iterator[A]
    def foreach(f: A => Unit) = {
      val iter = iterator
      while (iter.hasNext)
        f(iter.next)
    }
  }

  def main(args: Array[String]): Unit = {
    val list = new java.util.ArrayList[Int]() with TraitExample[Int]
    list.add(1); list.add(2)
    println("print for each")
    list.foreach { x => println(x) }
  }
}