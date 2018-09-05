package scott

import org.scalatest.{MustMatchers, WordSpec}

class SOptionSpec extends WordSpec with MustMatchers {

  import SList.{cons, nil, toList}
  import SOption._

  val expectedSome = "Expected some but received none"
  val expectedNone = "Expected none but received some"

  "SOption" should {
    "support casting to scala.Option" in {
      toOption(none).fold(succeed)(_ => fail(expectedNone))
      toOption(some(4)).fold(fail(expectedSome))(_ => succeed)
    }

    "support casting from scala.Option" in {
      fromOption[Int](None).runOption(succeed, _ => fail(expectedNone))
      fromOption[Int](Some(4)).runOption(fail(expectedSome), _ => succeed)
    }

    "support isSome" in {
      isSome(none) mustEqual false
      isSome(some(0)) mustEqual true
    }

    "support isNone" in {
      isNone(none) mustEqual true
      isNone(some(0)) mustEqual false
    }

    "support filter" in {
      val isZero: Int => Boolean = e => e == 0
      filter[Int](isZero, some(0)).runOption(fail(expectedSome), _ => succeed)
      filter[Int](isZero, some(1)).runOption(succeed, _ => fail(expectedNone))
      filter[Int](isZero, none).runOption(succeed, _ => fail(expectedNone))
    }

    "support map" in {
      val plusOne: Int => Int = e => e + 1
      map[Int, Int](plusOne, some(0)).runOption(fail(expectedSome), e => e mustEqual 1)
      map[Int, Int](plusOne, some(1)).runOption(fail(expectedSome), e => e mustEqual 2)
      map[Int, Int](plusOne, none[Int]).runOption(succeed, _ => fail(expectedNone))
    }

    "support flatMap" in {
      val plusOne: Int => SOption[Int] = e => some(e + 1)
      val giveNone: Int => SOption[Int] = _ => none
      flatMap[Int, Int](plusOne, some(0)).runOption(fail(expectedSome), e => e mustEqual 1)
      flatMap[Int, Int](giveNone, some(0)).runOption(succeed, _ => fail(expectedNone))
      flatMap[Int, Int](plusOne, none).runOption(succeed, _ => fail(expectedNone))
    }

    "support catOptions" in {
      toList(catOptions(cons(some(1))(cons(none[Int])(cons(some(2))(nil[SOption[Int]]))))) mustEqual List(1, 2)
    }
  }
}
