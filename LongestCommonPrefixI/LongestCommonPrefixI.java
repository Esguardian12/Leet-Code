package LongestCommonPrefixI;

package leetCode;

import java.util.HashSet;

/* 3043. Find the Length of the Longest Common Prefix
 * You are given two arrays with positive integers arr1 and arr2.
 * A prefix of a positive integer is an integer formed by one or more of its digits, starting from its leftmost digit. 
 * For example, 123 is a prefix of the integer 12345, while 234 is not.
 * A common prefix of two integers a and b is an integer c, such that c is a prefix of both a and b. For example, 
 * 5655359 and 56554 have common prefixes 565 and 5655 while 1223 and 43456 do not have a common prefix.
 * You need to find the length of the longest common prefix between all pairs of integers (x, y) such that x belongs to arr1 and y belongs to arr2.
 * Return the length of the longest common prefix among all pairs. If no common prefix exists among them, return 0.
 * 
Example 1:

Input: arr1 = [1,10,100], arr2 = [1000]
Output: 3
Explanation: There are 3 pairs (arr1[i], arr2[j]):
- The longest common prefix of (1, 1000) is 1.
- The longest common prefix of (10, 1000) is 10.
- The longest common prefix of (100, 1000) is 100.
The longest common prefix is 100 with a length of 3.
Example 2:

Input: arr1 = [1,2,3], arr2 = [4,4,4]
Output: 0
Explanation: There exists no common prefix for any pair (arr1[i], arr2[j]), hence we return 0.
Note that common prefixes between elements of the same array do not count.
 

Constraints:

1 <= arr1.length, arr2.length <= 5 * 104
1 <= arr1[i], arr2[i] <= 108
 * 
 * Time Complexity: O(n + m), if integers are bounded (32-bit int), d ≤ 10, so this behaves almost like. 
 *                Similarly, each number in arr2 may also be divided by 10 at most d times: O(m * d)
 * Space Complexity: O(n * d), Each number stores all prefixes from arr1. 
 *                With bounded integer size: O(n)
 */

public class LongestCommonPrefix1 {
	
	public int longestCommonPrefix(int[] arr1, int[] arr2) {
		HashSet<Integer> arr1Prefixes = new HashSet<>();
		
		// Store all prefixes of numbers in arr1
		for(int val: arr1) {
			while(!arr1Prefixes.contains(val) && val > 0) {
				arr1Prefixes.add(val);
				val /= 10;
			}
		}
		
		int longestPrefix = 0;
		
		// Check prefixes from arr2
		for(int val : arr2) {
			while (!arr1Prefixes.contains(val) && val > 0) {
				val /= 10;
			}
			if (val > 0) {
				longestPrefix = Math.max(longestPrefix, (int) Math.log10(val) + 1);
			}
		}
		
		return longestPrefix;
	}
	public static void main(String[] args) {

        LongestCommonPrefix1 sol = new LongestCommonPrefix1();

        int[] arr1 = {12345, 5678, 12, 789};
        int[] arr2 = {123, 567, 78, 1299};

        int result = sol.longestCommonPrefix(arr1, arr2);

        System.out.println("Longest Common Prefix Length: " + result);
    }

}
