package com.example.algorithm.solution;

import com.example.algorithm.solution.dp.ChangeMaker;
import com.example.algorithm.solution.tree.ConstructBinaryTree;
import com.example.algorithm.solution.tree.NQueen;
import com.example.algorithm.solution.tree.Permutation;
import com.example.algorithm.solution.tree.TreeNode;
import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description:
 * @Date: Created on 17:30 2021/1/31
 */

@Component
public class SolutionRunner implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        // runConstructBinaryTreeSample();
        // runMoneySample();
        // runNumberPermutationSample();
        runNQueenSample();
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

    public void runNumberPermutationSample() {
        Permutation permutation = new Permutation();
        int[] numbers = { 1, 4, 6, 2, 49, 3};
        List<List<Integer>> numberArrangeMents = permutation.arrange(numbers);
        for (List<Integer> arrangeMent : numberArrangeMents) {
            System.out.println(arrangeMent);
            System.out.println("\n");
        }
    }

    public void runNQueenSample() {
        NQueen nQueen = new NQueen();
        LinkedList<LinkedList<NQueen.Coordinate>> nQeenSolutionList = nQueen.arrange(10);
        for (LinkedList<NQueen.Coordinate> solution : nQeenSolutionList) {
            System.out.println(solution);
            System.out.println("\n");
        }
    }
}
