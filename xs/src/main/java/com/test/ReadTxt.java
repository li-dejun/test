package com.test;

import com.alibaba.fastjson2.JSON;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReadTxt {

    public static void ReadTxtFile(){
        try{
            String txtPath = "C:\\Users\\Administrator\\Desktop\\小说\\my\\自动生成姓名.txt";
            FileInputStream inputStream = new FileInputStream(txtPath);
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String strTem = "";

            //原始数组
            ArrayList<String> arrayList = new ArrayList<>();
            //去重数组
            HashSet<String> set = new HashSet<>();
            //重复项
            ArrayList<String> dupList = new ArrayList<>();

            //控制台遍历打印
            while ((strTem = bufferedReader.readLine()) != null ){
                if (strTem.trim() != "" && strTem.length() > 0){
                    System.out.println(strTem);
                    if (ReDup(arrayList,strTem)){
                        dupList.add(strTem);
                    }
                    arrayList.add(strTem);
                    set.add(strTem);
                }
            }
            System.out.println(JSON.toJSONString(arrayList));
            System.out.println("array.size-->"+arrayList.size());
            System.out.println("set.size-->"+set.size());
            System.out.println("dupArray-->"+JSON.toJSONString(dupList));
            bufferedReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static boolean ReDup(ArrayList<String> arrayList, String name){
        AtomicBoolean flag = new AtomicBoolean(false);
        arrayList.forEach(item ->{
            if (name.equals(item)){
                flag.set(true);
            }
        });
        return flag.get();
    }

    public static void main(String[] args) {
        ReadTxtFile();
    }
}
