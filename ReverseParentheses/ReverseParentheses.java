package ReverseParentheses;

/* 1190. Reverse Substrings Between Each Pair of Parentheses
You are given a string s that consists of lower case English letters and brackets.

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should not contain any brackets.

 

Example 1:

Input: s = "(abcd)"
Output: "dcba"
Example 2:

Input: s = "(u(love)i)"
Output: "iloveu"
Explanation: The substring "love" is reversed first, then the whole string is reversed.
Example 3:

Input: s = "(ed(et(oc))el)"
Output: "leetcode"
Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
 

Constraints:

1 <= s.length <= 2000
s only contains lower case English characters and parentheses.
It is guaranteed that all parentheses are balanced.
 * 
 * Time Complexity: O(N), Where $N$ is the length of the string s. The first pass iterates through the string once to map matching parentheses using a primitive 
 * 			stack in ${O}(N)$ time. The second pass traverses the string character by character (teleporting across matching pairs via pair[i]), 
 * 			visiting each element a constant number of times. Thus, the total time complexity scales linearly, outperforming naive ${O}(N^2)$ 
 * 			substring reversal approaches.
 * Space Complexity: O(N), We allocate the pair array of size $N$ and a primitive stack array of size $N$ to track bracket indices without object overhead. 
 * 			The output StringBuilder requires space proportional to the resulting string length. 
 */

public class ReverseParentheses {
	
	public String reverseParentheses(String s) {
		int n = s.length();
		
		// pair[i] will hold the index of the matching parenthesis for the parenthesis at index i
		int[] pair = new int[n];
		
		// Primitive array to act as a stack for O(1) matching (avoids Object overhead)
		int[] stack = new int[n];
		int top = 0;
		
		// First Pass: Match all pairs of parentheses
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == '(') {
				stack[top++] = i;
			} else if (s.charAt(i) == ')') {
				int j = stack[--top];
				pair[i] = j;
				pair[j] = i;
			}
		}
		
		StringBuilder res = new StringBuilder();
		int i = 0;
		int direction = 1; // 1 for right (forward), -1 for left (backward)
		
		// Second Pass: Build the string using the wormholes
		while (i < n) {
			char c =  s.charAt(i);
			
			if(c == '(' || c == ')') {
				// Teleport to the matching parenthesis
				i = pair[i];
				// Reverse the direction of traversal
				direction = -direction;
			} else {
				res.append(c);
			}
			
			// Move to the next character in the current direction
			i += direction;
		}
		
		return res.toString();
	}
	
	public static void main(String[] args) {
		ReverseParentheses sol = new ReverseParentheses();

		// Example 1
		String s1 = "(abcd)";
		System.out.println("Input: s = \"" + s1 + "\"");
		System.out.println("Output: \"" + sol.reverseParentheses(s1) + "\""); 
		// Expected: "dcba"
		System.out.println("-----------------------------------");

		// Example 2
		String s2 = "(u(love)i)";
		System.out.println("Input: s = \"" + s2 + "\"");
		System.out.println("Output: \"" + sol.reverseParentheses(s2) + "\""); 
		// Expected: "iloveu"
		System.out.println("-----------------------------------");

		// Example 3
		String s3 = "(ed(et(oc))el)";
		System.out.println("Input: s = \"" + s3 + "\"");
		System.out.println("Output: \"" + sol.reverseParentheses(s3) + "\""); 
		// Expected: "leetcode"
	}

}

