package MinOperations;

import java.util.*;

public class MinOperations {
	
	public int minOperations(int[][] grid, int x) {
        List<Integer> numsArray = new ArrayList<>();
        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                numsArray.add(grid[i][j]);
            }
        }

        Collections.sort(numsArray);
        int length = numsArray.size();
        int finalCommonNumber = numsArray.get(length / 2);

        for (int number : numsArray) {
            if (number % x != finalCommonNumber % x) {
                return -1;
            }
            result += Math.abs(finalCommonNumber - number) / x;
        }

        return result;
    }
	
	 public static void main(String[] args) {
	        MinOperations sol = new MinOperations();

	        int[][] grid1 = {
	            {2, 4},
	            {6, 8}
	        };
	        int x1 = 2;

	        int[][] grid2 = {
	            {1, 5},
	            {2, 3}
	        };
	        int x2 = 1;

	        int[][] grid3 = {
	            {1, 2},
	            {3, 4}
	        };
	        int x3 = 2;

	        System.out.println("Grid1 result: " + sol.minOperations(grid1, x1)); // expected: 4
	        System.out.println("Grid2 result: " + sol.minOperations(grid2, x2)); // expected: 5
	        System.out.println("Grid3 result: " + sol.minOperations(grid3, x3)); // expected: -1
	    }

}
