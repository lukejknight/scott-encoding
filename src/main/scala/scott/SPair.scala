package scott

sealed trait SPair[A, B] {
  def runPair[C](f: A => B => C): C
}

object SPair {

  /**
    * Convenient constructor for SPair
    */
  def sPair[A, B](a: A, b: B): SPair[A, B] = new SPair[A, B] {
    override def runPair[C](f: A => B => C): C = f(a)(b)
  }

  def toPair[A, B](p: SPair[A, B]): (A, B) = sys.error("toPair")

  def fromPair[A, B](p: (A, B)): SPair[A, B] = sys.error("fromPair")

  def fst[A, B](p: SPair[A, B]): A = sys.error("fst")

  def snd[A, B](p: SPair[A, B]): B = sys.error("snd")

  def swap[A, B](p: SPair[A, B]): SPair[B, A] = sys.error("swap")

  def curry[A, B, C](p: SPair[A, B] => C): A => B => C = sys.error("curry")

  def uncurry[A, B, C](f: A => B => C): SPair[A, B] => C = sys.error("uncurry")
}
