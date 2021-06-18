object SegmentTree extends App {

    class SegmentTree(_nums: Array[Int], empty: Int, combine: (Int, Int) => Int) {
        private val n = _nums.length
        private val data = Array.fill(n * 2)(0)
        Array.copy(_nums, 0, data, n, n)

        // build the tree in reverse order
        for (i <- (n- 1) to 1  by (-1)) data(i) = combine(data(2 * i), data(2 * i + 1))

        def update(index: Int, v: Int) {
            var i = n + index
            data(i) = v
            i = i / 2
            while (i > 0) {
                data(i) = combine(data(2 * i), data(2 * i + 1))
                i /= 2
            }
        }

        def rangeQuery(left: Int, right: Int): Int = {
            var l = left + n
            var r = right + n + 1
            var res = empty

            while (l < r) {
                // if this is right node we include it and don't include the parent
                if (l % 2 == 1) {
                  res = combine(res, data(l))
                  l += 1
                }
                // Remember r is exclusive, this mean we are considering the left node
                // we include it and don't include the parent.

                if (r % 2 == 1) {
                    r -= 1
                    res = combine(res, data(r))
                }

                l /= 2
                r /= 2
            }
            res
        }
    }


    val arr = Array(1, 3, 5)
    println(arr.mkString(","))

    def test1() = {
        val st = new SegmentTree(arr, 0, _ + _)
        println(st.rangeQuery(0, 2)) //
        st.update(1, 2)
        println(st.rangeQuery(0, 2))
    }

    def test2() = {
        val st = new SegmentTree(arr, Integer.MIN_VALUE, _ max _)
        println(st.rangeQuery(0, 2)) //
        println(st.rangeQuery(1, 2))
        st.update(1, 2)
        println(st.rangeQuery(1, 2))
    }

    test1()
    test2()
}