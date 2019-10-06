import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Rotten {
    class P{
        int i, j;

        public P(int i, int j) {
            this.i = i;
            this.j = j;

        }
    }
    class PT {
        int i, j, t;

        public PT(int i, int j, int t) {
            this.i = i;
            this.j = j;
            this.t = t;

        }
    }
    private boolean isValid(int i, int j, int n, int m)  {
        return 0 <=i && i < n && 0<=j && j <m;
    }

    private List<P> gen(int i, int j, int n, int m) {
        ArrayList<P> res = new ArrayList<>();
        if (isValid(i + 1, j, n, m)) res.add(new P(i + 1, j));
        if (isValid(i, j + 1, n, m)) res.add(new P(i, j + 1));
        if (isValid(i - 1, j, n, m)) res.add(new P(i - 1, j));
        if (isValid(i, j - 1, n, m)) res.add(new P(i, j - 1));
        return res;
    }

    public int orangesRotting(int[][] grid) {
        PriorityQueue <PT> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.t));
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j] == 2)
                    pq.add(new PT(i, j, 0));
            }
        }
        boolean [][] v = new boolean[n][m];
        int max = 0;
        while (!pq.isEmpty()) {
            PT curr = pq.poll();
            v[curr.i][curr.j] = true;
            for (P p : gen(curr.i, curr.j, n, m)) {
                if (!v[p.i][p.j] && grid[p.i][p.j] == 1) {
                    max = Math.max(curr.t + 1, max);
                    grid[p.i][p.j] = 2;
                    v[p.i][p.j] = true;
                    pq.add(new PT(p.i, p.j, curr.t + 1));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) return -1;
            }
        }
        return max;
    }
}
