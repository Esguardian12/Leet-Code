package TrimMean;

import java.util.Arrays;

/* 1619. Mean of Array After Removing Some Elements
 * Given an integer array arr, return the mean of the remaining integers after removing the smallest 5% and the largest 5% of the elements.
 * Answers within 10-5 of the actual answer will be considered accepted.

Example 1:

Input: arr = [1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3]
Output: 2.00000
Explanation: After erasing the minimum and the maximum values of this array, all elements are equal to 2, so the mean is 2.
Example 2:

Input: arr = [6,2,7,5,1,2,0,3,10,2,5,0,5,5,0,8,7,6,8,0]
Output: 4.00000
Example 3:

Input: arr = [6,0,7,0,7,5,7,8,3,4,0,7,8,1,6,8,1,1,2,4,8,1,9,5,4,3,8,5,10,8,6,6,1,0,6,10,8,2,3,4]
Output: 4.77778
 

Constraints:

20 <= arr.length <= 1000
arr.length is a multiple of 20.
0 <= arr[i] <= 105
 * 
 * Time Complexity: O(n log n), There are two main operations: Sorting the array For primitive int[], Java uses Dual-Pivot Quicksort.
 * 			Average and worst-case time complexity: O(n log n), Summing the remaining elements Traverses about 90% of the array.
 * Space Complexity: O(log n), int len, start, end; double limit, sum, new_length, avg; These use a constant amount of extra memory.
 * 			For Arrays.sort(int[]): Dual-Pivot Quicksort is in-place. Uses only a small recursion stack. Therefore: Space Complexity: 
 * 			O(log n) (due to recursion stack), If recursion stack space is ignored (as some interviewers do for in-place sorting), you may see it described as:
 * 			
 */

public class TrimMean {
	
	public double trimMean(int[] arr) {
		int len = arr.length;
		Arrays.sort(arr);
		double limit = 0.05 * len;
		int start = (int) limit;
		int end = len - (int) limit;
		double sum = 0, new_length = 0;
		for (int i = start; i < end; i++) {
			sum = sum + arr[i];
			new_length++;
		}
		double avg = sum / new_length;
		return avg;
	}

	   public static void main(String[] args) {
	        TrimMean tm = new TrimMean();

	        // Test 1: LeetCode example — 20 elements
	        // 5% of 20 = 1 → trim 1 from each end
	        // Sorted: [1,2,2,2,3,3,3,4,4,4,4,5,5,5,6,6,7,8,8,9]
	        // Trimmed: indices 1..18 → remove 1 and 9
	        // Sum of middle 18 = (2+2+2+3+3+3+4+4+4+4+5+5+5+6+6+7+8+8) = 81
	        // avg = 81 / 18 = 4.5
	        int[] arr1 = {1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 7, 8, 8, 9};
	        System.out.printf("%.5f%n", tm.trimMean(arr1)); // Expected: 4.50000

	        // Test 2: LeetCode example — 20 elements
	        // 5% of 20 = 1 → trim 1 from each end
	        // Sorted: [1,3,5,5,5,6,6,7,7,7,7,8,8,8,9,9,9,10,10,10]
	        // Trimmed: indices 1..18 → remove 1 and last 10
	        // Sum = 3+5+5+5+6+6+7+7+7+7+8+8+8+9+9+9+10+10 = 134
	        // avg = 134 / 18 = 7.44444...
	        int[] arr2 = {6, 2, 7, 5, 1, 2, 0, 3, 10, 2, 5, 0, 5, 5, 0, 8, 7, 6, 8, 0};
	        System.out.printf("%.5f%n", tm.trimMean(arr2)); // Expected: 4.00000

	        // Test 3: All same values — trimming doesn't change the mean
	        // 5% of 20 = 1 → trim 1 from each end
	        // All values are 5, so avg = 5.0
	        int[] arr3 = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
	        System.out.printf("%.5f%n", tm.trimMean(arr3)); // Expected: 5.00000

	        // Test 4: 40 elements → 5% of 40 = 2 → trim 2 from each end
	        // Sorted: [1,1, 2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2, 3,3]
	        // Trim indices 0-1 and 38-39 → 36 twos remain
	        // avg = (36 * 2) / 36 = 2.0
	        int[] arr4 = new int[40];
	        arr4[0] = 1; arr4[1] = 1;
	        arr4[38] = 3; arr4[39] = 3;
	        for (int i = 2; i < 38; i++) arr4[i] = 2;
	        System.out.printf("%.5f%n", tm.trimMean(arr4)); // Expected: 2.00000
	    }
}
