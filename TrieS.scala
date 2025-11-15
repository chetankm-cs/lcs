import collection.{mutable => mut}

@annotation.nowarn
class TrieS {
  // (root s) -> (u) -> |m| -> (o) -> (end)
  //                    |r| -> (e) -> (end)

  class T {
    var end = false
    val nodes: mut.Map[Char, T] = mut.Map[Char, T]()
  }
  private val root = new T()

  def insert(w: String): Unit =  {
    var temp = root
    for (c <- w) temp = temp.nodes.getOrElseUpdate(c, new T())
    temp.end = true
  }

  def search(word: String): Boolean  = {
    var temp = root
    for (c <- word) {
      if (temp.nodes.contains(c))
        temp = temp.nodes(c)
      else
        return false
    }
    // can do dfs to search further
    temp.end
  }

  def delete(word: String): Unit = {
    if (!search(word)) return
    def deleteRecur(i: Int, t: T): Boolean = {
      if (i == word.length) {
        t.end = false
        return t.nodes.isEmpty
      }
      val x = deleteRecur(i + 1, t.nodes(word(i)))
      if (x) t.nodes.remove(word(i))
      t.nodes.isEmpty
    }
    deleteRecur(0, root)
  }

  def startsWith(prefix: String): Boolean = {
    var temp = root
    for (c <- prefix) {
      if (temp.nodes.contains(c))
        temp = temp.nodes(c)
      else
        return false
    }
    true
  }

  def printAllPaths() = {
    println("*" * 10)
    val temp = root
    val q = mut.Queue[(T, String)]()
    q += temp -> ""
    val lb = mut.ListBuffer[String]()
    while (q.nonEmpty) {
      val (node, curr) = q.dequeue()
      if (node.end)
        lb += curr
      for (c <- node.nodes.keys) {
        q += (node.nodes(c) -> (curr + c.toString))
      }
    }
    println {
      lb.toList.mkString("\n")
    }
    println("*" * 10)
  }
}
object TestTrie extends App {
  val t = new TrieS()
  t.insert("apple")
  t.insert("")
  println(t.search("apple"))
  println(t.search("app"))
  println(t.startsWith("app"))
  t.insert("app")
  println(t.search("app"))
  t.printAllPaths()

  t.delete("app")
  t.delete("")
  println(t.search("app"))
  println(t.startsWith("app"))
  t.printAllPaths()
}
