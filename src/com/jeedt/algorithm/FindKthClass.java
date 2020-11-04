package com.jeedt.algorithm;

/**
 * 有一个整数数组，请你根据快速排序的思路，找出数组中第K大的数。
 *
 * 给定一个整数数组a,同时给定它的大小n和要找的K(K在1到n之间)，请返回第K大的数，保证答案存在。
 *
 * 示例1
 * 输入
 * [1,3,5,2,2],5,3
 * 返回值
 * 2
 *
 * 思路：快速排序，从大到小，pivot为第7个元素位置时，即找到
 */
public class FindKthClass {
    /*最小堆
    public int findKth(int[] a, int n, int K) {
        // write code here
        buildMinHeap(a,K);
        for(int i=K;i<n;i++){
            if(a[0]<a[i]){
                swap(a,0,i);
                minHeapify(a,0,K);
            }
        }
        return a[0];
    }
    public void buildMinHeap(int[] a,int K){
        for(int i=K/2-1;i>=0;i--){
            minHeapify(a,i,K);
        }
    }
    public void minHeapify(int[] a,int i,int K){
        int left=2*i+1;
        int right=2*i+2;
        int min=i;
        if(left<K&&a[left]<a[min]){
            min=left;
        }
        if(right<K&&a[right]<a[min]){
            min=right;
        }
        if(min!=i){
            swap(a,min,i);
            minHeapify(a,min,K);
        }
    }
    public void swap(int[] a,int i,int j){
        int tmp;
        tmp=a[i];
        a[i]=a[j];
        a[j]=tmp;
    }
    */
    public int findKth(int[] a, int n, int K) {
        // write code here

        int l = 0; int r = n-1;
        return quik(a, l, r, K);

    }

    private int quik(int[] a, int l, int r, int k) {
        int position = find(a, l , r, k);
        if(position == k-1) {
            return a[position];
        } else if(position > k-1) {
            return quik(a, l, position -1, k);
        } else {
            return quik(a, position+1, r, k);
        }
    }

    private int find(int[] a, int l, int r, int k) {
        int i = l;
        int j = r;
        int key = a[i];
        while(i < j) {
            while(i<j && a[j] <= key) j--;
            a[i] = a[j] ;
            while(i<j && a[i] >= key) i++;
            a[j] = a[i];
        }
        a[j] = key;
        return j;
    }
    public static void main(String[] args) {
        int[] a={1,3,5,2,2};
        int n=5;
        int K=3;
        FindKthClass findKthClass=new FindKthClass();
        System.out.println(findKthClass.findKth(a,n,K));
    }
}
