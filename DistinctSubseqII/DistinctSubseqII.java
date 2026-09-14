package DistinctSubseqII;

/* 940. Distinct Subsequences II
Given a string s, return the number of distinct non-empty subsequences of s. Since the answer may be very large, return it modulo 109 + 7.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not.
 

Example 1:

Input: s = "abc"
Output: 7AC
Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
Example 2:

Input: s = "aba"
Output: 6
Explanation: The 6 distinct subsequences are "a", "b", "ab", "aa", "ba", and "aba".
Example 3:

Input: s = "aaa"
Output: 3
Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".
 

Constraints:

1 <= s.length <= 2000
s consists of lowercase English letters.
 *
 * Time Complexity: O(N), Where N is the length of the string s. We iterate through the characters of the string exactly once. Inside the loop, all operations 
 * 			(modulo, addition, and subtraction) execute in constant ${O}(1)$ time.
 * Space Complexity: O(1), We allocate an array ends of fixed size 26 (representing the lowercase English alphabet) to track the state. Because this array size never 
 * 		changes regardless of the input string length, the space complexity remains strictly constant.
 */

public class DistinctSubseqII {

	public int distinctSubseqII(String s) {
		int MOD = 1_000_000_007;
		
		// ends[i] stores the number of distinct subsequences ending with character ('a' + i)
		long[] ends = new long[26];
		long total = 0;
		
		for(char c : s.toCharArray()) {
			int idx = c - 'a';
			
			long oldEnds = ends[idx];
			// Appending current character to all existing subsequences + 1 for the character itself
			long newEnds = (total + 1) % MOD;
			
			// Update the total by removing the old subsequences ending with 'c' 
            // and adding the newly formed ones. (Add MOD before subtracting to prevent negative values)
			total = (total - oldEnds + MOD) % MOD;
			total = (total + newEnds) % MOD;
			
			// Store the new count for this character
			ends[idx] = newEnds;
		}
		
		return (int) total;
	}
	
	public static void main(String[] args) {
        DistinctSubseqII sol = new DistinctSubseqII();
        
        // Example 1
        String s1 = "abc";
        System.out.println("Input: s = \"" + s1 + "\"");
        System.out.println("Output: " + sol.distinctSubseqII(s1)); // Expected: 7
        System.out.println("-----------------------------------");
        
        // Example 2
        String s2 = "aba";
        System.out.println("Input: s = \"" + s2 + "\"");
        System.out.println("Output: " + sol.distinctSubseqII(s2)); // Expected: 6
        System.out.println("-----------------------------------");
        
        // Example 3
        String s3 = "aaa";
        System.out.println("Input: s = \"" + s3 + "\"");
        System.out.println("Output: " + sol.distinctSubseqII(s3)); // Expected: 3
    }
}
