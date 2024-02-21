package com.test;


import java.util.ArrayList;
import java.util.List;

/**
 * 泛型的使用
 *  1、在类或接口上使用
 *      在类名后用 <T> 表明传入数据类型为任意引用数据类型，多参数用<T, K, V>
 *  2、extends类型约束
 *      extends表示传入类型只能为约束类型的子类
 *  3、在方法上使用
 *      需要在方法的返回类型前用 <T> 定义传入数据类型为泛型
 *      在方法()中传入 T param参数
 *  4、通配符的使用
 *      在指定List<Object>时，可以使用List<?>，表示List的参数可以匹配任意类型
 */
public class Generics<T extends Object> {

    /**
     * 接收参数
     */
    T param;

    /**
     * 构造函数
     */
    Generics(T param){
        this.param = param;
    }

    /**
     * 方法传入参数使用泛型
     * @param param
     * @param <T>
     * @return
     */
    public static <T, K extends Object> String print(T param, K param2){
        System.out.println("--->"+param + " ---->"+param2);
        return "";
    }

    /**
     * 通配符的使用
     */
    public static void mian2(String[] strings){
        List<String> list1 = new ArrayList<>();
        list1.add("123");
        print2(list1);

        List<Object> list2 = new ArrayList<>();
        list2.add(1234L);
        print2(list2);

    }

    /**
     * 在List<?></>中用?表示可以是任意类型的参数类型
     *  extends 表示传入类型必须为Object的子类
     *  super 表示传入类型必须为Object的父类
     * @param pList
     */
    public static void print2(List<? extends Object> pList){
        System.out.println(pList);
    }
}
