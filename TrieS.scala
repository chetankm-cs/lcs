import collection.{mutable => mut}

class TrieS {
  class T {
    var isEnd = false;
    val children: mut.Map[Char, T] = mut.Map[Char, T]()
  }
  private val root = new T()

  def insert(w: String): Unit =  {
    var temp = root
    for (c <- w) temp = temp.children.getOrElseUpdate(c, new T());
    temp.isEnd = true
  }

  def search(word: String): Boolean  = {
    var temp = root
    for (c <- word) {
      if (temp.children.contains(c))
        temp = temp.children(c)
      else
        return false;
    }
    temp.isEnd
  }

  def startsWith(prefix: String): Boolean = {
    var temp = root
    for (c <- prefix) {
      if (temp.children.contains(c))
        temp = temp.children(c);
      else
        return false;
    }
    true;
  }
}
object Test extends App {
  val t = new TrieS();
  t.insert("apple")
  System.out.println(t.search("apple"))
  System.out.println(t.search("app"))
  System.out.println(t.startsWith("app"))
  t.insert("app")
  System.out.println(t.search("app"))
}
