package com.qks.makerSpace.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件工具类
 */
public class FileUtils {
    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    private static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     * @param fileOriginName 源文件名
     * @return
     */
    private static String getFileName(String fileOriginName){
        return UUID.randomUUID() + getSuffix(fileOriginName);
    }

    /**
     * 上传文件
     * @param file
     * @param path
     * @return
     */
    public static String upload(MultipartFile file, String path) throws IOException{
        // 生成新的文件名和路径
        String fileName = file.getOriginalFilename();
        String newFileName = getFileName(fileName);
        String realPath = path + newFileName;

        File dest = new File(realPath);

        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }

        //保存文件
        file.transferTo(dest);
        return newFileName;
    }
}
