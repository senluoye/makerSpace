package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.OldEnterpriseDao;
import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.OldEnterpriseService;
import com.qks.makerSpace.util.ChangeUtils;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import com.qks.makerSpace.util.OldParserUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.*;

@Service
public class  OldEnterpriseServiceImpl implements OldEnterpriseService, Serializable {

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
        old.setRegisterAddress(map.get("registerAddress").toString());
        old.setRepresentEmail(map.get("representEmail").toString());
        old.setAgent(map.get("agent").toString());
        old.setAgentPhone(map.get("agentPhone").toString());
        old.setAgentEmail(map.get("agentEmail").toString());

        if (oldEnterpriseDao.oldRegister(old) > 0)
            return MyResponseUtil.getResultMap(
                    new HashMap<String, Object>().put("id", oldId), 0, "success");

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
            List<OldMainPerson> oldMainPeosons = oldEnterpriseDao.getOldMainPeopleById(x.getOldMainpersonId());
            List<OldProject> oldProjects = oldEnterpriseDao.getOldProjectById(x.getOldProjectId());
            List<OldFunding> oldFundings = oldEnterpriseDao.getOldFundingById(x.getOldFundingId());
            List<OldShareholder> oldShareholders = oldEnterpriseDao.getOldShareholderById(x.getOldShareholderId());
            List<OldIntellectual> oldIntellectuals = oldEnterpriseDao.getOldIntellectualById(x.getOldIntellectualId());

            Map<String, Object> temp = OldParserUtils.OldGetResponse(x);
            temp.put("oldDemand", oldDemands);
            temp.put("oldMainPerson", oldMainPeosons);
            temp.put("oldProject", oldProjects);
            temp.put("oldFunding", oldFundings);
            temp.put("oldShareholder", oldShareholders);
            temp.put("oldIntellectual", oldIntellectuals);
            data.add(temp);
        });

        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 缴费
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> oldEnterprisePay(Map<String, Object> map) {
        return null;
    }

    /**
     * 科技园场地申请
     * @param map
     * @return
     */
    @RequestMapping(value = "/old/demand", method = RequestMethod.POST)
    public Map<String, Object> oldEnterpriseDemand(@RequestBody JSONObject map) throws ServiceException {
        String id = UUID.randomUUID().toString();
        String creditCode = map.getString("creditCode");

        OldDemand oldDemand = new OldDemand();

        oldDemand.setLeaseArea(map.getString("leaseArea"));
        oldDemand.setPosition(map.getString("position"));
        oldDemand.setLease(map.getString("lease"));
        oldDemand.setFloor(map.getString("floor"));
        oldDemand.setElectric(map.getString("electric"));
        oldDemand.setWater(map.getString("water"));
        oldDemand.setWeb(map.getString("web"));
        oldDemand.setOthers(map.getString("others"));

//        if (oldEnterpriseDao.addOldDemand(oldDemand) > 0
//                && oldEnterpriseDao.updateOldForDemand(oldId, state, submitTime, room, inapplyId) > 0) {
//            return MyResponseUtil.getResultMap(new HashMap<>().put("", creditCode), 0, "success");
//        }

        throw new ServiceException("插入数据失败");
    }

    /**
     * 入园申请表填写
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> updateOldEnterprise(String token,
                                                   JSONObject map,
                                                   MultipartFile[] files) throws Exception {
        String userId = JWTUtils.parser(token).get("userId").toString();

        String id = map.get("id").toString();

        Old old = OldParserUtils.parser(map);
        List<OldShareholder> oldShareholders = OldParserUtils.OldShareholderParser(map.getJSONArray("oldShareholder"));
        List<OldMainPerson> oldMainPeoples =  OldParserUtils.OldMainPersonParser(map.getJSONArray("oldMainPerson"));
        List<OldProject> oldProjects = OldParserUtils.OldProjectsParser(map.getJSONArray("oldProject"));
        List<OldIntellectual> oldIntellectuals = OldParserUtils.OldIntellectualParser(map.getJSONArray("oldIntellectual"));
        List<OldFunding> oldFundings = OldParserUtils.OldFundingParser(map.getJSONArray("oldFunding"));

        old.setOldShareholderId(oldShareholders.get(0).getOldShareholderId());
        old.setOldMainpersonId(oldMainPeoples.get(0).getOldMainpersonId());
        old.setOldProjectId(oldProjects.get(0).getOldProjectId());
        old.setOldInapplyId(oldIntellectuals.get(0).getOldIntellectualId());
        old.setOldFundingId(oldFundings.get(0).getFundingId());
        old.setLicense(files[0].getBytes());
        old.setCertificate(files[1].getBytes());

        for (int i = 2; i < files.length; i++) {
            oldIntellectuals.get(i - 2).setIntellectualFile(files[i].getBytes());
        }

        if (oldEnterpriseDao.updateOld(old) > 0) {
            for (OldMainPerson oldMainPeople : oldMainPeoples) {
                if (oldEnterpriseDao.insertOldMainPeople(oldMainPeople) <= 0) {
                    throw new ServiceException("信息插入失败:oldMainPeople");
                }
            }
            for (OldProject oldProject : oldProjects) {
                if (oldEnterpriseDao.insertOldProjects(oldProject) <= 0) {
                    throw new ServiceException("信息插入失败:oldProject");
                }
            }
            for (OldIntellectual oldIntellectual : oldIntellectuals) {
                if (oldEnterpriseDao.insertOldIntellects(oldIntellectual) <= 0) {
                    throw new ServiceException("信息插入失败:oldIntellectual");
                }
            }
            for (OldFunding oldFunding : oldFundings) {
                if (oldEnterpriseDao.insertOldFundings(oldFunding) <= 0) {
                    throw new ServiceException("信息插入失败:oldFunding");
                }
            }
            for (OldShareholder oldShareholder : oldShareholders) {
                if (oldEnterpriseDao.insertOldShareholder(oldShareholder) <= 0) {
                    throw new ServiceException("信息插入失败:oldShareholder");
                }
            }

            Audit audit = new Audit();
            audit.setAuditId(id);
            audit.setAdministratorAudit(false);
            audit.setLeadershipAudit(false);

            if (oldEnterpriseDao.insertAudit(audit) <= 0)
                throw new ServiceException("信息插入失败:audit");
        } else throw new ServiceException("信息插入失败:old");

        if (oldEnterpriseDao.updateUserCompany(userId, id) < 1)
            throw new ServiceException("公司绑定失败");

        return MyResponseUtil.getResultMap(new HashMap<String, Object>().put("id", id), 0, "success");
    }

}

