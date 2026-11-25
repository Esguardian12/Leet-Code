package SubsequencePairCount;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*3336. Find the Number of Subsequences With Equal GCD
You are given an integer array nums.

Your task is to find the number of pairs of non-empty subsequences (seq1, seq2) of nums that satisfy the following conditions:

The subsequences seq1 and seq2 are disjoint, meaning no index of nums is common between them.
The GCD of the elements of seq1 is equal to the GCD of the elements of seq2.
Return the total number of such pairs.

Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: nums = [1,2,3,4]

Output: 10

Explanation:

The subsequence pairs which have the GCD of their elements equal to 1 are:

([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
Example 2:

Input: nums = [10,20,30]

Output: 2

Explanation:

The subsequence pairs which have the GCD of their elements equal to 10 are:

([10, 20, 30], [10, 20, 30])
([10, 20, 30], [10, 20, 30])
Example 3:

Input: nums = [1,1,1,1]

Output: 50

 

Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 200
 * 
 * Time Complexity: O(N log N), Cloning the array takes $O(N)$ time. Sorting the cloned array is the bottleneck, taking $O(N \log N)$ time. 
 * 			Iterating through the sorted array to populate the Hash Map takes $O(N)$ time, and iterating through the original array to fetch ranks 
 * 			also takes $O(N)$ time (assuming $O(1)$ average time for Hash Map lookups). The sorting dominates the overall time complexity.
 * Space Complexity: O(N), We allocate $O(N)$ extra space to store the cloned array sortedArr. Additionally, the Hash Map rankMap will store up to $N$ 
 * 			unique key-value pairs, which also takes $O(N)$ space. Finally, the returned result array takes $O(N)$ space.
 */

public class SubsequencePairCount {
	
	public int[] arrayRankTransform(int[] arr) {
		// 1. Create a copy of the array and sort it
		int[] sortedArr = arr.clone();
		Arrays.sort(sortedArr);
		
		// 2. Map to store the rank of each unique element
		Map<Integer, Integer> rankMap = new HashMap<>();
		int rank = 1;
		
		for(int num : sortedArr) {
			// Only assign a rank if we haven't seen this number before
            // This natively handles the "same elements share the same rank" rule
			if(!rankMap.containsKey(num)) {
				rankMap.put(num, rank);
				rank++;
			}
		}
		
		// 3. Build the result array using the precomputed ranks
		int[] result = new int[arr.length];
		for(int i = 0; i < arr.length; i++) {
			result[i] = rankMap.get(arr[i]);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
        RankTransform solver = new RankTransform();

        // Test Case 1
        int[] arr1 = {40, 10, 20, 30};
        System.out.println("Test Case 1 Output: " + Arrays.toString(solver.arrayRankTransform(arr1)));
        // Expected: [4, 1, 2, 3]

        // Test Case 2
        int[] arr2 = {100, 100, 100};
        System.out.println("Test Case 2 Output: " + Arrays.toString(solver.arrayRankTransform(arr2)));
        // Expected: [1, 1, 1]

        // Test Case 3
        int[] arr3 = {37, 12, 28, 9, 100, 56, 80, 5, 12};
        System.out.println("Test Case 3 Output: " + Arrays.toString(solver.arrayRankTransform(arr3)));
        // Expected: [5, 3, 4, 2, 8, 6, 7, 1, 3]
    }

}

