package com.example.algorithm.solution;

import com.example.algorithm.solution.dp.ChangeMaker;
import com.example.algorithm.solution.tree.ConstructBinaryTree;
import com.example.algorithm.solution.tree.TreeNode;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Date: Created on 17:30 2021/1/31
 */

@Component
public class SolutionRunner implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        // runConstructBinaryTreeSample();
        runMoneySample();
    }

    /**
     * run sample
     */
    public void runConstructBinaryTreeSample() {
        ConstructBinaryTree constructBinaryTree = new ConstructBinaryTree();

        int[] preOrder = {8, 3, 12, 20, 18, 19, 9, 7, 50};
        int[] inOrder = {20, 12, 3, 19, 18, 8, 7, 9, 50};

        TreeNode root = constructBinaryTree.buildTree(preOrder, inOrder);

        System.out.println(root);
    }

    public void runMoneySample() {
        ChangeMaker changeMaker = new ChangeMaker();
        int change = 23;
        int coins = changeMaker.getLeastCoinsOfValue(change);
        System.out.println("make change of " + change + " nees at least " + coins + " coins.");
    }
}
