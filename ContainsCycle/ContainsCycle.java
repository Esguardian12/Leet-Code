package ContainsCycle;

public class ContainsCycle{

    int [][] direct = {{1,0},{-1,0},{0,1},{0,-1}};

    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j]){
                    if(dfs(grid,i,j,-1,-1, visited)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] grid, int r, int c, int pr, int pc, boolean[][] visited){
        visited[r][c] = true;
        for(int[] d : direct){
            int nr = r + d[0];
            int nc = c + d[1];

            if(nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length){
                continue;
            }

            if(grid[nr][nc] != grid[r][c]){
                continue;
            }

            if(nr == pr && nc == pc){
                continue;
            }

            if(visited[nr][nc]){
                return true;
            }

            if(dfs(grid, nr, nc, r, c, visited)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsCycle sol = new ContainsCycle();

        char[][] grid1 = {
            {'a','a','a','a'},
            {'a','b','b','a'},
            {'a','b','b','a'},
            {'a','a','a','a'}
        };

        char[][] grid2 = {
            {'c','c','c','a'},
            {'c','d','c','c'},
            {'c','c','e','c'},
            {'f','c','c','c'}
        };

        System.out.println("Grid1 has cycle: " + sol.containsCycle(grid1)); // true
        System.out.println("Grid2 has cycle: " + sol.containsCycle(grid2)); // true
    }
}