@scala.annotation.nowarn
object Breakable extends App {
  def test(): Int = {
    for (i <- 1 to 10) {
      if ( i > 5)
        return 1
    }
    0
  }
  // write new test2 function using breakable
  def test2(): Int = {
    import scala.util.control.Breaks._
    var result = 0
    breakable {
      for (i <- 1 to 10) {
        if (i > 5) {
          result += i
          break()
        }
      }
    }
    result
  }
  println("Output " + test())
}
