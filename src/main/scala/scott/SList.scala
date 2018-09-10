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

  def toList[A](l: SList[A]): List[A] = l.runList(List(), a => l => List(a) ++ toList(l))

  def fromList[A](l: List[A]): SList[A] = l.foldRight[SList[A]](nil)((n, acc) => cons(n)(acc))

  def append[A](a: A, l: SList[A]): SList[A] = cons(a)(l)

  def concat[A](l1: SList[A], l2: SList[A]): SList[A] = l1.runList(l2, a => l => cons(a)(concat(l, l2)))

  def isEmpty[A](l: SList[A]): Boolean = l.runList(true, _ => _ => false)

  def length[A](l: SList[A]): Int = l.runList(0, _ => b => 1 + length(b))

  def map[A, B](f: A => B, l: SList[A]): SList[B] = l.runList(nil, a => b => cons(f(a))(map(f, b)))

  def zip[A, B](l1: SList[A], l2: SList[B]): SList[SPair[A, B]] = l1.runList(nil, a => l1l => l2.runList(nil, b => l2l => cons(SPair.sPair(a, b))(zip(l1l, l2l))))

  def foldl[A, B](f: B => A => B, b: => B, l: SList[A]): B = l.runList(b, a => l2 => foldl(f, f(b)(a), l2))

  def foldr[A, B](f: A => B => B, b: => B, l: SList[A]): B = l.runList(b, a => l2 => f(a)(foldr(f, b, l2)))

  def take[A](i: Int, l: SList[A]): SList[A] = l.runList(nil, a => l => if (i > 0) cons(a)(take(i - 1, l)) else nil)
}
