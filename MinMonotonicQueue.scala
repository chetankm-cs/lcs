import java.util._

object MinMonotonicQueue extends App {
	val dq: Deque[Int] = new LinkedList[Int]()
	def push(n: Int): Unit = {
		while(!dq.isEmpty && dq.peekLast > n) dq.pollLast
		dq.addLast(n)
	}
	def getMin: Int  = dq.peekFirst
	def pop(n: Int): Unit = if (!dq.isEmpty && n == dq.peekLast()) dq.pollFirst()
}