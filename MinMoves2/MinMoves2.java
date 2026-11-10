package MinMoves2;

import java.util.Arrays;

/* 462. Minimum Moves to Equal Array Elements II
Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

In one move, you can increment or decrement an element of the array by 1.

Test cases are designed so that the answer will fit in a 32-bit integer.

 

Example 1:

Input: nums = [1,2,3]
Output: 2
Explanation:
Only two moves are needed (remember each move increments or decrements one element):
[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
Example 2:

Input: nums = [1,10,2,9]
Output: 16
 

Constraints:

n == nums.length
1 <= nums.length <= 105
-109 <= nums[i] <= 109
 * 
 * Time Complexity: O(N log N), Where $N$ is the number of elements in the nums array. The dominant operation is sorting the array using Java's Dual-Pivot 
 * 			Quicksort to locate the median element. Summing the absolute differences takes $\mathcal{O}(N)$ linear time.
 * Space Complexity: O(log N), Auxiliary stack space required by the sorting algorithm for primitive arrays.
 */

public class MinMoves2 {
	
	public int minMoves2(int[] nums) {
		Arrays.sort(nums);
		// The median minimizes the sum of absolute deviations
		int median = nums[nums.length / 2];
		
		int moves = 0;
		for (int num : nums) {
			moves += Math.abs(num - median);
		}
		
		return moves;
	}

	public static void main(String[] args) {
		MinMoves2 sol = new MinMoves2();

		// Example 1
		int[] nums1 = {1, 2, 3};
		System.out.println("Input: nums = " + Arrays.toString(nums1));
		System.out.println("Output: " + sol.minMoves2(nums1)); 
		// Expected: 2
		System.out.println("-----------------------------------");

		// Example 2
		int[] nums2 = {1, 10, 2, 9};
		System.out.println("Input: nums = " + Arrays.toString(nums2));
		System.out.println("Output: " + sol.minMoves2(nums2)); 
		// Expected: 16
	}
}
