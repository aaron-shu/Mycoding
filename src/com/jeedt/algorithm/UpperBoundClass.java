package com.jeedt.algorithm;

/**
 * 请实现有重复数字的有序数组的二分查找。
 * 输出在数组中第一个大于等于查找值的位置，如果数组中不存在这样的数，则输出数组长度加一。
 *
 * 输入
 * 5,4,[1,2,4,4,5]
 *
 * 输出
 * 3
 */
public class UpperBoundClass {
    /**
     * 二分查找
     * @param n int整型 数组长度
     * @param v int整型 查找值
     * @param a int整型一维数组 有序数组
     * @return int整型
     */
    public int upperBound (int n, int v, int[] a) {
        // write code here
        int lowerIndex=0;
        int upperIndex=n-1;
        int index=(lowerIndex+upperIndex)/2;
        int minIndex=n+1;

        while (index>=lowerIndex&&index<=upperIndex&&lowerIndex<=upperIndex){
            if(a[index]<v){
                lowerIndex=index+1;
            }else if(a[index]>=v){
                upperIndex=index-1;
                minIndex=index+1;
            }
            index=(lowerIndex+upperIndex)/2;
        }
        return minIndex;
    }

    public static void main(String[] args) {
        int[] a={1,2,4,4,5};
        UpperBoundClass ubc=new UpperBoundClass();
        System.out.println(ubc.upperBound(a.length,4,a));
    }
}
