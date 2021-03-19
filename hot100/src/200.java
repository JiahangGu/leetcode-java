class Solution {
    public int numIslands(char[][] grid) {
        int ans = 0;
        int m = grid.length, n = grid[0].length;
        boolean [][] visited = new boolean[m][n];
        for (int i = 0;i < m;i++) {
            for (int j = 0;j < n;j++) {
                visited[i][j] = false;
            }
        }
        for (int i = 0;i < m;i++) {
            for (int j = 0;j < n;j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    ans++;
                    dfs(i, j, visited, grid);
                }
            }
        }
        return ans;
    }

    private void dfs(int x, int y, boolean [][]visited, char [][]grid) {
        visited[x][y] = true;
        int m = grid.length;
        int n = grid[0].length;
        int [][]dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0;i < 4;i++) {
            int new_x = x + dir[i][0];
            int new_y = y + dir[i][1];
            if (new_x >= 0 && new_x < m && new_y >= 0 && new_y < n && grid[new_x][new_y] == '1' && !visited[new_x][new_y]) {
                dfs(new_x, new_y, visited, grid);
            }
        }
    }
}