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
     *
     * @param token
     * @param map
     * @param picture
     * @param representCard
     * @param certificate
     * @param intellectualFile
     * @return
     * @throws IOException
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> newRegister(String token,
                                           JSONObject map,
                                           MultipartFile picture,
                                           MultipartFile representCard,
                                           MultipartFile certificate,
                                           MultipartFile[] intellectualFile) throws IOException, ServiceException {
        //数据处理
        String userId = JWTUtils.parser(token).get("userId").toString();
        String creditCode = map.getString("creditCode");

        News news = NewParserUtils.newsParser(map);
        news.setNewId(UUID.randomUUID().toString());

        //picture：名称预核准通知书    representCard：法人身份证复印件     certificate:  教室需要上传教师资格证/学生需要上传学生证
        news.setPicture(picture.getBytes());
        news.setRepresentCard(representCard.getBytes());
        news.setCertificate(certificate.getBytes());

        Date date = new Date();
        news.setSubmitTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

        List<NewMainPerson> newMainPeople = NewParserUtils.NewMainPersonParser(map.getJSONArray("newMainPerson"));
        List<NewProject> newProjects = NewParserUtils.NewProjectParser(map.getJSONArray("newProject"));
        List<NewIntellectual> newIntellectuals = NewParserUtils.NewIntellectualParser(map.getJSONArray("newIntellectual"));
        List<NewShareholder> newShareholders = NewParserUtils.NewShareholdersParser(map.getJSONArray("newShareholder"));

        NewDemand newDemand = JSONObject.parseObject(map.getString("newDemand"),NewDemand.class);
        newDemand.setId(UUID.randomUUID().toString());
        String newDemandId = UUID.randomUUID().toString();
        newDemand.setNewDemandId(newDemandId);
        news.setNewDemandId(newDemandId);
        newDemand.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

        news.setNewShareholderId(newShareholders.get(0).getNewShareholderId());
        news.setNewMainpersonId(newMainPeople.get(0).getNewMainpersonId());
        news.setNewProjectId(newProjects.get(0).getNewProjectId());
        news.setNewIntellectualId(newIntellectuals.get(0).getNewIntellectualId());

        for (int i = 0; i <intellectualFile.length; i++) {
            try {
                newIntellectuals.get(i).setIntellectualFile(intellectualFile[i].getBytes());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ServiceException("读取文件发生错误，请重新上传");
            }
        }

        //插入数据
        newEnterpriseDao.newRegister(news);
        newEnterpriseDao.addNewDemand(newDemand);
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


        //更新 Audit
        Audit audit = new Audit();
        audit.setAuditId(creditCode);
        audit.setAdministratorAudit("未审核");
        audit.setLeadershipAudit("未审核");
        audit.setDescribe("科技园");

        if (oldEnterpriseDao.insertAudit(audit) <= 0)
            throw new ServiceException("信息插入失败:audit");

        if (oldEnterpriseDao.selectUserCompany(creditCode) != null) {
            oldEnterpriseDao.updateUserCompany(userId,creditCode);
        } else {
            oldEnterpriseDao.insertUserCompany(userId, creditCode);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("creditCode", creditCode);
        return MyResponseUtil.getResultMap(data,0,"success");
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
     * 租赁缴费
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> newEnterprisePay(Map<String, Object> map) {
        return null;
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