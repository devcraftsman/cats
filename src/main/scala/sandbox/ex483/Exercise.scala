package sandbox.ex483

import cats.data.Reader
import cats.syntax.applicative._


object Exercise {

  case class Db(usernames: Map[Int, String], passwords: Map[String, String])

  type DbReader[A] = Reader[Db,A]

  def findUser(userId : Int) : DbReader[Option[String]] = Reader(db => db.usernames.get(userId))

  def checkPassword(username : String, password: String) : DbReader[Boolean] = Reader(db => db.passwords.get(username).contains(password))

  def checklogin(userId : Int, password: String) : DbReader[Boolean] =
    for {
    username <- findUser(userId)
    exists <- username.map(checkPassword(_,password)).getOrElse(false.pure[DbReader])
  } yield exists




  def run() = {

    val users = Map (
      1 -> "dade",
      2 -> "kate",
      3 -> "margo"
    )

    val passwords = Map (
      "dade" -> "zerocool",
      "kate" -> "acidburn",
      "margo" -> "secret"
    )

    val db = Db(users,passwords)

    println(checklogin(1,"zerocool").run(db))

    println(checklogin(4,"davinci").run(db))


  }


}

