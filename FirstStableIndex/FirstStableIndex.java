package FirstStableIndex;

import java.util.Arrays;

/* 3903. Smallest Stable Index I
You are given an integer array nums of length n and an integer k.
For each index i, define its instability score as max(nums[0..i]) - min(nums[i..n - 1]).
In other words:
max(nums[0..i]) is the largest value among the elements from index 0 to index i.
min(nums[i..n - 1]) is the smallest value among the elements from index i to index n - 1.
An index i is called stable if its instability score is less than or equal to k.
Return the smallest stable index. If no such index exists, return -1.
 
Example 1:
Input: nums = [5,0,1,4], k = 3
Output: 3
Explanation:
At index 0: The maximum in [5] is 5, and the minimum in [5, 0, 1, 4] is 0, so the instability score is 5 - 0 = 5.
At index 1: The maximum in [5, 0] is 5, and the minimum in [0, 1, 4] is 0, so the instability score is 5 - 0 = 5.
At index 2: The maximum in [5, 0, 1] is 5, and the minimum in [1, 4] is 1, so the instability score is 5 - 1 = 4.
At index 3: The maximum in [5, 0, 1, 4] is 5, and the minimum in [4] is 4, so the instability score is 5 - 4 = 1.

This is the first index with an instability score less than or equal to k = 3. Thus, the answer is 3.


Example 2:
Input: nums = [3,2,1], k = 1
Output: -1
Explanation:
At index 0, the instability score is 3 - 1 = 2.
At index 1, the instability score is 3 - 1 = 2.
At index 2, the instability score is 3 - 1 = 2.
None of these values is less than or equal to k = 1, so the answer is -1.


Example 3:
Input: nums = [0], k = 0
Output: 0
Explanation:
At index 0, the instability score is 0 - 0 = 0, which is less than or equal to k = 0. Therefore, the answer is 0.
 
Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 109
0 <= k <= 109
 * 
 * Time Complexity: O(N), Where $N$ is the length of the nums array. We iterate through the array once backwards to build the suffMin array, and once forwards to 
 * 			track the prefMax and calculate the score. Both passes are linear.
 * Space Complexity: O(N), We allocate a single auxiliary array suffMin of length $N$ to store the suffix minimums. Tracking the prefix maximum only requires one 
 * 			integer variable, taking ${O}(1)$ space.
 */

public class FirstStableIndex {
	
	public int firstStableIndex(int[] nums, int k) {
		int n = nums.length;
		
		// Precompute the minimum values from index i to n-1
		int[] suffMin = new int[n];
		suffMin[n - 1] = nums[n - 1];
		for(int i = n - 2; i >= 0; i--) {
			suffMin[i] = Math.min(nums[i], suffMin[i + 1]);
		}
		
		int prefMax = Integer.MIN_VALUE;
		
		// Iterate to find the first index satisfying the condition
		for(int i = 0; i < n; i++) {
			prefMax = Math.min(prefMax,  nums[i]);
			
			// Calculate instability score
			int score = prefMax - suffMin[i];
			
			if (score <= k) {
				return i;
			}
		}
		
		return -1;
	}

	public static void main(String[] args) {
        FirstStableIndex sol = new FirstStableIndex();
        
        // Example 1
        int[] nums1 = {5, 0, 1, 4};
        int k1 = 3;
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + sol.firstStableIndex(nums1, k1)); // Expected: 3
        System.out.println("-----------------------------------");
        
        // Example 2
        int[] nums2 = {3, 2, 1};
        int k2 = 1;
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + sol.firstStableIndex(nums2, k2)); // Expected: -1
        System.out.println("-----------------------------------");
        
        // Example 3
        int[] nums3 = {0};
        int k3 = 0;
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + sol.firstStableIndex(nums3, k3)); // Expected: 0
    }
}
