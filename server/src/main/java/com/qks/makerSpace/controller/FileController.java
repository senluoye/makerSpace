package com.qks.makerSpace.controller;

import com.qks.makerSpace.service.FileService;
import com.qks.makerSpace.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 文件服务
 */
@RestController
@RequestMapping("/api/image")
public class FileController {

    @Value("${web.upload-path}")
    String uploadPath;

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 获取图片
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE,
                                                                            MediaType.IMAGE_PNG_VALUE,
                                                                            MediaType.IMAGE_GIF_VALUE})
    @ResponseBody
    public byte[] getImage(@PathVariable("id") String id) throws IOException {
//        System.out.println(id);
        File file = new File(uploadPath + id);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        inputStream.close();
        return bytes;
    }

    /**
     * 测试用：上传图片的接口
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        return FileUtils.upload(file, uploadPath);
    }
}
