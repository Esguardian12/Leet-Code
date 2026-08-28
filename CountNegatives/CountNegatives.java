package CountNegatives;


public class CountNegatives {
	
	public int countNegatives(int[][] grid) {
		int totalRows = grid.length;
		int totalCols = grid[0].length;
		
		int row = 0, col = totalCols - 1, count = 0;
		
		while(row < totalRows && col >= 0) {
			if(grid[row][col] < 0) {
				col--;
				count += totalRows - row;
			} else {
				row++;
			}
		}
		return count;
	}
	
	public static void main(String[]args) {
		
		CountNegatives sol = new CountNegatives();
		
		int[][] grid = {
	            {4, 3, 2, -1},
	            {3, 2, 1, -1},
	            {1, 1, -1, -2},
	            {-1, -1, -2, -3}
	        };
		
		int result = sol.countNegatives(grid);
		
		System.out.println("Number of negtive numbers: " + result);
		
	}
}
