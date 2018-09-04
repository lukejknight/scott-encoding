package scott

import org.scalatest.{MustMatchers, WordSpec}

class SListSpec extends WordSpec with MustMatchers {

  import SList._

  "SList" should {
    "be castable to scala.collection.immutable.List" in {
      toList(nil[Int]) === List.empty[Int]
      toList(cons(1)(cons(2)(nil))) === List(1, 2)
    }

    "be castable from scala.collection.immutable.List" in {

    }
  }

}
