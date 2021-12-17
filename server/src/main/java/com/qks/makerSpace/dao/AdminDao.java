package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.response.All;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdminDao {

    @Select("select old.credit_code as creditCode, old.organization_code as organizationCode, " +
            "old.name as name, old.represent as represent, old.represent_phone as representPhone, " +
            "old.represent_email as representEmail, old_demand.floor as floor, old_demand.position as position, " +
            "audit.administrator_audit as administratorAudit " +
            "from old, old_demand, audit " +
            "where old.old_demand_id = old_demand.old_demand_id " +
            "and audit.audit_id = old.credit_code")
    List<All> getAllOldDetails();

    @Select("select new.credit_code as creditCode, new.organization_code as organizationCode, " +
            "new.name as name, new.represent as represent, new.represent_phone as representPhone, " +
            "new.represent_email as representEmail, new_demand.floor as floor, new_demand.position as position " +
            "from new, new_demand " +
            "where new.new_demand_id = new_demand.new_demand_id")
    List<All> getAllNewDetails();

    @Select("select * from old where old_id = #{id}")
    Map<String, Object> getOld(String id);

    @Select("select * from old_demand where old_demand_id = #{id}")
    List<OldDemand> getOldDemandById(String id);

    @Select("select * from old_mainperson where old_mainperson_id = #{id}")
    List<OldMainPerson>  getOldMainPeopleById(String id);

    @Select("select * from old_project where old_project_id = #{id}")
    List<OldProject> getOldProjectById(String id);

    @Select("select * from old_funding where old_funding.= #{id}")
    List<OldFunding> getOldFundingById(String id);

    @Select("select * from old_shareholder where old_shareholder_id = #{id}")
    List<OldShareholder> getOldShareholderById(String id);

    @Select("select * from old_intellectual where old_intellectual_id = #{id}")
    List<OldIntellectual> getOldIntellectualById(String id);

    @Select("select * from new where new_id = #{id}")
    Map<String, Object> getNew(String id);

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

    Map<String, Object> getSpaceById(String id);

    @Delete("delete space, space_person " +
            "where space.space_person_id")
    Integer deleteSpaceByCreditCode(String creditCode);

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

    @Update("update audit set administrator_audit = true where audit_id = #{creditCode}")
    Integer agreeById(String creditCode);

    @Update("update audit set administrator_audit = false where audit_id = #{creditCode}")
    Integer disagreeById(String creditCode);

    @Select("select credit_code from new where credit_code = #{creditCode}")
    String selectCreditCodeFromNewByCreditCode(String creditCode);
}
