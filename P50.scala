object P50 {
  def myPow(x: Double, n: Int): Double = {
    var p: Long = if (n >= 0) n else n * (-1L)
    var res: Double = 1
    var curr_power = x
    while (p > 0) {
      if (p % 2 == 1) res = res * curr_power
      p = p >> 1
      curr_power = curr_power * curr_power
    }
    if (n >= 0) res else 1 / res
  }

  def main(args: Array[String]): Unit = {
    println(myPow(2, Int.MinValue))
  }
}
