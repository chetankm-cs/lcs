
object Breakable extends App {
  def test(): Int = {
    for (i <- 1 to 10) {
      if ( i > 5)
        return 1
    }
    0
  }
  println("Output " + test())
}
