package MinMovesIII;

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
 * Time Complexity: O(N log N), Where $N$ is the length of the nums array. The dominant operation is sorting the array to locate the median element. 
 * 		(Note: This can theoretically be optimized to ${O}(N)$ average time using a Quickselect algorithm, but sorting is concise and well within 
 * 		limits for $N = 10^5$).
 * Space Complexity: O(N log N), auxiliary stack space Required by Java's Dual-Pivot Quicksort implementation used in Arrays.sort() for primitive arrays.
 */

public class MinMovesIII {

	public int minMovesIII(int[] nums) {
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
		MinMovesIII sol = new MinMovesIII();

		// Example 1
		int[] nums1 = {1, 2, 3};
		System.out.println("Input: nums = " + Arrays.toString(nums1));
		System.out.println("Output: " + sol.minMovesIII(nums1)); 
		// Expected: 2
		System.out.println("-----------------------------------");

		// Example 2
		int[] nums2 = {1, 10, 2, 9};
		System.out.println("Input: nums = " + Arrays.toString(nums2));
		System.out.println("Output: " + sol.minMovesIII(nums2)); 
		// Expected: 16
	}
}
