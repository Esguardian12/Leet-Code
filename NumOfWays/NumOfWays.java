package NumOfWays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/* 1569. Number of Ways to Reorder Array to Get Same BST
Given an array nums that represents a permutation of integers from 1 to n. We are going to construct a binary search tree (BST) by inserting the elements of nums in order into an initially empty BST. Find the number of different ways to reorder nums so that the constructed BST is identical to that formed from the original array nums.

For example, given nums = [2,1,3], we will have 2 as the root, 1 as a left child, and 3 as a right child. The array [2,3,1] also yields the same BST but [3,2,1] yields a different BST.
Return the number of ways to reorder nums such that the BST formed is identical to the original BST formed from nums.

Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:


Input: nums = [2,1,3]
Output: 1
Explanation: We can reorder nums to be [2,3,1] which will yield the same BST. There are no other ways to reorder nums which will yield the same BST.
Example 2:


Input: nums = [3,4,5,1,2]
Output: 5
Explanation: The following 5 arrays will yield the same BST: 
[3,1,2,4,5]
[3,1,4,2,5]
[3,1,4,5,2]
[3,4,1,2,5]
[3,4,1,5,2]
Example 3:


Input: nums = [1,2,3]
Output: 0
Explanation: There are no other orderings of nums that will yield the same BST.
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= nums.length
All integers in nums are distinct.
 * 
 * Time Complexity: 
 * Space Complexity:
 */

public class NumOfWays {
	
	private static final int MOD = 1_000_000_007;
	private long[][] combinations;
	
	public int numOfWays(int[] nums) {
		int n = nums.length;
		
		// Precompute combinations using Pascal's triangle
		combinations = new long[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			combinations[i][0] = 1;
			for (int j = 1; j <= i; j++) {
				combinations[i][j] = (combinations[i - 1][j - 1] + combinations[i - 1][j]) % MOD;
			}
		}
		
		List<Integer> list = new ArrayList<>();
		for (int num : nums) {
			list.add(num);
		}
		
		// Subtract 1 because we want alternative reorderings, excluding the original array itself
		return (int) ((dfs(list) - 1 + MOD) % MOD);
	}
	
	private long dfs(List<Integer> nums) {
		 if (nums.size() <= 2) {
			 return 1;
		 }
		 
		 int root = nums.get(0);
		 List<Integer> left = new ArrayList<>();
		 List<Integer> right = new ArrayList<>();
		 
		 for (int i = 1; i < nums.size(); i++) {
			 int val = nums.get(i);
			 if (val < root) {
				 left.add(val);
			 } else {
				 right.add(val);
			 }
		 }
		 
		 long leftWays = dfs(left);
		 long rightWays = dfs(right);
		 
		 int leftSize = left.size();
		 int totalSize = nums.size() - 1;
		 
		 // number of ways to interleave left and right elements is C(totalSize, leftSize)
		 long ways = combinations[totalSize][leftSize];
		 
		 ways = (ways * leftWays) % MOD;
		 ways = (ways * rightWays) % MOD;
		 
		 return ways;
	}
	
	public static void main(String[] args) {
        NumOfWays sol = new NumOfWays();

        // Example 1
        int[] nums1 = {2, 1, 3};
        System.out.println("Input: nums = " + Arrays.toString(nums1));
        System.out.println("Output: " + sol.numOfWays(nums1)); 
        // Expected: 1
        System.out.println("-----------------------------------");

        // Example 2
        int[] nums2 = {3, 4, 5, 1, 2};
        System.out.println("Input: nums = " + Arrays.toString(nums2));
        System.out.println("Output: " + sol.numOfWays(nums2)); 
        // Expected: 5
        System.out.println("-----------------------------------");

        // Example 3
        int[] nums3 = {1, 2, 3};
        System.out.println("Input: nums = " + nums3);
        System.out.println("Output: " + sol.numOfWays(nums3)); 
        // Expected: 0
    }

}
