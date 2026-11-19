package RemoveInvalidParenthesis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 301. Remove Invalid Parentheses
Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.

Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.

 
Example 1:

Input: s = "()())()"
Output: ["(())()","()()()"]
Example 2:

Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]
Example 3:

Input: s = ")("
Output: [""]
 

Constraints:

1 <= s.length <= 25
s consists of lowercase English letters and parentheses '(' and ')'.
There will be at most 20 parentheses in s.
 * 
 * Time Complexity: 
 * Space Complexity:
 */

public class RemoveInvalidParentheses {

	public List<String> removeInvalidParentheses(String s) {
		int leftRem = 0;
		int rightRem = 0;
		
		// Pass 1: Determine the exact minimum number of '(' and ')' that must be removed
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ( c == '(') {
				leftRem++;
			} else if ( c == ')') {
				if (leftRem > 0) {
					leftRem--; // This ')' matches a previous '('
				} else {
					rightRem++; // Unmatched ')'
				}
			}
		}
		
		Set<String> validExpressions = new HashSet<>();
		dfs(s, 0, leftRem, rightRem, 0, new StringBuilder(), validExpressions);
		
		return new ArrayList<>(validExpressions);
	}
	
	private void dfs(String s, int index, int leftRem, int rightRem, int openCount, StringBuilder sb, Set<String> res) {
		// Base case: Reached the end of the string
		if (index == s.length()) {
			if (leftRem == 0 && rightRem == 0 && openCount == 0) {
				res.add(sb.toString());
			}
			return;
 		}

		char c = s.charAt(index);
		int len = sb.length(); 
		
		// Option 1: Remove the current character (if it's a parenthesis and we still need to remove this type)
		if (c == '(' && leftRem > 0) {
            dfs(s, index + 1, leftRem - 1, rightRem, openCount, sb, res);
        } else if (c == ')' && rightRem > 0) {
            dfs(s, index + 1, leftRem, rightRem - 1, openCount, sb, res);
        }
        
        // Option 2: Keep the current character
        sb.append(c);
        
        if (c != '(' && c != ')') {
            // It's a letter, keep it and move on without changing the parenthesis balance
            dfs(s, index + 1, leftRem, rightRem, openCount, sb, res);
        } else if (c == '(') {
            // Keeping an open parenthesis increases our open count
            dfs(s, index + 1, leftRem, rightRem, openCount + 1, sb, res);
        } else if (c == ')' && openCount > 0) {
            // We can only keep a closing parenthesis if there is a matching open one available
            dfs(s, index + 1, leftRem, rightRem, openCount - 1, sb, res);
        }
        
        // Backtrack: Remove the appended character to restore state for the caller
        sb.setLength(len);
    }
	
	public static void main(String[] args) {
		RemoveInvalidParentheses sol = new RemoveInvalidParentheses();

		// Example 1
		String s1 = "()())()";
		System.out.println("Input: s = \"" + s1 + "\"");
		System.out.println("Output: " + sol.removeInvalidParentheses(s1)); 
		// Expected: ["(())()", "()()()"]
		System.out.println("-----------------------------------");

		// Example 2
		String s2 = "(a)())()";
		System.out.println("Input: s = \"" + s2 + "\"");
		System.out.println("Output: " + sol.removeInvalidParentheses(s2)); 
		// Expected: ["(a())()", "(a)()()"]
		System.out.println("-----------------------------------");

		// Example 3
		String s3 = ")(";
		System.out.println("Input: s = \"" + s3 + "\"");
		System.out.println("Output: " + sol.removeInvalidParentheses(s3)); 
		// Expected: [""]
	}
}

