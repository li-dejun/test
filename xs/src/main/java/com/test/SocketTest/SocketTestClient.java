package com.test.SocketTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketTestClient {
    //https://www.jianshu.com/p/3e2b6ebc86c9
    private String createSocket(String message) {
        //变量定义：应答报文
        String returnCode="";
        Socket socket = new Socket();
        OutputStream outputStream = null;
        InputStream inputStream = null ;
        try {
            //计算报文长度
            int mesLength = message.getBytes("GBK").length+4;//整个报文的长度
            String length = mesLength+"";//报文长度字符串
            if(String.valueOf(mesLength).length()<4){//报文长度占4位，不足4位补0
                for(int i=0;i<4-String.valueOf(mesLength).length();i++){
                    length = "0"+length;
                }
            }
            byte b[] = new byte[mesLength+10];
            message = length+message;
//            log.error("短信发送开始：IP为"+host+" 端口为："+port);
//            log.error("报文内容:"+message);
            // 建立socket连接
            socket.connect(new InetSocketAddress("127.0.0.1", Integer.parseInt("12345")), 2000);//设置网络连接请求超时时间2s\
//            log.error("短信发送建立连接成功！");
            socket.setSoTimeout(1000);//设置读取超时时间
            outputStream = socket.getOutputStream();
            //以GBK格式发送报文
            outputStream.write(message.getBytes("GBK"));
            //清空缓存并输出流
            outputStream.flush();
            //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
            socket.shutdownOutput();
//            log.error("短信发送完成，准备读取返回信息");
            //接受返回数据
            inputStream = socket.getInputStream();
            inputStream.read(b,0,b.length);
            System.out.println();
//            System.out.println("b is --->"+JSON.toJSONString(b));
            System.out.println("GBK b is --->"+new String(b,"GBK"));
            System.out.println("UTF8 is --->"+new String(b, StandardCharsets.UTF_8));

            int count = 0;
            while (count == 0) {
                count = inputStream.available();
            }
            System.out.println("count is -->"+count);
            byte[] buffer = new byte[count]; // 创建足够大小的byte数组
            int bytesRead = inputStream.read(buffer); // 读取数据

            if (bytesRead > 0) {
                // 处理buffer中的数据
//                log.info("打印buffer中的数据："+JSON.toJSONString(buffer));
            } else {
                // 输入流结束或没有数据可读
                System.out.println("输入流没有数据可读");
            }
            returnCode=new String(b);
            socket.shutdownInput();
            //发送后返回数据：0000成功
            System.out.println("发送后返回数据："+returnCode);
        } catch (Exception e) {
            System.out.println("连接到主机错误，请检查服务器地址或端口号正确性！"+e);
            String error = e.toString();
           /* if(error.indexOf("Connection reset")>-1)
            {
                //存储失败信息，用于页面提示
                StatusChangeContainer.getMessageFailInfoMap().put(new MSMProxyPT_MengWang().getClass().toString(), "短信平台交互异常，发送后未返回成功标示");
                //给失败原因变量赋值
                failreason = "短信平台交互异常，发送后未返回成功标示";
            }else if(error.indexOf("Connection refused")>-1)//端口通讯异常
            {
                //存储失败信息，用于页面提示
                StatusChangeContainer.getMessageFailInfoMap().put(new MSMProxyPT_MengWang().getClass().toString(), "短信平台通讯端口异常");
                //给失败原因变量赋值
                failreason = "短信平台通讯端口异常";
            }else//网络通讯异常 java.net.SocketTimeoutException: connect timed out
            {
                //存储失败信息，用于页面提示
                StatusChangeContainer.getMessageFailInfoMap().put(new MSMProxyPT_MengWang().getClass().toString(), "短信平台网络异常");
                //给失败原因变量赋值
                failreason = "短信平台网络异常";
            }*/
        }finally{
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    System.out.println("关闭输出流出错，原因为："+e);
                }
                if(inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        System.out.println("关闭输入流出错，原因为："+e);
                    }
                    if (socket != null){
                        try {
                            socket.close();
                        } catch (IOException e) {
                            System.out.println("关闭socket出错，原因为："+ e);
                        }
                    }
                }
            }
        }
        return returnCode;
    }
    public static void main(String[] args) {
        String a = "single^0001^mob^20151026^000020^13932485088^您帐户*0016于07月26日16时32分储蓄取款金额1000000.00元，余额195383.08元。^^";
        System.out.println(a.length());
        a = "0098single^0009^donghuan^20200427^000020^18660816025^2020-04-27 15:23:42-承德银行-总行中心机房-主机房空调2-报警恢复^^";
        a = "single^0009^S164^20240920^155338^18653159786^ceshi^^";

       SocketTestClient client = new SocketTestClient();
       client.createSocket(a);
    }
}
