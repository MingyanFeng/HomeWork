/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
*/

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
// 本题我参考了答案
// Version 1: Recursion
    public int closestValue(TreeNode root, double target) {
        /*
        根据二叉树的性质，我们知道当遍历到某个根节点时，最近的那个节点要么是在子树里面，要么就是根节点本身。
        所以我们根据这个递归，返回子树中最近的节点，和根节点中更近的那个就行了。
        */
        
        // Corner case
        if (root == null) {
            return root.val;
        }
        // 选出子树的根节点
        TreeNode kid = (root.val < target)? root.right : root.left; 
        // 如果没有子树，也就是递归到底时，直接返回当前节点值
        if (kid == null) {
            return root.val;
        }
        // 找出子树中最近的那个节点
        int closest = closestValue(kid, target);
        // 返回根节点和子树最近节点中，更近的那个节点
        return (Math.abs(root.val - target) < Math.abs(closest - target))? root.val : closest;
    }
    /*
    Time Complexity: O(logn), n == # of nodes;
    Space Complexity: O(h), h == height of the tree;
    */

// Version 2: Iteration
    public int closestValue(TreeNode root, double target) {
        /*
        记录一个最近的值，然后沿着二叉搜索的路径一路比较下去，并更新这个最近值就行了。
        因为我们知道离目标数最接近的数肯定在二叉搜索的路径上。
        */
        
        // Corner case
        if (root == null) {
            return root.val;
        }
        
        int closest = root.val;
        
        while (root != null) {
            // 如果该节点离目标更近，则更新到目前为止的最近值
            closest = Math.abs(closest - target) < Math.abs(root.val - target) ? closest : root.val;
            // 二叉搜索
            root = root.val < target ? root.right : root.left;
        }
        return closest;
    }
    /*
    Time Complexity: O(logn), n == # of nodes;
    Space Complexity: O(h), h == height of tree;
    */
}