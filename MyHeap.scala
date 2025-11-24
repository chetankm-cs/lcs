
object MyHeap extends App {
  class MyHeap {
    case class Node(k: Int, var idx: Int = -1) {
      override def toString: String = s"${idx.toString} -> ${k.toString}"
    }
    private val buff = collection.mutable.ListBuffer[Node]()
    buff += Node(-1)

    def enqueue(i: Int): Unit = {
      enqueue(Node(i))
    }

    def enqueue(n: Node): Unit = {
      n.idx = buff.size
      buff += n
      heapifyUp(buff.size - 1)
      println(buff)
    }

    def size: Int = {
      buff.size - 1
    }

    def isEmpty: Boolean = size == 0
    def nonEmpty: Boolean = size > 0

    private def swap(i: Int, j: Int): Unit = {
      val temp = buff(i)
      buff(i) = buff(j)
      buff(j) = temp

      buff(j).idx = j
      buff(i).idx = i
    }

    def dequeue(): Int = {
      if (nonEmpty) {
        val result = buff(1).k
        swap(1, buff.size - 1)
        buff.remove(buff.size - 1)
        heapifyDown(1)
        result
      } else {
        throw IllegalStateException("dequeue on empty")
      }
    }

    private def heapifyUp(i: Int): Unit = {
      var curr = i
      while (curr > 1) {
        val parent = curr / 2
        if (buff(parent).k > buff(i).k) {
          swap(parent, curr)
        }
        curr = parent
      }
    }

    private def heapifyDown(i: Int): Unit = {
      var curr = i
      while (curr < buff.size) {
        val li = curr * 2
        val ri = li + 1
        val lchild = if(li < buff.size) Some(li) else None
        val rchild = if(ri < buff.size) Some(ri) else None

        (lchild , rchild) match {
          case (Some(r), Some(l))  =>
            if (buff(curr).k > buff(l).k || buff(curr).k > buff(r).k) {
              if (buff(l).k < buff(r).k) {
                swap(l, curr)
                curr = l
              } else  {
                swap(r, curr)
                curr = r
              }
            } else {
              return
            }

          case (Some(r), None) => if (buff(curr).k > buff(r).k) {
            swap(curr, r)
            curr = r
          } else {
            return
          }
          case (None, Some(l)) => if (buff(curr).k > buff(l).k) {
            swap(curr, l)
            curr = l
          } else {
            return
          }
          case (None, None)  =>
            curr = buff.size
        }
      }
    }
  }

  val heap = new MyHeap()
  List(3, 2, 1, 100,  4, 5, 6, 50, 13, 14, 18).foreach(heap.enqueue)
  while (heap.nonEmpty) {
    println("Got " + heap.dequeue())
  }
}
