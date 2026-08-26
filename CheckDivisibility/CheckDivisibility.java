package CheckDivisibility;

/* 3622. Check Divisibility by Digit Sum and Product
You are given a positive integer n. Determine whether n is divisible by the sum of the following two values:

The digit sum of n (the sum of its digits).

The digit product of n (the product of its digits).

Return true if n is divisible by this sum; otherwise, return false.

 

Example 1:

Input: n = 99

Output: true

Explanation:

Since 99 is divisible by the sum (9 + 9 = 18) plus product (9 * 9 = 81) of its digits (total 99), the output is true.

Example 2:

Input: n = 23

Output: false

Explanation:

Since 23 is not divisible by the sum (2 + 3 = 5) plus product (2 * 3 = 6) of its digits (total 11), the output is false.

 

Constraints:

1 <= n <= 106
 * 
 * Time Complexity: O(log n), The number of iterations in the while loop is determined by the number of digits in $n$, which is mathematically proportional to 
 * 			the base-10 logarithm of $n$. Given the constraint $n \le 10^6$, the loop runs at most 7 times, resulting in O(1) practical execution time.
 * Space Complexity: O(1), We only allocate a few primitive integer variables (temp, digitSum, digitProduct, digit, and combinedTotal) to track the mathematical 
 * 			states. Since this memory footprint does not scale with the size of the input, the auxiliary space complexity is strictly constant.
 */

public class CheckDivisibility {
	
	public boolean checkDivisibility(int n) {
		int temp = n;
		int sum = 0;
		int prod = 1;
		
		while(temp > 0) {
			int digit = temp % 10;
			sum += digit;
			prod *= digit;
			temp /= 10;
		}
		
		return n % (sum + prod) == 0;
	}
	
	public static void main(String[] args) {
        CheckDivisibility sol = new CheckDivisibility();

        // Example 1
        int n1 = 99;
        System.out.println("Input: n = " + n1);
        System.out.println("Output: " + sol.checkDivisibility(n1)); // Expected: true
        System.out.println("-----------------------------------");

        // Example 2
        int n2 = 23;
        System.out.println("Input: n = " + n2);
        System.out.println("Output: " + sol.checkDivisibility(n2)); // Expected: false
    }

}

