package com.jeedt.algorithm;

/**
 *
 * 两个有序数组，要求不用其他空间，都只遍历一遍，得到两个数组对应集合的差集。A-B  B-A都要求到，数组中可能包含重复数字，比如A 1 2 2 5 6
 * B为2  4  7
 *
 * A-B就是1 5 6
 * B-A就是4  7
 *
 * 如果B不包含2时，A-B中应该有两个2
 *
 * 思路：参考归并排序思路，两数组从头到尾遍历，使用临时变量存储相同值，找出A-B和B-A
 */
public class DiffSet {
    public void showDiffSet(int[] arrA,int[] arrB){
        int i=0;
        int j=0;
        int lenA=arrA.length;
        int lenB=arrB.length;
        int tmp=Integer.MAX_VALUE;
        while (i<lenA&&j<lenB){
            if(arrA[i]<arrB[j]){
                if(arrA[i]==tmp){
                    i++;
                    continue;
                }
                System.out.println(arrA[i++]+"(A-B)");
            } else if(arrA[i]>arrB[j]){
                if(arrB[j]==tmp){
                    j++;
                    continue;
                }
                System.out.println(arrB[j++]+"(B-A)");
            }else {
                i++;
                j++;
                tmp=arrA[i];
            }
        }
        while (i<lenA){
            if(arrA[i]==tmp){
                i++;
                continue;
            }
            System.out.println(arrA[i++]+"(A-B)");
        }
        while (j<lenB){
            if(arrB[j]==tmp){
                j++;
                continue;
            }
            System.out.println(arrB[j++]+"(B-A)");
        }
    }
    public static void main(String[] args) {
        DiffSet ds=new DiffSet();
        int[] arrA={1,2,2,5,6};
        int[] arrB={4,7};
        ds.showDiffSet(arrA,arrB);
    }
}