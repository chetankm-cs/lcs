
import collection.{mutable => m}

object PostOrderWithoutRecursionScala extends App {
    case class TreeNode(val value: Int, left: TreeNode = null, right: TreeNode = null) 

    def postorderTraversal(root: TreeNode): List[Int] = {
        val s = m.Stack[TreeNode]()
        val visited = m.Set[TreeNode]()
        val res = m.ListBuffer[Int]()

        def v(node: TreeNode) = node == null || visited.contains(node)

        Option(root).foreach(s.push)
        while (s.nonEmpty) {
            val curr = s.top
            if(v(curr.left) && v(curr.right))  {
                visited += curr
                res += curr.value
                s.pop()
            } else {
                if (!v(curr.left)) {
                    s.push(curr.left)
                } else if ( !v(curr.right)) {
                    s.push(curr.right)
                }
            }
        }
        res.result
    }
}
