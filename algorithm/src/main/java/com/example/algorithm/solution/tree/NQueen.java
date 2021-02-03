package com.example.algorithm.solution.tree;

import sun.plugin.dom.core.CoreConstants;

import java.util.LinkedList;

/**
 * @Description: N皇后问题，这里借助坐标(以N*N棋盘左上角为原点，正右方为正X轴，正下方为正Y轴，每格棋盘坐标单位为1)来判断选择是否合法，似乎更简单
 * @Date: Created on 15:22 2021/2/3
 */


public class NQueen {

    private LinkedList<LinkedList<Coordinate>> arrangeMents;

    public NQueen() {
        arrangeMents = new LinkedList<>();
    }



    public LinkedList<LinkedList<Coordinate>> arrange(int N) {
        arrange(0, N, new LinkedList<Coordinate>());

        return arrangeMents;
    }

    /**
     * @Description:
     * @param row :
     * @param N : row 和 N 即可表示当前可供选择的策略了，并且还能确定一个完整的决策过程结束的条件。
     *          或者说，其实这里通过row和N这两个参数传入了一个可选择的策略列表：所有y坐标为row，x坐标为[0,N-1]的点。
     * @param path :
     * @Return void
     */
    protected void arrange(int row, int N, LinkedList<Coordinate> path) {
        /**
        * 可供选择的策略，退出条件
        */
        if (row >= N) {
            arrangeMents.add((LinkedList<Coordinate>)path.clone());
            return;
        }

        /**
        * 遍历可供选择的策略：选择，递归，取消选择
        */
        for (int selector = 0; selector < N; ++selector) {
            /**
            * x,y 代表当前的选择
            */
            int x = selector;
            int y = row;

            /**
            * 根据之前选择过的策略判断当前选择是否合法
            */
            boolean isValid = true;
            for (Coordinate coordinate : path) {
                /**
                * 同一行或同一列的选择都不合法
                */
                if (coordinate.x == x || coordinate.y == y) {
                    isValid = false;
                    break;
                }

                /**
                * 45度方向的选择都不合法
                */
                if ( (x - coordinate.x == y - coordinate.y) || (x - coordinate.x == coordinate.y - y)) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                /**
                * 做出选择
                */
                path.add(new Coordinate(x, y));

                /**
                * 继续后一步的决策，在深度上继续遍历决策树
                */
                arrange(row+1, N, path);

                /**
                * 取消选择，即在决策树上回溯，准备后续在广度上遍历可选择策略列表中的下一个策略
                */
                path.removeLast();
            }
        }
    }

    /**
     * 描述坐标，标记一个选择的策略
     */
    public static class Coordinate {
        public int x;
        public int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
