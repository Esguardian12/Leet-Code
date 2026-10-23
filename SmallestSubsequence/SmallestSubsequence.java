package SmallestSubsequence;

package leetCode;

/* 1081. Smallest Subsequence of Distinct Characters
 * 
Given a string s, return the lexicographically smallest subsequence of s that contains all the distinct characters of s exactly once.

Example 1:

Input: s = "bcabc"
Output: "abc"
Example 2:

Input: s = "cbacdcbc"
Output: "acdb"
 

Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
 

Note: This question is the same as 316: https://leetcode.com/problems/remove-duplicate-letters/
 * 
 * Time Complexity: O(N), We scan the string twice. The first pass maps the last occurrences ($O(N)$). The second pass builds the result. 
 * 			During the second pass, even though there is a while loop inside the for loop, every character is pushed onto the stack exactly once 
 * 			and popped off the stack at most once. Therefore, the inner loop operations amortize to $O(1)$ per character, making the total time strictly linear.
 * Space Complexity: O(1), The lastOccurrence array, the inStack boolean array, and the StringBuilder (stack) are all bounded by the 26 characters of the English 
 * 			alphabet. Because this space requirement does not grow with the size of the input string $N$, the auxiliary space is strictly constant.
 */

public class SmallestSubsequence {
	
	public String smallestSubsequence(String s) {
		// 1. Record the last occurrence index of each character
		int[] lastOccurrence = new int[26];
		for(int i = 0; i< s.length(); i++) {
			lastOccurrence[s.charAt(i) - 'a'] = i;
		}
		
		// Keep track of what is currently in our stack
		boolean[] inStack = new boolean[26];
		
		// Use a StringBuilder to act as our monotonic stack
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			// If we've already placed this character in out optimaL subsequence, skip it
			if(inStack[c - 'a']) {
				continue;
			}
			
			// If the current character is smaller than the top of our stack,
            // AND the character at the top of the stack appears again later,
            // we can safely remove the top character to get a better lexicographical order.
			while (sb.length() > 0
				  && sb.charAt(sb.length() - 1) > c
				  && lastOccurrence[sb.charAt(sb.length() - 1) - 'a'] > i) {
				
				// Mark the removed character as no longer in the stack
				inStack[sb.charAt(sb.length() - 1) - 'a'] = false;
				sb.deleteCharAt(sb.length() - 1);
			}
			
			// Ad the current character to the stack and mark it as seen
			sb.append(c);
			inStack[c - 'a'] = true;
		}
	    
		return sb.toString();
	}
	
	public static void main(String[] args) {
        SmallestSubsequence solver = new SmallestSubsequence();

        // Test Case 1
        String s1 = "bcabc";
        System.out.println("Test Case 1 Output: " + solver.smallestSubsequence(s1));
        // Expected Output: "abc"

        // Test Case 2
        String s2 = "cbacdcbc";
        System.out.println("Test Case 2 Output: " + solver.smallestSubsequence(s2));
        // Expected Output: "acdb"
    }
}

