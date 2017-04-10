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
    public int maxDepth(TreeNode root) {
        // base case
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        // max depth == 1 + max(left tree's depth, right tree's depth)
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
    /*
    Time Complexity: O(n), n == # of total node's in the tree
    Space Complexity: O(h), h == height of the tree
    */