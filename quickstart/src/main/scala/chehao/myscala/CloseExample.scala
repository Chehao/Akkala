package chehao.myscala

object CloseExample {

  def main(args: Array[String]): Unit = {
    val con = new Connection();
    //withClose will run close with connection, input 2 is to implement process with connection
    withClose(con, conn => println("execute connection "))
    withClose2(con)(conn=>println("execute connection 2"))
  }
  //like delegate of C#
  //define function with 2 interface input delegate
  //delegate 1. connection class with close function, 
  //delegate 2. use connection instance to do something, 
  def withClose(closeAble: { def close(): Unit }, op: { def close(): Unit } => Unit) {
    try {
      op(closeAble)
    } finally {
      closeAble.close()
    }
  }

  def withClose2(closeAble: { def close(): Unit })(op: { def close(): Unit } => Unit) {
    try {
      op(closeAble)
    } finally {
      closeAble.close()
    }
  }
  class Connection {
    def close() = println("close Connection")
  }
}

