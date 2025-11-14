@scala.annotation.nowarn
object P36 {
   def check(arr: Array[Char]): Boolean = {
    arr.toSeq.filter(_ !=  '.').groupMapReduce(x => x)(_ => 1)(_ + _).forall {
      case (_, v) => v == 1
    }
  }

  def isValidSudoku(board: Array[Array[Char]]): Boolean = {
    //horizontal check
    val h = board.forall { arr => check(arr) }

    val v = (0 to 8).forall {
      i =>
        val arrs = board.map(arr => arr(i))
        check(arrs)
    }

    val s = (for { i <- 0 until 3; j <- 0 until 3 } yield (i, j)).forall {
       case (i,j) =>  
          val square = (for { a <- 0 until 3; b <- 0 until 3 } yield board(3 * i + a)(3 * j + b)).toArray
          check(square)
    }

    h && v && s
  }
}
