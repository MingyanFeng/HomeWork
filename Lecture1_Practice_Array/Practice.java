public calss Practice {
	/*
	1. Moving Zeros
	Move all zeros to the back of the array
	E.g: 1, 0, 2, 0, 3
	  => 1, 2, 3, 0, 0
	*/
	public static void moveZeros(int[] nums) {
		// corner case
		if (nums == null || nums.length == 0) {
			return;
		}
		// index represents the index of results
		int index = 0;

		for (int i = 0; i < nums.length; i++) {
			// if nums[i] != 0, let nums[index] = nums[i], and let index++
			// otherwise, only keep i++, the for loop will do this
			if (nums[i] != 0) {
				nums[index] = nums[i];
				index++;
			}
		}
		// after i has finished iterating, we change elements in [ nums[j], nums[nums.length - 1] ] to 0
		while (index < nums.length) {
			nums[index] = 0;
			index++;
		}
	}
	/*
	Time Complexity: O(n); n == nums.length;
	Space Complexity: O(1); index + i == O(1) extra space;
	*/


	/*
	2. Rotate Array by K Steps
	E.g: Input: [1, 2, 3, 4, 5, 6, 7], k =3.
		 Output: [5, 6, 7, 1, 2, 3, 4]
	*/
	/*
	Use 3 step reversing
	*/
	public static void rotateArray(int[] nums, int k) {
		k %= nums.length;
		reverse(nums, 0, nums.length - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, nums.length - 1);
	}

	private static void reverseArray(int[] nums, int start, int end) {
		while (start < end) {
			int temp = nums[start];
			num[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}
	/*
	Time Complexity: O(n); n == nums.length;
	Space Complexity: O(1);
	*/
}