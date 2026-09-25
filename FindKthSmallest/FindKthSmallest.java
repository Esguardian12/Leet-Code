package FindKthSmallest;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 3116. Kth Smallest Amount With Single Denomination Combination
You are given an integer array coins representing coins of different denominations and an integer k.

You have an infinite number of coins of each denomination. However, you are not allowed to combine coins of different denominations.

Return the kth smallest amount that can be made using these coins.



Example 1:

Input: coins = [3,6,9], k = 3

Output: 9

Explanation: The given coins can make the following amounts:
Coin 3 produces multiples of 3: 3, 6, 9, 12, 15, etc.
Coin 6 produces multiples of 6: 6, 12, 18, 24, etc.
Coin 9 produces multiples of 9: 9, 18, 27, 36, etc.
All of the coins combined produce: 3, 6, 9, 12, 15, etc.

Example 2:

Input: coins = [5,2], k = 7

Output: 12

Explanation: The given coins can make the following amounts:
Coin 5 produces multiples of 5: 5, 10, 15, 20, etc.
Coin 2 produces multiples of 2: 2, 4, 6, 8, 10, 12, etc.
All of the coins combined produce: 2, 4, 5, 6, 8, 10, 12, 14, 15, etc.

 

Constraints:

1 <= coins.length <= 15
1 <= coins[i] <= 25
1 <= k <= 2 * 109
coins contains pairwise distinct integers.
 * 
 * Time Complexity: O(2² * log(k * min_coin)), Where N is the number of filtered elements from coins (at most 15).Precomputing the LCM for all combinations takes 
 * 			O(N * 2ⁿ) time. The Binary Search runs for = log₂(5 * 10¹⁰) iterations (at most 36 times). Within each search step, 
 * 			we iterate through the 2ⁿ - 1 precomputed subsets. The overall operations comfortably peak below $1.5 \times 10^6$, which processes near-instantaneously 
 * 			in Java (less than 10 milliseconds).
 * Space Complexity:O(2ⁿ), The size of the arrays addLcm and subLcm is bounded by 2ⁿ For N ≤ 15, these arrays require roughly 32,767 slots for long primitive 
 * 			values in memory, which easily fits inside = 256 KB of contiguous auxiliary space.
 */

public class FindKthSmallest {
	
	public long findKthSmallest(int[] coins, int k) {
		// Step 1: Sort and filter out redundant coins
        // (Any coin that is a multiple of a smaller coin is redundant)
		Arrays.sort(coins);
		List<Integer> filtered = new ArrayList<>();
		for(int c : coins) {
			boolean isMultiple = false ;
			for(int f : filtered) {
				if(c % f == 0) {
					isMultiple = true;
					break;
				}
			}
			if(!isMultiple) {
				filtered.add(c);
			}
		}
		
		int n = filtered.size();
		
		// Step 2: Precompute LCMs for all subsets using PIE
        // oddSize handles + configurations, evenSize handles - configurations
		int oddSize = 1 << (n - 1);
		int evenSize = (1 << n) - 1 - oddSize;
		
		long[] addLcm = new long[oddSize];
		long[] subLcm = new long[evenSize];
		int addIdx = 0, subIdx = 0;
		
		// Loop through all 2^N - 1 subsets
		for(int mask = 1; mask < (1 << n); mask++) {
			long currentLcm = 1;
			for(int i = 0; i < n; i++) {
				if((mask & (1 << i)) != 0) {
					currentLcm = lcm(currentLcm, filtered.get(i));
				}
			}
			
			// If the number of selected coins is odd, it's an addition in PIE
			if(Integer.bitCount(mask) % 2 == 1) {
				addLcm[addIdx++] = currentLcm;
			} else {
				subLcm[subIdx++] = currentLcm;
			}
		}
		
		// Step 3: Binary search for the k-th smallest amount
		long low = 1;
		long high = (long) k * filtered.get(0);
		long ans = high;
		
		while(low <= high) {
			long mid = low + (high - low) / 2;
			long count = 0;
			
			for(long l : addLcm) {
				count += mid / l;
			}
			for(long l : subLcm) {
				count -= mid / l;
			}
			
			// If the total multiples generated is at least k, 
            // the target is mid or something smaller
			if(count >= k) {
				ans = mid;
				high = mid - 1;
			} else {
				 low = mid + 1;
			}
		}
		
		return ans;
	}
	
	// Helper method to find the Greatest Common Divisor
	private long gcd(long a, long b) {
		while(b != 0) {
			long temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
	
	// Helper method to find the Least Common Multiple
	private long lcm(long a, long b) {
		return (a / gcd(a, b)) * b;
	}
	
	public static void main(String[] args) {
        FindKthSmallest sol = new FindKthSmallest();

        // Example 1
        int[] coins1 = {3, 6, 9};
        int k1 = 3;
        System.out.println("Input: coins = " + Arrays.toString(coins1) + ", k = " + k1);
        System.out.println("Output: " + sol.findKthSmallest(coins1, k1)); // Expected: 9
        System.out.println("-----------------------------------");

        // Example 2
        int[] coins2 = {5, 2};
        int k2 = 7;
        System.out.println("Input: coins = " + Arrays.toString(coins2) + ", k = " + k2);
        System.out.println("Output: " + sol.findKthSmallest(coins2, k2)); // Expected: 12
    }

}
