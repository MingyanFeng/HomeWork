/*
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        /*
        刚开始我想用Hashmap, Use a HashMap, index, to store each element's index, the Key is String element, the Value is element's index;
        然后在存储的过程中，通过word1和word2的距离差，不断更新index中的Value;
        但是，这个方法在同时找word1和word2的时会比较麻烦；
        我参考了答案，觉得答案的方法非常好，遍历一遍就可以，而且不需要额外的数据结构，也节省了空间；
        */
        
        // Corner case
        if (words == null || words.length < 2) {
            return -1;
        }
        
        int p1 = -1;
        int p2 = -1;
        
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                p1 = i;
            }
// 这里没有写else,是因为每次循环执行的时候，都要对word1和word2全部判断一遍吗？
/*
解答：我们默认word1和word2是不一样的，所以这两个if语句不可能同时执行，所以这么写没有问题
            要不要写else这个问题，主要看两点：
                1. 你需不需要if穿透（如果需要，不要写；如果不需要，请参考2）；
                2. 前后两个if条件是否可能同时发生（如果是，不要写；如果不是，不用写）
*/
            if (words[i].equals(word2)) {
                p2 = i;
            }
            // 更新好新的p1和p2后，只有当p1和p2均不是-1时，才能保证找到了两个单词，然后再更新距离差
            if (p1 != -1 && p2 != -1) {
                minDistance = Math.min(minDistance, Math.abs(p1 - p2));
            }
        }
        return minDistance;
    }
    /*
    Time Complexity: O(n), n == words.length;
    Space Complexity: O(1), p1 + p2 == O(1) extra space;
    */
}
/*
Method: Two Pointers 同向前进， 遍历输入同时，更新结果。要注意更新结果的条件
*/