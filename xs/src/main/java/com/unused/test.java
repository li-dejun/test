package com.unused;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class test {
    public static void main(String[] args) {
//        a();
        try {
            timeTest();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void s (){
        String l1 = "100510";
        String l2 = "36000";
        int overrun = Integer.parseInt(l1)-Integer.parseInt(l2);
        if(overrun<0) {
            overrun=0;
        }
        System.out.println("-->"+Double.toString(overrun));
        double overLimitRate = (overrun*100000)/(Integer.parseInt(l2)*0.001);
        System.out.println("-->"+Double.toString(overLimitRate));
        //四舍五入保留一位小数
        BigDecimal overLimitRateA = new BigDecimal(overLimitRate);
        double overLimitRateAA = overLimitRateA.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("--->"+overLimitRateAA+" ");
    }

    public static void a (){
        int num = 56;
        char c_n = (char) num;
        System.out.println(num);
        System.out.println(c_n);


    }


    public static SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm:ss");
    public static void timeTest() throws ParseException {
        String time1 = "08:00:01";
        String time2 = "08:00:02";

        System.out.println(time1+"->"+sdftime.parse(time1).getTime());
        System.out.println(time2+"->"+sdftime.parse(time2).getTime());
    }
}
