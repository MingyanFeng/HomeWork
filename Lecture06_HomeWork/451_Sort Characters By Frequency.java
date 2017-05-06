/*
451. Sort Characters By Frequency

Given a string, sort it in decreasing order based on the frequency of characters

Example 1: 
    Input: "tree"
    Output: "eert"
    Explanation:
        'e' appears twice while 'r' and 't' both appear once.
        So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2: 
    Input: "cccaaa"
    Output: "cccaaa"
    Explanation:
        Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
        Note that "cacaca" is incorrect, as the same characters must be together.

Example 3: 
    Input: "Aabb"
    Output: "bbAa"
    Explanation:
        "bbaA" is also a valid answer, but "Aabb" is incorrect.
        Note that 'A' and 'a' are treated as two different characters.
*/

public class SortCharactersByFrequency {
    public String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        Map<Character, Integer> hm = new HashMap<>(); // hm is used to store each character and its frequency
        for (int i = 0; i < s.length(); i++) {
            if (hm.containsKey(s.charAt(i))) {
                hm.put(s.charAt(i), hm.get(s.charAt(i)) + 1);
            } else {
                hm.put(s.charAt(i), 1);
            }
        }
        LinkedList<Character>[] freqTable = new LinkedList[s.length() + 1]; // freqTable is used to sort characters by frequency
        for (Map.Entry<Character, Integer> e : hm.entrySet()) {
            if (freqTable[e.getValue()] == null) {
                freqTable[e.getValue()] = new LinkedList<Character>();
            }
            freqTable[e.getValue()].add(e.getKey());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = freqTable.length - 1; i >= 0; i--) {
            if (freqTable[i] == null) {
                continue;
            }
            while (!freqTable[i].isEmpty()) {
                char c = freqTable[i].poll();
                for (int j = 0; j < i; j++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
    /*
    Time complexity: O(n);
    Space complexity: O(n);
    */

    public static void main(String[] args) {
        String s = "tree";
        SortCharactersByFrequency test = new SortCharactersByFrequency();
        System.out.println(test.frequencySort(s));
    }
}