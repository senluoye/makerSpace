package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.OldEnterpriseDao;
import com.qks.makerSpace.entity.*;
import com.qks.makerSpace.service.OldEnterpriseService;
import com.qks.makerSpace.util.ChangeUtils;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import com.qks.makerSpace.util.OldParserUtils;
import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@Service
public class OldEnterpriseServiceImpl implements OldEnterpriseService, Serializable {

    private final OldEnterpriseDao oldEnterpriseDao;

    public OldEnterpriseServiceImpl(OldEnterpriseDao oldEnterpriseDao) {
        this.oldEnterpriseDao = oldEnterpriseDao;
    }

    /**
     * 注册
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> oldRegister(Map<String, Object> map) {
        Old old = new Old();

        String oldId = UUID.randomUUID().toString();
        old.setOldId(oldId);
        old.setCreditCode(map.get("creditCode").toString());
        old.setOrganizationCode(map.get("organizationCode").toString());
        old.setPassword(map.get("password").toString());
        old.setName(map.get("name").toString());
        old.setRepresent(map.get("represent").toString());
        old.setRepresentPhone(map.get("representPhone").toString());
        old.setRepresentEmail(map.get("representEmail").toString());
        old.setAgent(map.get("agent").toString());
        old.setAgentPhone(map.get("agentPhone").toString());
        old.setCreditCode(map.get("agentEmail").toString());

        if (oldEnterpriseDao.oldRegister(old) > 0)
            return MyResponseUtil.getResultMap(new HashMap<String, Object>().put("id", oldId), 0, "success");

        return MyResponseUtil.getResultMap(null, -1, "fail");
    }

    /**
     * 信息状态展示
     * @return
     */
    @Override
    public Map<String, Object> getOldEnterprise() {
        List<Map<String, Object>> data = new ArrayList<>();
        List<Old> oldList = oldEnterpriseDao.getAllOld();

        oldList.forEach(x -> {
            try {
                data.add(ChangeUtils.getObjectToMap(x));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        oldList.forEach(x -> {
            List<OldDemand> oldDemands = oldEnterpriseDao.getOldDemandById(x.getOldDemand_id());
            List<OldMainPerson> oldMainPeople = oldEnterpriseDao.getOldMainPeopleById(x.getOldMainpersonId());
            List<OldProject> oldProjects = oldEnterpriseDao.getOldProjectById(x.getOldProjectId());
            List<OldFunding> oldFundings = oldEnterpriseDao.getOldFundingById(x.getOldFundingId());
            List<OldShareholder> oldShareholders = oldEnterpriseDao.getOldShareholderById(x.getOldShareholderId());
            List<OldIntellectual> oldIntellectuals = oldEnterpriseDao.getOldIntellectualById(x.getOldIntellectualId());


            oldList.add(oldDemands);

        });


        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 众创空间场地申请
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> applyForMakerSpace(Map<String, Object> map) {
        return null;
    }

    /**
     * 租赁缴费
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> oldEnterprisePay(Map<String, Object> map) {
        return null;
    }

    /**
     * 入园申请表填写
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> updateOldEnterprise(String token,
                                                   Map<String, Object> map,
                                                   MultipartFile[] files) throws IllegalAccessException, IOException {
        String id = JWTUtils.parser(token).get("id").toString();
        map.put("id", id);

        Old old = OldParserUtils.parser(map);
        List<OldMainPerson> oldMainPeoples =  OldParserUtils.OldMainPersonParser(map.get("oldMainPerson"));
        List<OldProject> oldProjects = OldParserUtils.OldProjectsParser(map.get("oldProject"));
        List<OldIntellectual> oldIntellectuals = OldParserUtils.OldIntellectualParser(map.get("oldIntellectual"));
        List<OldFunding> oldFundings = OldParserUtils.OldFundingParser(map.get("oldFunding"));
        List<OldShareholder> oldShareholders = OldParserUtils.OldShareholderParser(map.get("oldShareholder"));

        old.setOldShareholderId(oldShareholders.get(0).getOldShareholderId());
        old.setOldMainpersonId(oldMainPeoples.get(0).getOldMainpersonId());
        old.setOldProjectId(oldProjects.get(0).getOldProjectId());
        old.setOldInapplyId(oldIntellectuals.get(0).getOldIntellectualId());
        old.setOldFundingId(oldFundings.get(0).getFundingId());
        old.setLicense(files[0].getBytes());
        old.setCertificate(files[1].getBytes());

        for (int i = 1; i < files.length; i++) {
            oldIntellectuals.get(i).setIntellectualFile(files[i].getBytes());
        }

        if (oldEnterpriseDao.updateOld(old) > 0) {
            oldMainPeoples.forEach(oldEnterpriseDao::insertOldMainPeople);
            oldProjects.forEach(oldEnterpriseDao::insertOldProjects);
            oldIntellectuals.forEach(oldEnterpriseDao::insertOldIntellects);
            oldFundings.forEach(oldEnterpriseDao::insertOldFundings);
            oldShareholders.forEach(oldEnterpriseDao::insertOldShareholder);
        }

        return MyResponseUtil.getResultMap(new HashMap<String, Object>().put("id", id), 1, "success");
    }

    /**
     * 众创空间退出
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> quitMakerSpace(Map<String, Object> map) {
        return null;
    }
}

