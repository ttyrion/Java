package com.example.algorithm.solution.dp;


/**
 * @Description: 根据现有硬币面值（硬币个数不限），找出凑到面值N所需的最少硬币个数
 * @Date: Created on 18:21 2021/2/2
 */


public class ChangeMaker {
    /**
    * 现有硬币的面值
    */
    private int[] coinValue = {1, 2, 5};

    /**
     * @Description: 找出凑到金额value所需的最少硬币个数
     * @param value : 目标金额
     * @Return int
     */
    public int getLeastCoinsOfValue(int value) {
        if (value < 0) {
            return -1;
        } else if (value == 0) {
            return 0;
        }

        int solution = Integer.MAX_VALUE;
        for (int v : coinValue) {
            int subSolution = getLeastCoinsOfValue(value - v);
            /**
            * -1 表示无解
            */
            if (subSolution < 0) {
                continue;
            }

            solution = (solution - (subSolution + 1) > 0) ? (subSolution + 1) : solution;
        }

        return solution;
    }
}
