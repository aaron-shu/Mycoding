package com.jeedt.algorithm;

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * 思路：动态规划，数组元素dp[i][j]，第一字符串A前i个元素到第二个字符串B前j个元素的编辑距离,
 * 本质上3种操作：A插入字符、B插入字符，A修改字符，操作的顺序不影响最终的结果，故操作均在A、B的末尾进行
 */

public class MinDistanceClass {
    public int minDistance(String word1, String word2) {
        int m=word1.length();
        int n=word2.length();
        if(m*n==0){
            return m+n;
        }

        int[][] dp=new int[m+1][n+1];
        //处理边界
        for(int i=0;i<m+1;i++){
            dp[i][0]=i;
        }
        for(int i=0;i<n+1;i++){
            dp[0][i]=i;
        }
        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                int op1=dp[i-1][j]+1;
                int op2=dp[i][j-1]+1;
                int op12=dp[i-1][j-1];
                //最后一个字母不相同的情况
                if(word1.charAt(i-1)!=word2.charAt(j-1)){
                    op12+=1;
                }
                dp[i][j]=Math.min(op1,Math.min(op2,op12));
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        MinDistanceClass minDistanceClass=new MinDistanceClass();
        String word1 = "intention", word2 = "execution";
        System.out.println(minDistanceClass.minDistance(word1,word2));
    }
}
