package sandbox.common

import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._
import cats.syntax.eq._
import cats.Show
import cats.Eq


final case class Cat(name: String, age: Int, color: String)

object Cat {

  implicit val catPrintable: Printable[Cat] =
    new Printable[Cat] {
      def format(c: Cat): String = c.name + " is " + c.age + " years-old " + c.color + " cat."
    }

  implicit val catShow: Show[Cat] =
    Show.show(c => {
      val name = c.name.show
      val age = c.age.show
      val color = c.color.show
      s"$name is a $age years-old $color cat."
    })

  implicit val catEqual: Eq[Cat] =
    Eq.instance[Cat] { (cat1, cat2) =>
      (cat1.name === cat2.name ) &&
        (cat1.age === cat2.age ) &&
        (cat1.color === cat2.color)
    }
}
