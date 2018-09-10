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

  def toPair[A, B](p: SPair[A, B]): (A, B) = p.runPair(a => b => (a, b))

  def fromPair[A, B](p: (A, B)): SPair[A, B] = sPair(p._1, p._2)

  def fst[A, B](p: SPair[A, B]): A = p.runPair(a => _ => a)

  def snd[A, B](p: SPair[A, B]): B = p.runPair(_ => b => b)

  def swap[A, B](p: SPair[A, B]): SPair[B, A] = p.runPair(a => b => sPair(b, a))

  def curry[A, B, C](p: SPair[A, B] => C): A => B => C = a => b => p(sPair(a, b))

  def uncurry[A, B, C](f: A => B => C): SPair[A, B] => C = p => p.runPair(f)
}
