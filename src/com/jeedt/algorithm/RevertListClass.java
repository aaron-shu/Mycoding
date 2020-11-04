package com.jeedt.algorithm;
import com.jeedt.algorithm.struct.ListNode;
/**
 * 输入一个链表，反转链表后，输出新链表的表头。
 */

public class RevertListClass {
    //头插法
    public ListNode reverseList(ListNode head) {
        ListNode e=head;
        head=null;
        while (e!=null){
            ListNode next=e.getNext();
            e.setNext(head);
            head=e;
            e=next;
        }
        return head;
    }

    public static void main(String[] args) {
        //链表初始化
        ListNode head=ListNode.createRandomList();
        //反转前
        ListNode.display(head);
        RevertListClass revertListClass=new RevertListClass();
        head=revertListClass.reverseList(head);
        //反转后
        ListNode.display(head);
    }
}
