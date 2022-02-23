package com.qks.makerSpace.entity.request;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FormReq {
    String map;
    MultipartFile mediumFile;
    MultipartFile highEnterpriseFile;
    MultipartFile headerFile;
    MultipartFile[] contractFile;
    MultipartFile[] awardsFile;
}
