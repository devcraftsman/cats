package sandbox.ex631

import cats.Monad
import cats.syntax.flatMap._
import cats.syntax.functor._

object Exercise {


  def product[M[_]: Monad, A, B](x: M[A], y: M[B]): M[(A, B)] = x.flatMap(v => y.map((v,_)))



  def run() = {



  }


}

