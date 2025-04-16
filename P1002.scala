object P1002 extends App {
  def commonChars(words: Array[String]): List[String] = {
    var map = Map.empty[Char, Int]
    words.foreach {
      w => val f = w.groupMapReduce(x => x)(_ => 1)(_ + _)
        if (map.isEmpty) {
          map = f
        } else {
          map = (f.keySet.intersect(map.keySet)).map {
            k => k -> f(k).min(map(k))
          }.toMap
        }
    }
    map.toSeq.foldLeft(scala.collection.mutable.ListBuffer.empty[String]) {
      case (agg, (k: Char, v: Int)) =>

        println(s"$agg $k $v")
        (1 to v) foreach {
         i => agg.append(k.toString)
      }
        agg
    }.result
  }
  println(commonChars(Array("cook", "locko", "cool")).mkString(","))
}
