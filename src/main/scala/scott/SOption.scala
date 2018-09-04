package scott

sealed trait SOption[A] {
  def runOption[B](none: => B, some: A => B): B
}

object SOption {

  /**
    * Convenient constructor for the none case
    */
  def none[A]: SOption[A] = new SOption[A] {
    override def runOption[B](none: => B, some: A => B): B = none
  }

  /**
    * Convenient constructor for the some case
    */
  def some[A](a: A): SOption[A] = new SOption[A] {
    override def runOption[B](none: => B, some: A => B): B = some(a)
  }

  def toOption[A](o: SOption[A]): Option[A] = sys.error("toOption")

  def fromOption[A](o: Option[A]): SOption[A] = sys.error("fromOption")

  def isSome[A](o: SOption[A]): Boolean = sys.error("isSome")

  def isNone[A](o: SOption[A]): Boolean = sys.error("isNone")

  def filter[A](f: A => Boolean, o: SOption[A]): SOption[A] = sys.error("filter")

  def map[A, B](f: A => B, o: SOption[A]): SOption[B] = sys.error("map")

  def flatMap[A, B](f: A => SOption[B], o: SOption[A]): SOption[B] = sys.error("flatMap")

  def catOptions[A](l: SList[SOption[A]]): SList[A] = sys.error("catOptions")
}
