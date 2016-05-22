package chehao.myscala.func

object Example {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4)

    println("list contains Odd ? " + containsOdd(list))
    println("list exist Odd? " + list.exists { (x: Int) => x % 2 == 1 })
    println("list exist Odd? " + list.exists { _ % 2 == 1 })

    val file = List("debug 2013 msg", "debug 2015 msg", "error 2015 msg", "warn 2016 msg");
    println("cat file |grep 'debug' | grep '2013' | wc : " +
      file.filter(_.contains("debug")).filter(_.contains("2013")).size)

    val num = file.map(wordcount).reduceLeft(_ + _)
    println(num)

    val num2 = foldLeft(file.map(wordcount))(0)(_ + _)
    println(num2)
  }

  //def wordcount(str:String):Int = str.split(" ").count { x => x=="msg" }
  def wordcount(str: String): Int = str.split(" ").count(_ == "msg")
  //tail call
  def foldLeft(list: List[Int])(init: Int)(f: (Int, Int) => Int): Int = {
    list match {
      case List() => init
      case head :: tail => println(head);foldLeft(tail)(f(init, head))(f)
    }
  }

  def containsOdd(list: List[Int]): Boolean = {
    for (i <- list) {
      if (i % 2 == 1)
        return true;
    }
    return false;
  }

}