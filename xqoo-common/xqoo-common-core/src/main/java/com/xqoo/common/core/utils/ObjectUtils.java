package com.xqoo.common.core.utils;

import java.io.*;

/**
 * Object 对象工具
 * @author Gaoyang by 2020-11-24
 */
public class ObjectUtils {

    /// <summary>
    /// 将对象转换为byte数组
    /// </summary>
    /// <param name="obj">被转换对象</param>
    /// <returns>转换后byte数组</returns>
    public static byte[] ObjectToBytes(Object obj)
    {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    /// <summary>
    /// 将byte数组转换成对象
    /// </summary>
    /// <param name="buff">被转换byte数组</param>
    /// <returns>转换完成后的对象</returns>
    public static Object BytesToObject(byte[] buff)
    {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(buff);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

}
