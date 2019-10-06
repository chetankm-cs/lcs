import sun.security.provider.ConfigFile;

public class Spiral {
    public int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = -1;
            }
        }

        int curr_dir = 0; // 1,2,3
        int i =0 ,j=0;
        for (int x = 0; x < n * n; x++) {
            if (arr[i][j] == -1)
                arr[i][j] = x + 1;

            switch (curr_dir) {
                case 0:
                    if (i + 1 >= n || arr[i + 1][j] != -1) {
                        ++j;
                        curr_dir = 1;
                    } else {
                        ++i;
                    }
                    break;

                case 1:
                    if (j + 1 >= n || arr[i][j + 1] != -1) {
                        --i;
                        curr_dir = 2;
                    } else {
                        ++j;
                    }
                    break;
                case 2:
                    if (i - 1 < 0 || arr[i - 1][j] != -1) {
                        --j;
                        curr_dir = 3;
                    } else {
                        --i;
                    }
                    break;
                case 3:
                    if (j - 1 <0 || arr[i][j - 1] != -1) {
                        ++i;
                        curr_dir = 0;
                    } else {
                        --j;
                    }
                    break;
            }
        }
        int [][] b = new int[n][n];
        for (int i1 = 0; i1 < n; i1++) {
            for (int j1 = 0; j1 < n; j1++) {
                b[i1][j1] = arr[j1][i1];
            }
        }
        return b;
    }

    public static void main(String[] args) {
        int n = 10;
        int [][] a = new Spiral().generateMatrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[j][i] + ",");
            }
            System.out.println();
        }
    }
}
