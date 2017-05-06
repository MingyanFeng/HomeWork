/*
456. 132 Pattern

Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. 
Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.
Note: n will be less than 15,000.

Example 1:
    Input: [1, 2, 3, 4]
    Output: False
Explanation: There is no 132 pattern in the sequence.

Example 2:
    Input: [3, 1, 4, 2]
    Output: True
Explanation: There is a 132 pattern in the sequence: [1, 4, 2].

Example 3:
    Input: [-1, 3, 2, 0]
    Output: True
Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
*/

public class Pattern {
    private class Pair {
        public int min;
        public int max;
        public Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
    // Class Pair is used to store temporary min value and max value
    
    public boolean find132pattern(int[] nums) {
        // stack is used to store pairs
        Deque<Pair> stack = new ArrayDeque<>();
        for (int i : nums) {
            if (stack.isEmpty() || i < stack.peek().min) {
                stack.push(new Pair(i, i));
            } else if (i > stack.peek().min) {
                Pair last = stack.pop();
                if (i < last.max) { // 132 pair has found
                    return true;
                }
                last.max = i;
                while (!stack.isEmpty() && i >= stack.peek().max) { // remove pairs with small elements
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek().min < i) { // 132 pair has found
                    return true;
                }
                stack.push(last);
            }
        }
        return false;
    }
    /*
    Time complexity: O(n);
    Space complexity: O(n);
    */

    public static void main(String[] args) {
        int[] nums = {3, 1, 4, 2};
        Pattern test = new Pattern();
        System.out.println(test.find132pattern(nums));
    }
}