import java.util.*;

public class NMaxPair {
    class P {
        int i, j;

        public P(int i, int j) {
            this.i = i;
            this.j = j;
        }
        public Integer sum(ArrayList<Integer> A, ArrayList<Integer> B) {
            return A.get(i) + B.get(j);
        }

        public boolean isValid(int n) {
            return i < n && j < n ;
        }

        @Override
        public String toString() {
            return i + "," + j;
        }
    }
        public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
            ArrayList<Integer> result = new ArrayList<>();

            Comparator<P> c = (p1, p2) -> {
                int sum1 = p1.sum(A, B);
                int sum2 = p2.sum(A, B);
                return sum2 - sum1;
            } ;
            Collections.sort(A);
            Collections.reverse(A);

            Collections.sort(B);
            Collections.reverse(B);

            PriorityQueue<P> q = new PriorityQueue<>(c);
            HashSet<String> s = new HashSet<>();
            P start = new P(0,0);
            q.add(start);
            s.add(start.toString());

            int n = A.size();
            while (!q.isEmpty() && result.size()< n) {
                P curr = q.poll();
                int i = curr.i;
                int j = curr.j;
                result.add(curr.sum(A, B));
                P n1 = new P(i + 1, j);
                P n2 = new P(i, j + 1);
                if (!s.contains(n1.toString()) && n1.isValid(n)) {
                    s.add(n1.toString());
                    q.add(n1);
                }
                if (!s.contains(n2.toString()) && n2.isValid(n)) {
                    s.add(n2.toString());
                    q.add(n2);
                }
            }
            return result;
        }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(3,2,4,2));
        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(4,3,1,2));
        new NMaxPair().solve(a, b).forEach(System.out::println);
    }
}

