package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.NewEnterpriseDao;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NewEnterpriseServiceImpl implements NewEnterpriseService , Serializable {

    private final NewEnterpriseDao newEnterpriseDao;

    public NewEnterpriseServiceImpl(NewEnterpriseDao newEnterpriseDao) {
        this.newEnterpriseDao = newEnterpriseDao;
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
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> newRegister(Map<String, Object> map, MultipartFile[] file) throws IOException{

        News news = new News();

        String newId = UUID.randomUUID().toString();
        news.setNewId(newId);
        news.setCreditCode(map.get("creditCode").toString());
        news.setOrganizationCode(map.get("organizationCode").toString());
        news.setPassword(map.get("password").toString());
        news.setName(map.get("name").toString());
        news.setPicture(file[0].getBytes());
        news.setRepresent(map.get("represent").toString());
        news.setRepresentCard(map.get("representCard").toString());
        news.setRepresentPhone(map.get("representPhone").toString());
        news.setRepresentEmail(map.get("representEmail").toString());
        news.setAgent(map.get("agent").toString());
        news.setAgentPhone(map.get("agentPhone").toString());
        news.setAgentEmail(map.get("agentEmail").toString());

        if (newEnterpriseDao.newRegister(news) > 0) {
            return MyResponseUtil.getResultMap(new HashMap<String, Object>().put("id",newId),1,"注册成功");
        } else {
            return MyResponseUtil.getResultMap(null,0,"注册失败");
        }
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
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> updateNewEnterprise(String token,
                                                   Map<String, Object> map,
                                                   MultipartFile[] files) throws Exception {
        String userId = JWTUtils.parser(token).get("userId").toString();

        String id = map.get("id").toString();

        News news = NewParserUtils.newsParser(map);
        List<NewMainPerson> newMainPeople = NewParserUtils.NewMainPersonParser(map.get("newMainPerson"));
        List<NewProject> newProjects = NewParserUtils.NewProjectParser(map.get("newProject"));
        List<NewIntellectual> newIntellectuals = NewParserUtils.NewIntellectualParser(map.get("newIntellectual"));
        List<NewShareholder> newShareholders = NewParserUtils.NewShareholdersParser(map.get("newShareholder"));

        news.setNewShareholderId(newShareholders.get(0).getNewShareholderId());
        news.setNewMainpersonId(newMainPeople.get(0).getNewMainpersonId());
        news.setNewProjectId(newProjects.get(0).getNewProjectId());
        news.setNewIntellectualId(newIntellectuals.get(0).getNewIntellectualId());
        news.setCertificate(files[0].getBytes());
        news.setSubmitTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").toString());

        for (int i = 1; i <files.length; i++) {
            newIntellectuals.get(i).setIntellectualFile(files[i].getBytes());
        }

        if (newEnterpriseDao.updateNew(news) > 0) {
            newMainPeople.forEach(newEnterpriseDao::insertNewMainPerson);
            newProjects.forEach(newEnterpriseDao::insertNewProject);
            newIntellectuals.forEach(newEnterpriseDao::insertNewIntellectual);
            newShareholders.forEach(newEnterpriseDao::insertNewShareholder);
        }

        if (newEnterpriseDao.updateUserCompany(userId, id) < 1)
            throw new ServiceException("公司绑定失败");

        return MyResponseUtil.getResultMap(new HashMap<String, Object>().put("id",id),1,"success");
    }

}