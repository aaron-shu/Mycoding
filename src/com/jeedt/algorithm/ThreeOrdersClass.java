package com.jeedt.algorithm;

import com.jeedt.algorithm.struct.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 分别按照二叉树先序，中序和后序打印所有的节点。
 *
 * 示例1
 * 输入
 * {1,2,3}
 *
 * 返回值
 * [[1,2,3],[2,1,3],[2,3,1]]
 *
 * 备注:
 * n≤10^6
 *
 * 思路：先序（中左右）、中序（左中右）、后序（左后中），递归
 */
public class ThreeOrdersClass {
    List<Integer> preList=new ArrayList<>();
    List<Integer> inList=new ArrayList<>();
    List<Integer> postList=new ArrayList<>();
    /**
     *
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    public int[][] threeOrders (TreeNode root) {
        // write code here
        List<List<Integer>> resultList=new ArrayList<>();
        preOrder(root);
        inOrder(root);
        postOrder(root);
        resultList.add(preList);
        resultList.add(inList);
        resultList.add(postList);
        int n=preList.size();
        int[][] result=new int[3][n];
        for(int i=0;i<3;i++){
            for(int j=0;j<n;j++){
                result[i][j]=resultList.get(i).get(j);
            }
        }
        return result;
    }
    //先序
    public void preOrder(TreeNode root){
        if(root==null){
            return;
        }
        preList.add(root.getVal());
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }
    //中序
    public void inOrder(TreeNode root){
        if(root==null){
            return;
        }
        inOrder(root.getLeft());
        inList.add(root.getVal());
        inOrder(root.getRight());
    }
    //后序
    public void postOrder(TreeNode root){
        if(root==null){
            return;
        }
        postOrder(root.getLeft());
        postOrder(root.getRight());
        postList.add(root.getVal());
    }

    public static void main(String[] args) {
        TreeNode node2=new TreeNode(2);
        TreeNode node3=new TreeNode(3);
        TreeNode node1=new TreeNode(1,node2,node3);
        ThreeOrdersClass threeOrdersClass=new ThreeOrdersClass();
        System.out.println(Arrays.deepToString(threeOrdersClass.threeOrders(node1)));
    }
    /*
    另解：
    private int preIndex = 0, inIndex = 0, postIndex = 0;
    public int[][] threeOrders (TreeNode root) {
        // write code here
        int n = count(root);
        int[][] res = new int[3][n];
        orders(root, res);
        return res;
    }
    public int count(TreeNode root){
        if(root == null){
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    }
    public void orders(TreeNode root, int[][] res){
        if(root == null){
            return;
        }
        res[0][preIndex++] = root.val;
        orders(root.left, res);
        res[1][inIndex++] = root.val;
        orders(root.right, res);
        res[2][postIndex++] = root.val;
    }
     */
}
