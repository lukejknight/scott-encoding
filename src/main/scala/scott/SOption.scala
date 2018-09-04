package scott

trait SOption[A] {
  def runOption[B](none: => B, some: A => B): B
}

object SOption {
  def toOption[A](o: SOption[A]): Option[A] = sys.error("toOption")

  def fromOption[A](o: Option[A]): SOption[A] = sys.error("fromOption")

  def isSome[A](o: Option[A]): Boolean = sys.error("isSome")

  def isNone[A](o: Option[A]): Boolean = sys.error("isNone")

  def catOptions[A](l: SList[Option[A]]): SList[A] = sys.error("catOptions")
}
