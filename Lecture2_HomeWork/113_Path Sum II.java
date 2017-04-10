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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        // need a helper to find and store all paths
        helper(root, sum, results, path);
        return results;
    }
    
    private void helper(TreeNode root, 
                        int sum, 
                        List<List<Integer>> results, 
                        List<Integer> path) {
        // corner case
        if (root == null) {
            return;
        }
        // !!! add root.val first !!!
        path.add(root.val);
        // if root.val == sum && only root
        if (root.left == null && root.right == null && root.val == sum) {
            results.add(new ArrayList<Integer>(path));
            path.remove(path.size() - 1);
            return;
        // otherwise, find sum - root.val in subtree
        } else {
            helper(root.left, sum - root.val, results, path);
            helper(root.right, sum - root.val, results, path);
        }
        // back track
        path.remove(path.size() - 1);
    }
}
    /*
    Time Complexity: O(m*h), m == # of paths, h == height of the tree;
    Space Complexity: O(h), h == height of the tree;
    */