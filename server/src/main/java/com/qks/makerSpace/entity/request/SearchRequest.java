package com.qks.makerSpace.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
@ApiModel("SearchRequest 搜索请求")
public class SearchRequest {

    @ApiModelProperty("搜索内容")
    private String content;

    @ApiModelProperty("搜索起始时间")
    @Pattern(regexp = "^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$|^\\s*$", message = "时间格式不匹配")
    private String beginTime;

    @ApiModelProperty("搜索中止时间")
    @Pattern(regexp = "^\\d{4}\\D\\:\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$|^\\s*$", message = "时间格式不匹配")
    private String endTime;
}