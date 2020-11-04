package com.jeedt.algorithm;

import com.jeedt.algorithm.struct.ListNode;

/**
 * 判断链表中是否有环
 *
 * 扩展：
 * 你能给出空间复杂度O(1)的解法么？
 *
 * 思路：快慢指针
 */
public class HasCycleClass {
    //快慢指针
    public boolean hasCycle(ListNode head) {
        ListNode fastNode=head;
        ListNode slowNode=head;

        while (fastNode!=null&&fastNode.getNext()!=null){
            fastNode=fastNode.getNext().getNext();
            slowNode=slowNode.getNext();
            if (fastNode==slowNode){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //初始化链表
        //无环
        //ListNode head=ListNode.createRandomList();

        //有环
        ListNode head=new ListNode(1);//有环
        ListNode node1=new ListNode(3);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(5);
        ListNode node4=new ListNode(4);

        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node2);


        HasCycleClass hasCycleClass=new HasCycleClass();
        System.out.println(hasCycleClass.hasCycle(head));
    }
}
