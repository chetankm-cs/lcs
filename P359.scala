import scala.collection.mutable

object P359 {
  def longestSubstring(s: String, k: Int): Int = {
    val map = new mutable.HashMap[Char, Int]()
    var max = 0

    s.indices.foreach {
      i =>
        map.clear()
        (i until s.length).foreach {
          j =>
            val c = map.getOrElse(s.charAt(j), 0)
            map += (s.charAt(j) -> (c + 1))
            if (map.forall { case (_, count) => count >= k }) {
              max = Math.max(j - i + 1, max)
            }
        }
    }
    max
  }
}