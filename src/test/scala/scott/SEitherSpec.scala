package scott

import org.scalatest.{EitherValues, MustMatchers, WordSpec}

class SEitherSpec extends WordSpec with MustMatchers with EitherValues {

  import SEither._
  import SList._

  val expectedRight = "Expected right but received left"
  val expectedLeft = "Expected left but received right"

  "SEither" should {
    "support casting to scala.util.Either" in {
      toEither[Int, Int](left(0)).fold(_ => succeed, _ => fail(expectedLeft))
      toEither[Int, Int](right(1)).fold(_ => fail(expectedRight), _ => succeed)
    }

    "support casting from scala.util.Either" in {
      fromEither[Int, Int](Left(0)).runEither(_ => succeed, _ => fail(expectedLeft))
      fromEither[Int, Int](Right(1)).runEither(_ => fail(expectedRight), _ => succeed)
    }

    "support isLeft" in {
      isLeft(left(0)) mustEqual true
      isLeft(right(1)) mustEqual false
    }
    "support isRight" in {
      isRight(left(0)) mustEqual false
      isRight(right(1)) mustEqual true
    }

    "support partition" in {
        partition[Int, Int](
          cons(left[Int, Int](3))(
            cons(right[Int, Int](5))(
              cons(left[Int, Int](4))(
                cons(right[Int, Int](6))(
                  nil[SEither[Int, Int]]))))).runPair(
          p1 => p2 => {
            toList(p1) mustEqual List(3, 4)
            toList(p2) mustEqual List(5, 6)
          }
        )
    }
  }

}
