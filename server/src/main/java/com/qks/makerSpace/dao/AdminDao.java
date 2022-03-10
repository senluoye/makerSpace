package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.request.ApplyingReq;
import com.qks.makerSpace.entity.response.AdminSuggestion;
import com.qks.makerSpace.entity.response.AllForm;
import com.qks.makerSpace.entity.response.AllSpace;
import com.qks.makerSpace.entity.response.AllTechnology;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface AdminDao {

    /**
     * 根据名称查找用户
     * @param name
     * @return
     */
    @Select("select * from user where name = #{name}")
    List<User> getUserByName(String name);

    /**
     * 增加新用户
     * @param user
     * @return
     */
    @Insert("insert into user(user_id, name, password) values (#{userId}, #{name}, #{password})")
    Integer addNewUser(User user);

    @Update("update user set name = #{name}, password = #{password}, user_describe = #{userDescribe} " +
            "where user_id = #{userId}")
    Integer UpdateUser(User user);

    /**
     *  获取最新所有未审核科技园入园申请
     * @return
     */
    @Select("select credit_code, administrator_audit administratorAudit, `describe`, max(submit_time) submitTime " +
            "from (select * from audit where `describe` = '科技园') temp " +
            "group by credit_code")
    List<ApplyingReq> getAllTechnologyApplying();

    @Select("select credit_code, administrator_audit, `describe`, max(submit_time) submit_time " +
            "from (select * from audit where `describe` = '众创空间') temp " +
            "group by credit_code ")
    List<ApplyingReq> getAllSpaceApplying();

    @Select("select name from old where credit_code = #{creditCode}")
    List<String> getOldNameByCreditCode(String creditCode);

    @Select("select name from new where credit_code = #{creditCode}")
    List<String> getNewNameByCreditCode(String creditCode);

    @Select("select create_name name from space where in_apply_id = #{in_apply_id}")
    List<String> getSpaceNameByCreditCode(String inApplyId);


    @Select("select old.credit_code as creditCode, old.organization_code as organizationCode, " +
            "old.name as name, old.represent as represent, old.represent_phone as representPhone, " +
            "old.represent_email as representEmail, old_demand.floor as floor, old_demand.position as position, " +
            "audit.administrator_audit as administratorAudit " +
            "from old, old_demand, audit " +
            "where old.old_demand_id = old_demand.old_demand_id " +
            "and audit.audit_id = old.credit_code")
    List<AllTechnology> getAllOldDetails();

    @Select("select new.credit_code as creditCode, new.organization_code as organizationCode, " +
            "new.name as name, new.represent as represent, new.represent_phone as representPhone, " +
            "new.represent_email as representEmail, new_demand.floor as floor, new_demand.position as position, " +
            "audit.administrator_audit as administratorAudit " +
            "from new, new_demand, audit " +
            "where new.new_demand_id = new_demand.new_demand_id " +
            "and audit.audit_id = new.credit_code")
    List<AllTechnology> getAllNewDetails();

    @Select("select in_apply_id, create_name, apply_time, team_number, " +
            "`describe`, help " +
            "from space")
    List<Space> getAllSpaceDetails();

    @Select("select * from space_person where in_apply_id = #{inApplyId}")
    List<SpacePerson> getSpacePeopleById(String inApplyId);

    @Select("select * from old where credit_code = #{creditCode}")
    Old getOld(String creditCode);

    @Select("select old_id from old where credit_code = #{creditCode}")
    String getOldId(String creditCode);

    @Select("select * from old_demand where old_demand_id = #{id}")
    List<OldDemand> getOldDemandById(String id);

    @Select("select * from old_mainperson where old_mainperson_id = #{id}")
    List<OldMainPerson>  getOldMainPeopleById(String id);

    @Select("select * from old_project where old_project_id = #{id}")
    List<OldProject> getOldProjectById(String id);

    @Select("select * from old_funding where funding_id = #{id}")
    List<OldFunding> getOldFundingById(String id);

    @Select("select * from old_shareholder where old_shareholder_id = #{id}")
    List<OldShareholder> getOldShareholderById(String id);

    @Select("select * from old_intellectual where old_intellectual_id = #{id}")
    List<OldIntellectual> getOldIntellectualById(String id);

    @Select("select * from new where credit_code = #{id}")
    News getNew(String id);

    @Select(" select * from new_demand where new_demand_id = #{newDemandId}")
    List<NewDemand> getNewDemandById(String newDemandId);

    @Select("select * from new_mainperson where new_mainperson_id = #{newMainPersonId}")
    List<NewMainPerson> getNewMainPerson(String newMainpersonId);

    @Select("select * from new_intellectual where new_intellectual_id = #{newIntellectualId}")
    List<NewIntellectual> getNewIntellectual(String newIntellectualId);

    @Select("select * from new_project where new_project_id = #{newProjectId}")
    List<NewProject> getNewProject(String newProjectId);

    @Select("select * from new_shareholder where new_shareholder_id = #{newShareholderId}")
    List<NewShareholder> getNewShareholder(String newShareholderId);

    @Select("select * from space where in_apply_id = #{id}")
    Space getSpaceById(String id);

    @Select("select * from space_person where in_apply_id = #{inApplyId}")
    List<SpacePerson> getPersonListByInApplyId(String inApplyId);

    @Delete("delete space, space_person " +
            "from space, space_person " +
            "where space.in_apply_id = space_person.in_apply_id " +
            "and space.in_apply_id = #{inApplyId}")
    Integer deleteSpaceByCreditCode(String inApplyId);

    @Delete("delete old, old_demand, old_funding, old_intellectual, " +
            "old_mainperson, old_project, old_shareholder " +
            "from old, old_demand, old_funding, old_intellectual, " +
            "old_mainperson, old_project, old_shareholder " +
            "where old.old_demand_id = old_demand.old_demand_id " +
            "and old.old_shareholder_id = old_shareholder.old_shareholder_id " +
            "and old.old_mainperson_id = old_mainperson.old_mainperson_id " +
            "and old.old_project_id = old_project.old_project_id " +
            "and old.old_intellectual_id = old_intellectual.old_intellectual_id " +
            "and old.old_funding_id = old_funding.funding_id " +
            "and old.credit_code = #{creditCode}")
    Integer deleteOldByCreditCode(String creditCode);

    @Update("update audit set administrator_audit = #{agree} where audit_id = #{creditCode}")
    Integer agreeById(String creditCode, String agree);

    @Update("update audit set administrator_audit = #{disagree} where audit_id = #{creditCode}")
    Integer disagreeById(String creditCode, String disagree);

    @Select("select * from audit where audit_id = #{id}")
    Audit getAuditById(String id);

    @Select("select credit_code from new where credit_code = #{creditCode}")
    List<String> selectCreditCodeFromNewByCreditCode(String creditCode);

    @Select("select credit_code from old where credit_code = #{creditCode}")
    List<String> selectCreditCodeFromOldByCreditCode(String creditCode);

    @Update("update new set suggestion = #{suggestion}, note = #{note} where credit_code = #{creditCode}")
    Integer updateNewSuggestion(AdminSuggestion adminSuggestion);

    @Update("update old set suggestion = #{suggestion}, note = #{note} where credit_code = #{creditCode}")
    Integer updateOldSuggestion(AdminSuggestion adminSuggestion);
}
