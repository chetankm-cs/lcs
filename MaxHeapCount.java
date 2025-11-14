public class MaxHeapCount {
    private int mm = 1000000007;
    private int C[][] = new int[101][101];

    private void setup (){
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                C[i][j] = -1;
            }
        }
    }

    private int nCk(int n, int k) {
        if (k > n) return 0;
        if (n <= 1) return 1;
        if (k == 0) return 1;
        if (C[n][k] == -1) {
            int res = nCk(n - 1, k - 1) + nCk(n - 1, k);
            C[n][k] = res % mm;
        }
        return C[n][k];
    }

    private int getL(int n) {
        if (n==1) return 1;
        if (n==0) return 1;
        int h = (int)(Math.log(n)/ Math.log(2));
        int m = (int) Math.pow(2, h);
        int p = n - (int)(Math.pow(2, h) - 1);
        if(p >= m/2) return (int)Math.pow(2, h) - 1;
        else return (int)Math.pow(2, h - 1) - 1 + p;
    }

    public long getHeap(int A){
        if (A <= 1) return 1;
        int l = getL(A);
        return ((nCk(A-1 , l) * getHeap(l) % mm) % mm  * getHeap(A - 1 - l) % mm) % mm;
    }

    public int solve(int A) {
        setup();
        return (int) getHeap(A);
    }
}
