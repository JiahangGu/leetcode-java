class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean [][]visited = new boolean[m][n];
        for (int i = 0;i < m;i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }
        for (int i = 0;i < m;i++) {
            for (int j = 0;j < n;j++) {
                if (dfs(word, board, i, j, m, n, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean dfs(String word, char [][]board, int x, int y, int m, int n, boolean [][]visited) {
        if (word.length() == 0) {
            return true;
        }
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || board[x][y] != word.charAt(0)) {
            return false;
        }
        visited[x][y] = true;
        int [][]dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0;i < 4;i++) {
            int xx = x + dir[i][0];
            int yy = y + dir[i][1];
            if (dfs(word.substring(1), board, xx, yy, m, n, visited)) {
                return true;
            }
        }
        visited[x][y] = false;
        return false;
    }
}