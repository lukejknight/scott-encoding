package scott

import org.scalatest.{MustMatchers, WordSpec}

class SListSpec extends WordSpec with MustMatchers {

  import SList._

  "SList" should {
    "support casting to scala.collection.immutable.List" in {
      toList(nil[Int]) mustEqual List.empty[Int]
      toList(cons(1)(cons(2)(nil))) mustEqual List(1, 2)
    }

    "support casting from scala.collection.immutable.List" in {
      toList(fromList(List.empty[Int])) mustEqual List.empty[Int]
      toList(fromList(List(1, 2))) mustEqual List(1, 2)
    }

    "support append" in {
      toList(append(1, nil)) mustEqual List(1)
      toList(append(1, cons(2)(nil))) mustEqual List(1, 2)
    }

    "support concat" in {
      toList(concat(nil, nil)) mustEqual List()
      toList(concat(nil, cons(1)(nil))) mustEqual List(1)
      toList(concat(cons(1)(nil), nil)) mustEqual List(1)
      toList(concat(cons(1)(cons(2)(nil)), cons(3)(nil))) mustEqual List(1, 2, 3)
    }

    "support isEmpty" in {
      isEmpty(nil) mustEqual true
      isEmpty(cons(1)(nil)) mustEqual false
    }

    "support map" in {
      val plusOne: Int => Int = _ + 1
      toList(map(plusOne, cons(1)(cons(2)(nil)))) mustEqual List(2, 3)
    }

    "support length" in {
      SList.length(cons(1)(cons(2)(cons(3)(nil)))) mustEqual 3
      SList.length(nil) mustEqual 0
    }

    "support foldr" in {
      toList(foldr[Int, SList[Int]](cons, nil, cons(1)(cons(2)(cons(3)(nil))))) mustEqual List(1, 2, 3)
    }

    "support foldl" in {
      toList(foldl[Int, SList[Int]](acc => n => cons(n)(acc), nil, cons(1)(cons(2)(cons(3)(nil))))) mustEqual List(3, 2, 1)
    }

    "support take" in {
      toList(take(2, cons(1)(cons(2)(cons(3)(nil))))) mustEqual List(1, 2)
      toList(take(0, cons(1)(cons(2)(cons(3)(nil))))) mustEqual List()
      toList(take(4, cons(1)(cons(2)(cons(3)(nil))))) mustEqual List(1, 2, 3)
    }

    "support zip" in {
      toList(zip(cons(1)(cons(2)(cons(3)(nil))), cons(1)(cons(2)(cons(3)(nil)))))
        .map(SPair.toPair) mustEqual List((1, 1), (2, 2), (3, 3))
      toList(zip(cons(1)(cons(2)(cons(3)(nil))), cons(1)(cons(2)(cons(3)(cons(4)(nil))))))
        .map(SPair.toPair) mustEqual List((1, 1), (2, 2), (3, 3))
      toList(zip(cons(1)(cons(2)(cons(3)(cons(4)(nil)))), cons(1)(cons(2)(cons(3)(nil)))))
        .map(SPair.toPair) mustEqual List((1, 1), (2, 2), (3, 3))
    }
  }

}
