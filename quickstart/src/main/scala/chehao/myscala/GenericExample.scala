package chehao.myscala

object GenericExample {
  def main(args: Array[String]): Unit = {
    val conn = new Connection()
    val msg = withClose(conn)(conn => {
      println("do something with Connection..")
      "123456"
    })
    println(msg)
    println(withClose(conn)(conn => {
      println("do something with Connection..")
      123456
    }))
  }

  def withClose[A <: { def close(): Unit }, B](closeAble: A)(f: A => B): B = {
    try {
      f(closeAble)
    } finally {
      closeAble.close()
    }
  }

  class Connection {
    def close() = println("close connection")
  }

}