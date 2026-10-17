package MissingInteger;

package MissingInteger;

import java.util.HashSet;
import java.util.Set;

/* 2996. Smallest Missing Integer Greater Than Sequential Prefix Sum
You are given a 0-indexed array of integers nums.

A prefix nums[0..i] is sequential if, for all 1 <= j <= i, nums[j] = nums[j - 1] + 1. In particular, the prefix consisting only of nums[0] is sequential.

Return the smallest integer x missing from nums such that x is greater than or equal to the sum of the longest sequential prefix.

 

Example 1:

Input: nums = [1,2,3,2,5]
Output: 6
Explanation: The longest sequential prefix of nums is [1,2,3] with a sum of 6. 6 is not in the array, therefore 6 is the smallest missing integer greater than or equal to the sum of the longest sequential prefix.
Example 2:

Input: nums = [3,4,5,1,12,14,13]
Output: 15
Explanation: The longest sequential prefix of nums is [3,4,5] with a sum of 12. 12, 13, and 14 belong to the array while 15 does not. Therefore 15 is the smallest missing integer greater than or equal to the sum of the longest sequential prefix.
 

Constraints:

1 <= nums.length <= 50
1 <= nums[i] <= 50
 * 
 * Time Complexity: O(N), Where $N$ is the number of elements in the nums array.Finding the longest sequential prefix sum takes at most $\mathcal{O}(N)$ time.
 * 			Inserting all elements into the HashSet takes $\mathcal{O}(N)$ time.Finding the missing integer increments the prefixSum variable. In the worst-case 
 * 			scenario, it increments $N$ times if the array contains consecutive sums starting from prefixSum, which also takes $\mathcal{O}(N)$ time.Overall, the 
 * 			execution scales strictly linearly with the size of the array
 * Space Complexity: O(N), We utilize a HashSet to store the unique numbers from the input array. In the worst case (all elements are unique), the set will store 
 * 			exactly $N$ elements, requiring $\mathcal{O}(N)$ auxiliary memory space.
 */

public class MissingInteger {
	
	public int missingInteger(int[] nums) {
		// Calculate the sum of the longest sequential prefix
		int prefixSum = nums[0];
		for(int i = 1; i < nums.length; i++) {
			if(nums[i] == nums[i - 1] + 1) {
				prefixSum += nums[i];
			} else {
				// Break as soon as the sequence is no longer valid
				break;
			}
		}
		
		// Add all numbers to a HashSet for O(1) lookups
		Set<Integer> numSet = new HashSet<>();
		for(int num : nums) {
			numSet.add(num);
		}
		
		// Find the smallest missing integer >= prefixSum
		while(numSet.contains(prefixSum)) {
			prefixSum++;
		}
		
		return prefixSum;
	}
	
	public static void main(String[] args) {
		MissingInteger sol = new MissingInteger();

		// Example 1
		int[] nums1 = {1, 2, 3, 2, 5};
		System.out.println("Input: nums = [1, 2, 3, 2, 5]");
		System.out.println("Output: " + sol.missingInteger(nums1)); // Expected: 6
		System.out.println("-----------------------------------");

		// Example 2
		int[] nums2 = {3, 4, 5, 1, 12, 14, 13};
		System.out.println("Input: nums = [3, 4, 5, 1, 12, 14, 13]");
		System.out.println("Output: " + sol.missingInteger(nums2)); // Expected: 15
		System.out.println("-----------------------------------");

		// Example 3 (Custom)
		int[] nums3 = {29, 30, 31, 32, 33, 34, 35, 36, 37};
		System.out.println("Input: nums = [29, 30, 31, 32, 33, 34, 35, 36, 37]");
		System.out.println("Output: " + sol.missingInteger(nums3)); // Expected: 297
	}

}

