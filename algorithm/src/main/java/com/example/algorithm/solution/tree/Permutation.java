package com.example.algorithm.solution.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 对一组非重复数字进行排列，求所有排列结果
 * @Date: Created on 11:35 2021/2/3
 */


public class Permutation {
    private List<List<Integer>> arrangeMents;

    public Permutation() {
        arrangeMents = new ArrayList<>();
    }

    public List<List<Integer>> arrange(int[] numbers) {
        arrange(numbers, new LinkedList<Integer>());

        return arrangeMents;
    }

    /**
    * 策略树遍历，需要的参数：可供选择的策略，以及已经选过的策略
    */
    protected void arrange(int[] selectors, LinkedList<Integer> path) {
        /**
        * 结束，遍历至策略树的叶子节点
        */
        if (selectors.length <= 0) {
            arrangeMents.add((LinkedList<Integer>)path.clone());
            return;
        }

        for (int i = 0; i < selectors.length; ++i) {
            int n = selectors[i];
            /**
            * 选择策略
            */
            path.add(n);

            int[] leftSelectors = new int[selectors.length - 1];
            int ii = 0;
            for (int j = 0; j < selectors.length; ++j) {
                if (j != i) {
                    leftSelectors[ii] = selectors[j];
                    ++ii;
                }
            }

            arrange(leftSelectors, path);

            /**
            * 取消选择，策略回溯
            */
            path.removeLast();
        }
    }
}
