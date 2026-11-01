package NumberOfSets;

/* 1621. Number of Sets of K Non-Overlapping Line Segments
Given n points on a 1-D plane, where the ith point (from 0 to n-1) is at x = i, find the number of ways we can draw exactly k non-overlapping line segments such that each segment covers two or more points. The endpoints of each segment must have integral coordinates. The k line segments do not have to cover all n points, and they are allowed to share endpoints.

Return the number of ways we can draw k non-overlapping line segments. Since this number can be huge, return it modulo 109 + 7.

 
Example 1:


Input: n = 4, k = 2
Output: 5
Explanation: The two line segments are shown in red and blue.
The image above shows the 5 different ways {(0,2),(2,3)}, {(0,1),(1,3)}, {(0,1),(2,3)}, {(1,2),(2,3)}, {(0,1),(1,2)}.
Example 2:

Input: n = 3, k = 1
Output: 3
Explanation: The 3 ways are {(0,1)}, {(0,2)}, {(1,2)}.
Example 3:

Input: n = 30, k = 7
Output: 796297179
Explanation: The total number of possible ways to draw 7 line segments is 3796297200. Taking this number modulo 109 + 7 gives us 796297179.
 

Constraints:

2 <= n <= 1000
1 <= k <= n-1
 * 
 * Time Complexity: O(K), Where $K$ is the number of segments requested. We execute a single for loop that runs exactly $2K$ times to calculate the numerator and 
 * 			denominator. The modular inverse via binary exponentiation takes strictly ${O}(\log \text{MOD})$ time, which operates as a small constant 
 * 			(around 30 iterations). Overall time scales linearly with $K$.
 * Space Complexity:O(1), We evaluate the mathematical formula using primitive long variables (numerator, denominator, and iteration counters). No arrays or 
 * 			dynamic memory structures are needed, achieving a strict constant space footprint.
 */

public class NumberOfSets {
	
	public int numberOfSets(int n, int k) {
		int MOD = 1_000_000_007;
		
		// We are choosing 2k distinct points from n + k - 1 total points
		int totalPoints = n + k - 1;
		int pointsToChoose = 2 * k;
		
		// If we need to choose more points than are available, it's impossible
		if (pointsToChoose > totalPoints) {
			return 0;
		}
		
		long numerator = 1;
		long denominator = 1;
		
		// Calculate (totalPoints)! / (totalPoints - pointsToChoose)!
        // and (pointsToChoose)! simultaneously
		for (int i = 1; i <= pointsToChoose; i++) {
			numerator = (numerator * (totalPoints - i + 1)) % MOD;
			denominator = (denominator * i) % MOD; 
		}
		
		// To perform (numerator / denominator) % MOD, we multiply by the modular in inverse of the denominator
		long result = (numerator * modInverse(denominator, MOD)) % MOD;
		
		return (int) result;
	}
	
	// Format's Little Theorem for Modular Inverse
	private long modInverse(long base, int mod) {
		return power(base, mod - 2, mod);
	}
	
	// Standard modular exponentiation
	private long power(long base, int exp, int mod) {
		long res = 1;
		base %= mod;
		while (exp > 0) {
			if(exp % 2 == 1) {
				res = (res * base) % mod;
			}
			base = (base * base) % mod;
			exp /= 2;
		}
		return res;
	}
	
	public static void main(String[] args) {
        NumberOfSets sol = new NumberOfSets();

        // Example 1
        int n1 = 4, k1 = 2;
        System.out.println("Input: n = " + n1 + ", k = " + k1);
        System.out.println("Output: " + sol.numberOfSets(n1, k1)); // Expected: 5
        System.out.println("-----------------------------------");

        // Example 2
        int n2 = 3, k2 = 1;
        System.out.println("Input: n = " + n2 + ", k = " + k2);
        System.out.println("Output: " + sol.numberOfSets(n2, k2)); // Expected: 3
        System.out.println("-----------------------------------");

        // Example 3
        int n3 = 30, k3 = 7;
        System.out.println("Input: n = " + n3 + ", k = " + k3);
        System.out.println("Output: " + sol.numberOfSets(n3, k3)); // Expected: 796297179
    }

}

