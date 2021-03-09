package com.example.algorithm.solution.slideWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * 滑动窗口的关键也就几点：
 * 1. 窗口区间
 * 2. 什么条件下窗口右侧一直扩张
 * 3. 什么条件下窗口左侧一直收缩
 * 4. 窗口滑动过程中更新窗口状态，窗口状态记录的数据结构，其实是跟问题有关的，问题要求满足什么条件，窗口数据结构记录的数据能
 *    让我们进行条件判断即可。
 * 5. 什么时候记录（当前）结果
 * @Date: Created on 12:53 2021/3/5
 */


public class ShortestSubString {

    /**
    * 找出S中包含T中全部字母的最短子串
    */
    public static String findShortestSubString(String s, String t) {
        /**
        * 窗口：(left, right]
        */
        int left = -1;
        int right = -1;

        /**
        * 目标条件
        */
        Map<Character, Integer> need = new HashMap<>();
        int size = t.length();
        for (int i = 0; i < size; ++i) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        /**
        * 目标条件的简易表达
        */
        int valid = 0;
        int needValid = need.size();

        /**
        * 滑动窗口状态
        */
        Map<Character, Integer> window = new HashMap<>();

        /**
        * 记录最终结果(子串)
        */
        int start = -1;
        int length = Integer.MAX_VALUE;

        size = s.length();
        while(right < size - 1) {
            /**
             * 窗口扩张
             */
            ++right;

            /**
            * 更新窗口
            */
            char cr = s.charAt(right);
            window.put(cr, window.getOrDefault(cr, 0) + 1);
            if (window.get(cr) == need.getOrDefault(cr, -1)) {
                ++valid;
            }

            /**
            * 满足条件后，窗口持续收缩至再次不满足条件，可以得到目前为止最短子串
            */
            while(valid == needValid) {
                if (right - left < length) {
                    start = left + 1;
                    length = right - left;
                }

                /**
                 * 窗口收缩
                 */
                ++left;

                /**
                 * 更新窗口
                 */
                char cl = s.charAt(left);
                int clNum = window.getOrDefault(cl, 0) - 1;
                if (clNum > 0) {
                    window.put(cl, clNum);
                } else {
                    window.remove(cl);
                }

                if (window.getOrDefault(cl, 0) == need.getOrDefault(cl, -1) - 1) {
                    --valid;
                }
            }
        }

        /**
        * 注意，窗口退出滑动时候，窗口状态是(left, right]，并且这个状态是不满足条件的
         * 满足条件的子串应该是窗口(left-1, right], 也就是[left, right]
        */

        if (length < s.length()) {
            return s.substring(start, start + length);
        }

        return "";
    }

    /**
    * 找出S中是否包含一个由T中字母排列成的子串
    */
    public static String findArrangementSubString(String s, String t) {
        /**
         * 窗口：(left, right]
         */
        int left = -1;
        int right = -1;

        /**
         * 目标条件
         */
        Map<Character, Integer> need = new HashMap<>();
        int size = t.length();
        for (int i = 0; i < size; ++i) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        /**
         * 目标条件的简易表达
         */
        int valid = 0;
        int needValid = need.size();

        /**
         * 滑动窗口状态
         */
        Map<Character, Integer> window = new HashMap<>();

        /**
         * 记录最终结果(子串)
         */
        int start = -1;
        int length = Integer.MAX_VALUE;

        size = s.length();
        while(right < size - 1) {
            /**
             * 窗口扩张
             */
            ++right;

            /**
             * 更新窗口
             */
            char cr = s.charAt(right);
            window.put(cr, window.getOrDefault(cr, 0) + 1);
            if (window.get(cr) == need.getOrDefault(cr, -1)) {
                ++valid;
            }

            /**
             * 满足条件后，窗口持续收缩至再次不满足条件，可以得到目前为止最短子串
             */
            while(valid == needValid) {
                if (right - left < length) {
                    start = left + 1;
                    length = right - left;
                }

                /**
                * 找排列子串的问题，相比于前面的找最短子串的问题，只不过是多了一个条件：
                 * 子串的长度必须等于t的长度，不可能有更短的了
                */
                if (length == t.length()) {
                    return s.substring(start, start + length);
                }

                /**
                 * 窗口收缩
                 */
                ++left;

                /**
                 * 更新窗口
                 */
                char cl = s.charAt(left);
                int clNum = window.getOrDefault(cl, 0) - 1;
                if (clNum > 0) {
                    window.put(cl, clNum);
                } else {
                    window.remove(cl);
                }

                if (window.getOrDefault(cl, 0) == need.getOrDefault(cl, -1) - 1) {
                    --valid;
                }
            }
        }

//        /**
//         * 注意，窗口退出滑动时候，窗口状态是(left, right]，并且这个状态是不满足条件的
//         * 满足条件的子串应该是窗口(left-1, right], 也就是[left, right]
//         */
//
//        if (length < s.length()) {
//            return s.substring(start, start + length);
//        }

        return "";
    }
}
