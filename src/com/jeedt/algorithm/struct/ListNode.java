package com.jeedt.algorithm.struct;

import java.util.Random;

/**
 * 单链表结点
 */
public class ListNode {
    private int val;
    private ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    //生成随机链表
    public static ListNode createRandomList(){
        Random rd=new Random();
        ListNode head=new ListNode(rd.nextInt(40));
        ListNode e=head;
        for(int i=0;i<9;i++){
            e.setNext(new ListNode(rd.nextInt(40)));
            e=e.getNext();
        }
        return head;
    }

    //生成指定链表
    public static ListNode createRandomList(int[] a){
        ListNode head=new ListNode(a[0]);
        ListNode e=head;
        for(int i=1;i<a.length;i++){
            e.setNext(new ListNode(a[i]));
            e=e.getNext();
        }
        return head;
    }

    // 遍历链表
    public static void display(ListNode head){
        ListNode e=head;
        while (e!=null){
            System.out.print(e.val+"->");
            e=e.next;
        }
        System.out.println("null");
    }

}

