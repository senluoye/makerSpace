package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.NewEnterpriseDao;
import com.qks.makerSpace.dao.OldEnterpriseDao;
import com.qks.makerSpace.entity.database.*;
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

        if (newEnterpriseDao.getNewsByCreditCode(creditCode) != null)
            throw new ServerException("该用户已经递交公司入驻申请表");

        News news = new News();
        String newId = UUID.randomUUID().toString();

        news.setNewId(newId);
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

        if (newEnterpriseDao.newRegister(news) > 0) {
            return MyResponseUtil.getResultMap(creditCode, 0, "注册成功");
        } else throw new ServerException("注册失败");
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
        System.out.println(str);
        JSONObject map = JSONObject.parseObject(str);
        String creditCode = map.get("creditCode").toString();

        News news = NewParserUtils.newsParser(map);
        List<NewMainPerson> newMainPeople = NewParserUtils.NewMainPersonParser(map.getJSONArray("newMainPerson"));
        List<NewProject> newProjects = NewParserUtils.NewProjectParser(map.getJSONArray("newProject"));
        List<NewIntellectual> newIntellectuals = NewParserUtils.NewIntellectualParser(map.getJSONArray("newIntellectual"));
        List<NewShareholder> newShareholders = NewParserUtils.NewShareholdersParser(map.getJSONArray("newShareholder"));

        news.setNewShareholderId(newShareholders.get(0).getNewShareholderId());
        news.setNewMainpersonId(newMainPeople.get(0).getNewMainpersonId());
        news.setNewProjectId(newProjects.get(0).getNewProjectId());
        news.setNewIntellectualId(newIntellectuals.get(0).getNewIntellectualId());
        news.setCertificate(files[0].getBytes());
        news.setSubmitTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").toString());

        for (int i = 1; i <files.length; i++) {
            newIntellectuals.get(i - 1).setIntellectualFile(files[i].getBytes());
        }

        if(newEnterpriseDao.updateNew(news) > 0) {
            for(NewMainPerson newMainPerson : newMainPeople) {
                if(newEnterpriseDao.insertNewMainPerson(newMainPerson) <= 0) {
                    throw new ServiceException("信息插入失败:newMainPeople");
                }
            }
            for(NewShareholder newShareholder : newShareholders) {
                if(newEnterpriseDao.insertNewShareholder(newShareholder) <= 0) {
                    throw new ServiceException("信息插入失败:newShareholder");
                }
            }
            for(NewProject newProject : newProjects) {
                if(newEnterpriseDao.insertNewProject(newProject) <= 0) {
                    throw new ServiceException("信息插入失败:newProject");
                }
            }
            for(NewIntellectual newIntellectual : newIntellectuals) {
                if(newEnterpriseDao.insertNewIntellectual(newIntellectual) <= 0) {
                    throw new ServiceException("信息插入失败:newIntellectual");
                }
            }

            Audit audit = new Audit();
            audit.setAuditId(creditCode);
            audit.setAdministratorAudit(false);
            audit.setLeadershipAudit(false);

            if (oldEnterpriseDao.insertAudit(audit) <= 0)
                throw new ServiceException("信息插入失败:audit");
        } else throw new ServiceException("信息插入失败:new");

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
     * 房间申请
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

        if (newEnterpriseDao.addNewDemand(newDemand) < 1) {
            throw new ServiceException("插入数据失败:addNewDemand");
        }
        if (newEnterpriseDao.updateNewForDemand(creditCode, "0", submitTime, room) < 1){
            throw new ServiceException("插入数据失败:updateNewForDemand");
        }

        Map<String, Object> data = new HashMap<>();
        data.put("creditCode", creditCode);
        return MyResponseUtil.getResultMap(data, 0, "success");
    }

}