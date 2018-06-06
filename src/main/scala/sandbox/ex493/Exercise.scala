package sandbox.ex493

import cats.data.State
import cats.syntax.applicative._

object Exercise {


  type CalcState[A] = State[List[Int], A]

  def operator(op: (Int, Int) => Int): CalcState[Int] = State[List[Int], Int] {
    case (a :: b :: ls) =>
      val r = op(a, b)
      (r :: ls, r)

    case _ => sys.error("Fail!")
  }


  def operand(n: Int): CalcState[Int] = State[List[Int], Int] {
    l => (n :: l, n)
  }

  def evalOne(sym: String): CalcState[Int] = sym match {
    case "+" => operator(_ + _)
    case "-" => operator(_ - _)
    case "*" => operator(_ * _)
    case "/" => operator(_ / _)
    case num => operand(num.toInt)
  }

  def evalAll(input : List[String]) : CalcState[Int] = input.foldLeft(0.pure[CalcState])((a,b)=> a.flatMap(_ => evalOne(b)))

  def evalInput(input : String) : Int = {
    val inputs = input.split(" ").toList
    evalAll(inputs).runA(Nil).value
  }


  def run() = {
    val program = evalAll(List("1","2","+","3","*"))
    println(program.runA(Nil).value)

    println(evalInput("1 2 + 3 4 + *"))
  }


}

