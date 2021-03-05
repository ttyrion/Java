package com.example.algorithm.solution.slideWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 找出S中包含T中全部字母的最短子串
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
                if (window.get(cl) == need.getOrDefault(cl, -1)) {
                    --valid;
                }
                window.put(cl, window.getOrDefault(cl, 0) - 1);
            }
        }

        /**
        * 注意，窗口退出滑动时候，窗口状态是(left, right]，并且这个状态是不满足条件的
         * 满足条件的子串应该是窗口(left-1, right], 也就是[left, right]
        */

        if (length < s.length()) {
            return s.substring(left, left + length);
        }

        return "";
    }
}
