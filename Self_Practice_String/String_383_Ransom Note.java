/*
Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
*/

public class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
// Version 1: 自己想的
        /*
        For magazine, use a HashMap to store each char(Key) and each char's appeared times(Value);
        For ransomNote, check each char in HashMap, to see if the HashMap contains that char && HashMap.get(char) > 0;
            If HashMap contains that char && HashMap.get(char) > 0, HashMap.put(HashMap.get(char) - 1);
            Otherwise, return false;
        After finishing traverse the ransomNote, return true;
        */
        
        // Corner case
        if (magazine == null || ransomNote == null) {
            return false;
        }
        
        if (ransomNote.length() == 0) {
            return true;
        }
        
        // Build a map
        Map<Character, Integer> map = new HashMap<>();
        // Convert magazine to charArray
        char[] cm = magazine.toCharArray();
        // Count each char's appreaed times in magazine
        for (char c : cm) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        
        // Check each char in ransomNote
        char[] cr = ransomNote.toCharArray();
        for (char c : cr) {
            if (map.containsKey(c) && map.get(c) > 0) {
                map.put(c, map.get(c) - 1);
            } else {
                return false;
            }
        }
        return true;
    }
    /*
    Time Complexity: O(n + m), n == ransomNote.length(), m == magazine.length();
    Space Complexity: ideal is O(n), but real extra space is lager than O(n) due to the property of the Hash Table; 
    */

// Discuss里投票最高的答案，好像AC不上去？ 我觉得用26长度的数组这个方法挺好的，因为空间复杂度可以降到O(1), disscuss的答案哪里错了呢？
// Discuss答案如下：
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] arr = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            arr[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if(--arr[ransomNote.charAt(i)-'a'] < 0) {
                return false;
            }
        }
        return true;
    }
    /*
    Time Complexity: O(n + m), n == ransomNote.length(), m == magazine.length();
    Space Complexity: O(1), ch + i == O(1) extra space; 
    */
}