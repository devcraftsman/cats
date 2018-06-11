package sandbox.cases.cap9

import cats.Monoid
import cats.instances.int._
import cats.syntax.semigroup._

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._


object Chapter9 extends App {

  def foldMap[A,B : Monoid](as: Vector[A],func : A => B): B =
    as.foldLeft(Monoid[B].empty)(_ |+| func(_))


  def parallelFoldMap[A,B : Monoid](values: Vector[A])(func : A => B): Future[B] = {
    val numCores = Runtime.getRuntime.availableProcessors
    val size = (1.0 * values.size / numCores).ceil.toInt

    val grouped : Vector[Vector[A]] = values.grouped(size).toVector
    val folded : Vector[Future[B]] = grouped.map(l => Future{foldMap(l,func)})
    Future.sequence(folded).map(_.foldLeft(Monoid[B].empty)(_ |+| _))
  }

  val result : Future[Int] = parallelFoldMap((1 to 1000000).toVector)(identity)

  Await.result(result.map(println(_)),1.second)

}
