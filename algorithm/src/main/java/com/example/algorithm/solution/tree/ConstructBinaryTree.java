package com.example.algorithm.solution.tree;

/**
 * @Description: Given preorder and inorder traversal of a tree, construct the binary tree.
 * @Date: Created on 17:31 2021/1/31
 * https://medium.com/@harycane/construct-binary-tree-from-preorder-and-inorder-traversal-2b6797cd209d
 */

/**
* 前序遍历结果: root.val PRE{root.left} PRE{root.left}
* 中序遍历结果：IN{root.left} root.val IN{root.right}
*/

public class ConstructBinaryTree {

    public TreeNode buildTree(int[] preOrder, int[] inOrder) {
        return buildTree(preOrder, 0, preOrder.length - 1,
                inOrder, 0, inOrder.length -1);
    }

    private TreeNode buildTree(int[] preOrder, int preStart, int preEnd,
                          int[] inOrder, int inStart, int inEnd) {
        /**
        * Note: base condition in recursion, in case of infinite loop
        */
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        /**
        * contruct root node
        */
        TreeNode root = new TreeNode(preOrder[preStart], null, null);

        /**
        * the index of root in inOrder, it's what we need to find out
         * we assume duplicates do not exist in this tree.
        */
        int indexOfRoot = -1;
        for (int i = inStart; i <= inEnd; ++i) {

            if (inOrder[i] == root.val) {
                indexOfRoot = i;
                break;
            }
        }

        /**
         * construct left sub tree
         */
        int preStartOfLeft = preStart + 1;

        // int nodesOfLeft = indexOfRoot;
        int nodesOfLeft = indexOfRoot - inStart;
        int preEndOfLeft = preStartOfLeft + nodesOfLeft -1;

        int inStartOfleft = inStart;
        // int inEndOfLeft = indexOfRoot - 1;
        int inEndOfLeft = inStartOfleft + nodesOfLeft - 1;

        root.left = buildTree(preOrder, preStartOfLeft, preEndOfLeft,
                inOrder, inStartOfleft, inEndOfLeft);

        /**
        * construct right sub tree
        */
        int preStartOfRight = preEndOfLeft + 1;
        int preEndOfRight = preEnd;
        int inStartOfRight = indexOfRoot + 1;
        int inEndOfRight = inEnd;
        root.right = buildTree(preOrder, preStartOfRight, preEndOfRight,
                inOrder, inStartOfRight, inEndOfRight);

        return root;
    }
}
