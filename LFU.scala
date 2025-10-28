import scala.collection.{mutable => m}
class LFUCache(capacity: Int) {
  var debug: Boolean = false
  var time = 0L

  case class Node(var k: Int, var v: Int, var f: Long, var t: Long) {
    override def toString: String = s"Node($k, f: $f, t: $t)"
  }

  val compare = new Ordering[Node] {
    override def compare(x: Node, y: Node): Int = {
      val first = x.f compare y.f
      if (first == 0) {
        x.t compare y.t
      } else {
        first
      }
    }
  }

  private var pq: m.PriorityQueue[Node] = new m.PriorityQueue[Node]()(compare.reverse)
  private val map = m.Map[Int, Node]()

  def updateAccess(key: Int): Unit = {
    pq.mapInPlace { n => if (n.k == key) { n.f += 1; n.t = getTime() }; n }
  }

  private def getTime() = {
    time += 1
    time
  }

  def print(op: String) = {
    if (debug) {
      println(s"Op: $op")
      println(map)
      println(pq)
    }
  }


  private def evictIfNeeded(): Unit = {
    if (map.size == capacity) {
      val node = pq.dequeue()
      if (node != null) {
        map.remove(node.k)
      }
    }
  }

  def get(key: Int): Int = {
    val res = map.get(key) match {
      case Some(p) =>
        updateAccess(p.k)
        p.v
      case None => -1
    }
    print(s"get: $key")
    res
  }

  def put(key: Int, value: Int): Unit = {
    map.get(key) match {
      case Some(p) =>
        p.v = value
        updateAccess(p.k)

      case None =>
        evictIfNeeded()
        val p = Node(key, value, 1, getTime())
        pq += p
        map.put(key, p)
    }
    print(s"put: $key")
  }
}