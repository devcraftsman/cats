package sandbox.ex362


import sandbox.common.Box
import sandbox.common.CodecInterface._
import sandbox.common.CodecInstances._


object Exercise {

  def run() = {

    println(encode(123.4))

    println(decode[Double]("123.4"))

    println(encode(Box(123.4)))

    println(decode[Box[Double]]("123.4"))

  }

}
