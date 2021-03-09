package com.example.algorithm;

import com.example.algorithm.solution.slideWindow.ShortestSubString;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlgorithmApplication {
    static class Original implements Cloneable {
        Integer i;

        @Override
        public Object clone() throws CloneNotSupportedException {
            Original o = (Original)super.clone();
            return o;
        }
    }

    public static void main(String[] args) {
        String s = "i love banana and apple.";
        String t = "dn";
        System.out.println("source: " + s);
        System.out.println("need: " + t);
        System.out.println("dest: " + ShortestSubString.findArrangementSubString(s,t));

        SpringApplication.run(AlgorithmApplication.class, args);
    }

}
