package com.test;

//import com.alibaba.fastjson2.JSONObject;

import jdk.nashorn.api.scripting.JSObject;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        byte[] bytes = new byte[]{
            (byte) 0x01,
            (byte) 0x02,
            (byte) 0x03,
        };
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < bytes.length; i++){
            if (bytes[i] == (byte) 0xFF){
                endIndex = i;
            }
            if (bytes[i] == (byte) 0xFF && bytes[i+1] == (byte) 0xFF){
                startIndex = i;
            }
        }

        byte[] b = Arrays.copyOfRange(bytes,1,bytes.length);

//        System.out.println(b.length+"-"+b[0]+"-"+b[1]);
//        byte[] b = Arrays.copyOfRange(bytes,startIndex+2,endIndex);
//        System.out.println("-->"+ JSONObject.toJSONString(b));

        short a = 0x04EA;
        byte a1 = (byte)(a >> 8);
        byte a2 = (byte)(a & 0xff00);
        System.out.println(a);
        System.out.println(a1);
        System.out.println(a2);
    }

}
