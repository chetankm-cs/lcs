import scala.collection.mutable as mut
object P317 extends App {
  val dir = List((0, 1), (1, 0), (-1, 0), (0, -1))
  def shortest(mat: Array[Array[Int]]): Int = {
    val m = mat.length
    val n = mat.head.length

    def bfs(i: Int, j: Int): (Int, Int) = {
      val queue = mut.Queue[(Int, Int, Int)]()
      val visited = mut.Set[(Int, Int)]()

      queue += ((i, j, 0))
      visited += ((i, j))

      var building = 0
      var buildingSum = 0
      while (queue.nonEmpty) {
        val (ci, cj, cd) = queue.dequeue()

        if (mat(ci)(cj) == 1) {
          building += 1
          buildingSum += cd
        }

        dir.map {
          case (di, dj) => (ci + di, cj + dj)
        }.foreach {
          case (ni, nj) =>
            if (mat.indices.contains(ni) && mat.head.indices.contains(nj) && !visited.contains((ni, nj)) && mat(i)(j) < 2) {
              queue +=  ((ni, nj , cd + 1))
              visited += ((ni, nj))
            }
        }
      }
      (building, buildingSum)
    }

    val buildingCount: Int = mat.map { arr => arr.count(_ == 1) }.sum
    val result = (for (i <- 0 until m; j <- 0 until n) yield {
      if (mat(i)(j) == 0) {
        val (bc, sum) = bfs(i, j)
        if (bc == buildingCount) sum else Integer.MAX_VALUE
      } else {
        Integer.MAX_VALUE
      }
    }).min
    if (result == Integer.MAX_VALUE) -1 else result
  }

  println(shortest(Array(Array(1,0,2,0,1), Array(0,0,0,0,0), Array(0,0,1,0,0))))
  println(shortest(Array(Array(1,0))))
  println(shortest(Array(Array(1) )))

}
