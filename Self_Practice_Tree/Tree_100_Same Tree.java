/*
Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
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
// Version 1：错误版本，我想到了用递归，但是没有写对
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 错因： base case没有考虑对
        // 如果p, q的val不同，那么就不用往下走了，肯定是false。
        // ！！！ 但是这里没有提前判断p和q是否为null，所以这里会报java.lang.NullPointerException错误 ！！！
        if (p.val != q.val) {
            return false;
        }
        // 程序走过上一个if后，说明p和q的val此时一定相等，应该继续检查p和q的左右孩子节点
        if (p.left == null && p.right == null && q.left == null && q.right == null) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        
        return false;
    }
    /*
	为什么会出现base case没有考虑对的情况？

	因为自己没有一个清晰的判断思路。
	一是没有考虑递归的思路是Buttom Up还是Top Down。
	二是没有考虑先从哪方面开始判断这两棵树是否相等； 一个TreeNode, 除了它自己的val，不就是只剩它的左右孩子了吗？应该画两个都只有两层的tree, 现有的水平，不能脑补。
	
	所以两个Tree是否相等，要从“结构”和“val”这两方面分开判断。
	我没有从“总-分”的思路来判断，同时也说明自己对于Tree这个结构掌握得还是不够。

	光知道用递归可以实现是远远不够的，写正确才是硬道理。
    */

// Version 2: 参照了答案，重新写了一遍
    public boolean isSameTree(TreeNode p, TreeNode q) {
        /*
		正确的思路应该是：
		Bottom Up, 假设p, q上面的点（也就是离root比p和q更近的那些点）都已经处理好了；
		
		先判断结构：
		如果p和q同时为null，return true就可以了；
		如果p和q其中一个为null，说明两棵树的结构不一样，return false;
		
		再判断值：
		如果p和q均不为null, 且他们的val相同，那么继续对p和q的孩子节点判断，相当于p和q的左右子节点是isSameTree这个方法的新的形参；
		如果p和q均不为null, 且他们的val不相等，return false；
        */
        if (p == null && q == null) {
            return true;
        }
        
        if (p == null || q == null) {
            return false;
        }
        
        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        
        return false;
    }
    /*
    Time Complexity: O(n + m), n == # of nodes in p tree, m == # of nodes in q tree;
    Space Complexity: O(h1 + h2), h1 == height of p tree, h2 == height of q tree;
    */
}