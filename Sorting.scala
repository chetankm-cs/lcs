object Sorting extends App {
  val ordString = Ordering.by[(Int, String), String](_._2).reverse
  val intString = Ordering.by[(Int, String), Int](_._1)
  val ord1 = ordString.orElse(intString)

  val ord2 = new Ordering[(Int, String)] {
    override def compare(x: (Int, String), y: (Int, String)): Int = {
      val first = y._2 compare x._2
      if (first == 0)  x._1 compare y._1 else first
    }
  }

  val z1 = List (1 -> "hello", 2 -> "world", 3 -> "world").sorted(ord1)
  val z2 = List (1 -> "hello", 2 -> "world", 3 -> "world").sorted(ord2)
  println(z1)
  println(z2)
}
