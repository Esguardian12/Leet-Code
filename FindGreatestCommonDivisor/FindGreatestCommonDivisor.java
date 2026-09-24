package FindGreatestCommonDivisor;


/* 1979. Find Greatest Common Divisor of Array
Given an integer array nums, return the greatest common divisor of the smallest number and largest number in nums.

The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.

Example 1:

Input: nums = [2,5,6,9,10]
Output: 2
Explanation:
The smallest number in nums is 2.
The largest number in nums is 10.
The greatest common divisor of 2 and 10 is 2.
Example 2:

Input: nums = [7,5,6,8,3]
Output: 1
Explanation:
The smallest number in nums is 3.
The largest number in nums is 8.
The greatest common divisor of 3 and 8 is 1.
Example 3:

Input: nums = [3,3]
Output: 3
Explanation:
The smallest number in nums is 3.
The largest number in nums is 3.
The greatest common divisor of 3 and 3 is 3.
 

Constraints:

2 <= nums.length <= 1000
1 <= nums[i] <= 1000
 *
 * Time Complexity: O(N), Finding the minimum and maximum values requires a single pass through the array, which takes $O(N)$ time. 
 * 			The Euclidean algorithm for calculating the GCD takes logarithmic time relative to the value of the numbers, specifically $O(\log M)$. 
 * 			Since the maximum possible value in the array is 1000 (a small constant), the GCD calculation takes negligible time $O(1)$. 
 * 			Therefore, the overall time complexity is strictly linear, $O(N)$.
 * Space Complexity: O(1), We only allocate a few integer variables (min, max, a, b, temp) regardless of the size of the input array. 
 * 			This requires strictly constant auxiliary space.
 */

public class FindGreatestCommonDivisor {
	
	public int findGCD(int[] nums) {
		int min = nums[0];
		int max = nums[0];
		
		// 1. Find the smallest and largest numbers in a single pass
		for(int i = 1; i < nums.length; i++) {
			if(nums[i] < min) {
				min = nums[i];
			}
			if(nums[i] > max) {
				max = nums[i];
			}
		}
		
		// 2. Return their Greatest Common Divisor
		return gcd(min, max);
	}
	
	// Helper method to compute GCD using the Euclidean algorithm
	private int gcd(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
	
	public static void main(String[] args) {
        FindGreatestCommonDivisor solver = new FindGreatestCommonDivisor();

        // Test Case 1
        int[] nums1 = {2, 5, 6, 9, 10};
        System.out.println("Test Case 1 Output: " + solver.findGCD(nums1));
        // Expected Output: 2

        // Test Case 2
        int[] nums2 = {7, 5, 6, 8, 3};
        System.out.println("Test Case 2 Output: " + solver.findGCD(nums2));
        // Expected Output: 1

        // Test Case 3
        int[] nums3 = {3, 3};
        System.out.println("Test Case 3 Output: " + solver.findGCD(nums3));
        // Expected Output: 3
    }

}
