/*
Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
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
// Version 1: Traverse
    public List<String> binaryTreePaths(TreeNode root) {
        /*
        Use DFS to find each path;
        
        错因： helper函数没写对。
        
        分析：
        1. dfs和递归的掌握不牢固，需要加强练习，自己独立写一遍，写不下去了再参考答案，对比自己的思路和答案差在哪里。
        2. String.valueOf()这个方法挺好用的，可以直接return the String representation of Primitive type/Object 以后尝试多使用一下，注意这是一个static方法。
        3. 还是想再强调一下，解题之前，一定要再三留意输入是什么，输出是什么，有没有特别的输出格式要求，比如本题的"->"。
        */
        
        // Initialize results
        List<String> results = new ArrayList<>();
        // Corner case
        if (root == null) {
            return results;
        }
        
        // Use a dfsHelper
        dfsHelper(root, String.valueOf(root.val), results);
        
        return results;
    }
    
    /*
    dfsHelper部分的代码我参考了九章的答案;
    九章答案的思路：
    当root != null时，先把root.val存在path中，这样path初始化之后，存有root.val相应的String格式；
    如果当前的TreeNode是叶子结点，results直接加上path(此时的path已经含有root.val相应的String格式了)，返回；
    如果当前的TreeNode有左/右孩子，那么path要先加上"->", 然后再加上当前TreeNode左/右孩子的val的相应的String格式；
    */
    // 1. 定义
    private void dfsHelper(TreeNode root, String path, List<String> results) {
        // 3. 出口，特殊情况，Base case
        /*
        分析： 这步自己想到了
        */ 
        if (root == null) {
            return;
        }
        
        // 2. 拆解，current layer
        /*
        分析：这步自己也想到了
        */ 
        if (root.left == null && root.right == null) {
            results.add(path);
            return;
        }
        
        // 2. 拆解，next layer
        /*
        错因：之后的步骤没有想到，想想为什么？
        分析：对于Tree的问题分析没有形成套路思维。
        回想一下师兄讲课的内容，人生若只如初见嘛~ 先画一个最简单的Tree，就两层
                O
               / \
              O   O
        观察一下这棵最简单的Tree, 除了root之外，就剩下root.left和root.right了。再复杂的Tree都无外乎这样的结构。
        而且你要使用递归，那么其实只要考虑当前层就可以了，当前层就相当于这个最简单的Tree, 所以当前层有以下几种情况：
            1.  缺头，即root == null；
            2.  只有头，没胳膊，即root.left == null && root.right == null;
            3.1 只有头和左胳膊
            3.2 只有头和右胳膊
            3.3 有头，有左胳膊，有右胳膊
            3.1~3.1的情况，就是我现在思路最困难的地方，其实整体看，如何把1~3这三种情况整体考虑，也是我思路最困难的地方，多练习递归的拆解部分，一定要记住，递归的前提是，只考虑当前处理的对象，其他的都处理好了，不用你瞎操心。
            
        所以以后遇到Tree的题目，
        如果可以用Divide & Conquer, 先试试Divide & Conquer；
        如果需要使用递归，就先画一个两层的树，分析清楚当前层！！！注意！！！是当前层！！！的所有可能情况（其实就上面那几种情况啊，否则你还想把树画成一朵花么。。。）
        
        多练习递归的写法，这也是DFS的根基啊，不可以逃避DFS的题目，硬着头皮也得上。。。
        */
        if (root.left != null) {
            dfsHelper(root.left, path + "->" + String.valueOf(root.left.val), results);
        }
        
        if (root.right != null) {
            dfsHelper(root.right, path + "->" + String.valueOf(root.right.val), results);
        }
    }
    /*
    Time Complexity: O(n), n == # of nodes;
    Space Complexity: O(h), h == height of the tree, h == logn;
    */


// Version 2: Divide and Conquer
    public List<String> binaryTreePaths(TreeNode root) {
        /*
        错因：第一个解法没有想到Divide and Conquer
        
        分析：还是对Divide and Conquer不够熟悉，多练！
        
        答案思路：
        无脑先Divide and Conquer；
        左/右子树如果有node，就把左/右子树每一条路径的所有node作为一个新的结果，加到paths里面。
        注意，每一条包含左/右子树的路径都包含根节点的val！
        注意，如果是只有单独的root，需要单独讨论，把root.val单独加到paths里面去！
        */
        
        // Initialize result, call it paths
        List<String> paths = new ArrayList<>();
        
        // Corner case
        if (root == null) {
            return paths;
        }
        
        List<String> leftPaths = binaryTreePaths(root.left);
        List<String> rightPaths = binaryTreePaths(root.right);
        
        // Add all left subtree's path
        for (String path : leftPaths) {
            paths.add(root.val + "->" + path);
        }
        
        // Add all right subtree's path
        for (String path : rightPaths) {
            paths.add(root.val + "->" + path);
        }
        
        // If root itself is a leaf, there's no subtree's path, only root itself
        if (paths.size() == 0) {
            paths.add("" + root.val);
        }
        
        return paths;
    }
    /*
    Time Complexity: O(n), n = # of nodes;
    Space Conplexity: O(h), h == height of the tree, h == logn;
    */
}