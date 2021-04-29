package com.learn.algorithm.graph;

/**
 * @description: 节点
 * @author: Huo
 * @create: 2020-06-01 17:03
 */
public class TreeNode {
    int data;
    TreeNode leftNode;
    TreeNode rightNode;
    public TreeNode() {

    }
    public TreeNode(int d) {
        data=d;
    }

    public TreeNode(TreeNode left,TreeNode right,int d) {
        leftNode=left;
        rightNode=right;
        data=d;
    }
}
