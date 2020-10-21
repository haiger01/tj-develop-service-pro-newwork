package com.scistor.develop.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    /**
     * @param file     文件
     * @param path     文件存放路径
     * @param fileName 原文件名
     * @return
     */
    public static String upload(MultipartFile file, String path, String fileName) {

        String newFileName = FileNameUtils.getFileName(fileName);

        // 生成新的文件名
        String realPath = path + "/" + newFileName;

        File dest = new File(realPath);

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            //保存文件
            file.transferTo(dest);
            return realPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * @param file     文件
     * @return
     */
    public static String comanyUpload(MultipartFile file, String realPath) {

        File dest = new File(realPath);

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            //保存文件
            file.transferTo(dest);
            return realPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * @param file     视频
     * @return
     */
    public static String uploadVideo(MultipartFile file, String realPath) {

        File dest = new File(realPath);

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            //保存文件
            file.transferTo(dest);
            return realPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * @Function: 图片上传
     * @author: YangXueFeng
     * @Date: 2019/4/18 14:13
     */
    public static String uploadFiles(byte[] file, String filePath, String fileName,String newFileName) throws Exception {
        if (StringUtils.isEmpty(newFileName))
            newFileName = FileNameUtils.getFileName(fileName);
        // 生成新的文件名
        String realPath = filePath + newFileName;
        File targetFile = new File(realPath);
        //判断文件父目录是否存在
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }

        FileOutputStream out = new FileOutputStream(targetFile);
        out.write(file);
        out.flush();
        out.close();

        return realPath;
    }

}
