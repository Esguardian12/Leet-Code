package DiagonalSum;


/* 1572. Matrix Diagonal Sum
 * Given a square matrix mat, return the sum of the matrix diagonals.
 * Only include the sum of all the elements on the primary diagonal and all the elements on the secondary 
 * diagonal that are not part of the primary diagonal.

Example 1:
Input: mat = [[1,2,3],
              [4,5,6],
              [7,8,9]]
Output: 25
Explanation: Diagonals sum: 1 + 5 + 9 + 3 + 7 = 25
Notice that element mat[1][1] = 5 is counted only once.

Example 2:
Input: mat = [[1,1,1,1],
              [1,1,1,1],
              [1,1,1,1],
              [1,1,1,1]]
Output: 8
Example 3:

Input: mat = [[5]]
Output: 5
 

Constraints:

n == mat.length == mat[i].length
1 <= n <= 100
1 <= mat[i][j] <= 100
 *
 * Time Complexity: O(N), N means side length of the matrix(mat.length). The loop runs exactly N iterations — one pass
 * 		 to collect both diagonals simultaneously. The center subtraction is a single O(1) operation. Overall: O(N), which is optimal. 
 *       You cannot do better since you must read at least N elements (the diagonal itself).
 * Space Complexity: O(1), Only two integer variables (sum, len) are used regardless of matrix size.
 * 		 No extra arrays or data structures are allocated. Constant auxiliary space.
 */

public class DiagonalSum {
	
	public int diagonalSum(int[][] mat) {
		int sum = 0;
		int len = mat.length;
		for (int i = 0; i < len; i++) {
			sum += mat[i][i];
			sum += mat[len - 1 - i][i];
		}
		if (len % 2 != 0)  {
			sum -= mat[len / 2][len / 2];
		}
		return sum;
	}

	 public static void main(String[] args) {
	        DiagonalSum ds = new DiagonalSum();

	        // Test 1: 3x3 matrix (odd size — center gets double-counted, so subtract once)
	        // Primary diagonal:   1 + 5 + 9 = 15
	        // Secondary diagonal: 3 + 5 + 7 = 15
	        // Center 5 counted twice → subtract once → 15 + 15 - 5 = 25
	        int[][] mat1 = {
	            {1, 2, 3},
	            {4, 5, 6},
	            {7, 8, 9}
	        };
	        System.out.println(ds.diagonalSum(mat1)); // Expected: 25

	        // Test 2: 2x2 matrix (even size — no center overlap)
	        // Primary:   1 + 4 = 5
	        // Secondary: 2 + 3 = 5 → total = 10
	        int[][] mat2 = {
	            {1, 2},
	            {3, 4}
	        };
	        System.out.println(ds.diagonalSum(mat2)); // Expected: 10

	        // Test 3: 1x1 matrix (odd size — only one element, added twice then subtracted once)
	        // sum = 7 + 7 - 7 = 7
	        int[][] mat3 = {{7}};
	        System.out.println(ds.diagonalSum(mat3)); // Expected: 7

	        // Test 4: 4x4 matrix (even size — no center overlap)
	        // Primary:   1 + 6 + 11 + 16 = 34
	        // Secondary: 4 + 7 + 10 + 13 = 34 → total = 68
	        int[][] mat4 = {
	            { 1,  2,  3,  4},
	            { 5,  6,  7,  8},
	            { 9, 10, 11, 12},
	            {13, 14, 15, 16}
	        };
	        System.out.println(ds.diagonalSum(mat4)); // Expected: 68

	        // Test 5: All zeros
	        int[][] mat5 = {
	            {0, 0, 0},
	            {0, 0, 0},
	            {0, 0, 0}
	        };
	        System.out.println(ds.diagonalSum(mat5)); // Expected: 0
	    }
}

