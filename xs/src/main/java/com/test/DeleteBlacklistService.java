package com.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 定期删除黑名单文件
 * @author
 */
public class DeleteBlacklistService extends Thread{

//    protected static Log log = LogFactory.getLog(DeleteBlacklistService.class);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void run(){
    	while (true) {
			try {
				//黑名单解压缩路径
				String BLACKLISTPATH = "C:\\Test";
				//黑名单FTP存储路径
				String BLACKLISTPATHFTP = "C:\\TestZip";
				//删除若干过期黑名单
				getBlacklistFileAndDel(BLACKLISTPATH);
                //删除若干过期黑名单压缩包
				getBlacklistCompressFileAndDel(BLACKLISTPATHFTP);
				//5分钟轮询一次
				sleep(1000*60*5);
			}catch (Exception e){
//				log.error("定期删除黑名单出错",e);
			}
		}
    }

    /**
     *  根据路径获取黑名单文件夹，并删除若干过期黑名单
     * @param path
     */
    public void getBlacklistFileAndDel(String path){
        //黑名单路径
//        String path = "C:\\Test";

        File blacklistDir = new File(path);

        if (blacklistDir.isDirectory()){
            //获取所有文件夹和文件
            File[] files = blacklistDir.listFiles();

            List<File> list_5 = new ArrayList<>();
            List<File> list_7 = new ArrayList<>();

            //遍历，获取 5或7开头文件夹
            if (files != null){
                for (File file : files){
                    //文件夹 是否是5或7开头
                    if (file.isDirectory() && file != null ){
                        String fileName = file.getName();
                        //文件夹以5开头
                        if (fileName.length() > 0 && "5".equals(fileName.substring(0,1))){
                            list_5.add(file);
                        }
                        //文件夹以7开头
                        else if (fileName.length() > 0 && "7".equals(fileName.substring(0,1))){
                            list_7.add(file);
                        }
                    }
                }
            }
            //集合转数组
            File[] array5 = new File[list_5.size()];
            array5 = list_5.toArray(array5);
            //保留30个文件
            deleteFiles(array5,30);

            //集合转数组
            File[] array7 = new File[list_7.size()];
            array7 = list_7.toArray(array7);
            //保留3个文件
            deleteFiles(array7,3);
        }
    }

    /**
     * 删除若干过期黑名单压缩文件
     * @param path
     */
    public void getBlacklistCompressFileAndDel(String path){
        //黑名单路径
//        String path = "C:\\Test";

        File blacklistDir = new File(path);

        if (blacklistDir.isDirectory()){
            //获取所有文件夹和文件
            File[] files = blacklistDir.listFiles();

            List<File> list_5 = new ArrayList<>();
            List<File> list_7 = new ArrayList<>();

            //遍历，获取 5和7开头文件
            if (files != null){
                for (File file : files){
                    //文件 是否是5或7开头
                    if (!file.isDirectory() && file != null ){
                        String fileName = file.getName();
                        //文件以5开头
                        if (fileName.length() > 0 && "5".equals(fileName.substring(0,1))){
                            list_5.add(file);
                        }
                        //文件以7开头
                        else if (fileName.length() > 0 && "7".equals(fileName.substring(0,1))){
                            list_7.add(file);
                        }
                    }
                }
            }
            //集合转数组
            File[] array5 = new File[list_5.size()];
            array5 = list_5.toArray(array5);
            //保留30个文件
            deleteFiles(array5,30);

            //集合转数组
            File[] array7 = new File[list_7.size()];
            array7 = list_7.toArray(array7);
            //保留3个文件
            deleteFiles(array7,3);
        }
    }

    /**
     *  删除文件夹下文件
     * @param files 文件夹对象数组
     * @param saveFilesNum 保留最新文件的个数
     */
    public void deleteFiles(File[] files, int saveFilesNum){
        if (files != null) {
            //按修改时间重新排序，最新的在前面
            Arrays.sort(files, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    if (o1.lastModified() > o2.lastModified()) {
                        return -1;
                    } else if (o1.lastModified() < o2.lastModified()) {
                        return 1;
                    }
                    return 0;
                }
            });

            int overNum = 0;
            for (int i = 0; i < files.length; i++){
                File file = files[i];

                overNum++;
                //超出规定个数的删除
                if (overNum > saveFilesNum){
                    Date lastModifiedDate = new Date(file.lastModified());
//                    log.info("黑名单文件定期删除: "+file.getPath() + " - 文件修改日期：" + sdf.format(lastModifiedDate));
                    System.out.println("黑名单文件定期删除: "+file.getPath() + " - 文件修改日期：" + sdf.format(lastModifiedDate));
//                        file.delete();
                    deleteFolder(file);
                }
            }
        }
    }

    /**
     * 删除文件 （如果是文件夹，同时删除子文件）
     * @param folder
     */
    public static void deleteFolder(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteFolder(file);
                }
            }
        }
        folder.delete();
    }

    public static void main(String[] args) {
        DeleteBlacklistService d = new DeleteBlacklistService();
        d.run();
    }
}
