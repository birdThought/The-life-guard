package com.lifeshs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;



/**
 *  文件操作工具类
 */
public class FileUtils {

    /**
     *  获得一个文件对象
     *
     *  @param fileName 文件名
     */
    public static File getFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            // 如果文件目录不存在，就创建目录
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();   // 在磁盘上创建文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
    
    /**
     *  读文件
     *
     *  @param file 文件对象
     */
    public static String readFile(File file) {
        Reader reader = null;
        StringBuffer sb = new StringBuffer();
        
        char[] tempChars = new char[30];
        int charRead = 0;
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            while ((charRead = reader.read(tempChars)) != -1) {
                if ((charRead == tempChars.length) && (tempChars[tempChars.length - 1] != '\r')) {
                    sb.append(tempChars);
                } else {
                    for (int i = 0; i < charRead; i++) {
                        if (tempChars[i] == '\r') {
                            continue;
                        } else {
                            sb.append(tempChars[i]);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return sb.toString();
    }
    
    /**
     *  写文件
     *
     *  @param file 文件对象
     *  @param content 写入内容
     *  @param append 是否以追加方式写入
     */
    public static void writeFile(File file, String content, boolean append) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, append);
            PrintWriter pw = new PrintWriter(fw);
            pw.write(content);
            pw.flush();
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String saveAPKFile(String fileName,byte [] buf){
        if(fileName==null)
            return null;
        File file=new File(CommonParam.apk_download_tmp,fileName);
        try {
            FileOutputStream out=new FileOutputStream(file);
            out.write(buf);
            out.close();
            return file.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String copyFileToOtherPath(String oldFileName,String newRootDir){
        File oldFile=new File(oldFileName);
        if(!oldFile.exists())
            return null;
        oldFileName=oldFileName.replaceAll("\\\\", "/");
        String fileName=oldFileName.substring(oldFileName.lastIndexOf("/")+1,oldFileName.length());
        File newPath=new File(newRootDir,fileName);
        try {
            FileInputStream inputStream=new FileInputStream(oldFile.getPath());
            byte [] buf=new byte[1024];
            int len =0;
            FileOutputStream outputStream=new FileOutputStream(newPath);
            while ((len=inputStream.read(buf))!=-1){
                outputStream.write(buf,0,len);
            }
            inputStream.close();
            outputStream.close();
            oldFile.delete();
            return newPath.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
