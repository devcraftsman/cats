package sandbox.ex641

import cats.data.Validated
import cats.instances.list._
import cats.syntax.either._
import cats.syntax.apply._


object Exercise {

  case class User(name: String, age: Int)

  type FormData = Map[String, String]
  type FailFast[A] = Either[List[String], A]
  type FailSlow[A] = Validated[List[String], A]

  def getValue(name: String)(formData: FormData): FailFast[String] = formData.get(name).toRight(List(s"$name field not Specified"))

  def parseInt(name: String)(data: String): FailFast[Int] = Either.catchOnly[NumberFormatException](data.toInt).leftMap(_ => List(s"$name must be an integer"))

  def nonBlank(name: String)(data: String): FailFast[String] = Right(data).ensure(List(s"$name cannot be blank"))(_.nonEmpty)

  //Either.cond(data.nonEmpty, data, List(s"$name cannot be blank"))

  def nonNegative(name: String)(data: Int): FailFast[Int] = Right(data).ensure(List(s"$name cannot be negative"))(_ > 0)

  //Either.cond(data > 0, data, List(s"$name cannot be negative"))

  /*def readName(data: FormData): FailFast[String] = for {
    value <- getValue("name")(data)
    valid <- nonBlank("name")(value)
  } yield valid

  def readAge(data: FormData): FailFast[Int] = for {
    value <- getValue("age")(data)
    nonBlank <- nonBlank("age")(value)
    intValue <- parseInt("age")(nonBlank)
    valid <- nonNegative("age")(intValue)
  } yield valid */


  def readName(data: FormData): FailFast[String] =
    getValue("name")(data)
      .flatMap(nonBlank("name") _)

  def readAge(data: FormData): FailFast[Int] =
    getValue("age")(data)
      .flatMap(nonBlank("age") _)
      .flatMap(parseInt("age") _)
      .flatMap(nonNegative("age") _)

  def readuser(data: FormData) : FailSlow[User] = (
    readName(data).toValidated,
    readAge(data).toValidated)
    .mapN(User.apply(_,_))




  def run() = {
    println(readuser(Map("name" -> "Dave", "age" -> "37")))

    println(readuser(Map("age" -> "-1")))

  }


}

