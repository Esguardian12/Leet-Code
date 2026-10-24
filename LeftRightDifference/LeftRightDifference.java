package LeftRightDifference;

package leetCode;

/* 2574. Left and Right Sum Differences
 * You are given a 0-indexed integer array nums of size n.
 * Define two arrays leftSum and rightSum where:

leftSum[i] is the sum of elements to the left of the index i in the array nums. If there is no such element, leftSum[i] = 0.
rightSum[i] is the sum of elements to the right of the index i in the array nums. If there is no such element, rightSum[i] = 0.
Return an integer array answer of size n where answer[i] = |leftSum[i] - rightSum[i]|.

Example 1:

Input: nums = [10,4,8,3]
Output: [15,1,11,22]
Explanation: The array leftSum is [0,10,14,22] and the array rightSum is [15,11,3,0].
The array answer is [|0 - 15|,|10 - 11|,|14 - 3|,|22 - 0|] = [15,1,11,22].
Example 2:

Input: nums = [1]
Output: [0]
Explanation: The array leftSum is [0] and the array rightSum is [0].
The array answer is [|0 - 0|] = [0].
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 105
 * 
 * Time Complexity: O(n²), Both the leftSum and rightSum loops run n iterations, and each iteration calls sumOfArray(),
 * 			which itself loops up to n times. That gives n × n work across both loops, landing at O(n²) overall.
 * Space Complexity: O(n), Three auxiliary arrays are allocated (leftSum, rightSum, answer), each of size n, so space is
 * 			O(n). The input array is not counted.
 */

public class LeftRightDifference {
	
//	public int[] leftRightDifference(int[] nums) {
//		int leftSum[] = new int[nums.length];
//		int rightSum[] = new int[nums.length];
//		leftSum[0] = 0;
//		rightSum[nums.length - 1] = 0;
//		int l_index = 1, r_index = 0;
//		for (int i = 1; i < nums.length; i++) {
//			leftSum[l_index++] = sumOfArray(nums, 0 , i - 1);
//		}
//		for (int i = 0; i < nums.length; i++) {
//			rightSum[r_index++] = sumOfArray(nums, i + 1, nums.length - 1);
//		}
//		int answer[] = new int [nums.length];
//		for (int i = 0; i < answer.length; i++) {
//			answer[i] = Math.abs(rightSum[i] - leftSum[i]);
//		}
//		return answer;
//	}
//	
//	private static int sumOfArray(int nums[], int start, int end) {
//		int sum = 0;
//		for (int i = start; i <= end; i++) {
//			sum += nums[i];
//		}
//		return sum;
//	}
	
	/* Quick Optimization Note
	 * You can drop this to O(n) time by computing prefix sums in a single pass rather than calling
	 * sumOfArray() repeatedly on every iteration — that removes the inner loop entirely. Let me
	 * know if you'd like to see that version!
	 */
	
	public int[] leftRightDifference(int[] nums) {
		int n = nums.length;
		int[] leftSum = new int[n];
		int[] rightSum = new int[n];
		
		// Build leftSum in one pass
		// leftSum[0] = 0 by default
		for (int i = 1; i < n; i++) {
			leftSum[i] = leftSum[i - 1] + nums[i - 1];
		}
		
		// Build rightSum in one pass
		// rightSum[n-1] = 0 by default
		for (int i = n - 2; i >= 0; i--) {
			rightSum[i] = rightSum[i + 1] + nums[i + 1];
		}
		
		int[] answer = new int[n];
		for (int i = 0; i < n; i++) {
			answer[i] = Math.abs(rightSum[i] - leftSum[i]);
		}
		return answer;
  		
	}
	
	/* What Changed -> Optimized Version
	 * 		leftSum[i - 1] + nums[i-1] -> O(1)
	 * 		rightSum[i + 1] + nums[i + 1] -> O(1)
	 * Time:  O(n)
	 * Space: O(n)
	 * 
	 * Each sum is now just one addition using the already-computed neighbor, so every loop runs in
	 * a single clean O(n) pass.
	 * 
	 */

	  public static void main(String[] args) {
	        LeftRightDifference sol = new LeftRightDifference();

	        // Test 1: [10, 4, 8, 3]
	        // leftSum  = [0, 10, 14, 22]
	        // rightSum = [15, 11,  3,  0]
	        // answer   = |15-0|, |11-10|, |3-14|, |0-22| = [15, 1, 11, 22]
	        int[] t1 = sol.leftRightDifference(new int[]{10, 4, 8, 3});
	        System.out.println("Test 1: " + java.util.Arrays.toString(t1));
	        System.out.println("Expected: [15, 1, 11, 22]");

	        // Test 2: [1]
	        // leftSum  = [0]
	        // rightSum = [0]
	        // answer   = [0]
	        int[] t2 = sol.leftRightDifference(new int[]{1});
	        System.out.println("\nTest 2: " + java.util.Arrays.toString(t2));
	        System.out.println("Expected: [0]");

	        // Test 3: [1, 2, 3, 4, 5]
	        // leftSum  = [0, 1, 3, 6, 10]
	        // rightSum = [14, 12, 9, 5, 0]
	        // answer   = [14, 11, 6, 1, 10]
	        int[] t3 = sol.leftRightDifference(new int[]{1, 2, 3, 4, 5});
	        System.out.println("\nTest 3: " + java.util.Arrays.toString(t3));
	        System.out.println("Expected: [14, 11, 6, 1, 10]");
	    }
}

