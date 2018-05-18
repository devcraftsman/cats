package sandbox.ex361

import sandbox.common.Printable
import sandbox.common.PrintableInstances._


object Exercise {

  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)

  def run() = {

    println(format("hello"))
    println(format(true))




  }

}
