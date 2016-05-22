package chehao.myscala

import java.util.Scanner

import scala.reflect.io.File
import scala.reflect.io.Path.string2path

object ScannerPrint {
  def main(args: Array[String]): Unit = {
      //object is like singleton object of a class defined implicitly.
      ScannerPrint.withScan(File("src/main/resources/scanfile.properties"),
        scanner => println("pid is " + scanner.next()))
      
      //throw exception
      ScannerPrint.withScan(File("src/main/resources/scanfile.properties"),
      scanner => log("pid is " + scanner.next() + 1/0))
      
      //call By Name  don't throw exception, , evaluate until execute 
      ScannerPrint.withScan(File("src/main/resources/scanfile.properties"),
      scanner => logByName("pid is " + scanner.next() + 1/0))
  }

  def withScan(f: File, op: Scanner => Unit) {
    val scanner = new Scanner(f.bufferedReader())
    try {
      op(scanner)
    } finally {
      scanner.close()
    }
  }
  val logEnable = false
  
  def log(msg:String) = if(logEnable) println(msg)
  //call by name
  def logByName(msg: =>String) = if(logEnable) println(msg)

}