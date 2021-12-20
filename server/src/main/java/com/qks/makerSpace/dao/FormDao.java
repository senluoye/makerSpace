package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.*;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Repository
public interface FormDao {
    //从数据空中那数据，没有考虑图片，所以可以直接全拿，在工具类中会忽略没有用的字段值
    @Select("select * from form where credit_code = #{creditCode}")
    Map<String , Object> getAllInformation(String creditCode);

    @Select("select * from user_company where user_id = #{userId}")
    List<UserCompany> getCompanyByUserId(String userId);

    @Insert("insert into form(team_name, credit_code, register_time, " +
            "join_time, register_capital, register_kind, industry_kind, " +
            "field, graduated_enterprise, graduated_time, high_enterprise, " +
            "high_enterprise_id, medium_sized, mentor_relationship, " +
            "header_kind, serial_entrepreneur, header_gender, " +
            "tax_kind, header, statistic_header, submit_header, submit_phone, " +
            "submit_time, risk_investment, area, institutions, total_transformation, " +
            "relying, winning, result, incubate_income, incubate_product, " +
            "incubate_profit, incubate_tax, incubate_out, employee, doctor, " +
            "master, graduate, bachelor, college, tec_secondary, tec_activists, " +
            "rad_number, returnees, talents, trainee, employment, employment_id, " +
            "applications, applications_patent, granted, granted_patent, valid, " +
            "valid_patent, soft_copyright, plant_variety, ic_layout, foreign_patents, " +
            "contract_transaction, contract_urnover, project_num, total_awards, " +
            "awards_id, province_awards, under_projects, national_project, " +
            "school_project, declaration_name, declaration_num, expenditure, " +
            "rad_expenditure, product_expenditure, government_grant, self_raised, time) " +
            "VALUES (#{teamName}, #{creditCode}, #{registerTime}, #{joinTime}, #{registerCapital}, " +
            "#{registerKind}, #{industryKind}, #{field}, #{graduatedEnterprise}, #{graduatedTime}, #{highEnterprise}, " +
            "#{highEnterpriseId}, #{mediumSized}, #{mentorRelationship}, #{headerKind}, " +
            "#{serialEntrepreneur}, #{headerGender}, #{taxKind}, #{header}, #{statisticHeader}, #{submitHeader}, " +
            "#{submitPhone}, #{submitTime}, #{riskInvestment}, #{area}, #{institutions}, #{totalTransformation}, " +
            "#{relying}, #{winning}, #{result}, #{incubateIncome}, #{incubateProduct}, #{incubateProfit}, " +
            "#{incubateTax}, #{incubateOut}, #{employee}, #{doctor}, #{master}, #{graduate}, " +
            "#{bachelor}, #{college}, #{tecSecondary}, #{tecActivists}, #{radNumber}, #{returnees}, " +
            "#{talents}, #{trainee}, #{employment}, #{employmentId}, #{applications}, #{applicationsPatent}, " +
            "#{granted}, #{grantedPatent}, #{valid}, #{validPatent}, #{softCopyright}, #{plantVariety}, " +
            "#{icLayout}, #{foreignPatents}, #{contractTransaction}, #{contractUrnover}, #{projectNum}, #{totalAwards}, " +
            "#{awardsId}, #{provinceAwards}, #{underProjects}, #{nationalProject}, #{schoolProject}, #{declarationName}, " +
            "#{declarationNum}, #{expenditure}, #{radExpenditure}, #{productExpenditure}, #{governmentGrant}, #{selfRaised}, " +
            "#{time})")
    Integer addForm(Form form);

    @Update("update form set medium_file = #{mediumFile} where credit_code = #{creditCode}")
    Integer addMediumFile(byte[] mediumFile, String creditCode);

    @Insert("update form set header_file = #{headerFile} where credit_code = #{creditCode}")
    Integer addHeaderFile(byte[] headerFile, String creditCode);

    @Insert("insert into form_high_enterprise(high_enterprise_id, high_enterprise_file, get_time) VALUES (#{highEnterpriseId}, #{highEnterpriseFile},#{getTime},#{certificateCode})")
    Integer addHighEnterpriseFile(FormHighEnterprise formHighEnterprise);

    @Insert("insert into form_employment(form_employment_id, employment_id, contract_file) VALUES (#{formEmploymentId}, #{employmentId}, #{contractFile})")
    Integer addContractFile(FormEmployment formEmployment);

    @Insert("insert into form_awards(form_awards_id, awards_id, awards_file) VALUES (#{formAwardId}, #{awardsId}, #{awardsFile})")
    Integer addAwardsFile(FormAwards formAwards);
}
