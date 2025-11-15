
## Interview
Suppress warnings.
> @annotation.nowarn

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

Vector could be good choice when we need immutable prepend and append. 
For most of the imports we don't need scala prefix. For exmaple `collection.mutable` works. 

