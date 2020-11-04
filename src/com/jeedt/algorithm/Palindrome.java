package com.jeedt.algorithm;

/**
 * 最长回文子串
 *
 * 对于一个字符串，请设计一个高效算法，计算其中最长回文子串的长度。
 *
 * 给定字符串A以及它的长度n，请返回最长回文子串的长度。
 *
 * 测试样例：
 * "abc1234321ab",12
 * 返回：7
 */
public class Palindrome {

    /**
     * 方法一 从长度为n->1开始遍历，不能利用子串的先验信息
    public int getLongestPalindrome(String A, int n) {
        // write code here
        int len=n;
        while(len>1){
            for(int i=0;i+len-1<n;i++){//i+len-1为子串的尾字符位置
                if(isPalindrome(A,i,i+len-1)){
                    return len;
                }
            }
            len--;
        }
        return len;
    }
    public boolean isPalindrome(String str,int start,int end){
        while (start<end){
            if(str.charAt(start)==str.charAt(end)){
                start++;
                end--;
            }else{
                return false;
            }
        }
        return true;
    }
    */

    /**
     * 方法二 动态规划，建立dp二维数组，空间换时间
    public int getLongestPalindrome(String A, int n) {
        // write code here
        char[] arr=A.toCharArray();
        boolean[][] dp=new boolean[n][n];
        int max=1;
        for(int i=0;i<n;i++){
            dp[i][i]=true;
        }
        for(int i=1;i<n;i++){
            for(int j=i-1;j>=0;j--){
                if(i-j==1){
                    dp[j][i]=(arr[j]==arr[i]);
                    max=max==1?2:1;
                }else{
                    if(dp[j+1][i-1]&&arr[j]==arr[i]){
                        dp[j][i]=true;
                        max=max<i-j+1?i-j+1:max;
                    }else{
                        dp[j][i]=false;
                    }
                }
            }
        }
        return max;
    }
     */
    //方法三 即利用了子串先验信息，也不需要额外空间
    public int getLongestPalindrome(String A, int n) {
        // write code here
        int max=1;
        for(int i=1;i<n;i++){
            int L=i-1;
            int H=i;
            for(;L>=0&&H<n&&A.charAt(L)==A.charAt(H);L--,H++){
                if(max<H-L+1){
                    max=H-L+1;
                }
            }

            L=i-1;
            H=i+1;
            for(;L>=0&&H<n&&A.charAt(L)==A.charAt(H);L--,H++){
                if(max<H-L+1){
                    max=H-L+1;
                }
            }
        }
        return max;
    }
    public static void main(String[] args) {
        Palindrome p=new Palindrome();
        String str="abc1234321ab";
        int len=str.length();
        System.out.println(p.getLongestPalindrome(str,len));
    }
}
