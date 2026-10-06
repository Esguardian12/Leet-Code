package GcdOfFormedPairs;

import java.util.Arrays;

/* 3658. GCD of Odd and Even Sums
You are given an integer n. Your task is to compute the GCD (greatest common divisor) of two values:

sumOdd: the sum of the smallest n positive odd numbers.

sumEven: the sum of the smallest n positive even numbers.

Return the GCD of sumOdd and sumEven.


Example 1:

Input: n = 4

Output: 4

Explanation:

Sum of the first 4 odd numbers sumOdd = 1 + 3 + 5 + 7 = 16
Sum of the first 4 even numbers sumEven = 2 + 4 + 6 + 8 = 20
Hence, GCD(sumOdd, sumEven) = GCD(16, 20) = 4.

Example 2:

Input: n = 5

Output: 5

Explanation:

Sum of the first 5 odd numbers sumOdd = 1 + 3 + 5 + 7 + 9 = 25
Sum of the first 5 even numbers sumEven = 2 + 4 + 6 + 8 + 10 = 30
Hence, GCD(sumOdd, sumEven) = GCD(25, 30) = 5.

 

Constraints:

1 <= n <= 10​​​​​​​00
 * 
 * Time Complexity: O(N log N + N log M), Calculating the prefixGcd array requires $N$ iterations. Inside the loop, the Euclidean algorithm 
 * 			for GCD takes $O(\log M)$ time, resulting in $O(N \log M)$ for the first step. Sorting the array takes $O(N \log N)$ time. Finally, 
 * 			the two-pointer pairing step processes $N/2$ pairs, doing another GCD calculation each time, taking an additional $O(N \log M)$ time.
 * Space Complexity:O(N), We allocate a new integer array prefixGcd of size $N$ to store the calculated GCDs. The sorting algorithm 
 * 			(Java uses a Dual-Pivot Quicksort for primitives) also takes $O(\log N)$ auxiliary space on the call stack. Overall, the space complexity 
 * 			scales linearly with the input size.
 */

public class GcdOfFormedPairs {

	public long gcdSum(int[] nums) {
		int n = nums.length;
		int[] prefixGcd = new int[n];
		int currentMax = 0;
		
		// 1. Construct prefixedGcd Array
		for(int i = 0; i < n; i++) {
			currentMax =  Math.max(currentMax, nums[i]);
			prefixGcd[i] = gcd(nums[i], currentMax);
		}
		
		// 2. Sort the array
		Arrays.sort(prefixGcd);
		
		// 3. Form pairs (smallest and largest) and sum their GCDs
		long sum = 0;
		int left = 0;
		int right = n - 1;
		
		// The condition left < right natively ignores the middle element if n is odd
		while(left < right) {
			sum += gcd(prefixGcd[left], prefixGcd[right]);
			left++;
			right--;
		}
		
		return sum;
	}
	
	// Helper method to compute the Greatest Common Divisor using the Euclidean algorithm
	private int gcd(int a, int b) {
		while(b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
	
	public static void main(String[] args) {
        GcdOfFormedPairs solver = new GcdOfFormedPairs();

        // Test Case 1
        int[] nums1 = {2, 6, 4};
        System.out.println("Test Case 1 Output: " + solver.gcdSum(nums1));
        // Expected Output: 2

        // Test Case 2
        int[] nums2 = {3, 6, 2, 8};
        System.out.println("Test Case 2 Output: " + solver.gcdSum(nums2));
        // Expected Output: 5
    }
}

