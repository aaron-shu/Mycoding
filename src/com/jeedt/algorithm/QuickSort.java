package com.jeedt.algorithm;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    public void sort(int[] arr){
        if(arr==null||arr.length<=1){
            return;
        }
        int len=arr.length;
        quickSort(arr,0,len-1);
    }
    public int partition(int[] arr,int left,int right){
        int pivot=arr[left];
        int pivotPoint=left;
        while(left<right){
            while(left<right&&arr[right]>=pivot){
                right--;
            }
            while(left<right&&arr[left]<=pivot){
                left++;
            }
            swap(arr, left, right);
        }
        swap(arr, pivotPoint, left);
        return left;
    }
    public void quickSort(int[] arr,int left,int right){
        if(left>=right){
            return;
        }
        int pivot=partition(arr,left,right);
        quickSort(arr,left,pivot-1);
        quickSort(arr,pivot+1,right);
    }
    public void swap(int[] arr,int i,int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }

    public static void main(String[] args) {
        int[] arr={1,3,5,2,2,8,10};
        QuickSort quickSort=new QuickSort();
        quickSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
