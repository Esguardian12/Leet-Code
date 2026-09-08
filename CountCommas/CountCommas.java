package CountCommas;


/* 3870. Count Commas in Range
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

All numbers from 1 to 998 have fewer than four digits. Therefore, no commas are used.

 

Constraints:

1 <= n <= 105
 * 
 * Time Complexity: O(log1000 N), The while loop multiplies the threshold by 1,000 on each iteration. For standard integer bounds, this loop runs at most 3 times, 
 * 			making the execution strictly constant ${O}(1)$ time.
 * Space Complexity: O(1), The algorithm utilizes only two primitive variables (totalCommas and threshold) regardless of the size of n, keeping the memory footprint 
 * 			minimal and strictly constant.
 */

public class CountCommas {
	
	public int countCommas(int n) {
		int totalCommas = 0;
		long threshold = 1000;
		
		// Loop through thresholds: 1,000 -> 1,000,000 -> 1,000,000,000, etc.
		while(n >= threshold) {
			totalCommas += (n - threshold + 1);
			threshold *= 1000;
		}
		
		return totalCommas;
	}

	public static void main(String[] args) {
        CountCommas sol = new CountCommas();

        // Example 1
        int n1 = 1002;
        System.out.println("Input: n = " + n1);
        System.out.println("Output: " + sol.countCommas(n1)); // Expected: 3
        System.out.println("-----------------------------------");

        // Example 2
        int n2 = 998;
        System.out.println("Input: n = " + n2);
        System.out.println("Output: " + sol.countCommas(n2)); // Expected: 0
        System.out.println("-----------------------------------");

        // Example 3 (Larger number to demonstrate scaling)
        int n3 = 2000000;
        System.out.println("Input: n = " + n3);
        System.out.println("Output: " + sol.countCommas(n3)); // Expected: 1999002
    }
}

