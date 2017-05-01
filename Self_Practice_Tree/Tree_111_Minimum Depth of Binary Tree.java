/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
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
    public int minDepth(TreeNode root) {
        /*
        刚开始想用DFS，没有写出来。
        参考了答案，觉得用Divide and Conquer的方法很好，自己写了一遍

        错因：刚开始想用DFS，没有写出来，DFS掌握程度还是不行。

        分析： 你真的搞懂DFS了吗？还差得很远呢，少犯困，多学习！
        而且，之前不是做过那道Maximum Depth of Binary Tree吗？不要被每道题的Tag顺序误导，先写对一种自己最熟悉的算法。
        */
        
        // Corner case
        if (root == null) {
            return 0;
        }
        
        // 答案中没有写这个情况，其实下面的代码是涵盖这个corner case的，可以删除这段代码
        if (root.left == null && root.right == null) {
            return 1;
        }
        
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }
    /*
    Time Complexity: O(n), n == # of nodes;
    Space Complexity: O(h), h == height of the tree;
    */
}
/*
Method: Tree的问题，先想想可不可以用Divide and Conquer来解决，想一下那道Maximum Depth of Binary Tree的做法 
*/