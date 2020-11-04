package com.jeedt.algorithm;

import java.io.*;
import java.util.Arrays;

/**
 * 文本有200亿行数据（10G大小），每行都是数字，请用java程序统计出最大的100个数字
 * 思路：构建最小堆
 */
public class TopK {
    /**
     * 构建堆
     * @param arr
     */
    public void buildMinHeap(double[] arr){
        int len=arr.length;
        for(int i=len/2-1;i>=0;i--){//最后的非叶子节点
            minHeapify(arr,i,len);//非递归
            //minHeapify0(arr,i,len);//递归
        }
    }

    /**
     * 非递归调整堆
     * @param arr
     * @param i
     * @param len
     */
    public void minHeapify(double[] arr,int i,int len){
        double tmp=arr[i];
        for(int k=2*i+1;k<len;k=2*k+1){
            if(k+1<len&&arr[k]>arr[k+1]){
                k++;
            }
            if(arr[k]<tmp){//子结点比父结点小
                swap(arr,i,k);
                i=k;
            }else{
                break;
            }
        }
    }
    /**
     * 递归调整堆
     * @param arr
     * @param i
     * @param len
     */
    public void minHeapify0(double[] arr,int i,int len){
        int l=2*i+1;
        int r=2*i+2;
        int min=i;
        if(l<len&&arr[min]>arr[l]){
            min=l;
        }
        if(r<len&&arr[min]>arr[r]){
            min=r;
        }
        if(i!=min){
            swap(arr, i, min);
            minHeapify0(arr,min,len);
        }
    }

    public void swap(double[] arr,int i,int j){
        double tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }

    /**
     * 生成测试数据
     * @param path
     * @param total
     */
    public void genData(String path,int total){
        try {
            FileWriter writer = new FileWriter(path);
            for (int i = 0; i < total-1; i++) {
                writer.write(String.valueOf(Math.random() * total));
                writer.write(System.getProperty("line.separator"));
            }
            writer.write(String.valueOf(Math.random() * total));
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 非递归查找
     * @param path
     * @param k
     * @return
     */
    public double[] findTopk(String path,int k){
        BufferedReader bufferedReader= null;
        double[] doubles=new double[k];
        int lineNo=1;
        double lastDouble;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
            String line=bufferedReader.readLine();
            while (line!=null){
                lastDouble=Double.valueOf(line);
                if(lineNo<=k){
                    doubles[lineNo-1]=lastDouble;
                }else {
                    if(lineNo==k+1){//建最小堆
                        buildMinHeap(doubles);
                    }
                    if(lastDouble>doubles[0]){
                        doubles[0]=lastDouble;
                        minHeapify(doubles,0,k);//非递归
                        //minHeapify0(doubles,0,k);//递归
                    }
                }
                line=bufferedReader.readLine();
                lineNo++;
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doubles;
    }
    public static void main(String[] args) {
        TopK topK=new TopK();
        topK.genData("C:\\Users\\aaron\\Desktop\\topk.txt",10000000);
        long startTime=System.currentTimeMillis();
        double[] result=topK.findTopk("C:\\Users\\aaron\\Desktop\\topk.txt",100);
        System.out.println(Arrays.toString(result));
        long endTime=System.currentTimeMillis();
        System.out.println("costTime:"+(endTime-startTime)+"ms");
    }
}