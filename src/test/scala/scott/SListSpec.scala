package scott

import org.scalatest.{MustMatchers, WordSpec}

class SListSpec extends WordSpec with MustMatchers {

  import SList._

  "SList" should {
    "be castable to scala.collection.immutable.List" in {
      toList(new SList[Int] {
        override def runList[B](nil: => B, cons: Int => SList[Int] => B): B = nil
      }) === List.empty[Int]

      toList(new SList[Int] {
        override def runList[B](nil: => B, cons: Int => SList[Int] => B): B = cons(1)(new SList[Int] {
          override def runList[B](nil: => B, cons: Int => SList[Int] => B): B = cons(2)(new SList[Int] {
            override def runList[B](nil: => B, cons: Int => SList[Int] => B): B = nil
          })
        })
      }) === List(1, 2)
    }

    "be castable from scala.collection.immutable.List" in {

    }
  }

}
