package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.Temp.EmploymentData;
import com.qks.makerSpace.entity.Temp.FormAwardsData;
import com.qks.makerSpace.entity.Temp.HighEnterpriseData;
import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.request.FormReq;
import com.qks.makerSpace.entity.response.AllForm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormDao {
    @Select("select * from user where user_id = #{userId}")
    User getUserByUserId(String userId);

    //从数据空中那数据，没有考虑图片，所以可以直接全拿，在工具类中会忽略没有用的字段值
    @Select("select * from form where form_id = #{formId}")
    Form getAllInformation(String formId);

    @Select("select * from user_company where user_id = #{userId}")
    List<UserCompany> getCompanyByUserId(String userId);

    @Select("select * from new " +
            "where credit_code = #{creditCode} " +
            "and submit_time = (" +
            "   select max(submit_time) from new where credit_code = #{creditCode} " +
            ")")
    News getLastNewByCreditCode(String creditCode);

    @Select("select * from form where get_time = (select max(get_time) from form where credit_code = #{creditCode}) and credit_code = #{creditCode}")
    Form getLastFormByCreditCode(String creditCode);

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
            "rad_expenditure, product_expenditure, government_grant, self_raised, year, quarter, " +
            "form_id, get_time, admin_audit, leader_audit)  " +
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
            "#{year}, #{quarter}, #{formId}, #{getTime}, #{adminAudit}, #{leaderAudit})")
    Integer addForm(FormReq form);

    @Update("update form set medium_file = #{mediumFile} where form_id = #{formId}")
    Integer updateMediumFile(String mediumFile, String formId);

    @Update("update form set header_file = #{headerFile} where form_id = #{formId}")
    Integer updateHeaderFile(String headerFile, String formId);

    @Insert("insert into form_high_enterprise(high_enterprise_id, high_enterprise_file, get_time, certificate_code) " +
            "VALUES (#{highEnterpriseId}, #{highEnterpriseFile}, #{getTime}, #{certificateCode})")
    Integer addHighEnterpriseFile(FormHighEnterprise formHighEnterprise);

    @Select("select * from form_high_enterprise where high_enterprise_id = #{highEnterpriseId}")
    HighEnterpriseData getHighEnterpriseById(String highEnterpriseId);

    @Insert("insert into form_employment(form_employment_id, employment_id, contract_file) " +
            "VALUES (#{formEmploymentId}, #{employmentId}, #{contractFile})")
    Integer addContractFile(FormEmployment formEmployment);

    @Insert("insert into form_awards(form_awards_id, awards_id, awards_file) " +
            "VALUES (#{formAwardsId}, #{awardsId}, #{awardsFile})")
    Integer addAwardsFile(FormAwards formAwards);

    @Select("select * from space where in_apply_id = #{inApplyId}")
    Space selectSpace(String inApplyId);

    @Select("select * from space_person where in_apply_id = #{inApplyId}")
    List<SpacePerson> selectSpacePerson(String inApplyId);

    @Select("select old.old_id as id, old.credit_code, old.name, old.represent, " +
            "old.represent_phone, old.represent_email, temp.get_time, temp.form_id " +
            "from old, (" +
            "   select max(get_time) get_time, credit_code, form_id " +
            "   from form group by credit_code" +
            "   ) temp " +
            "where old.credit_code = temp.credit_code group by old.credit_code")
    List<AllForm> getOldForm();

    @Select("select new.new_id as id, new.credit_code, new.name, new.represent, " +
            "new.represent_phone, new.represent_email, temp.get_time, temp.form_id " +
            "from new, (" +
            "   select max(get_time) get_time, credit_code, form_id " +
            "   from form group by credit_code" +
            "   ) temp " +
            "where new.credit_code = temp.credit_code group by new.credit_code")
    List<AllForm> getNewForm();

    @Select("select form.form_id, form.get_time, old.credit_code, old.name, old.represent, " +
            "old.represent_phone, old.represent_email " +
            "from old, form " +
            "where old.credit_code = #{creditCode} " +
            "and form.credit_code = #{creditCode} " +
            "group by form.get_time " +
            "order by get_time")
    List<AllForm> getFormByOldCreditCode(String creditCode);

    @Select("select form.form_id, new.credit_code, new.name, new.represent, " +
            "new.represent_phone, new.represent_email, form.get_time " +
            "from new, form " +
            "where new.credit_code = #{creditCode} " +
            "and form.credit_code = #{creditCode} " +
            "group by get_time order by get_time")
    List<AllForm> getFormByNewCreditCode(String creditCode);

    @Select("select credit_code from user_company where user_id = #{userId}")
    List<String> getCreditCodeByUserId(String userId);

    @Select("select credit_code from old where credit_code = #{creditCode}")
    List<String> getOldByCreditCode(String creditCode);

    @Select("select * from form where form_id = #{formId}")
    Form getFormByFormId(String formId);

    @Select("select * " +
            "from old " +
            "where submit_time = (" +
            "   select max(submit_time) " +
            "   from old " +
            "   where credit_code = #{creditCode}" +
            ") and credit_code = #{creditCode}")
    Old getLastOldByCreditCode(String creditCode);

    @Select("select * from form_employment where form_employment_id = #{employmentId}")
    List<EmploymentData> getEmploymentById(String employmentId);

    @Select("select * from form_awards where form_awards_id = #{awardsId}")
    List<FormAwardsData> getFormAwardsById(String awardsId);
}
