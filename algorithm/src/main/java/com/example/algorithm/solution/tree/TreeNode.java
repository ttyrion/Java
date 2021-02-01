package com.example.algorithm.solution.tree;

import java.util.ArrayList;
import java.util.List;

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

    public List<Integer> postOrder() {
        /**
        * left sub tree
        */
        List<Integer> leftPostOrder = new ArrayList<>();
        if (left != null) {
            leftPostOrder = left.postOrder();
        }

        /**
        * right sub tree
        */
        List<Integer> rightPostOrder = new ArrayList<>();
        if (right != null) {
            rightPostOrder = right.postOrder();
        }

        /**
        * root
        */
        List<Integer> postOrder = new ArrayList<>();
        postOrder.addAll(leftPostOrder);
        postOrder.addAll(rightPostOrder);
        postOrder.add(val);

        return postOrder;
    }

    /**
    * 后序遍历
    */
    @Override
    public String toString() {
        return postOrder().toString();
    }
}
