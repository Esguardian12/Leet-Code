package GcdValues;

import java.util.Arrays;

/* 3312. Sorted GCD Pair Queries
You are given an integer array nums of length n and an integer array queries.

Let gcdPairs denote an array obtained by calculating the GCD of all possible pairs (nums[i], nums[j]), where 0 <= i < j < n, and then sorting these values in ascending order.

For each query queries[i], you need to find the element at index queries[i] in gcdPairs.

Return an integer array answer, where answer[i] is the value at gcdPairs[queries[i]] for each query.

The term gcd(a, b) denotes the greatest common divisor of a and b.
 

Example 1:

Input: nums = [2,3,4], queries = [0,2,2]

Output: [1,2,2]

Explanation:

gcdPairs = [gcd(nums[0], nums[1]), gcd(nums[0], nums[2]), gcd(nums[1], nums[2])] = [1, 2, 1].

After sorting in ascending order, gcdPairs = [1, 1, 2].

So, the answer is [gcdPairs[queries[0]], gcdPairs[queries[1]], gcdPairs[queries[2]]] = [1, 2, 2].

Example 2:

Input: nums = [4,4,2,1], queries = [5,3,1,0]

Output: [4,2,1,1]

Explanation:

gcdPairs sorted in ascending order is [1, 1, 1, 2, 2, 4].

Example 3:

Input: nums = [2,2], queries = [0,0]

Output: [2,2]

Explanation:

gcdPairs = [2].

 

Constraints:

2 <= n == nums.length <= 105
1 <= nums[i] <= 5 * 104
1 <= queries.length <= 105
0 <= queries[i] < n * (n - 1) / 2
 * 
 * Time Complexity: O(N + M log M + Q log M), Finding the maximum value and populating the frequency array takes $O(N)$ time.
 * 			The nested loops for calculating exactGcd iterate through multiples. The total number of iterations is $M/1 + M/2 + M/3 + \dots + 1$, 
 * 			which forms a Harmonic Series. This mathematically evaluates to $O(M \log M)$ operations.Building the prefix sum takes $O(M)$ time.
 * 			For each of the $Q$ queries, we perform a binary search over the range 1 to M, which takes $O(\log M)$ time per query, totaling $O(Q \log M)$.
 * 			Summing these up gives the final overall time complexity.
 * Space Complexity: O(M + Q), We allocate three arrays of size $M + 1$: freq, exactGcd, and prefix. This takes $O(M)$ auxiliary space.
 * 			We allocate an output array ans of size $Q$ to store the answers to our queries.Therefore, the total space scales with the maximum value 
 * 			in the array and the number of queries, not the length of nums.
 */

public class GcdValues {
	
	public int[] gcdValues(int[] nums, long[] queries) {
		int maxVal = 0;
		for(int num : nums) {
			if(num > maxVal) {
				maxVal = num;
			}
		}
		
		// 1. Frequency map of the numbers in nums
		int[] freq = new int[maxVal + 1];
		for(int num : nums) {
			freq[num]++;
		}
		
		// exactGcd[i] will store the number of pairs whose GCD is exactly i
		long[] exactGcd = new long[maxVal + 1];
		
		// 2. Calculate exact GCD counts going backwards(Inclusion-Exclusion)
		for(int i = maxVal; i >= 1; i--) {
			int multiplesCount = 0;
			// Count all elements in nums that are multiples of i
			for(int j = i; j <= maxVal; j += i) {
				multiplesCount += freq[j];
			}
			
			// Total possible pairs where both elements are multiple of i
			long pairs = (long) multiplesCount * (multiplesCount - 1) / 2;
			
			// Subtract pairs where the GCD is larger multiple of i (e.g., 2i, 3i ...)
			for(int j = 2 * i; j <= maxVal; j += i) {
				pairs -= exactGcd[j];
			}
			
			exactGcd[i] = pairs;
		}
		
		// 3. Create a prefix sum array to answer positional queries
		long[] prefix = new long[maxVal + 1];
		for(int i = 1; i <= maxVal; i++) {
			prefix[i] = prefix[i - 1] + exactGcd[i];
		}
		
		// 4. Answer each query using Binary Search
		int qLen = queries.length;
		int[] ans = new int[qLen];
		
		for(int i = 0; i < qLen; i++) {
			long q = queries[i];
			int low = 1;
			int high = maxVal;
			int res = 1;
			
			// Find the smallest index 'mid' where prefix[mid] > q
			while(low <= high) {
				int mid = low + (high - low) / 2;
				if(prefix[mid] > q) {
					res = mid;
					high = mid - 1; // Try to find a smaller valid index
				} else {
					low = mid + 1;
				}
			}
			ans[i] = res;
		}
		
		return ans;
	}

	public static void main(String[] args) {
        GcdValues solver = new GcdValues();

        // Test Case 1
        int[] nums1 = {2, 3, 4};
        long[] queries1 = {0, 2, 2};
        System.out.println("Test Case 1 Output: " + Arrays.toString(solver.gcdValues(nums1, queries1)));
        // Expected Output: [1, 2, 2]

        // Test Case 2
        int[] nums2 = {4, 4, 2, 1};
        long[] queries2 = {5, 3, 1, 0};
        System.out.println("Test Case 2 Output: " + Arrays.toString(solver.gcdValues(nums2, queries2)));
        // Expected Output: [4, 2, 1, 1]

        // Test Case 3
        int[] nums3 = {2, 2};
        long[] queries3 = {0, 0};
        System.out.println("Test Case 3 Output: " + Arrays.toString(solver.gcdValues(nums3, queries3)));
        // Expected Output: [2, 2]
    }
}

