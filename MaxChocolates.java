import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxChocolates {
    private Comparator<Integer> c = (o1, o2) -> o2 - o1;
    private int mm = 1000000007;

    public int nchoc(int A, ArrayList<Integer> B) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(c);
        B.forEach(pq::add);
        long sum = 0;
        for (int i = 0; i < A; i++) {
            int curr = pq.poll();
            sum += curr;
            sum %= mm;
            pq.add(curr / 2);
        }
        return (int) sum;
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(6,5));
        System.out.println(new MaxChocolates().nchoc(3, a));
    }
}
