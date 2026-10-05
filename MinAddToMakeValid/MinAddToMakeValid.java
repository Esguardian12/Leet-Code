package MinAddToMakeValid;

/* 921. Minimum Add to Make Parentheses Valid
A parentheses string is valid if and only if:

It is the empty string,
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.

For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
Return the minimum number of moves required to make s valid.

 

Example 1:

Input: s = "())"
Output: 1
Example 2:

Input: s = "((("
Output: 3
 

Constraints:

1 <= s.length <= 1000
s[i] is either '(' or ')'.
 * 
 * Time Complexity: O(N), Where $N$ is the length of the string s. We traverse the string exactly once from left to right. The character comparisons and 
 * 		integer arithmetic take ${O}(1)$ time per character.
 * Space Complexity: O(1), We completely avoid allocating a Stack or any arrays. We only use two primitive integer variables (openBalance and movesRequired), 
 * 		meaning our memory usage remains strictly constant regardless of the string's size.
 */

public class MinAddToMakeValid {
	
	public int minAddToMakeValid(String s) {
		int openBalance = 0;
		int movesRequired = 0;
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				openBalance++;
			} else { // It's a ')'
				if (openBalance > 0) {
					// This closing parenthesis successfully matches a previous open one
					openBalance--;
				} else {
					// We have a closing parenthesis with no matching open one
					// We must add a '(' to make it valid
					movesRequired++;
				}
			}
		}
		
		// movesRequired fixes the unmatched ')', openBalance fixes the unmatched '('
		return openBalance + movesRequired;
	}

	public static void main(String[] args) {
        MinAddToMakeValid sol = new MinAddToMakeValid();

        // Example 1
        String s1 = "())";
        System.out.println("Input: s = \"" + s1 + "\"");
        System.out.println("Output: " + sol.minAddToMakeValid(s1)); 
        // Expected: 1
        System.out.println("-----------------------------------");

        // Example 2
        String s2 = "(((";
        System.out.println("Input: s = \"" + s2 + "\"");
        System.out.println("Output: " + sol.minAddToMakeValid(s2)); 
        // Expected: 3
        System.out.println("-----------------------------------");
        
        // Example 3 (Custom: Mixed unbalanced)
        String s3 = "()))((";
        System.out.println("Input: s = \"" + s3 + "\"");
        System.out.println("Output: " + sol.minAddToMakeValid(s3)); 
        // Expected: 4 (Add two '(' at the start, and two ')' at the end)
    }
}

