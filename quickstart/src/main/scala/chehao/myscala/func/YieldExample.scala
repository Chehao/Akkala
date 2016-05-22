package chehao.myscala.func

object YieldExample {
  def main(args: Array[String]): Unit = {
    var file = List("warn 2013 msg", "warn 2012 msg","error 2013 msg", "warn 2013 msg")
    def wordcount(str: String): Int = str.split(" ").count(_ == "msg")
    val counts = 
      for(line <- file)
        yield wordcount(line)
    val sum = counts.reduceLeft(_+_)
    println("word count: " + sum)
  }
}