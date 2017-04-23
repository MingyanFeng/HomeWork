/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
(ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // initialize results
        LinkedList<List<Integer>> results = new LinkedList<>();
        
        // corner case
        if (root == null) {
            return results;
        }
        
        // use BFS to traverse the tree level by level
        // store each level's nodes while doing the traversal
        // after finishing one level's traverse, inserts each level's nodes at the beginning of the results, using addFirst() method in LinkedList 
        
        // initialize the queue
        Queue<TreeNode> queue = new LinkedList<>();
        // offer the root into queue
        queue.offer(root);
        
        // use BFS to traverse the tree level by level
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new LinkedList<>();
            
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                level.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            // inserts each level's nodes at the beginning of the results
            results.addFirst(level);
        }
        
        return results;
    }
    /*
    Time Complexity: O(n), n == # of nodes;
    Space Complexity: O(m), m == the largest # of nodes in one level, worst case is when the tree is a balanced tree, m = 2 / n;
    */
}

/*
初始化results时候，如果results需要用LinkedList独有的方法时，需要用LinkedList Class进行初始化，而不是用List Interface进行初始化。

List<List<Integer>> results = new LinkedList<>(); 和 
LinkedList<List<Integer>> results = new LinkedList<>(); 的区别：

List<List<Integer>> results = new LinkedList<>(); 初始化的results，能使用的方法仅限于List Interface的所有方法

LinkedList<List<Integer>> results = new LinkedList<>(); 初始化的results，能使用的方法是LinkedList的所有方法 + List Interface的所有方法
*/