package scott

import org.scalatest._

class SPairSpec extends WordSpec with MustMatchers {

  import SPair._

  "SPair" should {
    "support casting to scala.Tuple2" in {
      toPair(sPair(1, 2)) mustEqual (1, 2)
    }
    "support casting from scala.Tuple2" in {
      fromPair((1, 2)).runPair(a => b => a + b mustEqual 3)
    }
    "support fst" in {
      fst(sPair(1, 2)) mustEqual 1
    }
    "support snd" in {
      snd(sPair(1, 2)) mustEqual 2
    }
    "support swap" in {
      swap(sPair(1, 2)).runPair(a => b => {
        a mustEqual 2
        b mustEqual 1
      })
    }
    "support curry" in {
      curry[Int, Int, Int](sPair =>
        fst(sPair) + snd(sPair)
      )(1)(2) mustEqual 3
    }
    "support uncurry" in {
      uncurry[Int, Int, Int](a => b => a + b)(sPair(1, 2)) mustEqual 3
    }
  }
}
