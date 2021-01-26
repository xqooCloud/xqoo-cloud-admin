package com.xqoo.feign.utils;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Gao
 * @version 1.0
 * @since 2020-05-12
 */
public class FeignFileHandleUtil {
    private static final Logger logger = LoggerFactory.getLogger(FeignFileHandleUtil.class);

    /**
     * 获取文件分隔符
     *
     * @return
     */
    public static String getFileSeparator() {
        /* return File.separator;*/
        return File.separator;
    }

    /**
     * 读取内容
     *
     * @param in
     * @return
     */
    public static String readString(InputStream in) {
        if (in == null) {
            return "";
        }

        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        try {
            for (int n; (n = in.read(b)) != -1;) {
                out.append(new String(b, 0, n, StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            logger.info("", e);
        }
        return out.toString();
    }

    public static MultipartFile getMulFile(File file) {
        FileItem fileItem = createFileItem(file);
        MultipartFile mfile = new CommonsMultipartFile(fileItem);
        return mfile;
    }

    public static FileItem createFileItem(File file)
    {
        String filePath = file.getPath();
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "file";
        int num = filePath.lastIndexOf(".");
        String extFile = filePath.substring(num);
        FileItem item = factory.createItem(textFieldName, "multipart/form-data", true,
                file.getName());
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try
        {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192))
                    != -1)
            {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return item;
    }
}
