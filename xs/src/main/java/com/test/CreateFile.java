package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class CreateFile {

    public static void main(String[] args) {

        String dirPath = "C://TestFile/file";

        CreateFile cf = new CreateFile();

//        cf.createDirectory(dirPath);

//        cf.createFile(dirPath);

//        cf.createTest();

        cf.dateFormatTest2();
//        String date = "2023-01-26 16:23:12 测试数据";
//        System.out.println(date.substring(0,19));
    }

    /**
     * 创建目录
     */
    public void createDirectory(String dirPath){
        try{

            File file = new File(dirPath);

            if (!file.exists()){
                //创建单级目录
//                file.mkdir();
                //创建多级目录
                file.mkdirs();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 创建文件
     * @param dirPath
     */
    public void createFile(String dirPath){
        try {
            String filePath = dirPath + File.separator + "text.txt";
            System.out.println("文件路径-->"+filePath);
            File file = new File(filePath);
            /*if (!file.exists()){

            }*/
            if (file.createNewFile()){
                System.out.println("创建文件成功");
            }else{
                System.out.println("创建文件失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createTest(){
        try {
           FileInputStream inputStream =  new FileInputStream("C://aaa.zip");
           if (inputStream == null){
               System.out.println("is null");
           }else {
               System.out.println("is not null");
           }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 七天前日期
     */
    public void dateFormatTest(){
        LocalDate localDate = LocalDate.now();

        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < 7; i++){
            LocalDate previousDate = localDate.minusDays(i);
            System.out.println(previousDate.format(sdf));
        }
    }

    public void dateFormatTest2(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //创建 Calendar 对象并设置为当前时间
        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 6; i++){
            // 将日期向前调整7天
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            // 输出结果
            System.out.println("七天前的日期是：" + sdf.format(calendar.getTime()));
        }
    }

    /**
     * 泛型测试
     */
    public <T> void Generics(T param){
        System.out.println("泛型参数--> 类型：" + param.getClass() + ", 值："+param);
    }
}
