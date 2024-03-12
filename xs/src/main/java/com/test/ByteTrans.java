package com.test;

public class ByteTrans {

    /**
     * int值转为4个byte数值
     */
    public static void main(String[] args) {
        trans2();
    }
    public static void trans1(){
        int a = 1445435;
        //先利用移位符将bit字节右移24位
        //利用byte转int类型数据只保留低8位特性获取int值的高8位
        byte i0 = (byte) ((a >>24) & 0xFF);

        //先利用移位符将bit字节右移16位
        //利用byte转int类型数据只保留低8位特性获取int值的次高8位
        byte i1 = (byte) ((a >>16) & 0xFF);

        //先利用移位符将bit字节右移8位
        //利用byte转int类型数据只保留低8位特性获取int值的次低8位
        byte i2 = (byte) ((a >>8) & 0xFF);

        //利用byte转int类型数据只保留低8位特性获取int值的低8位
        byte i3 = (byte) (a & 0xFF);

        System.out.println(""+i0+" "+i1+" "+i2+" "+i3+" ");
    }

    /**
     * 高低位互换
     */
    public static void trans2(){
        int num = 123;
        int heiBits = (num & 0xff) << 8;
        int lowBits = (num >> 8) & 0xff;
        int sw = heiBits | lowBits;
    }
}
