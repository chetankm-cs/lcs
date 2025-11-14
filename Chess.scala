import scala.util.Try

object Chess extends App {
  abstract class Piece(char: Char, white: Boolean) {
    var alive: Boolean = true
    def isWhite: Boolean = white

    def markDead(): Piece = {
      alive = false
      this
    }

    def render(): String = {
      if (white) {
        char.toString + " "
      } else {
        char.toString + "."
      }
    }
  }
  // create a pieceFactory
  object Piece {
    def apply(char: Char, white: Boolean): Piece = {
      char match {
        case 'p' => Pawn(white)
        case 'K' => King(white)
        case 'Q' => Queen(white)
        case 'b' => Bishop(white)
        case 'k' => Knight(white)
        case 'r' => Rook(white)
      }
    }
  }

  case class Pawn(white: Boolean = true) extends Piece('p', white)
  case class King(white: Boolean = true) extends Piece('K', white)
  case class Queen(white: Boolean = true) extends Piece('Q', white)
  case class Bishop(white: Boolean = true) extends Piece('b', white)
  case class Knight(white: Boolean = true) extends Piece('k', white)
  case class Rook(white: Boolean = true) extends Piece('r', white)


  case class Pos(i: Int, j: Int) {

    def this(string: String) = {
      this(string(1) - '1', string(0) - 'a')
    }
    // validate i, j to be within 0, 8

    assert((0 until 8).contains(i))
    assert((0 until 8).contains(j))
  }

  class Board {
    private var state: Array[Array[Piece]] = Array.fill(8, 8)(null)

    for (j <- 0 until 8) {
      state(1)(j) = Pawn()
      state(6)(j) = Pawn(white = false)
    }

    private val orderedPieces = "rkbKQbkr"
    orderedPieces.map(Piece.apply(_, white = true)).zipWithIndex.foreach {
      case (c, j) => state(0)(j) = c
    }

    orderedPieces.map(Piece.apply(_, white = false)).zipWithIndex.foreach {
      case (c, j) => state(7)(j) = c
    }

    def getPiece(pos: Pos): Option[Piece] = {
      Option(state(pos.i)(pos.j))
    }
    def clear(pos: Pos): Unit = {
      state(pos.i)(pos.j) = null
    }

    private def moveInternal(pos: Pos, piece: Piece): Unit = {
      state(pos.i)(pos.j) = piece
    }


    def move(whiteTurn: Boolean, from: Pos, to: Pos): Boolean = {
      getPiece(from) match {
        case Some(piece) if piece.isWhite == whiteTurn =>
          moveInternal(to, piece)
          clear(from)
        true
        case _ =>
        println("Invalid move")
        false
      }
    }

    def render(): Unit = {
      for (x <- 'a' to 'h') {print(s"$x  ")}
      println()
      for (i <- 0 until 8) {
        for (j <- 0 until 8) {
          val c = Option(state(i)(j)).map(_.render()).getOrElse("  ")
          print(c + " ")
        }
        print(i + 1)
        println()
      }
      println("################################################################################")
    }
  }

  class Game {
    class Player
    private var whiteTurn =  true
    private var state = 0 // start or finish


    private val board = new Board()
    def start() = {
      while (true) {
        board.render()
        if (whiteTurn) {
          println("White to move")
        } else {
          println("Black to move")
        }

        val name = scala.io.StdIn.readLine().split(" ")
        val fromT = Try(new Pos(name(0))).toOption
        val toT = Try(new Pos(name(1))).toOption
        println (s"From: $fromT")
        println (s"To: $toT")

        for {
          from <-fromT
          to <- toT
        } yield  {
          if(board.move(whiteTurn, from, to)) {
            whiteTurn = !whiteTurn
          }
        }
      }
    }
  }

  val g = new Game()
  g.start()
}
