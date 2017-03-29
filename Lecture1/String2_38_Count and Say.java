public class Solution {
    public String countAndSay(int n) {
        // Base case is: when n = "1", we return "1"
        // Initialize the first string is 1
        String s = "1";
        for (int j = 1; j < n; j++) {
            StringBuilder sb = new StringBuilder();
            // Initialize say to s.charAt(0)
            char say = s.charAt(0);
            // Initialize count to 1
            int count = 1;
            for (int i = 1; i < s.length(); i++) {
                // if the following char == say, let count++, until we find a different char
                if (s.charAt(i) == say) {
                    count++;
                // Otherwise, when we find a different char, append the count and say into sb
                // And, reset the count to 1, and update say to s.charAt(i), since we need to start counting another new number
                } else {
                    sb.append(count);
                    sb.append(say);
                    // reset count to 1, and update say to s.charAt(i)
                    count = 1;
                    say = s.charAt(i);
                }
            }
            // NOTE: Begining point and End point need to pay attention
            sb.append(count);
            sb.append(say);
            // convert sb to string
            s = sb.toString();
        }
        return s;
    }
}
/*
TimeComplexity: O(n^2); since we have to at least generate every solution from n = 1 to n = n, which is O(n) for each case.

Space Complexity: O(n)
*/