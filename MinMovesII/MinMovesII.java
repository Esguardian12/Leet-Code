package MinMovesII;

import java.util.Arrays;

/* 453. Minimum Moves to Equal Array Elements
Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

In one move, you can increment n - 1 elements of the array by 1.

 
Example 1:

Input: nums = [1,2,3]
Output: 3
Explanation: Only three moves are needed (remember each move increments two elements):
[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
Example 2:

Input: nums = [1,1,1]
Output: 0
 

Constraints:

n == nums.length
1 <= nums.length <= 105
-109 <= nums[i] <= 109
The answer is guaranteed to fit in a 32-bit integer.
 * 
 * Time Complexity: {O}(N), Where $N$ is the length of the nums array. We iterate through the array twice: once to find the minimum element and once to sum 
 * 		the difference of each element from the minimum.
 * Space Complexity: {O}(1), Only a few primitive integer variables are used for tracking values and counts, requiring constant auxiliary storage.
 */

public class MinMovesII {

	public int minMovesII(int[] nums) {
		int min = Integer.MAX_VALUE;
		for (int num : nums) {
			if (num < min) {
				min = num;
			}
		}
		
		int moves = 0;
		for (int num : nums) {
			moves += (num - min);
		}
		
		return moves;
	}
	
	//** The other MinMoves has the same function as this one because it has the same names it needs to be in that file in order for it to work **//
	
	public static void main(String[] args) {
		MinMovesII sol = new MinMovesII();

		// Example 1
		int[] nums1 = {1, 2, 3};
		System.out.println("Input: nums = " + Arrays.toString(nums1));
		System.out.println("Output: " + sol.minMovesII(nums1)); 
		// Expected: 3
		System.out.println("-----------------------------------");

		// Example 2
		int[] nums2 = {1, 1, 1};
		System.out.println("Input: nums = " + Arrays.toString(nums2));
		System.out.println("Output: " + sol.minMovesII(nums2)); 
		// Expected: 0
	}
	
}

