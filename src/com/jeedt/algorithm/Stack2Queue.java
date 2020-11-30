package com.jeedt.algorithm;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 *
 * 思路：一个栈做入队操作，一个栈做出队操作
 */
public class Stack2Queue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        Stack2Queue stack2Queue=new Stack2Queue();
        stack2Queue.push(1);
        stack2Queue.push(2);
        stack2Queue.push(3);
        stack2Queue.push(4);
        System.out.println(stack2Queue.pop());
        System.out.println(stack2Queue.pop());
        stack2Queue.push(5);
        System.out.println(stack2Queue.pop());
        System.out.println(stack2Queue.pop());
        System.out.println(stack2Queue.pop());
    }
}
