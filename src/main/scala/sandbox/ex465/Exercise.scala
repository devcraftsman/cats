package sandbox.ex465

import cats.Eval


object Exercise {

  def foldRight[A,B](as :List[A],acc: B)(fn:  (A,B) => B) : B =
    as match {
      case head :: tail  => fn(head,foldRight(tail,acc)(fn))
      case Nil => acc
    }

  def foldRightEval[A,B](as :List[A],acc: Eval[B])(fn:  (A,Eval[B]) => Eval[B]) : Eval[B] =
    as match {
      case head :: tail  => Eval.defer(fn(head,foldRight(tail,acc)(fn)))
      case Nil => acc
    }

  def foldRight2[A,B](as :List[A],acc: B)(fn:  (A,B) => B) : B =
    foldRightEval(as,Eval.now(acc)){ (a,b) => b.map(fn(a,_))}.value


  println(foldRight2((1 to 1000000).toList,0L)(_ + _))

}

