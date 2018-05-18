package sandbox.common

// Type Class
trait Printable[A] {
  self =>

  def format(a: A): String

  def contramap[B](func: B => A): Printable[B] =
    new Printable[B] {
      def format(value: B): String = self.format(func(value))
    }
}

// Interface
object PrintableInterface {
  def format[A](a: A)(implicit p: Printable[A]): String = p.format(a)

  def print[A](a: A)(implicit p: Printable[A]): Unit = println(format(a));
}

// Instances
object PrintableInstances {

  implicit val intPrintable: Printable[Int] =
    new Printable[Int] {
      def format(a: Int): String = a.toString
    }

  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String = "\"" + value + "\""
    }

  implicit val booleanPrintable: Printable[Boolean] =
    new Printable[Boolean] {
      def format(value: Boolean): String = if(value) "yes" else "no"
    }

}

object PrintableSyntax {

  implicit class PrintableOps[A](a: A) {

    def format(implicit p: Printable[A]): String = p.format(a)

    def print(implicit p: Printable[A]): Unit = println(p.format(a));

  }

}
