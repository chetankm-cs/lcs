import scala.collection.mutable

object P139 {
  def wordBreak(s: String, wordDict: List[String]): Boolean = {
    val dp: mutable.Map[Int, Boolean] = new mutable.HashMap[Int, Boolean]()

    def recur(i: Int): Boolean = {
      if (i == s.length) true
      else {
        if (!dp.contains(i))
          dp(i) = wordDict.exists {
            word => s.regionMatches(i, word, 0, word.length) && recur(i + word.length)
          }
        dp(i)
      }
    }

    recur(0)
  }
}