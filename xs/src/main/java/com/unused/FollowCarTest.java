package com.unused;

import java.awt.*;

public class FollowCarTest {
    //是否允许落杆（车辆允许通行）
    static boolean isAlowDown = false; //是否允许落杆。 true: 车压过第二个线圈了，可以落杆了；   false：车还没压过第二个线圈，不能落杆
    //是否允许连续跟车（允许的话就不用落杆，）（第一辆车允许通行的前提下，连续跟车才有用）
    static boolean isAlowFollow = false; //是否允许落杆-连续跟车. true: 连续跟车，后车允许同行; false：不想允许连续跟车

    //跟车状态：true：跟车，false：未跟车
    static boolean isFollow = false;
    public static void main(String[] args) {
        int[] sigArr1Car = {1,0,2,0};
        int[] sigArr2Car1 = {1,0,2,1,0,0,2,0};
        int[] sigArr2Car2 = {1,0,1,2,0,0,2,0};
        int[] sigArr3Car0 = {1,0,1,2,0,0,  2,0   , 1,0,2,0};
        int[] sigArr3Car1 = {1,0,1,2,0,0,1,2,0,0,2,0};
        int[] sigArr3Car2 = {1,0,1,2,0,0,2,1,0,0,2,0};
        for (int s:
             sigArr3Car0) {
            downGan(s);
        }
    }

    public static void downGan(int sig){


        if(sig == 1){
            //进行抓拍车牌

            //允许通行的话进行抬杆命令

            //第二辆车确定是否通行，确定是否允许跟车
            if(isAlowDown == true){
                //是否允许跟车
                isAlowFollow = true;

                //跟车状态
                isFollow = true;
            }else{
                isAlowFollow = false;

                isFollow = false;
            }
            System.out.println("车压过第 1 个线圈，抬杆");
        }else if(sig == 2){

            //????重置是否允许连续跟车（会有和第一个线圈设置是否允许跟车的先后问题）
            if(isAlowDown == true && isFollow == true){
                isAlowFollow = false;
            }else{
                isAlowFollow = true;
            }

            //允许通行的车辆已经通过，允许落杆
            isAlowDown = true;
            System.out.println("车压过第 2 个线圈");
        }else if(sig == 0){
            System.out.println("0--"+isAlowDown+","+isAlowFollow+"--");
            if(isAlowDown && !isAlowFollow){//允许落杆 且 不允许连续跟车
                //进行落杆命令
                System.out.println("落杆");
                //落完杆后，重置是否允许落杆和连续跟车
                isAlowDown = false;
                isAlowFollow = false;

                isFollow = false;
            }
        }
    }
}
