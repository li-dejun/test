package com.test;

import java.util.Map;

public class LotteryTest {

    public static void main(String[] args) {
        int[] BEFORE = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35};
        int[] AFTER = {1,2,3,4,5,6,7,8,9,10,11,12};

        int b = 0;
        for (int i = 0; i< 100; i++){
            b = (int)(Math.random()*BEFORE.length);
            System.out.println(b);
        }
    }
}
