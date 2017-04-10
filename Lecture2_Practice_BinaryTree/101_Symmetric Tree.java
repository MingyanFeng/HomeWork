/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
	// version 1: Recursive
    public boolean isSymmetric(TreeNode root) {
        // corner case
        if (root == null) {
            return true;
        }
        // call helper method to compare root.left and root.right when root != null
        return helper(root.left, root.right);
    }
    
    private boolean helper(TreeNode left, TreeNode right) {
        // current layer
        if (left == null || right == null) {
            return left == right;
        }
        if (left.val != right.val) {
            return false;
        }
        // next layer
        return helper(left.left, right.right) && helper(left.right, right.left);
    }
    /*
    Time Complexity: O(n), n == # of nodes in the tree;
    Space Complexity: O(h), h == height of the tree;
    */


	// version 2: Iterative
    public boolean isSymmetric(TreeNode root) {
        // corner case
        if (root == null) {
            return true;
        }
        // use BFS to do level traversal of the tree
        Queue<TreeNode> queue = new LinkedList<>();
        // first, add root.left and root.right into queue
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            // current layer
            // queue is first in first out
            TreeNode left = queue.remove(); // ??? LinkedList has both poll() and remove(), which one?
                                            /*
                                            !!! It depends on when you initialize the queue, what Interface you use. 
                                            If you use Queue Interface, it's better to use Queue Interface's methods.
                                            Queue also has both poll() and remove(),
                                            but NOTE that poll() is better than remove() if the queue is empty,
                                            cause poll() would return null if the queue is empty, remove() does not have this feature.
                                            */                                                                   
            TreeNode right = queue.remove();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            // next layer
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }
    /*
    Time Complexity: O(n); n == total nodes of the tree;
    Space Complexity: O(m), m == # of the nodes in the max level that contains max # of tree nodes
    */
}