package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.NewEnterpriseDao;
import com.qks.makerSpace.dao.OldEnterpriseDao;
import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.response.FormDetails;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.NewEnterpriseService;
import com.qks.makerSpace.util.ChangeUtils;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import com.qks.makerSpace.util.NewParserUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.ServerException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NewEnterpriseServiceImpl implements NewEnterpriseService , Serializable {

    private final NewEnterpriseDao newEnterpriseDao;

    private final OldEnterpriseDao oldEnterpriseDao;

    public NewEnterpriseServiceImpl(NewEnterpriseDao newEnterpriseDao, OldEnterpriseDao oldEnterpriseDao) {
        this.newEnterpriseDao = newEnterpriseDao;
        this.oldEnterpriseDao = oldEnterpriseDao;
    }

    /**
     * 信息状态展示
     * @return
     */
    @Override
    public Map<String, Object> getNewEnterprise() {
        List<Map<String, Object>> data = new ArrayList<>();
        List<News> newsList = newEnterpriseDao.getAllNew();

        newsList.forEach(x ->{
            try {
                data.add(ChangeUtils.getObjectToMap(x));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        newsList.forEach(x->{
            List<NewDemand> newDemands = newEnterpriseDao.getNewDemandById(x.getNewDemandId());
            List<NewMainPerson> newMainPeople = newEnterpriseDao.getNewMainPerson(x.getNewMainpersonId());
            List<NewIntellectual> newIntellectuals = newEnterpriseDao.getNewIntellectual(x.getNewIntellectualId());
            List<NewProject> newProjects = newEnterpriseDao.getNewProject(x.getNewProjectId());
            List<NewShareholder> newShareholders = newEnterpriseDao.getNewShareholder(x.getNewShareholderId());

            Map<String, Object> temp = NewParserUtils.NewGetReponse(x);
            temp.put("newDemand",newDemands);
            temp.put("newShareholder",newShareholders);
            temp.put("newMainPerson",newMainPeople);
            temp.put("newProject",newProjects);
            temp.put("newIntellectual",newIntellectuals);
            data.add(temp);
        });

        return MyResponseUtil.getResultMap(data,0,"success");
    }

    /**
     * 注册
     * @param str
     * @return
     */
    @Override
    public Map<String, Object> newRegister(String str, MultipartFile[] file) throws IOException{
        System.out.println(str);
        JSONObject map = JSONObject.parseObject(str);
        String creditCode = map.get("creditCode").toString();

//        if (newEnterpriseDao.getNewsByCreditCode(creditCode) != null)
//            throw new ServerException("该用户已经递交公司入驻申请表");

        News news = new News();
        String newId = UUID.randomUUID().toString();

        news.setCreditCode(creditCode);
        news.setOrganizationCode(map.get("organizationCode").toString());
//        news.setPassword(map.get("password").toString());
        news.setName(map.get("name").toString());
        news.setPicture(file[0].getBytes());
        news.setRepresent(map.get("represent").toString());
        news.setRepresentCard(file[1].getBytes());
        news.setRepresentPhone(map.get("representPhone").toString());
        news.setRepresentEmail(map.get("representEmail").toString());
        news.setAgent(map.get("agent").toString());
        news.setAgentPhone(map.get("agentPhone").toString());
        news.setAgentEmail(map.get("agentEmail").toString());

        if (newEnterpriseDao.exit(creditCode) != null) {
            //之前没有申请过
            news.setNewId(newId);
            if (newEnterpriseDao.newRegister(news) < 1)
                throw new ServerException("录入信息失败");
        } else {
            //之前申请过
            if(newEnterpriseDao.updateNewRegister(news) < 1){
                throw new ServerException("更新信息失败");
            }
        }

        Map<String, Object> data = new HashMap<>();
        data.put("id",creditCode);
        return MyResponseUtil.getResultMap(data,0,"success");

    }

    /**
     * 租赁缴费
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> newEnterprisePay(Map<String, Object> map) {
        return null;
    }

    /**
     * 入园申请表填写
     * @param str
     * @return
     */
    @Override
    public Map<String, Object> updateNewEnterprise(String token,
                                                   String str,
                                                   MultipartFile[] files) throws Exception {
        String userId = JWTUtils.parser(token).get("userId").toString();
        JSONObject map = JSONObject.parseObject(str);
        String creditCode = map.get("creditCode").toString();

        List<Audit> auditList = newEnterpriseDao.getAudit(creditCode);

        if (auditList.size() != 0)
            if (auditList.get(0).isAdministratorAudit() && auditList.get(0).isLeadershipAudit())
                throw new ServiceException("领导和管理员均已审核通过，无法重新填报申请表");


        News news = NewParserUtils.newsParser(map);
        List<NewMainPerson> newMainPeople = NewParserUtils.NewMainPersonParser(map.getJSONArray("newMainPerson"));
        List<NewProject> newProjects = NewParserUtils.NewProjectParser(map.getJSONArray("newProject"));
        List<NewIntellectual> newIntellectuals = NewParserUtils.NewIntellectualParser(map.getJSONArray("newIntellectual"));
        List<NewShareholder> newShareholders = NewParserUtils.NewShareholdersParser(map.getJSONArray("newShareholder"));

        news.setNewShareholderId(newShareholders.get(0).getNewShareholderId());
        news.setNewMainpersonId(newMainPeople.get(0).getNewMainpersonId());
        news.setNewProjectId(newProjects.get(0).getNewProjectId());
        news.setNewIntellectualId(newIntellectuals.get(0).getNewIntellectualId());
        try {
            news.setCertificate(files[0].getBytes());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ServiceException("有文件没有上传");
        }

        Date date = new Date();
        news.setSubmitTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

        for (int i = 1; i <files.length; i++) {
            newIntellectuals.get(i - 1).setIntellectualFile(files[i].getBytes());
        }

        if(newEnterpriseDao.exitMainPerson(creditCode) != null) {
            // 如果之前已经有信息存在 --->删除对应信息
            if (newEnterpriseDao.deleteNewMainPerson(newEnterpriseDao.selectNewMainPersonId(creditCode)) <= 0)
                throw new ServiceException("删除MainPerson错误");
            if (newEnterpriseDao.deleteNewProject(newEnterpriseDao.selectNewProject(creditCode)) <= 0)
                throw new ServiceException("删除Project失败");
            if (newEnterpriseDao.deleteNewShareholder(newEnterpriseDao.selectNewShareholder(creditCode)) <=0)
                throw new ServiceException("删除Shareholder失败");
            if (newEnterpriseDao.deleteNewIntellectual(newEnterpriseDao.selectNewIntellectual(creditCode)) <= 0)
                throw new ServiceException("删除Intellectual失败");
        }

        //更新主表
        if(newEnterpriseDao.updateNew(news) <= 0)
            throw new ServiceException("信息插入失败:new");
        for (NewMainPerson newMainPerson : newMainPeople) {
            if (newEnterpriseDao.insertNewMainPerson(newMainPerson) <= 0) {
                throw new ServiceException("信息插入失败:newMainPeople");
            }
        }
        for (NewShareholder newShareholder : newShareholders) {
            if (newEnterpriseDao.insertNewShareholder(newShareholder) <= 0) {
                throw new ServiceException("信息插入失败:newShareholder");
            }
        }
        for (NewProject newProject : newProjects) {
            if (newEnterpriseDao.insertNewProject(newProject) <= 0) {
                throw new ServiceException("信息插入失败:newProject");
            }
        }
        for (NewIntellectual newIntellectual : newIntellectuals) {
            if (newEnterpriseDao.insertNewIntellectual(newIntellectual) <= 0) {
                throw new ServiceException("信息插入失败:newIntellectual");
            }
        }

        Audit audit = new Audit();
        audit.setAuditId(creditCode);
        audit.setAdministratorAudit(false);
        audit.setLeadershipAudit(false);

        if (oldEnterpriseDao.insertAudit(audit) <= 0)
            throw new ServiceException("信息插入失败:audit");

        if (oldEnterpriseDao.selectUserCompany(creditCode) != null) {
            oldEnterpriseDao.updateUserCompany(userId,creditCode);
        } else {
            oldEnterpriseDao.insertUserCompany(userId, creditCode);
        }

        Map<String, Object> forMap = new HashMap<>();
        forMap.put("creditCode",creditCode);

        return MyResponseUtil.getResultMap(forMap, 0, "success");
    }

    /**
     * 场地申请
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> newEnterpriseDemand(JSONObject map) throws ServiceException {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String submitTime = dateFormat.format(date);


        String room = map.getString("floor") + " - " + map.getString("position");
        String creditCode = map.getString("creditCode");

        NewDemand newDemand = new NewDemand();

        newDemand.setNewDemandId(UUID.randomUUID().toString());
        newDemand.setLeaseArea(map.getString("leaseArea"));
        newDemand.setPosition(map.getString("position"));
        newDemand.setLease(map.getString("lease"));
        newDemand.setFloor(map.getString("floor"));
        newDemand.setElectric(map.getString("electric"));
        newDemand.setWater(map.getString("water"));
        newDemand.setWeb(map.getString("web"));
        newDemand.setOthers(map.getString("others"));

        if(newEnterpriseDao.demandExit(creditCode) != null) {
            //已经存在
            String newDemandId = newEnterpriseDao.selectNewDemandByCreditCode(creditCode);
            if(newEnterpriseDao.updateNewDemand(newDemand,newDemandId) < 1) {
                throw new ServiceException("插入数据失败:addNewDemand");
            } else {
                newDemand.setNewDemandId(UUID.randomUUID().toString());
                if(newEnterpriseDao.addNewDemand(newDemand) < 1
                    || newEnterpriseDao.updateNewForDemand(creditCode,"0",submitTime,room,newDemand.getNewDemandId()) < 1) {
                    throw new ServiceException("插入数据失败:addNewDemand");
                }
            }
        }

        if(newEnterpriseDao.updateNewForDemand(creditCode,"0",submitTime,room,newDemand.getNewDemandId()) < 1)
            throw new ServiceException("插入数据失败:updateNewForDemand");

        Map<String, Object> data = new HashMap<>();
        data.put("creditCode", creditCode);
        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 获取某个企业的所有季度报表
     * @param str
     * @return
     */
    @Override
    public Map<String, Object> getFormByCreditCode(String token) throws ServiceException {
        String userId = JWTUtils.parser(token).get("userId").toString();
        String creditCode = newEnterpriseDao.selectCreditCodeByUserId(userId);

        if (creditCode == null)
            throw new ServiceException("您并没有填写入驻申请表");

        List<FormDetails> data = newEnterpriseDao.getAllFormDetails(creditCode);

        return MyResponseUtil.getResultMap(data, 0, "success");
    }
}