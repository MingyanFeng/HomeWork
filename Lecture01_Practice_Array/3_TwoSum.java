public class TwoSum {
    /*
    version 1: HashMap

    The key is the element of the array, and the value is the index of the element.
    We first store the element and its index into the HashMap,
    and then we try to find target minus each element(target - nums[i]) in the HashMap.
    If it exists and its(target - nums[i]) index is not as same as the index of the nums[i](subtractor),
    we can say we've found the result.
    */ 
    public int[] twoSum(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return null;
        }

        // initialize the result
        int[] result = new int[2];

        // use HashMap to sotre each element and each element's index
        // keys are elements, value are elements' index 
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            index.put(nums[i], i);
        }

        // iterate the array, to check if the array contains (target - nums[i])
        // and if (target - nums[i])'s index is different with i
        for (int i = 0; i < nums.length; i++) {
            if (index.containsKey(target - nums[i]) && index.get(target - nums[i]) != i) {
                result[0] = Math.min(i, index.get(target - nums[i]));
                result[1] = Math.max(i, index.get(target - nums[i]));
            }
        }
        return result;
    }
    /*
    The advantage is that it is fast.
    The time complexity is O(n).

    However, the disadvantage is that it is not in-place.
    The ideal space complexity is O(n),
    but the real space complexity is much larger because of the property of Hash Table
    */


    /*
    version 2: if return value type is not int[], but two integers, we can use HashSet
    */
    public Set<Integer> twoSum(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return null;
        }
        // initialize the result
        int[] result = new int[2];
        
        // use HashSet to store the difference between target and nums[i], that is (target - nums[i])
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) {
                result[0] = target - i;
                result[1] = i;
                return result;
            }
            // store (target - nums[i]) into HashSet
            int diff = target - i;
            set.add(diff);
        }
        // if not found such pair, return result as null
        return result;
    }
    /*
    Time Complexity: O(n); n == nums.length;
    Space Complexity: O(n);
    */
}