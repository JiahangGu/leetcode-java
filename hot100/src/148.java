/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        return twoListMerge(left, right);
    }

    static ListNode twoListMerge(ListNode l, ListNode r) {
        if (l == null || r == null) {
            return l == null ? r : l;
        }
        ListNode ans = new ListNode();
        ListNode head = ans;
        while (l != null && r != null) {
            if (l.val < r.val) {
                ans.next = l;
                l = l.next;
            }
            else {
                ans.next = r;
                r = r.next;
            }
            ans = ans.next;
        }
        ans.next = l == null ? r : l;
        return head.next;
    }
}