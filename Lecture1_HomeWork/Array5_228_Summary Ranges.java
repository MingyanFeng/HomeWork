/*
思路与count and say有些相似，本题是找连续递增的一段range, 一旦出现了前后元素差 > 1的情况，就断开range

count and say的思路是，一旦出现了与前面数字(即，say)相同的数字，count++，继续遍历；一旦出现与say不同的数字，则更新say, 同时将count reset to 1
*/ 
 
public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> results = new ArrayList<>();
        // corner case
        if (nums == null || nums.length == 0) {
            return results;
        }
        if (nums.length == 1) {
            results.add(nums[0] + "");
            return results;
        }
        
        // find the continuous growth sequence
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            // if there's a continuous growth sequence, let i++, keep traveral
            while (i + 1 < nums.length && nums[i] + 1 == nums[i + 1]) {
                i++;
            }
            // when temp != nums[i], we say we find a range
            if (temp != nums[i]) {
                results.add(temp + "->" + nums[i]);
            // otherwise, we only find a isolated number, not a range
            } else {
                results.add(temp + "");
            }
        }
        return results;
    }
}
/*
Time Complexity: O(n); n == nums.length

Space Complexity: O(1); i + temp == extra constant space
*/