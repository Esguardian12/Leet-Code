package MergeAlternately;


/* 1768. Merge Strings Alternately
 * You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. 
 * If a string is longer than the other, append the additional letters onto the end of the merged string.

Return the merged string.

Example 1:

Input: word1 = "abc", word2 = "pqr"
Output: "apbqcr"
Explanation: The merged string will be merged as so:
word1:  a   b   c
word2:    p   q   r
merged: a p b q c r
Example 2:

Input: word1 = "ab", word2 = "pqrs"
Output: "apbqrs"
Explanation: Notice that as word2 is longer, "rs" is appended to the end.
word1:  a   b 
word2:    p   q   r   s
merged: a p b q   r   s
Example 3:

Input: word1 = "abcd", word2 = "pq"
Output: "apbqcd"
Explanation: Notice that as word1 is longer, "cd" is appended to the end.
word1:  a   b   c   d
word2:    p   q 
merged: a p b q c   d
 

Constraints:

1 <= word1.length, word2.length <= 100
word1 and word2 consist of lowercase English letters.
 * 
 * Time Complexity: O(n+m), n = word1.length(), m = word2.length(), Time: toCharArray() is O(n) and O(m), the loop runs O(min(n,m)) times,
 * 		 and substring + append of the tail is O(|longer − shorter|). Total is O(n + m).
 * Space Complexity: O(n+m), output StringBuilder holds all characters, Space: The two char arrays cost O(n) + O(m), 
 * 		 and the StringBuilder grows to hold all n + m characters. Total is O(n + m).
 * 
 * One small heads-up: when len1 == len2, both if blocks fire and longerString ends up set
 * to word1 — but since substring(min) where min == len1 returns "", the output is still
 * correct. You could make the intent cleaner with else if on the second condition.
 */

public class MergeAlternately {

	public String mergeAlternately(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
		int min = Math.min(len1, len2);
		String longerString = "";
		if(min == len1) {
			longerString = word2;
		}
		if (min == len2) {
			longerString = word1;
		}
		char chars1[] = word1.toCharArray();
		char chars2[] = word2.toCharArray();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < min; i++) {
			sb.append(chars1[i]);
			sb.append(chars2[i]);
		}
		sb.append(longerString.substring(min));
		return sb.toString();
	}
	
	 public static void main(String[] args) {
	        MergeAlternately sol = new MergeAlternately();

	        // Test 1: equal lengths
	        // "abc" + "xyz" → "axbycz"
	        System.out.println(sol.mergeAlternately("abc", "xyz"));

	        // Test 2: word1 shorter
	        // "ab" + "xyzw" → "axby" + "zw" → "axbyzw"
	        System.out.println(sol.mergeAlternately("ab", "xyzw"));

	        // Test 3: word2 shorter
	        // "abcd" + "xy" → "axby" + "cd" → "axbycd"
	        System.out.println(sol.mergeAlternately("abcd", "xy"));

	        // Test 4: one empty string
	        // "" + "xyz" → "xyz"
	        System.out.println(sol.mergeAlternately("", "xyz"));

	        // Test 5: single characters
	        // "a" + "b" → "ab"
	        System.out.println(sol.mergeAlternately("a", "b"));
	    }
}
