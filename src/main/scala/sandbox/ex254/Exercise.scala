package sandbox.ex254

import cats.Monoid
import cats.instances.int._
import cats.instances.option._
import cats.syntax.semigroup._

object Exercise {

  def add[A: Monoid](items: List[A]): A = items.foldLeft(Monoid[A].empty)(_ |+| _)

  def run() = {
    println(add(List(1,2,3,4,5)))
    println(add(List(Option(1), Option(2),Option.empty, Option(3),Option(4),Option(5))))

  }

}
