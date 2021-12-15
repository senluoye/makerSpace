package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.response.All;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdminDao {

    @Select("select old.credit_code as creditCode, old.organization_code as organizationCode, " +
            "old.name as name, old.represent as represent, old.represent_phone as representPhone, " +
            "old.represent_email as representEmail, old_demand.floor as floor, old_demand.position as position " +
            "from old, old_demand " +
            "where old.old_demand_id = old_demand.id")
    List<All> getAllOldDetails();

    @Select("select new.credit_code as creditCode, new.organization_code as organizationCode, " +
            "new.name as name, new.represent as represent, new.represent_phone as representPhone, " +
            "new.represent_email as representEmail, new_demand.floor as floor, new_demand.position as position " +
            "from new, new_demand " +
            "where new.new_demand_id = new_demand.id")
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

    Map<String, Object> deleteSpaceById(String id);

    @Update("update audit set administrator_audit = true where audit_id = #{id}")
    Integer updateAuditById(String id);
}
