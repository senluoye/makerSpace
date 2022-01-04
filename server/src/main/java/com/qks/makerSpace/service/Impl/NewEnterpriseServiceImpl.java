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
    public Map<String, Object> newRegister(String str, MultipartFile[] files) throws IOException{
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
        news.setRepresent(map.get("represent").toString());
        news.setRepresentPhone(map.get("representPhone").toString());
        news.setRepresentEmail(map.get("representEmail").toString());
        news.setAgent(map.get("agent").toString());
        news.setAgentPhone(map.get("agentPhone").toString());
        news.setAgentEmail(map.get("agentEmail").toString());

        try {
            news.setPicture(files[0].getBytes());
            news.setRepresentCard(files[1].getBytes());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ServerException("上传文件数量不足");
        }

        if (newEnterpriseDao.exit(creditCode) != null) {
            //之前申请过
            newEnterpriseDao.updateNewRegister(news);
        } else {
            //之前没有申请过
            news.setNewId(newId);
            newEnterpriseDao.newRegister(news);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("creditCode", creditCode);
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
            throw new ServiceException("上传文件数量不足");
        }

        Date date = new Date();
        news.setSubmitTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

        for (int i = 1; i <files.length; i++) {
            try {
                newIntellectuals.get(i - 1).setIntellectualFile(files[i].getBytes());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ServiceException("读取文件发生错误，请重新上传");
            }
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
        audit.setAdministratorAudit("未审核");
        audit.setLeadershipAudit("未审核");

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

        NewDemand newDemand = JSONObject.parseObject(String.valueOf(map),NewDemand.class);

        String id = UUID.randomUUID().toString();
        newDemand.setId(id);
        newDemand.setNewDemandId(UUID.randomUUID().toString());
        newDemand.setTime(submitTime);
        if(newEnterpriseDao.addNewDemand(newDemand) < 1
                || newEnterpriseDao.updateNewDemandId(creditCode,newDemand.getNewDemandId()) < 1)
            throw new ServiceException("插入数据失败:addNewDemand");

        if(newEnterpriseDao.updateNewForDemand(creditCode,"0",submitTime,room,newDemand.getNewDemandId()) < 1)
            throw new ServiceException("插入数据失败:updateNewForDemand");

        Map<String, Object> data = new HashMap<>();
        data.put("creditCode", creditCode);
        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 获取某个企业的所有季度报表
     * @param
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

    /**
     * 新企业续约
     * @param json
     * @param voucher
     * @return
     * @throws ServiceException
     * @throws IOException
     */
    @Override
    public Map<String, Object> newEnterpriseContract(String json, MultipartFile voucher) throws ServiceException, IOException {
        JSONObject jsonObject = JSONObject.parseObject(json);
        NewDemand newDemand = JSONObject.parseObject(json, NewDemand.class);
        String creditCode = jsonObject.getString("creditCode");

        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String submitTime = dateFormat.format(new Date());

        String newDemandId = newEnterpriseDao.demandExit(creditCode);
        if (newEnterpriseDao.selectDemandByNewDemandId(newDemandId).size() == 0)
            throw new ServiceException("请先递交入驻申请书");

        String newDemandFormId = UUID.randomUUID().toString();
        String id = UUID.randomUUID().toString();
        newDemand.setId(newDemandFormId);
        newDemand.setTime(submitTime);
        newDemand.setNewDemandId(newDemandId);
        if (newEnterpriseDao.addNewDemand(newDemand) < 1 &&
                newEnterpriseDao.addNewDemandContract(id, creditCode, voucher.getBytes(), submitTime) < 1)
            throw new ServiceException("续约失败");

        return MyResponseUtil.getResultMap(creditCode, 0, "success");
    }
}