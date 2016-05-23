package chehao.myscala.slick

import slick.driver.H2Driver.api._
import slick.lifted.Tag
import slick.lifted.{ProvenShape, ForeignKeyQuery}

class Suppliers(tag: Tag) extends Table[(Int, String, String, String, String, String)](tag, "SUPPLIERS") {  
  // This is the primary key column:
  def id: Rep[Int] = column[Int]("SUP_ID", O.PrimaryKey)
  def name: Rep[String] = column[String]("SUP_NAME")
  def street: Rep[String] = column[String]("STREET")
  def city: Rep[String] = column[String]("CITY")
  def state: Rep[String] = column[String]("STATE")
  def zip: Rep[String] = column[String]("ZIP")
  
  // Every table needs a * projection with the same type as the table's type parameter
  def * : ProvenShape[(Int, String, String, String, String, String)] =
    (id, name, street, city, state, zip)
}