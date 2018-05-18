package sandbox.ex1

import sandbox.common.Cat
import sandbox.common.PrintableSyntax._

object Exercise {

  def run(): Unit = {
    val cat = Cat("fuffy", 5, "black")

    //Printable.print(cat)
    cat.print
  }

}
