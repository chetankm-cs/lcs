public class MinSwap {
        public int solve(int[] A, int B) {
            int wsize = 0;
            for (int value : A) if (value <= B) ++wsize;
            if (wsize == 0 || wsize == 1) return wsize;
            int max = 0, curr = 0;
            int i = 0, j = 0;

            while (j < A.length) {
                if (j - i  >= wsize) {
                    if (A[i] <= B) --curr;
                    i++;
                } else {
                    if (A[j] <= B) ++curr;
                    if (curr > max) max = curr;
                    j++;
                }
            }
            return wsize - max;
        }
}
