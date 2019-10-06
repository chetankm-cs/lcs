import java.util.HashSet;
import java.util.Set;

/**
 * [[0,6,0],
 * [5,8,7],
 * [0,9,0]]
 */

public class Gold {
    private boolean isValid(int i, int j, int m, int n, int[][] grid) {
        return 0 <= i && i < m && 0 <= j && j < n && grid[i][j] != 0;
    }

    private int dfs(int i, int j, int[][] grid, Set<Integer> visited, int m, int n) {
        System.out.println("dfs : i:"+ i + " j:" +j );
        int l = 0;
        int r = 0;
        int u = 0;
        int d = 0;

        int code = (100 * i) + j;
        if (visited.contains(code)) {
            System.out.println("contains");
            return 0;
        }
        visited.add(code);

        if (isValid(i - 1, j, m, n, grid)) {
            l = dfs(i - 1, j, grid, visited, m, n);
        }
        if (isValid(i + 1, j, m, n, grid)) {
            r = dfs(i + 1, j, grid, visited, m, n);
        }
        if (isValid(i, j - 1, m, n, grid)) {
            u = dfs(i, j - 1, grid, visited, m, n);
        }
        if (isValid(i, j + 1, m, n, grid)) {
            d = dfs(i, j + 1, grid, visited, m, n);
        }

        visited.remove(code);
        return grid[i][j] + Math.max(l, Math.max(r, Math.max(u, d)));
    }

    public int getMaximumGold(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    max = Math.max(max, dfs(i, j, grid, new HashSet<>(), m, n));
                    System.out.println("i:" + i + " j:" + j + " max:" + max);
                }
            }
        }
        return max;
    }


}
