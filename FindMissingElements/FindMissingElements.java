package FindMissingElements;

import java.util.ArrayList;
import java.util.List;

/* 3731. Find Missing Elements
You are given an integer array nums consisting of unique integers.

Originally, nums contained every integer within a certain range. However, some integers might have gone missing from the array.

The smallest and largest integers of the original range are still present in nums.

Return a sorted list of all the missing integers in this range. If no integers are missing, return an empty list.


Example 1:

Input: nums = [1,4,2,5]

Output: [3]

Explanation:

The smallest integer is 1 and the largest is 5, so the full range should be [1,2,3,4,5]. Among these, only 3 is missing.

Example 2:

Input: nums = [7,8,6,9]

Output: []

Explanation:

The smallest integer is 6 and the largest is 9, so the full range is [6,7,8,9]. All integers are already present, so no integer is missing.

Example 3:

Input: nums = [5,1]

Output: [2,3,4]

Explanation:

The smallest integer is 1 and the largest is 5, so the full range should be [1,2,3,4,5]. The missing integers are 2, 3, and 4.

 

Constraints:

2 <= nums.length <= 100
1 <= nums[i] <= 100
 * 
 * Time Complexity: O(N), Where N is the length of the nums array. We iterate through the input array exactly once to find the minimum, maximum, 
 * 			and mark the numbers, which takes O(N) time. The second loop iterates over the range of numbers bounded by the maximum possible value (100). 
 * 			Thus, the total time strictly scales linearly with the size of the array, operating in effective O(1) time in reality due to the tight 
 * 			constraints of the problem.
 * Space Complexity: O(1), We allocate a fixed-size boolean array of exactly 101 elements to act as our frequency/presence map. Because this array size is 
 * 			strictly bounded and does not grow based on the size of the input array N, the auxiliary space memory footprint is entirely constant. 
 * 			(Note: The space used to store the output missing list does not count toward the algorithm's auxiliary space complexity). 
 */

public class FindMissingElements {
	
	public List<Integer> findMissingElements(int[] nums) {
		// Since nums[i] is between 1 and 100, an array of size 101 is sufficient.
		boolean[] present = new boolean[101];
		int min = 101;
		int max = 0;
		
		// Find the range and mark present numbers
		for(int num : nums) {
			if(num < min) min = num;
			if(num > max) max = num;
			present[num] = true;
		}
		
		List<Integer> missing = new ArrayList<>();
		
		// Iterate through the original range to find missing numbers
		for(int i = min + 1; i < max; i++) {
			if(!present[i]) {
				missing.add(i);
			}
		}
		
		return missing;
	}
	
	public static void main(String[] args) {
		FindMissingElements sol = new FindMissingElements();

		// Example 1
		int[] nums1 = {1, 4, 2, 5};
		System.out.println("Input: nums = [1, 4, 2, 5]");
		System.out.println("Output: " + sol.findMissingElements(nums1)); // Expected: [3]
		System.out.println("-----------------------------------");

		// Example 2
		int[] nums2 = {7, 8, 6, 9};
		System.out.println("Input: nums = [7, 8, 6, 9]");
		System.out.println("Output: " + sol.findMissingElements(nums2)); // Expected: []
		System.out.println("-----------------------------------");

		// Example 3
		int[] nums3 = {5, 1};
		System.out.println("Input: nums = [5, 1]");
		System.out.println("Output: " + sol.findMissingElements(nums3)); // Expected: [2, 3, 4]
	}

}
