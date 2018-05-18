package sandbox.ex155

import cats.instances.option._
import cats.syntax.eq._
import sandbox.common.Cat

object Exercise {

  def run() = {
    val cat1 = Cat("Garfield", 38, "orange and black")
    val cat2 = Cat("Heathcliff", 33, "orange and black")

    val optionCat1 = Option(cat1)
    val optionCat2 = Option.empty[Cat]

    println("Cats are equal: "+ (cat1 === cat2))

    println("OptionalCats are equal: "+ (optionCat1 === optionCat2))
  }

}
