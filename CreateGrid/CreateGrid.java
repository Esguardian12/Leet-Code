package CreateGrid;

import java.util.Arrays;

/* 3963. Create Grid With Exactly One Path
You are given two integers m and n, representing the number of rows and columns of a grid.

Construct any m x n grid consisting only of the characters '.' and '#', where:

'.' represents a free cell.
'#' represents an obstacle cell.
A valid path is a sequence of free cells that:

Starts at the top-left cell (0, 0).
Ends at the bottom-right cell (m - 1, n - 1).
Moves only:
Right, from (i, j) to (i, j + 1), or
Down, from (i, j) to (i + 1, j).
Return any grid such that there is exactly one valid path from the top-left cell to the bottom-right cell.

 

Example 1:

Input: m = 2, n = 3

Output: ["..#","#.."]

Explanation:



The only valid path is: (0,0) → (0,1) → (1,1) → (1,2)

Example 2:

Input: m = 3, n = 3

Output: ["..#","#..","##."]

Explanation:



The only valid path is: (0,0) → (0,1) → (1,1) → (1,2) → (2,2)

Example 3:

Input: m = 1, n = 4

Output: ["...."]

Explanation:

The only valid path is: (0,0) → (0,1) → (0,2) → (0,3)

 

Constraints:

1 <= m, n <= 25
 * 
 * Time Complexity:
 * Space Complexity:
 */

public class CreateGrid {

	public String[] createGrid(int m, int n) {
		char[][] g = new char[m][n];
		
		// Step 1: Fill the entire grid with obstacles ('#')
		for (int i = 0; i < m; i++) {
			Arrays.fill(g[i], '#');
		}
		
		// Step 2: Open up the entire first row 
		Arrays.fill(g[0], '.');
		
		// Step 3: Open up the entire last column
		for (int i = 0; i < m; i++) {
			g[i][n - 1] = '.';
		}
		
		// Convert char arrays to String array
		String[] ans = new String[m];
		for (int i = 0; i < m; i++) {
			ans[i] = new String(g[i]);
		}
		
		return ans;
	}
	
	public static void main(String[] args) {
        CreateGrid sol = new CreateGrid();

        // Example 1
        int m1 = 2, n1 = 3;
        System.out.println("Input: m = " + m1 + ", n = " + n1);
        System.out.println("Output: " + Arrays.toString(sol.createGrid(m1, n1))); 
        // Expected: [..#, #..]
        System.out.println("-----------------------------------");

        // Example 2
        int m2 = 3, n2 = 3;
        System.out.println("Input: m = " + m2 + ", n = " + n2);
        System.out.println("Output: " + Arrays.toString(sol.createGrid(m2, n2))); 
        // Expected: [..#, #.., ##.]
        System.out.println("-----------------------------------");

        // Example 3
        int m3 = 1, n3 = 4;
        System.out.println("Input: m = " + m3 + ", n = " + n3);
        System.out.println("Output: " + Arrays.toString(sol.createGrid(m3, n3))); 
        // Expected: [....]
    }
	
}

