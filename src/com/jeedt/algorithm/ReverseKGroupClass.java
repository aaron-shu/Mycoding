package com.jeedt.algorithm;

import com.jeedt.algorithm.struct.ListNode;

/**
 * 链表中的节点每k个一组翻转
 *
 * 将给出的链表中的节点每k个一组翻转，返回翻转后的链表
 * 如果链表中的节点数不是k的倍数，将最后剩下的节点保持原样
 * 你不能更改节点中的值，只能更改节点本身。
 * 要求空间复杂度O(1)
 * 例如：
 * 给定的链表是1→2→3→4→5
 * 对于k=2, 你应该返回2→1→4→3→5
 * 对于k=3, 你应该返回3→2→1→4→5
 *
 * 示例1
 * 输入
 * {1,2,3,4,5},2
 *
 * 输出
 * {2,1,4,3,5}
 */
public class ReverseKGroupClass {
    /**
     *
     * @param head ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode reverseKGroup (ListNode head, int k) {
        // write code here
        ListNode head0=head;
        ListNode p=new ListNode(0);//辅助结点
        int length=getLength(head);
        int times=length/k;//反转次数
        int i=0;
        while (i<times){
            //ListNode tail=getTail(head,k);
            ListNode next2=reverseList(p,head,k);
            //此时head为被反转后的tail，仍然指向之前的结点，只是位置转到尾部，即下次反转的p
            if(i==0){
                head0=p.getNext();
            }
            p=head;
            p.setNext(next2);
            head=p.getNext();
            i++;
        }
        return head0;
    }

    //通过头插进行反转，反转后链表尾结点的next为null，返回下次循环的头结点
    public ListNode reverseList(ListNode p,ListNode head,int k){
        ListNode e=head;
        ListNode next2=null;//本次循环尾结点的next，即下次循环的头结点
        head=null;
        int i=0;
        while (i<k){
            ListNode next=e.getNext();//临时存储下一结点
            if(i==k-1){
                next2=next;
            }
            e.setNext(head);
            head=e;
            e=next;
            i++;
        }
        p.setNext(head);
        return next2;
    }

    public int getLength(ListNode head){
        ListNode e=head;
        int i=0;
        while (e!=null){
            e=e.getNext();
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        ReverseKGroupClass reverseKGroupClass=new ReverseKGroupClass();
        int[] arr={1,2,3,4,5,6,7};
        ListNode head=ListNode.createRandomList(arr);
        ListNode.display(head);
        ListNode.display(reverseKGroupClass.reverseKGroup(head,2));
    }
}
