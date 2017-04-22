/*
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
*/

public class Solution {
    public String countAndSay(int n) {
        /*
        The base string is "1";
        Initialize a int variable called count, set count to 1;
        Initialize a char variable called say, set say to base string.charAt(0);
        Initialize a StringBuilder sb, append count to sb, then, append say to sb;
        Traverse the extended string accourding to the value of n, and check each char of the extended string;
        
        If the next number in string is as same as the say, we let count++, say keeps the same;
        If the next number in string is NOT as same as the say, we firstly append count and say to sb, then, update the say to this current number, and reset count to 1;
        */

/*
这道题的算法需要口述讲一遍，我做题的时候思路非常乱，非常乱。。。
必须搞清楚这个流程是怎么回事情，要不然下次还是不会做
*/

        // Base case
        String s = "1";
        
        // Outter for loop: construct the string accourding to the value of n
        // !!! NOTE: i should start from 1 since we already set the base string
        for (int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            // Since the base string is "1" => one 1
            char say = s.charAt(0);
            int count = 1;
            
            // When s.length() > 1, the nest for loop can carry on
            // !!! NOTE: j should also start from 1 due to the base string is set
            for (int j = 1; j < s.length(); j++) {
                if (s.charAt(j) == say) {
                    count++;
                } else {
                    // !!! NOTE: append count and say first, then update say and reset count
                    sb.append(count);
                    sb.append(say);
                
                    say = s.charAt(j);
                    count = 1;
                }
            }
            
            // You have to add base case to sb, then s.length() will change, and i++, nested for loop will carry on
            sb.append(count);
            sb.append(say);
            
            s = sb.toString();
        }
        return s;
    }
/*
Time Complexity: O(n^2) or O(n * 串的长度) ? n is the input;
Space Complexity: O(n) or O(串的长度)?
*/
}