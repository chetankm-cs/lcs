error id: 
file://<WORKSPACE>/MinMonotonicQueue.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
|empty definition using fallback
non-local guesses:
	 -

Document text:

```scala
import java.util._

object MinMonotonicQueue extends App {
	// MonotonicQueue is a data structure that maintains a queue of elements in a monotonic order.
	// It supports the following operations:
	// 1. push(n: Int): adds an element n to the queue.
	// 2. getMin: returns the minimum element in the queue.
	// 3. pop(n: Int): removes the element n from the queue if it is present.
	class MonotonicQueue {
		private val dq: Deque[Int] = new LinkedList[Int]()

		// push(n: Int): adds an element n to the queue.
		// The push operation ensures that the elements in the queue are in increasing order.
		// It removes all elements from the end of the queue that are greater than n.
		// This ensures that the minimum element is always at the front of the queue.
		// The time complexity of this operation is O(1) on average.		
		def push(n: Int): Unit = {
			while(!dq.isEmpty && dq.peekLast > n) dq.pollLast
			dq.addLast(n)
		}

		def getMin: Int  = dq.peekFirst

		def pop(n: Int): Unit = if (!dq.isEmpty && n == dq.peekLast()) dq.pollFirst()
	}

	// test MonotonicQueue
	val q = new MonotonicQueue

	// generate a random array
	val random = scala.util.Random(0)
	val arr = Array.fill(10)(random.nextInt(100))
	val k = 3 

	println(arr.mkString(", "))
	val result = arr.sliding(2, 1).filter(_.size == k).map {
		arr => arr.min	
	}

	println(s"Min nums with k = $k" )

	println(result.mkString(", "))

	(0 until k - 1).foreach( i => q.push(arr(i)))

	for (i <- k - 1 until arr.length) {
		println("Pushing " + arr(i))
		q.push(arr(i))
		println(s"Min: ${q.getMin}")
		q.pop(arr(i - k + 1))
		println("Popping " + arr(i - k + 1))
	}
}
```

#### Short summary: 

empty definition using pc, found symbol in pc: 