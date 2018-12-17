object P5 {
  def longestPalindrome(s: String): String = {

    val dp = new Array[Array[Int]](s.length)
    dp.indices.foreach(i => dp(i) = new Array[Int](s.length))

    for {
      i <- dp.indices
      j <- dp.indices
    } yield {
      if (i == j) dp(i)(j) = 1
      else dp(i)(j) = -1
    }


    def isPalindrome(i: Int, j: Int): Boolean = {
      if (dp(i)(j) == -1) {
        val res = if (i == j) true
        else if (i + 1 == j && (s.charAt(i) == s.charAt(j))) true
        else if (i > j) false
        else if ((s.charAt(i) == s.charAt(j)) && isPalindrome(i + 1, j - 1)) true
        else false
        dp(i)(j) = if (res) 1 else 0
        res
      } else
        dp(i)(j) == 1
    }

    val results = for {
      i <- 0 until s.length
      j <- i until s.length if isPalindrome(i, j)
    } yield (i, j)


    val (mi, mj) = results.maxBy { case (i, j) => j - i }
    s.substring(mi, mj + 1)
  }

  def main(args: Array[String]): Unit = {
    println(longestPalindrome("babad"))
  }
}