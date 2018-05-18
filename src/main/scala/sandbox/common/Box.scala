package sandbox.common

final case class Box[A](value: A)

object  Box {

  implicit def boxPrintable[A](implicit p:Printable[A]) = p.contramap[Box[A]](_.value)

  implicit def boxCodec[A](implicit c:Codec[A]) : Codec[Box[A]] = c.imap(Box(_),_.value)

}

