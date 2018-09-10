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

  def toOption[A](o: SOption[A]): Option[A] = o.runOption(None, a => Some(a))

  def fromOption[A](o: Option[A]): SOption[A] = o.fold[SOption[A]](none)(a => some(a))

  def isSome[A](o: SOption[A]): Boolean = o.runOption(false, _ => true)

  def isNone[A](o: SOption[A]): Boolean = o.runOption(true, _ => false)

  def filter[A](f: A => Boolean, o: SOption[A]): SOption[A] = o.runOption(none, a => if (f(a)) some(a) else none)

  def map[A, B](f: A => B, o: SOption[A]): SOption[B] = o.runOption(none, a => some(f(a)))

  def flatMap[A, B](f: A => SOption[B], o: SOption[A]): SOption[B] = o.runOption(none, a => f(a))

  def catOptions[A](l: SList[SOption[A]]): SList[A] = l.runList(SList.nil, a => xs => a.runOption(catOptions(xs), a => SList.cons(a)(catOptions(xs))))
}
