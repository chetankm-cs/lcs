object P334 {
  def increasingTriplet(nums: Array[Int]): Boolean = {
    val min = new Array[Int](nums.length)
    val max = new Array[Int](nums.length)

    {
      var currMin = Integer.MAX_VALUE
      nums.indices.foreach {
        i =>
          currMin = Math.min(currMin, nums(i))
          min(i) = currMin
      }
    }

    {
      var currMax = Integer.MIN_VALUE
      nums.indices.reverse.foreach {
        i =>
          currMax = Math.max(currMax, nums(i))
          max(i) = currMax
      }
    }

    nums.indices.foreach {
      i => if (min(i) < nums(i) && nums(i) < max(i)) return true
    }
    false
  }
}