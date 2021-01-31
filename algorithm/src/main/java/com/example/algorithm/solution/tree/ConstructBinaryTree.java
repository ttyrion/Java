package com.example.algorithm.solution.tree;

/**
 * @Description: Given preorder and inorder traversal of a tree, construct the binary tree.
 * @Date: Created on 17:31 2021/1/31
 * https://medium.com/@harycane/construct-binary-tree-from-preorder-and-inorder-traversal-2b6797cd209d
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
        */
        int indexOfRoot = -1;
        for (int i = inStart; i <= inEnd; ++i) {

            if (inOrder[i] != root.val) {
                continue;
            }

            /**
            * double check: we assume duplicates exist in this tree.
            */
            boolean succeed = true;
            for (int iPre = preStart + 1, iIn = inStart;
                 iPre <= preEnd && iIn <= inEnd;
                 ++iPre, ++iIn) {
                if (iIn == i) {
                    ++iIn;
                    if (iIn > inEnd) {
                        succeed = false;
                        break;
                    }
                }

                if (preOrder[iPre] != inOrder[iIn]) {
                    succeed = false;
                    break;
                }
            }

            if (succeed) {
                indexOfRoot = i;
            }
        }

        /**
         * construct left sub tree
         */
        int preStartOfLeft = preStart + 1;

        int nodesOfLeft = indexOfRoot;
        int preEndOfLeft = preStartOfLeft + nodesOfLeft -1;

        int inStartOfleft = inStart;
        int inEndOfLeft = indexOfRoot - 1;

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

    /**
    * run sample
    */
    public void run() {
        int[] preOrder = {1, 5, 18, 8, 9, 20, 7, 8, 12, 20, 25};
        int[] inOrder = {8, 18, 9, 5, 20, 1, 8, 12, 7, 20, 25};
        TreeNode root = buildTree(preOrder, inOrder);

        System.out.println(root);
    }
}
