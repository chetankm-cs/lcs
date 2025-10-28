import scala.collection.{mutable => m}
class LRUCache(_capacity: Int) {
  case class Node( var k: Int, var v: Int, var next: Node = null, var prev: Node = null ) {
    override def toString: String = s"Node($k)"
  }

  class LL {
    var head: Node = null
    var last: Node = null

    def prepend(n: Node): Unit = {
      n.next = head
      n.prev = null
      if (head != null)
      {
        head.prev = n
      }

      head = n

      if (last == null) {
        last = head
      }
    }

    def remove(n: Node): Unit = {
      if (n.prev != null) {
        n.prev.next = n.next
      }

      if (n.next != null){
        n.next.prev = n.prev
      }

      if (n == head) {
        head = head.next
      }

      if (n == last) {
        last = last.prev
      }

      n.next = null
      n.prev = null
    }

    def getLast() = last
  }

  private var list: LL = new LL()
  private val map = m.Map[Int, Node]()

  def moveToHead(node: Node): Unit = {
    list.remove(node)
    list.prepend(node)
  }


  private def evictIfNeeded(): Unit = {
    if (map.size == _capacity) {
      val pair = list.getLast()
      if (pair != null) {
        map.remove(pair.k)
        list.remove(pair)
      }
    }
  }

  def get(key: Int): Int = {
    val res = map.get(key) match {
      case Some(p) =>
        moveToHead(p)
        p.v
      case None => -1
    }
    res
  }

  def put(key: Int, value: Int): Unit = {
    map.get(key) match {
      case Some(p) =>
        p.v = value
        moveToHead(p)

      case None =>
        evictIfNeeded()
        val p = new Node(key, value)
        moveToHead(p)
        map.put(key, p)
    }

  }
}
