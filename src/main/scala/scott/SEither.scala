package scott

trait SEither[A, B] {
  def runEither[C](left: A => C, right: B => C): C
}

object SEither {

  def toEither[A, B](e: SEither[A, B]): Either[A, B] = sys.error("toEither")

  def fromEither[A, B](e: Either[A, B]): SEither[A, B] = sys.error("fromEither")

  def isLeft[A, B](e: SEither[A, B]): Boolean = sys.error("isLeft")

  def isRight[A, B](e: SEither[A, B]): Boolean = sys.error("isRight")

  def partition[A, B](l: SList[SEither[A, B]]): SPair[SList[A], SList[B]] = sys.error("partition")
}
