package FindTheDistanceValue;

import java.util.Arrays;

/* 1385. Find the Distance Value Between Two Arrays
Given two integer arrays arr1 and arr2, and the integer d, return the distance value between the two arrays.

The distance value is defined as the number of elements arr1[i] such that there is not any element arr2[j] where |arr1[i]-arr2[j]| <= d.

 

Example 1:

Input: arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
Output: 2
Explanation: 
For arr1[0]=4 we have: 
|4-10|=6 > d=2 
|4-9|=5 > d=2 
|4-1|=3 > d=2 
|4-8|=4 > d=2 
For arr1[1]=5 we have: 
|5-10|=5 > d=2 
|5-9|=4 > d=2 
|5-1|=4 > d=2 
|5-8|=3 > d=2
For arr1[2]=8 we have:
|8-10|=2 <= d=2
|8-9|=1 <= d=2
|8-1|=7 > d=2
|8-8|=0 <= d=2
Example 2:

Input: arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
Output: 2
Example 3:

Input: arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
Output: 1
 

Constraints:

1 <= arr1.length, arr2.length <= 500
-1000 <= arr1[i], arr2[j] <= 1000
0 <= d <= 100
 * 
 * Time Complexity: O(M log M + N log M), • Sorting: Sorting arr2 takes \(\mathcal{O}(M \log M)\) time.
		• Binary Search Loop: We iterate through all \(N\) elements of arr1. For each element, your isValidDistance helper performs a binary search on arr2, 
		which takes \(\mathcal{O}(\log M)\) time. Thus, the loop execution requires \(\mathcal{O}(N \log M)\) operations.
		• Overall: Combining both stages results in \(\mathcal{O}(M \log M + N \log M)\), which can also be written as \(\mathcal{O}((M + N) \log M)\). Given 
			the constraints (\(N, M \le 500\)), this will run nearly instantaneously.
 * Space Complexity: O(log M) or O(1), • Pointers: The binary search logic uses primitive variable trackers (left, right, mid), which consume \(\mathcal{O}(1)\) 
		  extra storage.
		• Sorting Overhead: In Java, Arrays.sort() for primitive types utilizes a Dual-Pivot Quicksort algorithm, which introduces an internal call-stack space 
		  usage of \(\mathcal{O}(\log M)\). No additional heap arrays are cloned.
 */

public class FindTheDistanceValue {

	public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
		Arrays.sort(arr2);
		int distanceCount = 0;
		
		for (int x : arr1) {
			if (isValidDistance(arr2, x, d)) {
				distanceCount++;
			}
		}
		
		return distanceCount;
	}
	
	private boolean isValidDistance(int[] arr2, int x, int d) {
		int left = 0;
		int right = arr2.length - 1;
		
		while (left <= right) {
			int mid = left + (right - left) / 2;
			
			// If absolute difference is within d, then it violates the condition
			if (Math.abs(arr2[mid] - x) <= d) {
				return false;
			}
			
			if (arr2[mid] < x) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
        FindTheDistanceValue solver = new FindTheDistanceValue();

        // Example 1
        int[] arr1_ex1 = {4, 5, 8};
        int[] arr2_ex1 = {10, 9, 1, 8};
        int d_ex1 = 2;
        System.out.println("Example 1 Output: " + solver.findTheDistanceValue(arr1_ex1, arr2_ex1, d_ex1)); // Expected: 2

        // Example 2
        int[] arr1_ex2 = {1, 4, 2, 3};
        int[] arr2_ex2 = {-4, -3, 6, 10, 20, 30};
        int d_ex2 = 3;
        System.out.println("Example 2 Output: " + solver.findTheDistanceValue(arr1_ex2, arr2_ex2, d_ex2)); // Expected: 2

        // Example 3
        int[] arr1_ex3 = {2, 1, 100, 3};
        int[] arr2_ex3 = {-5, -2, 10, -3, 7};
        int d_ex3 = 6;
        System.out.println("Example 3 Output: " + solver.findTheDistanceValue(arr1_ex3, arr2_ex3, d_ex3)); // Expected: 1
    }
	
}

