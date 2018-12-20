object P36 {

  def check(arr: Seq[Char]) = {
    val validNums = arr.filter(x => x != '.')
    validNums.distinct.length == validNums.length
  }

  def isValidSudoku(board: Array[Array[Char]]): Boolean = {
    //horizontal check
    val h = board.forall { arr => check(arr) }

    val v = (0 to 8).forall {
      i =>
        val arrs = board.map(arr => arr(i))
        check(arrs)
    }
    val s = (for {
      i <- 0 until 3
      j <- 0 until 3
    } yield {
      val arr = for {
        a <- 0 until 3
        b <- 0 until 3
      } yield board(3 * i + a)(3 * j + b)
      check(arr)
    }).forall(identity)

    h && v && s
  }
}