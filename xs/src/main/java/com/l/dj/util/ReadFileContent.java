package com.l.dj.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ReadFileContent {
    public static List<String> readFile02(String path) throws IOException {
        List<String> list = new ArrayList<>();
        StringBuilder file = new StringBuilder();
        // 使用一个字符串集合来存储文本中的路径 ，也可用String []数组
        FileInputStream fis = new FileInputStream(path);
        // 防止路径乱码 如果utf-8 乱码 改GBK
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            if (line.trim().length() > 0)
            list.add(line);
//            file.append(line);
        }
        br.close();
        isr.close();
        fis.close();
        return list;
    }

    /*public static void main(String[] args) {
        try{
            System.out.println(readFile02("C:\\Users\\Administrator\\Desktop\\小说\\my\\自动生成姓名.txt"));
        }catch (Exception e ){
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        Object in = new Object();
        in = (double) 123.32222222;
        String type = in.getClass().getName();
        System.out.println("type -->"+type);
    }
}
