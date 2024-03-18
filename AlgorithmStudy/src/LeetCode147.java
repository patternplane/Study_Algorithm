
// 147. Insertion Sort List
// https://leetcode.com/problems/insertion-sort-list

/**
* Definition for singly-linked list.
* public class ListNode {
* int val;
* ListNode next;
* ListNode() {}
* ListNode(int val) { this.val = val; }
* ListNode(int val, ListNode next) { this.val = val; this.next = next; }
* }
*/
// => 임시 구현체
class ListNode {
 int val;
 ListNode next;
 ListNode() {}
 ListNode(int val) { this.val = val; }
 ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class LeetCode147 {
    public ListNode insertionSortList(ListNode head) {
        ListNode sortedListHeadNode = new ListNode();
        sortedListHeadNode.next = null;
        ListNode currentPos = sortedListHeadNode;
        ListNode newItem = null;

        while (head != null) {
            newItem = head;
            head = head.next;

            if (newItem.val < currentPos.val)
                currentPos = sortedListHeadNode;
            while (currentPos.next != null
                && newItem.val >= currentPos.next.val)
                currentPos = currentPos.next;
            
            newItem.next = currentPos.next;
            currentPos.next = newItem;
        }

        return sortedListHeadNode.next;
    }
}
