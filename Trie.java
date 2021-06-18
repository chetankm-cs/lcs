import java.util.HashMap;
import java.util.Map;

public class Trie {
    class T {
        boolean isEnd = false;
        Map<Character, T> children = new HashMap<>();
    }

    private T root;

    public Trie() {
        this.root = new T();
    }

    public void insert(String word1) {
        T temp = root;
        for (char c : word1.toCharArray()) {
            temp.children.putIfAbsent(c, new T());
            temp = temp.children.get(c);
        }
        temp.isEnd = true;
    }

    public boolean search(String word) {
        T temp = root;
        for (char c : word.toCharArray()) {
            if (temp.children.containsKey(c))
                temp = temp.children.get(c);
            else
                return false;
        }
        return temp.isEnd;
    }

    public boolean startsWith(String prefix) {
        T temp = root;
        for (char c : prefix.toCharArray()) {
            if (temp.children.containsKey(c))
                temp = temp.children.get(c);
            else
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        TrieS t = new TrieS();
        t.insert("apple");
        System.out.println(t.search("apple"));
        System.out.println(t.search("app"));
        System.out.println(t.startsWith("app"));
        t.insert("app");
        System.out.println(t.search("app"));
    }
}
