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

  def toEither[A, B](e: SEither[A, B]): Either[A, B] = sys.error("toEither")

  def fromEither[A, B](e: Either[A, B]): SEither[A, B] = sys.error("fromEither")

  def isLeft[A, B](e: SEither[A, B]): Boolean = sys.error("isLeft")

  def isRight[A, B](e: SEither[A, B]): Boolean = sys.error("isRight")

  def partition[A, B](l: SList[SEither[A, B]]): SPair[SList[A], SList[B]] = sys.error("partition")
}
