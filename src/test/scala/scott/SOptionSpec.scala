package scott

import org.scalatest.{MustMatchers, WordSpec}

class SOptionSpec extends WordSpec with MustMatchers {

  import SOption._

  "SOption" should {
    "be castable to scala.Option" in {
      toOption(none) === None
      toOption(some(4)) === Some(4)
    }

    "be castable from scala.Option" in {
      fromOption[Int](None).runOption(0, i => i + 1) === 0
      fromOption[Int](Some(4)).runOption(0, i => i + 1) === Some(5)
    }
  }
}
