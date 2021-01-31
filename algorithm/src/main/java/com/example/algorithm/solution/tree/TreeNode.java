package com.example.algorithm.solution.tree;

/**
 * @Description:
 * @Date: Created on 17:31 2021/1/31
 */


public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(val);

        if (left != null) {
            stringBuilder.append(" ").append(left.val);
        }

        if (right != null) {
            stringBuilder.append(" ").append(right.val);
        }

        return stringBuilder.toString();
    }
}
