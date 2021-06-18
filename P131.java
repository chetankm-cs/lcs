import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution131 {

    public static void main(String[] args) {
        Solution131 s = new Solution131();
        s.partition("bb");
    }

    public List<List<String>> solve(String s, int c,Boolean[][] p) {
        List<List<String>> sol = new LinkedList<>();
        if (c == s.length())
            sol.add(new LinkedList<>());
        else {
            for (int i = c; i < s.length(); i++) {
                if (p[c][i]) {
                    List<List<String>> ps = solve(s, i + 1, p);
                    List<List<String>> wp = new LinkedList<>();
                    String curr = s.substring(c, i + 1);
                    ps.forEach(x -> {
                        List<String> l = new ArrayList<>();
                        l.add(curr);
                        l.addAll(x);
                        wp.add(l);
                    });
                    sol.addAll(wp);
                }
            }
        }
        return sol;
    }

    public boolean isPalindrome(String s, int i, int j){
        if (i == j) return true;
        if (s.charAt(i) == s.charAt(j)) {
            if (j == i + 1) return true;
            else
                return isPalindrome(s, i + 1, j -1);
        }
        else
            return false;
    }

    public Boolean[][] compute(String s) {
        int n = s.length();
        Boolean[][] a = new Boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = false;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
               a[i][j] = isPalindrome(s, i, j);
            }
        }
        return a;
    }

    public List<List<String>> partition(String s) {
        Boolean[][] b = compute(s);
        List<List<String>> ll = solve(s, 0, b);

        for (int i = 0; i < ll.size(); i++) {
            for (int j = 0; j < ll.get(i).size(); j++) {
                System.out.print(ll.get(i).get(j) + ",");
            }
            System.out.println();
        }
        return  ll;
    }
}