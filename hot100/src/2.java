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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode();
        int c = 0;
        ListNode node = ans;
        int num = 0;
        while (l1 != null && l2 != null) {
            ListNode tmp = new ListNode();
            num = l1.val + l2.val + c;
            tmp.val = num % 10;
            c = num / 10;
            node.next = tmp;
            node = node.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            ListNode tmp = new ListNode();
            num = l1.val + c;
            tmp.val = num % 10;
            c = num / 10;
            node.next = tmp;
            node = node.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            ListNode tmp = new ListNode();
            num = l2.val + c;
            tmp.val = num % 10;
            c = num / 10;
            node.next = tmp;
            node = node.next;
            l2 = l2.next;
        }
        if (c > 0) {
            ListNode tmp = new ListNode();
            tmp.val = c;
            node.next = tmp;
        }
        return ans.next;
    }
}