package scott

sealed trait SEither[A, B] {
  def runEither[C](left: A => C, right: B => C): C
}

object SEither {

  /**
    * Convenient constructor for the left case
    */
  def left[A, B](a: A): SEither[A, B] = new SEither[A, B] {
    override def runEither[C](left: A => C, right: B => C): C = left(a)
  }

  /**
    * Convenient constructor for the right case
    */
  def right[A, B](b: B): SEither[A, B] = new SEither[A, B] {
    override def runEither[C](left: A => C, right: B => C): C = right(b)
  }

  def toEither[A, B](e: SEither[A, B]): Either[A, B] = e.runEither(a => Left(a), b => Right(b))

  def fromEither[A, B](e: Either[A, B]): SEither[A, B] = e.fold(a => left(a), b => right(b))

  def isLeft[A, B](e: SEither[A, B]): Boolean = e.runEither(_ => true, _ => false)

  def isRight[A, B](e: SEither[A, B]): Boolean = e.runEither(_ => false, _ => true)

  def partition[A, B](l: SList[SEither[A, B]]): SPair[SList[A], SList[B]] =
    l.runList(SPair.sPair(SList.nil, SList.nil), e => le => {
      val p = partition(le)
      e.runEither(
        a => p.runPair(p1 => p2 => SPair.sPair(SList.cons(a)(p1), p2)),
        b => p.runPair(p1 => p2 => SPair.sPair(p1, SList.cons(b)(p2)))
      )
    })

}
