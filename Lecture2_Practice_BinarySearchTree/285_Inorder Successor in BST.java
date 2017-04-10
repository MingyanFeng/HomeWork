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
    // version 1: Divide and conquer, using recursion
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // corner case
        if (root == null) {
            return root;
        }
        // p is the root or p is in the right subtree
        // find p's inorder successor in the right subtree
        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        // p is in the left subtree
        // find p's inorder successor in the left subtree, if not found, return root
        } else {
            TreeNode left = inorderSuccessor(root.left, p);
            return (left != null)? left : root;
        }
    }
    /*
    Time Complexity: O(h), h is the height of the BST
    Space Complexity: O(h)，h is the height of the BST
    */


    // version 2: Iterative
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // successor is a global variable, successor need to be updated according to its location
        TreeNode successor = null;
        // corner case
        if (root == null || p == null) {
            return successor;
        }
        while (root != null) {
            // p is in the left subtree
            if (p.val < root.val) {
                // NOTE: successor will be updated along with the root being updated
                successor = root;
                root = root.left;
            // p is the root or p is in the right subtree
            } else {
                root = root.right;
            }
        }
        return successor;
    }
    /*
    Time Complexity: O(h), h is the height of the BST
    Space Complexity: O(1)
    */
}