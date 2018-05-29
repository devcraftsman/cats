package sandbox.common

/*
import cats.Id
trait Monad[F[_]] {

  def pure[A](a: A) : F[A]
  def flatMap[A,B](value : F[A])(func: A => F[B]) : F[B]

  def map[A,B](value : F[A])(func : A => B) : F[B] = flatMap(value)(a =>pure(func(a)))

}

object MonadInstances {

  implicit def idMonad[A] : Monad[Id] = new Monad[Id[A]] {

    override def pure[A](a: A): Id[A] = a

    override def flatMap[A, B](value: Id[A])(func: A => Id[B]): Id[B] = func(value)

    override def map[A,B](value : Id[A])(func : A => B) : Id[B] =func(value)
  }
} */
