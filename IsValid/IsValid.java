package IsValid;

import java.util.Stack;

/* 20. Valid Parentheses
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
 

Example 1:

Input: s = "()"

Output: true

Example 2:

Input: s = "()[]{}"

Output: true

Example 3:

Input: s = "(]"

Output: false

Example 4:

Input: s = "([])"

Output: true

Example 5:

Input: s = "([)]"

Output: false

 

Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
 * 
 * Time Complexity: O(N), Where $N$ is the length of the string s.s.toCharArray() converts the string into a char array in ${O}(N)$ time.The loop 
 * 			iterates through each character in the string exactly once.Stack operations (push, pop, peek, isEmpty) all execute in ${O}(1)$ time.
 * Space Complexity: O(N), In the worst-case scenario (e.g., an input string containing only open brackets like "((((((("), the stack will store all $N$ characters, 
 * 			requiring ${O}(N)$ space.s.toCharArray() also allocates an array of size $N$ in heap memory.
 */

public class IsValid {
	
	
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		for(char c : s.toCharArray()){
			if(c == '(' || c == '{' || c == '[') {
				stack.push(c);
			}
			else if(c == ')' && !stack.isEmpty() && stack.peek() == '(') {
				stack.pop();
			}
			else if(c == '}' && !stack.isEmpty() && stack.peek() == '{') {
				stack.pop();
			}
			else if(c == ']' && !stack.isEmpty() && stack.peek() == '[') {
				stack.pop();
			}
			else {
				return false;
			}
		}
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		IsValid solution = new IsValid();

		// Example 1
		String s1 = "()";
		System.out.println("Input: s = \"" + s1 + "\"");
		System.out.println("Output: " + solution.isValid(s1)); 
		// Expected: true
		System.out.println("-----------------------------------");

		// Example 2
		String s2 = "()[]{}";
		System.out.println("Input: s = \"" + s2 + "\"");
		System.out.println("Output: " + solution.isValid(s2)); 
		// Expected: true
		System.out.println("-----------------------------------");

		// Example 3
		String s3 = "(]";
		System.out.println("Input: s = \"" + s3 + "\"");
		System.out.println("Output: " + solution.isValid(s3)); 
		// Expected: false
		System.out.println("-----------------------------------");

		// Example 4
		String s4 = "([])";
		System.out.println("Input: s = \"" + s4 + "\"");
		System.out.println("Output: " + solution.isValid(s4)); 
		// Expected: true
		System.out.println("-----------------------------------");

		// Example 5
		String s5 = "([)]";
		System.out.println("Input: s = \"" + s5 + "\"");
		System.out.println("Output: " + solution.isValid(s5)); 
		// Expected: false
	}
}
