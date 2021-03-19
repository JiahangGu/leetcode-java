/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//递归
class Solution {
    ListNode frontPoints;
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        frontPoints = head;
        return check(head.next);
    }

    private boolean check(ListNode head) {
        if (head != null) {
            if (!check(head.next)) {
                return false;
            }
            if (head.val != frontPoints.val) {
                return false;
            }
            frontPoints = frontPoints.next;
        }
        return true;
    }
}

//O(1)空间， 逆转后半部分链表
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode secondHalf = invert(slow.next);
        boolean flag = true;
        ListNode p1 = head, p2 = secondHalf;
        while (flag && p2 != null) {

            if (p1.val != p2.val) {
                flag = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        slow.next = invert(secondHalf);
        return flag;
    }

    private ListNode invert(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newNode = invert(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }
}