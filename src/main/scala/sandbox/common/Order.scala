package sandbox.common

import cats.Monoid

case class Order(totalCost: Double, quantity: Double)

object Order {

  implicit val orderMonoid : Monoid[Order] =
    new Monoid[Order]{

      def empty = Order(0,0)
      def combine(o1 :Order, o2 : Order) : Order =
        Order(o1.totalCost + o2.totalCost, o1.quantity + o2.quantity)
    }

}
