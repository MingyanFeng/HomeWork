public class Solution {
    /*
    version 1: HashTable
    */
    public boolean isAnagram(String s, String t) {
        // corner case
        if (s.length() != t.length()) {
            return false;
        }
        
        // count occurrences of each letter in the two strings and compare them. 
        // since both s and t contain only letters from a−z, a simple counter of size 26 is suffice.
        int[] counter = new int[26];
        
        // count s's letters
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
        }
        // count t's letters
        for (int i = 0; i < t.length(); i++) {
            counter[t.charAt(i) - 'a']--;
        }
        // check if t and s are anagrams
        for (int i : counter) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
    /*
    Time Complexity: O(n); n == s.length() == t.length();
	Space Complexity: O(1); 
    */
}
