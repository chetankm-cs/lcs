import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class PostOrderWithoutRecursion {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

    public ArrayList<Integer> postorderTraversal(TreeNode A) {
        Stack<TreeNode> s = new Stack<>();
        HashSet<TreeNode> visited = new HashSet<>();
        ArrayList<Integer> res = new ArrayList<>();
        s.push(A);
        while (!s.isEmpty()) {
            TreeNode curr = s.peek();
            boolean leftF = curr.left == null || visited.contains(curr.left);
            boolean rightF = curr.right == null || visited.contains(curr.right);

            if(leftF && rightF)  {
                visited.add(curr);
                res.add(curr.val);
                s.pop();
            } else {
                if (curr.right != null) s.push(curr.right);
                if (curr.left != null) s.push(curr.left);
            }

        }
        return res;
    }
}
