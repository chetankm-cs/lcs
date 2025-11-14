object P34 extends App {

  def searchRange(nums: Array[Int], target: Int): Array[Int] = {

    var (l,r) = (0, nums.length - 1)     // (0, 3)
    var start = -1
    var end = -1

    while (l < r) {
      if (l + 1 == r ) {
        if (nums(r) == target) {
          start = r
        }
        l += 1
      } else {
        val mid = l + (r - l + 1)/2
        if (nums(mid) >= target) {
          r = mid
        }  else if (nums(mid) < target) {
          l = mid
        }
      }
    }

    l = 0
    r = nums.length - 1
    while (l < r) {
      if (l + 1 == r) {
        if (nums(l) == target) {
          end = r
        }
        l += 1
      } else {
        val mid = l + (r - l + 1)/2
        if (nums(mid) > target) {
          r = mid
        }  else if (nums(mid) <= target) {
          l = mid
        }
      }
    }

    Array(start, end)
  }

//  println(searchRange(Array(5,7,7,8,8,10), 8).mkString(","))
  println(searchRange(Array(5,7,7,8,8,10), 6).mkString(","))

}
