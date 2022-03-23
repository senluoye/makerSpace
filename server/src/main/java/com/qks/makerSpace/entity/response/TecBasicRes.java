package com.qks.makerSpace.entity.response;

import lombok.Data;

@Data
public class TecBasicRes {
    private String teamName; // 企业名称
    private String creditCode; // 统一社会信用代码或组织机构代码
    private String joinTime; // 企业入驻科技园时间
    private String header; //企业负责人
    private String registerCapital; // 注册资金

//    private String industryKind; // 行业类别
//    private String field; //企业所属技术领域
//    private String submitHeader; //填报人
//    private String submitPhone; //填报人电话
//    private String submitTime; //填报日期
//    private String applications; //当年知识产权申请数
//    private String applicationsPatent; //当年知识产权申请数--->发明专利
//    private String granted; //当年知识产权授权数
//    private String grantedPatent; //当年知识产权授权数---->发明专利
//    private String valid; //拥有有效知识产权数
//    private String validPatent; //拥有有效知识产权数----->发明专利
//    private String softCopyright; //拥有有效知识产权数----->软件著作权
//    private String plantVariety; //拥有有效知识产权数----->植物新品种
//    private String icLayout; //拥有有效知识产权数----->集成电路布图
//    private String projectNum; //当年承担国家级科技计划项目数
//    private String totalAwards; //当年参赛获奖情况
//    private String provinceAwards; //当年参赛获奖情况--->省级以上
}
