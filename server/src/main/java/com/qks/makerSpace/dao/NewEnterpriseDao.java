package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.response.FormDetails;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NewEnterpriseDao {

//  注册
    @Insert("insert into " +
            "new(new_id,credit_code,organization_code,name,picture,represent,represent_card,represent_phone," +
            "represent_email,agent,agent_phone,agent_email) " +
            "VALUES (#{newId},#{creditCode},#{organizationCode}," +
            "#{name},#{picture},#{represent},#{representCard}," +
            "#{representPhone},#{representEmail},#{agent}," +
            "#{agentPhone},#{agentEmail})")
    int newRegister(News news);

    @Select("select new_demand_id from new where credit_code = #{creditCode}")
    String selectNewDemandByCreditCode(String creditCode);

//  更新提交更多数据
    @Update("update new " +
            "set register_capital = #{registerCapital}, real_capital = #{realCapital}, origin_number = #{originNumber}," +
            "register_time = #{registerTime}, nature = #{nature}, certificate = #{certificate}, involved = #{involved}," +
            "main_business = #{mainBusiness}, business = #{business}, new_shareholder_id = #{newShareholderId}," +
            "new_mainperson_id = #{newMainpersonId}, new_project_id = #{newProjectId}, new_intellectual_id = #{newIntellectualId}," +
            "suggestion = #{suggestion}, note = #{note}, submit_time = #{submitTime} " +
            "where credit_code = #{newId}")
    Integer updateNew(News news);

    @Update("update new set state = #{state}, submit_time = #{submitTime}, room = #{room}, new_demand_id = #{newDemandId} " +
            "where credit_code = #{creditCode}")
    Integer updateNewForDemand(String creditCode, String state, String submitTime, String room, String newDemandId);

    @Insert("insert into new_demand(lease_area, position, lease, floor, electric, water, web, others, new_demand_id) " +
            "VALUES (#{leaseArea}, #{position}, #{lease}, #{floor}, #{electric}, #{water}, #{web}, #{others}, #{newDemandId})")
    Integer addNewDemand(NewDemand newDemand);

    @Insert("insert into " +
            "new_shareholder(id, new_shareholder_id, name, stake, nature) " +
            "VALUES (#{id}, #{newShareholderId}, #{name}, #{stake}, #{nature})")
    int insertNewShareholder(NewShareholder newShareholder);

    @Insert("insert into new_mainperson (id, new_mainperson_id, name, born, job, school, title, background, professional)" +
            "VALUES (#{id}, #{newMainpersonId}, #{name}, #{born}, #{job}, #{school}, #{title}, #{background}, #{professional})")
    int insertNewMainPerson(NewMainPerson newMainPerson);

    @Insert("insert into new_project (id, new_project_id, project_brief, advantage, market, energy, pollution, noise, others)" +
            "VALUES (#{id}, #{newProjectId}, #{projectBrief}, #{advantage}, #{market}, #{energy}, #{pollution}, #{noise}, #{others})")
    int insertNewProject(NewProject newProject);

    @Insert(" insert into new_intellectual (id, new_intellectual_id, name, kind, apply_time, approval_time, intellectual_file) " +
            "values (#{id}, #{newIntellectualId}, #{name}, #{kind}, #{applyTime}, #{approvalTime}, #{inte" +
            "llectualFile})")
    int insertNewIntellectual(NewIntellectual newIntellectual);

    // 查询数据
    @Select(" select * from new")
    List<News> getAllNew();

    @Select("select * from new where credit_code = #{creditCode}")
    String getNewsByCreditCode(String creditCode);

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

    @Update("update user_company set credit_code = #{id} where user_id = #{userId}")
    Integer updateUserCompany(String userId, String id);

    @Select("select credit_code from user_company where user_id = #{userId}")
    String selectCreditCodeByUserId(String userId);

    @Select("select * from form where credit_code = #{creditCode}")
    List<FormDetails> getAllFormDetails(String creditCode);

    @Select("select * from new where credit_Code = #{creditCode}")
    News exit(String creditCode);

    @Update("update new set organization_code = #{organizationCode}, name = #{name}, picture = #{picture}, " +
            "represent = #{represent}, represent_card = #{representCard}, represent_phone =#{representPhone}, represent_email = #{representEmail}, " +
            "agent = #{agent}, agent_phone = #{agentPhone}, agent_email = #{agentEmail} where credit_code = #{creditCode}")
    int updateNewRegister(News news);

    @Select("select * from new_demand where credit_code = #{creditCode}")
    List<NewDemand> demandExit(String creditCode);

    // 下面是已经填过表的
    @Delete("delete * from new_mainperson where new_mainperson_id = #{id}")
    Integer deleteNewMainPerson(String id);

    @Delete("delete * from new_project where new_project_id = #{id}")
    Integer deleteNewProject(String id);

    @Delete("delete * from new_intellectual where new_intellectual_id = #{id}")
    Integer deleteNewIntellectual(String id);

    @Delete("delete * from new_shareholder where new_shareholder_id = #{id}")
    Integer deleteNewShareholder(String id);

    @Select("select new_mainperson_id from new where credit_code = #{creditCode}")
    String selectNewMainPersonId(String creditCode);

    @Select("select new_shareholder_id from new where credit_code = #{creditCode}")
    String  selectNewShareholder(String creditCode);

    @Select("select new_project_id from new where credit_code = #{creditCode}")
    String selectNewProject(String creditCode);

    @Select("select new_intellectual_id from new where credit_code = #{creditCode}")
    String selectNewIntellectual(String creditCode);

    @Select("select * from audit where audit_id = #{creditCode}")
    List<Audit> getAudit(String creditCode);

    @Update("update new_demand " +
            "set lease_area = #{leaseArea}, position = #{position}, lease = #{lease}, " +
            "floor = #{floor}, electric = #{electric}, water = #{water}, web = #{web}, others = #{others} " +
            "where new_demand.new_demand_id = #{newDemandId}")
    Integer updateNewDemand(NewDemand newDemand, String newDemandId);
}
