package FindSafeWalk;

import java.util.*;

/* 3286. Find a Safe Walk Through a Grid
 * You are given an m x n binary matrix grid and an integer health.

You start on the upper-left corner (0, 0) and would like to get to the lower-right corner (m - 1, n - 1).

You can move up, down, left, or right from one cell to another adjacent cell as long as your health remains positive.

Cells (i, j) with grid[i][j] = 1 are considered unsafe and reduce your health by 1.

Return true if you can reach the final cell with a health value of 1 or more, and false otherwise.

 

Example 1:

Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]], health = 1

Output: true

Explanation:

The final cell can be reached safely by walking along the gray cells below.


Example 2:

Input: grid = [[0,1,1,0,0,0],[1,0,1,0,0,0],[0,1,1,1,0,1],[0,0,1,0,1,0]], health = 3

Output: false

Explanation:

A minimum of 4 health points is needed to reach the final cell safely.


Example 3:

Input: grid = [[1,1,1],[1,0,1],[1,1,1]], health = 5

Output: true

Explanation:

The final cell can be reached safely by walking along the gray cells below.



Any path that does not go through the cell (1, 1) is unsafe since your health will drop to 0 when reaching the final cell.

 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
2 <= m * n
1 <= health <= m + n
grid[i][j] is either 0 or 1.
 * 
 * Time Complexity: O(R * C), We process each cell in the grid using a 0-1 BFS. Because 0-weight edges are added to the front of the queue and 1-weight edges 
 *        are added to the back, we are guaranteed to discover the shortest path (minimum damage) to any cell the first time it is processed. Each cell is 
 *        processed and expanded to its at most 4 neighbors exactly once when it's popped from the queue. Queue operations (offerFirst, offerLast, pollFirst)
 *        run in $\mathcal{O}(1)$ constant time.Therefore, the maximum number of operations scales linearly with the number of cells in the grid.
 *        
 * Space Complexity: O(R * C), The minDamage array requires $R \times C$ space to store an integer for every cell. The ArrayDeque can hold up to 
 * 		  $R \times C$ elements in the worst-case scenario (e.g., a grid full of 1s where cells are continually pushed to the back of the queue). 
 *        Total space used scales linearly with grid size.
 */

public class FindSafeWalk {

	// Define direction vectors clearly at the class level
	private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	public boolean findSafeWalk(List<List<Integer>> grid, int health) {
		int rows = grid.size();
		int cols = grid.get(0).size();
		
		// Track the minimum damage taken to reach each cell
		int[][] minDamage = new int[rows][cols];
		for(int[] row : minDamage) {
			Arrays.fill(row,  Integer.MAX_VALUE);
		}
		
		// 0-1 BFS setup
		Deque<int[]> deque = new ArrayDeque<>();
		deque.offerFirst(new int[] {0, 0});
		minDamage[0][0] = grid.get(0).get(0);
		
		while(!deque.isEmpty()) {
			int[] current = deque.pollFirst();
			int currentRow = current[0];
			int currentCol = current[1];
			
			// Early exit: If we reached the bottom-right corner, we found a safe path
			if(currentRow == rows - 1 && currentCol == cols - 1) {
				return true;
			}
			
			// Explore all 4 adjacent directions
			for(int[] dir : DIRECTIONS) {
				int nextRow = currentRow + dir[0];
				int nextCol = currentCol + dir[1];
				
				// Check grid boundaries
				if(nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
					int damageAtNextCell = grid.get(nextRow).get(nextCol);
					int totalDamage = minDamage[currentRow][currentCol] + damageAtNextCell;
					
					// Prune paths that would kill the player
					if(totalDamage >= health) {
						continue;
					}
					
					// If we found a safer way t o reach this cell, update and add to queue
					if(totalDamage < minDamage[nextRow][nextCol]) {
						minDamage[nextRow][nextCol] = totalDamage;
						
						// 0-cost moves go to front, 1-cost moves go to the back
						if(damageAtNextCell == 0) {
							deque.offerFirst(new int[] {nextRow ,nextCol});
						} else {
							deque.offerLast(new int[] {nextRow, nextCol});
						}
					}
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
        FindSafeWalk solver = new FindSafeWalk();

        // Test Case 1: Can safely reach the end
        List<List<Integer>> grid1 = Arrays.asList(
            Arrays.asList(0, 1, 0, 0, 0),
            Arrays.asList(0, 1, 0, 1, 0),
            Arrays.asList(0, 0, 0, 1, 0)
        );
        int health1 = 1;
        System.out.println("Test Case 1: " + solver.findSafeWalk(grid1, health1)); // Expected: true

        // Test Case 2: Not enough health to cross
        List<List<Integer>> grid2 = Arrays.asList(
            Arrays.asList(0, 1, 1, 0, 0),
            Arrays.asList(0, 1, 1, 1, 0),
            Arrays.asList(0, 0, 0, 1, 0)
        );
        int health2 = 1;
        System.out.println("Test Case 2: " + solver.findSafeWalk(grid2, health2)); // Expected: false

        // Test Case 3: Starts and ends on 1s
        List<List<Integer>> grid3 = Arrays.asList(
            Arrays.asList(1, 1, 1),
            Arrays.asList(1, 0, 1),
            Arrays.asList(1, 1, 1)
        );
        int health3 = 5;
        System.out.println("Test Case 3: " + solver.findSafeWalk(grid3, health3)); // Expected: true
    }
}
