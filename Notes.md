
## Interview
Suppress warnings.
> @scala.annotation.nowarn  


Sorting 

```
new Ordering[Node] {
    override def compare(x: Node, y: Node): Int = {
      val first = x.f compare y.f
      if (first == 0) {
        x.t compare y.t
      } else {
        first
      }
    }
  }

```

