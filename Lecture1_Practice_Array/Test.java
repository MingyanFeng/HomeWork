import java.util.*;

public class Test {
	public static void main(String[] args) {
		int[] nums1 = {1, 0, 2, 0, 3};
		int[] nums2 = {1, 2, 3, 4 ,5 ,6, 7};
		int k = 3;

		Practice.moveZeros(nums1);
		for (int i : nums1) {
			System.out.print(nums1[i]);
		}

	}
}