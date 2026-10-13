package GenerateParenthesis;

import java.util.ArrayList;
import java.util.List;

/* 22. Generate Parentheses
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.


Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
 

Constraints:

1 <= n <= 8
 * 
 * Time Complexity: $\mathcal{O}\left(\frac{4^n}{\sqrt{n}}\right), $The number of valid combinations of $n$ pairs of parentheses is given by the $n$-th Catalon 
 * 		number $C_n = \frac{1}{n+1}\binom{2n}{n}$, which asymptotically grows on the order of $\frac{4^n}{n^{3/2}}$. Since we must visit and construct each valid 
 * 		string, the time complexity is proportional to the number of valid solutions generated.
 * Space Complexity: $\mathcal{O}(N)$ auxiliary space (excluding the output list storage), The maximum depth of the recursion stack is $2n$, and the StringBuilder 
 * 		holds at most $2n$ characters at any given time. Storing the final results requires space proportional to the number of valid combinations times their 
 * 		length ($\mathcal{O}\left(\frac{4^n}{\sqrt{n}} \cdot n\right)$).
 */

public class GenerateParenthesis {

	public List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<>();
		backtrack(result, new StringBuilder(), 0, 0, n);
		return result;
	}
	
	private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
		// Base case: The string is complete when its length is 2 * max
		if (current.length() == max * 2) {
			result.add(current.toString());
			return;
		}
		
		// If we can still place an open parenthesis, do so and recurse
		if (open < max) {
			current.append("(");
			backtrack(result, current, open + 1, close, max);
			current.deleteCharAt(current.length() - 1); // backpack
		}
		
		// If we have more open than close, we can safely place a close parenthesis
		if (close < open) {
			current.append(")");
			backtrack(result, current, open, close + 1, max);
			current.deleteCharAt(current.length() - 1); // backtrack
		}
	}
	
	public static void main(String[] args) {
        GenerateParenthesis sol = new GenerateParenthesis();

        // Example 1
        int n1 = 3;
        System.out.println("Input: n = " + n1);
        System.out.println("Output: " + sol.generateParenthesis(n1)); 
        // Expected: ["((()))","(()())","(())()","()(())","()()()"]
        System.out.println("-----------------------------------");

        // Example 2
        int n2 = 1;
        System.out.println("Input: n = " + n2);
        System.out.println("Output: " + sol.generateParenthesis(n2)); 
        // Expected: ["()"]
    }
}

