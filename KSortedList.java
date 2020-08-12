import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KSortedList {
    class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        public class Solution {
            private Comparator<ListNode> c = Comparator.comparingInt(o -> o.val);

            public ListNode mergeKLists(ArrayList<ListNode> a) {
                PriorityQueue<ListNode> pq = new PriorityQueue<>(c);
                pq.addAll(a);
                ListNode head = new ListNode(0);
                ListNode tail = head;
                while (!pq.isEmpty()) {
                    ListNode curr = pq.poll();
                    tail.next = curr;
                    tail = tail.next;

                    curr = curr.next;
                    if (curr != null) pq.add(curr);
                }
                return head.next;
            }
        }
    }

}
