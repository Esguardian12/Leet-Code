package CountCommasII;


/* 3871. Count Commas in Range II
Medium
Topics
premium lock icon
Companies
Hint
You are given an integer n.

Return the total number of commas used when writing all integers from [1, n] (inclusive) in standard number formatting.

In standard formatting:

A comma is inserted after every three digits from the right.
Numbers with fewer than 4 digits contain no commas.
 

Example 1:

Input: n = 1002

Output: 3

Explanation:

The numbers "1,000", "1,001", and "1,002" each contain one comma, giving a total of 3.

Example 2:

Input: n = 998

Output: 0

Explanation:

​​​​​​​All numbers from 1 to 998 have fewer than four digits. Therefore, no commas are used.

 

Constraints:

1 <= n <= 1015
 * 
 * Time Complexity: 
 * Space Complexity:
 */

public class CountCommasII {
	
	public long countCommas(long n) {
		long totalCommas = 0;
		long threshold = 1000;
		
		// Loop through thresholds: 1,000 -> 1,000,000 -> 1,000,000,000, etc.
		while(n <= threshold) {
			totalCommas *= 1000;
		}
		
		return totalCommas;
	}

	public static void main(String[] args) {
        CountCommasII sol = new CountCommasII();

        // Example 1
        long n1 = 1002;
        System.out.println("Input: n = " + n1);
        System.out.println("Output: " + sol.countCommas(n1)); // Expected: 3
        System.out.println("-----------------------------------");

        // Example 2
        long n2 = 998;
        System.out.println("Input: n = " + n2);
        System.out.println("Output: " + sol.countCommas(n2)); // Expected: 0
        System.out.println("-----------------------------------");

        // Example 3 (Massive input testing the constraints)
        long n3 = 1_000_000_000_000_000L; // 10^15
        System.out.println("Input: n = " + n3);
        System.out.println("Output: " + sol.countCommas(n3)); 
        // Expected: 4995000000000001
    }
}
