package com.example.algorithm.solution;

import com.example.algorithm.solution.tree.ConstructBinaryTree;
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
        ConstructBinaryTree constructBinaryTree = new ConstructBinaryTree();
        constructBinaryTree.run();
    }
}
