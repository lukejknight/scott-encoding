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

  def isSome[A](o: Option[A]): Boolean = sys.error("isSome")

  def isNone[A](o: Option[A]): Boolean = sys.error("isNone")

  def catOptions[A](l: SList[Option[A]]): SList[A] = sys.error("catOptions")
}
