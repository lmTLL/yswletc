package com.yswl.yswletc.common.utils;

import javax.imageio.stream.FileImageInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * User: jang
 * Date: 2019/10/9
 * Time: 11:07
 * Description: No Description
 */
public class pictureUitil {
    /**
     将图像转为二进制数组
     * @param path
     * @return
     */
    public static byte[] readImageFile(String path){
        byte[] data = null;
        FileImageInputStream input = null;
        try {

            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }
    /**
     * 将文件转为二进制数组
     * @param
     * @return
     */
    public static byte[] readFile(File file){
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(file);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }
}
