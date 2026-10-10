package GcdOfSums;

/* 3658. GCD of Odd and Even Sums
You are given an integer n. Your task is to compute the GCD (greatest common divisor) of two values:

sumOdd: the sum of the smallest n positive odd numbers.

sumEven: the sum of the smallest n positive even numbers.

Return the GCD of sumOdd and sumEven.

 

Example 1:

Input: n = 4

Output: 4

Explanation:

Sum of the first 4 odd numbers sumOdd = 1 + 3 + 5 + 7 = 16
Sum of the first 4 even numbers sumEven = 2 + 4 + 6 + 8 = 20
Hence, GCD(sumOdd, sumEven) = GCD(16, 20) = 4.

Example 2:

Input: n = 5

Output: 5

Explanation:

Sum of the first 5 odd numbers sumOdd = 1 + 3 + 5 + 7 + 9 = 25
Sum of the first 5 even numbers sumEven = 2 + 4 + 6 + 8 + 10 = 30
Hence, GCD(sumOdd, sumEven) = GCD(25, 30) = 5.

 

Constraints:

1 <= n <= 10​​​​​​​00
 * 
 * Time Complexity: O(1), We are returning the input variable directly without any loops, iterations, or recursion. It takes constant time.
 * Space Complexity: O(1), No extra data structures or memory arrays are allocated. Memory usage is strictly constant.
 */

public class GcdOfSums {
	
	public int gcdOfOddAndEvenSums(int n) {
		// as proven by the math derivation, the answer is always just n!
		return n;
	}
	
	public static void main(String[] args) {
        GcdOfSums solver = new GcdOfSums();

        // Test Case 1
        int n1 = 4;
        System.out.println("Test Case 1 Output: " + solver.gcdOfOddAndEvenSums(n1));
        // Expected Output: 4

        // Test Case 2
        int n2 = 5;
        System.out.println("Test Case 2 Output: " + solver.gcdOfOddAndEvenSums(n2));
        // Expected Output: 5
        
        // Test Case 3 (Max constraint check)
        int n3 = 1000;
        System.out.println("Test Case 3 Output: " + solver.gcdOfOddAndEvenSums(n3));
        // Expected Output: 1000
    }

}

