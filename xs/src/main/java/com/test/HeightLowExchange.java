package com.test;

/**
 * 高低位互换
 */
public class HeightLowExchange {
    public static void main(String[] args) {
        int num = 123;
        int heightBits = (num & 0xff) << 8;
        int lowBits = (num >> 8) & 0xff;
        int result = heightBits | lowBits;
        System.out.println("高位：" + heightBits);
        System.out.println("底位：" + lowBits);
        System.out.println("互换：" + result);

        System.out.println("*********************1*****************");
        byte num1 = (byte)0x89;
        byte hBits = (byte)((num1 & 0xff) << 4);
        byte lBits = (byte)((num1 >> 4) & 0xff);
        byte r = (byte)(hBits | lBits);
        System.out.println("高位：" + hBits);
        System.out.println("底位：" + lBits);
        System.out.println("互换：" + r);
        System.out.println("a：" + 0x81);
        System.out.println("a：" + 0x18);

        System.out.println("*********************2*****************");
        byte b = (byte) 0x81;
        System.out.println(b);
        System.out.println((byte) (((b & 0xff) >> 4) ^ ((b & 0xff) << 4)));
        System.out.println((byte)0x18);




        //单字节实现高低位互换
        byte originalByte = 0x1A; // 示例字节：00011010
        byte swappedByte = swapBits(originalByte);
        System.out.println("Original byte: " + Integer.toBinaryString(originalByte & 0xFF));
        System.out.println("Swapped byte:  " + Integer.toBinaryString(swappedByte & 0xFF));

    }


    public static byte swapBits(byte b) {
        // 通过异或操作交换字节的高低四位
        return (byte) ((b >> 4) ^ (b << 4));
    }
}
