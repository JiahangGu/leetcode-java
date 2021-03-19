class Solution {
    //最朴素的做法
    static ListNode mergeKLists1(ListNode[] lists) {
        ListNode ans = new ListNode();
        ListNode head = ans;
        int n = lists.length;
        while (true) {
            boolean flag = false;
            int temp = 100000000;
            int idx = -1;
            for (int i = 0; i < n; i++) {
                if (lists[i] != null) {
                    if (lists[i].val < temp) {
                        idx = i;
                        temp = lists[i].val;
                    }
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
            head.next = lists[idx];
            head = head.next;
            lists[idx] = lists[idx].next;
        }
        return ans.next;
    }
}
//分治做法
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int l = 0, r = lists.length - 1;
        return merge(lists, l, r);
    }

    static ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = l + (r - l) / 2;
        return mergeTwoNodes(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    static ListNode mergeTwoNodes(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head = new ListNode();
        ListNode tail = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            }
            else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = l1 == null ? l2 : l1;
        return head.next;
    }
}

//优先队列
class Solution {
    class Status implements Comparable<Status> {
        int val;
        ListNode node;

        Status(int val, ListNode node) {
            this.val = val;
            this.node = node;
        }

        public int compareTo(Status b) {
            return this.val - b.val;
        }
    }

    PriorityQueue<Status> queue = new PriorityQueue<Status>();

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode();
        ListNode tail = head;
        for (int i = 0;i < lists.length;i++) {
            if (lists[i] != null) {
                queue.offer(new Status(lists[i].val, lists[i]));
            }
        }
        while (!queue.isEmpty()) {
            Status x = queue.poll();
            tail.next = x.node;
            tail = tail.next;
            if (x.node.next != null) {
                queue.offer(new Status(x.node.next.val, x.node.next));
            }
        }
        return head.next;
    }
}