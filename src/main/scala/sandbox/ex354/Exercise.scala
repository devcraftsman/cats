package sandbox.ex354

import sandbox.common.Tree
import cats.syntax.functor._

object Exercise {

  def run() = {

    Tree.leaf(100).map(_ * 2)
    // res10: wrapper.Tree[Int] = Leaf(200)
    val mapped = Tree.branch(Tree.leaf(10), Tree.branch(Tree.leaf(10), Tree.leaf(20))).map(_ * 2)
    // res11: wrapper.Tree[Int] = Branch(Leaf(20),Leaf(40))

    println(mapped)

  }

}
