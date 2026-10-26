package IsGood;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 2784. Check if Array is Good
 * You are given an integer array nums. We consider an array good if it is a permutation of an array base[n].
 * base[n] = [1, 2, ..., n - 1, n, n] (in other words, it is an array of length n + 1 which contains 1 to n - 1 
 * exactly once, plus two occurrences of n). For example, base[1] = [1, 1] and base[3] = [1, 2, 3, 3].
 * Return true if the given array is good, otherwise return false.
 * Note: A permutation of integers represents an arrangement of these numbers.
Example 1:

Input: nums = [2, 1, 3]
Output: false
Explanation: Since the maximum element of the array is 3, the only candidate n for which this array could be a permutation of base[n],
 is n = 3. However, base[3] has four elements but array nums has three. Therefore, it can not be a permutation of base[3] = [1, 2, 3, 3].
 So the answer is false.
Example 2:

Input: nums = [1, 3, 3, 2]
Output: true
Explanation: Since the maximum element of the array is 3, the only candidate n for which this array could be a permutation of base[n],
 is n = 3. It can be seen that nums is a permutation of base[3] = [1, 2, 3, 3] (by swapping the second and fourth elements in nums, 
 we reach base[3]). Therefore, the answer is true.
Example 3:

Input: nums = [1, 1]
Output: true
Explanation: Since the maximum element of the array is 1, the only candidate n for which this array could be a permutation of base[n],
 is n = 1. It can be seen that nums is a permutation of base[1] = [1, 1]. Therefore, the answer is true.
Example 4:

Input: nums = [3, 4, 4, 1, 2, 1]
Output: false
Explanation: Since the maximum element of the array is 4, the only candidate n for which this array could be a permutation of base[n],
 is n = 4. However, base[4] has five elements but array nums has six. Therefore, it can not be a permutation of base[4] = [1, 2, 3, 4, 4]. 
 So the answer is false.
 * 
 * Time Complexity: O(n log n), between sorting the array then building the frequency map and iterating through frequencies.
 * Space Complexity: O(n), You use HashMap -> up to O(n) then ArrayList -> up to O(n).
 */

public class IsGood {
	
	public boolean isGood(int[] nums) {
		Arrays.sort(nums);
		int max = nums[nums.length - 1];
		if(nums.length != (max + 1)) {
			return false;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < nums.length; i++) {
			if(map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i]) + 1);
			}
			else {
				map.put(nums[i], 1);
			}
		}
		List<Integer> list = new ArrayList<>(map.values());
		int count = 0;
		for(int num: list) {
			if(num >= 2) {
				count++;
			}
		}
		if (map.get(max) == 2 && (count == 1)) {
			return true;
		}
		return false;
	}
	
	 public static void main(String[] args) {

	        IsGood solution = new IsGood();
	        
	        int[] nums1 = {2, 1, 3, 3};
	        boolean result1 = solution.isGood(nums1);
	        System.out.println("Is nums1 good? " + result1);

	        int[] nums2 = {1, 3, 3, 2};
	        boolean result2 = solution.isGood(nums2);
	        System.out.println("Is nums2 good? " + result2);

	        int[] nums3 = {1, 1};
	        boolean result3 = solution.isGood(nums3);
	        System.out.println("Is nums3 good? " + result3);
	    }

}

