package MinMoves;

import java.util.HashMap;
import java.util.Map;

/* 1674. Minimum Moves to Make Array Complementary
 * You are given an integer array nums of even length n and an integer limit. In one move, you can replace any integer from nums with
 * another integer between 1 and limit, inclusive.The array nums is complementary if for all indices i (0-indexed), nums[i] + nums[n - 1 - i]
 * equals the same number. For example, the array [1,2,3,4] is complementary because for all indices i, nums[i] + nums[n - 1 - i] = 5.
 * Return the minimum number of moves required to make nums complementary.

Example 1:

Input: nums = [1,2,4,3], limit = 4
Output: 1
Explanation: In 1 move, you can change nums to [1,2,2,3] (underlined elements are changed).
nums[0] + nums[3] = 1 + 3 = 4.
nums[1] + nums[2] = 2 + 2 = 4.
nums[2] + nums[1] = 2 + 2 = 4.
nums[3] + nums[0] = 3 + 1 = 4.
Therefore, nums[i] + nums[n-1-i] = 4 for every i, so nums is complementary.

Example 2:

Input: nums = [1,2,2,1], limit = 2
Output: 2
Explanation: In 2 moves, you can change nums to [2,2,2,2]. You cannot change any number to 3 since 3 > limit.
Example 3:

Input: nums = [1,2,1,2], limit = 2
Output: 0
Explanation: nums is already complementary.
 * 
 * 
 * Time Complexity: O(n + limit), n = length of the array, limit = maximum possible value in the array
 * Space Complexity: O(n), sum1 map -> up[ to O(n) entries, diff map -> up to O(n) entries.
 */

public class MinMoves {
	
	public int minMoves(int[] nums, int limit) {
		Map<Integer, Integer> sum1 = new HashMap<>();
		Map<Integer, Integer> diff = new HashMap<>();
		
		int n = nums.length;
		
		for(int i = 0; i < n / 2; i++) {
			int a = nums[i];
			int b = nums[n - i - 1];
			
			int min = Math.min(a, b);
			int max = Math.max(a, b);
			
			int left = min + 1;
			int right = max + limit;
			diff.put(left, diff.getOrDefault(left, 0) - 1);
			diff.put(right + 1, diff.getOrDefault(right + 1, 0) + 1);
			
			int sum = a + b;
			sum1.put(sum,  sum1.getOrDefault(sum1, 0) + 1);
		}
		
		int ans = Integer.MAX_VALUE;
		int cur = n;
		
		int maxSum = 0;
		for( int key : sum1.keySet()) {
			maxSum = Math.max(maxSum, key);
		}
		
		for(int s = 2; s <= maxSum; s++) {
			cur += diff.getOrDefault(s, 0);
			ans =  Math.min(ans, cur - sum1.getOrDefault(s,0));
		}
		
		return ans;
	}
	
	 public static void main(String[] args) {

	        MinMoves solution = new MinMoves();

	        int[] nums1 = {1, 2, 4, 3};
	        int limit1 = 4;

	        int result1 = solution.minMoves(nums1, limit1);

	        System.out.println("Minimum moves: " + result1);

	        int[] nums2 = {1, 2, 2, 1};
	        int limit2 = 2;

	        int result2 = solution.minMoves(nums2, limit2);

	        System.out.println("Minimum moves: " + result2);
	 }

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
}

