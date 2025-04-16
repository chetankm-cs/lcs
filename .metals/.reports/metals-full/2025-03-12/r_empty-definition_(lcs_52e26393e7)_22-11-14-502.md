error id: `<none>`.
file://<WORKSPACE>/MinMonotonicQueue.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
|empty definition using fallback
non-local guesses:
	 -java/util/Random.nextInt.
	 -java/util/Random.nextInt#
	 -java/util/Random.nextInt().
	 -Random.nextInt.
	 -Random.nextInt#
	 -Random.nextInt().
	 -scala/Predef.Random.nextInt.
	 -scala/Predef.Random.nextInt#
	 -scala/Predef.Random.nextInt().

Document text:

```scala
import java.util._

object MinMonotonicQueue extends App {
	// add doc
	// MonotonicQueue is a data structure that maintains a queue of elements in a monotonic order.
	// It supports the following operations:
	// 1. push(n: Int): adds an element n to the queue.
	// 2. getMin: returns the minimum element in the queue.
	// 3. pop(n: Int): removes the element n from the queue if it is present.
	// The queue is implemented using a deque (double-ended queue) to maintain the order of elements.
	// The push operation ensures that the elements in the queue are in increasing order.
	class MonotonicQueue {
		private val dq: Deque[Int] = new LinkedList[Int]()

		// add doc
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
	val arr = Array.fill(10)(Random.nextInt(100))
	arr.foreach {
		n => 
			q.push(n)
			println(s"push $n, min: ${q.getMin}")
	}
}
```

#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.