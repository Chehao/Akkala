package chehao.myscala

object LazyExample {
  def main(args: Array[String]): Unit = {
    val version = new ScalaCurrentVersion(
      "https://github.com/scala/scala/blob/2.12.x/build.number")
    println("get scala version from " + version.url)
    version.majorVersion.foreach(println _)
    version.minorVersion.foreach(println _)
  }
}

class ScalaCurrentVersion(val url: String) {
  lazy val source = {
    println("fetching from url...")
    scala.io.Source.fromURL(url).getLines().toList
  }
  lazy val majorVersion = source.find(_.contains("version.major"))
  lazy val minorVersion = source.find(_.contains("version.minor"))
}