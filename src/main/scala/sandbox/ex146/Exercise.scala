package sandbox.ex146

import cats.syntax.show._ // for show
import sandbox.common.Cat

object Exercise {

  def run() = {
    val cat = Cat("fuffy",5,"black")
    println(cat.show)
  }

}
