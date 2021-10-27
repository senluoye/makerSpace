package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.OldEnterpriseDao;
import com.qks.makerSpace.entity.*;
import com.qks.makerSpace.service.OldEnterpriseService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import com.qks.makerSpace.util.OldParserUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
            return MyResponseUtil.getResultMap(oldId, 1, "注册成功");

        return MyResponseUtil.getResultMap(null, 0, "注册失败");
    }

    /**
     * 信息状态展示
     * @return
     */
    @Override
    public Map<String, Object> getOldEnterprise() {
        List<Map<String, Object>> data = new ArrayList<>();
        List<Old> oldList = oldEnterpriseDao.getAllOldEnterprise();

        if (oldList != null) {
            for (Old old : oldList) {
                List<OldDemand> oldDemands = oldEnterpriseDao.getOldDemandById(old.getOldDemand_id());


            }
            oldList.add();
        }


        return MyResponseUtil.getResultMap(data, 1, "获取成功");
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
                                                   MultipartFile[] files) throws IllegalAccessException {
        String id = JWTUtils.parser(token).get("id").toString();
        map.put("id", id);

        Old old = OldParserUtils.parser(map);
        List<OldMainPerson> oldMainPeople =  OldParserUtils.OldMainPersonParser(map.get("oldMainPerson"));
        List<OldProject> oldProjects = OldParserUtils.OldProjectsParser(map.get("oldProject"));
        List<OldIntellectual> oldIntellectuals = OldParserUtils.OldIntellectualParser(map.get("oldIntellectual"));
        List<OldFunding> oldFundings = OldParserUtils.OldFundingParser(map.get("oldFunding"));
        List<OldShareholder> oldShareholders = OldParserUtils.OldShareholderParser(map.get("oldShareholder"));

        old.setOldShareholderId(oldShareholders.get(0).getOldShareholderId());
        old.setOldMainpersonId(oldMainPeople.get(0).getOldMainpersonId());
        old.setOldProjectId(oldProjects.get(0).getOldProjectId());
        old.setOldInapplyId(oldIntellectuals.get(0).getOldIntellectualId());
        old.setOldFundingId(oldFundings.get(0).getFundingId());

        if (oldEnterpriseDao.updateOldEnterprise(old) > 0) {
            
        }


        return null;
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

