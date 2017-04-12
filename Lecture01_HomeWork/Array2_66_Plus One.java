public class Solution {
    public int[] plusOne(int[] digits) {
        /*
        // corner case
        if (digits == null || digits.length == 0) {
            int[] result = {1};
            return result;
        }
        */
        
        // traverse the digits backwards
        for (int i = digits.length - 1; i >= 0; i--) {
            // if digits[i] < 9, we let digits[i]++, return digits
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            // otherwise, we let the digit[i] = 0, then, keep the traversal unitl find the digit that < 9
            } else {
                digits[i] = 0;
            }
        }
        
        // if all digits are NOT < 9, like 999, we need to make a new array
        // this part covers the corner case
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }
}
/*
Time Complexity: O(n); n == # of elements in digits

Space Complexity: O(1); i == extra O(1) constant space
*/