object P279 {
  def numSquares(n: Int): Int = {
    val dp = new Array[Int](n + 1)
    (1 to n).foreach {
      i =>
        if (Math.floor(Math.sqrt(i)) == i) dp(i) = 1
        else {
          dp(i) =
            (1 to Math.floor(Math.sqrt(i)).toInt).map {
              x => dp(i - (x * x))
            }.min + 1
        }
    }
    //      println(dp.mkString(","))
    dp(n)
  }

  def main(args: Array[String]): Unit = {
    numSquares(40)
  }

}