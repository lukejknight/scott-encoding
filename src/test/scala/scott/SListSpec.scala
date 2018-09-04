package scott

import org.scalatest.{MustMatchers, WordSpec}

class SListSpec extends WordSpec with MustMatchers {

  import SList._

  def nil[A]: SList[A] = new SList[A] {
    override def runList[B](nil: => B, cons: A => SList[A] => B): B = nil
  }

  def cons[A](a: A)(l: SList[A]): SList[A] = new SList[A] {
    override def runList[B](nil: => B, cons: A => SList[A] => B): B = cons(a)(l)
  }

  "SList" should {
    "be castable to scala.collection.immutable.List" in {
      toList(nil[Int]) === List.empty[Int]
      toList(cons(1)(cons(2)(nil))) === List(1, 2)
    }

    "be castable from scala.collection.immutable.List" in {

    }
  }

}
