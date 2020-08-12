import java.util.Arrays;

public class SegmentTree {
    int [] data;
    int [] orig;
    int n;
    public SegmentTree(int [] arr) {
        n = arr.length;
        orig = arr;
        data = new int[2 * n];

        System.arraycopy(arr, 0, data, n, n);

        /* skip 1st index */
        for (int i = n - 1; i > 0; --i)
            data[i] = Math.min(data[2 * i], data[2 * i + 1]);
    }

    public void update(int index, int v) {
        int i = index + n;
        data[i] = v;
        i = i/2;
        while (i > 0) {
            data[i] = Math.min(data[2 * i], data[2 * i + 1]);
            i = i/2;
        }
        printData();
    }

    private void printData() {
        for (int j = n ; j < 2 *n; j++)
            System.out.print(data[j] +", ");
        System.out.println();
    }

    /** assumes l and r are within bounds and r is exclusive. */
    public int findMin(int l, int r) {
        l += n;r += n;
        int min = Integer.MAX_VALUE;
        while (l <  r) {
            // if this is right node we include it and don't include the parent
            if (l % 2 == 1) {
                min = Math.min(min, data[l]);
                l++;
            }
            // Remember r is exclusive, this mean we are considering the left node
            // we include it and don't include the parent.
            if (r % 2 == 1) {
                r--;
                min = Math.min(min, data[r]);
            }
            l = l/2;
            r = r/2;
        }

        printData();
        return min;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 3, 1, 2, -1, 0};
        System.out.println(arr.length);
        SegmentTree  st = new SegmentTree(arr);

        System.out.println(Arrays.toString(arr));
    }
}
