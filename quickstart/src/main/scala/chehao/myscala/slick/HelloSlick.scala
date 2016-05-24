package chehao.myscala.slick

// Use H2Driver to connect to an H2 database
import slick.driver.H2Driver.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await

import scala.concurrent.duration.Duration

// The main application
object HelloSlick extends App {
  val db = Database.forConfig("h2mem1")
  try {
    
    val suppliers = TableQuery[Suppliers]
    val setup = DBIO.seq(
      // create the schema
      suppliers.schema.create,
      suppliers += (101, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199"),
      suppliers += (49, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),
      suppliers += (150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966")

      // print the suppliers (select * from Suppliers)
      //suppliers.result.map(println)
      
    )
    Await.result(db.run(setup), Duration.Inf)

    db.run(suppliers.result).map(_.foreach {
      case (id, name, street, city, state, zip) => println(id + "," + name)
    })

  } finally db.close
}
 

