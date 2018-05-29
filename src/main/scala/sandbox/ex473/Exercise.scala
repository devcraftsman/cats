package sandbox.ex473

import cats.data.Writer
import cats.instances.vector._
import cats.syntax.applicative._
import cats.syntax.writer._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._


object Exercise {

  type Logged[A] = Writer[Vector[String],A]

  def slowly[A](body: => A) = try body finally Thread.sleep(100)

  def factorial(n: Int) : Logged[Int] = {
    for {
      ans <- if (n == 0){
        1.pure[Logged]
      }else{
        slowly(factorial(n-1).map(_*n))
      }
      _ <- Vector(s"fact $n $ans").tell

    } yield ans

    /*val ans = slowly(if(n == 0) 1 else n * factorial(n-1))
    println(s"fact $n $ans")
    ans*/
  }

  def run() = {

    Await.result(Future.sequence(Vector(
      Future(factorial(3).mapWritten(v => println(v)).run),
      Future(factorial(12).mapWritten(v => println(v)).run)
    )),5.seconds)


  }



}

