/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
// the return value type of the method isBalanced is boolean
// but we still need to know the value of the depth (should be int)
// hence, we need to make a new class called ResultType, and ResultType has two attributes, one boolean, one int
class ResultType {
    public boolean isBalanced;
    public int maxDepth;
    public ResultType(boolean isBalanced, int maxDepth) {
        this.isBalanced = isBalanced;
        this.maxDepth = maxDepth;
    }
}

public class Solution {
    public boolean isBalanced(TreeNode root) {
        // return value type of isBalanced is boolean
        return resultTypeHelper(root).isBalanced;
    }
    
    private ResultType resultTypeHelper(TreeNode root) {
        // corner case
        if (root == null) {
            return new ResultType(true, 0);
        }
        
        // current layer
        ResultType left = resultTypeHelper(root.left);
        ResultType right = resultTypeHelper(root.right);
        
        // if subtree not balanced
        if (!left.isBalanced || !right.isBalanced) {
            // if is not a balanced tree, the depth of the tree does not matter
            return new ResultType(false, -1);
        }
        
        // if root not balanced
        if (Math.abs(left.maxDepth - right.maxDepth) > 1) {
            return new ResultType(false, -1);
        }
        
        // balanced
        int depth = 1 + Math.max(left.maxDepth, right.maxDepth);
        return new ResultType(true, depth);
    }
}
    /*
    Time Complexity: O(n), n == # of nodes in the tree;
    Space Complexity: O(h), h == height of the tree;
    */