package com.qks.makerSpace.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("SearchResponse 搜索返回类")
public class SearchResponse {

    @ApiModelProperty("企业唯一id")
    private String id;
    @ApiModelProperty("信用代码")
    private String creditCode;
    @ApiModelProperty("企业名")
    private String name;
    @ApiModelProperty("提交时间")
    private String submitTime;
    @ApiModelProperty("管理员审核情况")
    private String administratorAudit;
    @ApiModelProperty("领导审核情况")
    private String leadershipAudit;
    @ApiModelProperty("企业类型")
    private String describe;

}