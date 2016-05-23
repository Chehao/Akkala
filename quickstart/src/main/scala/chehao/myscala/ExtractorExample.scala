package chehao.myscala

import scala.util.matching.Regex

object ExtractorExample {
  def main(args: Array[String]): Unit = {
    "user@domain.com" match {
      case Email(user, domain) => println(user + "@" + domain)

    }
    "user1domain.com" match {
      case Email(user, domain) => println(user + "@" + domain)
      case _=> println("none")
    }
  }
}

object Email {
  def unapply(str: String) = new Regex("""(.*)@(.*)""")
    .unapplySeq(str) match {
      case Some(list) => Some(list(0), list(1))
      case _ => None
    }
}

