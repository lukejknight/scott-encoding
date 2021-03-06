package scott

sealed trait SList[A] {
  def runList[B](nil: => B, cons: A => SList[A] => B): B
}

object SList {

  /**
    * Convenient constructor for the nil case
    */
  def nil[A]: SList[A] = new SList[A] {
    override def runList[B](nil: => B, cons: A => SList[A] => B): B = nil
  }

  /**
    * Convenient constructor for the cons case
    */
  def cons[A](a: A)(l: SList[A]): SList[A] = new SList[A] {
    override def runList[B](nil: => B, cons: A => SList[A] => B): B = cons(a)(l)
  }

  def toList[A](l: SList[A]): List[A] = sys.error("toList")

  def fromList[A](l: List[A]): SList[A] = sys.error("fromList")

  def append[A](a: A, l: SList[A]): SList[A] = sys.error("append")

  def concat[A](l1: SList[A], l2: SList[A]): SList[A] = sys.error("concat")

  def isEmpty[A](l: SList[A]): Boolean = sys.error("isEmpty")

  def length[A](l: SList[A]): Int = sys.error("length")

  def map[A, B](f: A => B, l: SList[A]): SList[B] = sys.error("map")

  def zip[A, B](l1: SList[A], l2: SList[B]): SList[SPair[A, B]] = sys.error("zip")

  def foldl[A, B](f: B => A => B, b: => B, l: SList[A]): B = sys.error("foldl")

  def foldr[A, B](f: A => B => B, b: => B, l: SList[A]): B = sys.error("foldr")

  def take[A](i: Int, l: SList[A]): SList[A] = sys.error("take")
}
