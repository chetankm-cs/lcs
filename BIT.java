import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

public class BIT {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public class Solution {

        Iterator<Integer> it = null;

        ArrayList<Integer> inorder(TreeNode root) {
            if (root == null) return new ArrayList<>();
            ArrayList<Integer> left = inorder(root.left);
            ArrayList<Integer> right = inorder(root.right);
            left.add(root.val);
            left.addAll(right);
            return left;
        }

        public Solution(TreeNode root) {
            it = this.inorder(root).iterator();
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return this.it.hasNext();
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            return this.it.next();
        }
    }

}
