package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.LeaderDao;
import com.qks.makerSpace.entity.database.News;
import com.qks.makerSpace.entity.database.Old;
import com.qks.makerSpace.entity.request.*;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.LeaderService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class LeaderServiceImpl implements LeaderService {

    private final LeaderDao leaderDao;

    public LeaderServiceImpl(LeaderDao leaderDao) {
        this.leaderDao = leaderDao;
    }

    /**
     * 获取科技园最新入园申请
     * @return
     */
    @Override
    public Map<String, Object> authorizationTechnology() {
        List<LeaderTechnologyApplyingReq> lists = leaderDao.getAllTechnologyApplying();
        for (LeaderTechnologyApplyingReq applyingReq : lists) {
            // 首先看看该公司在不在旧企业表中
            List<String> oldNameList = leaderDao.getOldNameByCreditCode(applyingReq.getCreditCode());
            if (oldNameList.size() > 0) // 不为0，在旧企业中
                applyingReq.setName(oldNameList.get(0));
            else {
                List<String> newNameList = leaderDao.getNewNameByCreditCode(applyingReq.getCreditCode());
                applyingReq.setName(newNameList.get(0));
            }
        }
        return MyResponseUtil.getResultMap(lists, 0, "success");
    }

    /**
     * 获取众创空间最新入园申请
     * @return
     */
    @Override
    public Map<String, Object> authorizationSpace() {
        List<LeaderSpaceApplyingReq> lists = leaderDao.getAllSpaceApplying();
        for (LeaderSpaceApplyingReq spaceApplyingReq : lists) {
            String inApplyId =  spaceApplyingReq.getInApplyId();
            String name = leaderDao.getSpaceNameByCreditCode(inApplyId).get(0);
            spaceApplyingReq.setName(name);
        }
        return MyResponseUtil.getResultMap(lists, 0, "success");
    }

    /**
     * 同意季度报表
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> agreeFormById(JSONObject map) throws ServiceException {
        String formId = map.getString("formId");
        if (leaderDao.agreeForm(formId) > 0) {
            return MyResponseUtil.getResultMap(null,0,"success");
        } else throw new ServiceException("同意操作异常");
    }

    /**
     * 不同意季度报表
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> disagreeFormById(JSONObject map) throws ServiceException {
        String formId = map.getString("formId");
        if (leaderDao.disagreeForm(formId) > 0) {
            return MyResponseUtil.getResultMap(null,0,"success");
        } else throw new ServiceException("不同意操作异常");
    }

    /**
     * 获取领导未通过的季度报表
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> getFormLeaderAudit() throws ServiceException {
        List<BriefFormReq> list = leaderDao.getLeaderAudit();

        if (list.size() != 0){
            Iterator<BriefFormReq> iterator = list.iterator();
            while (iterator.hasNext()) {
                BriefFormReq briefFormReq = iterator.next();
                briefFormReq.setAdminAudit("已通过");
                briefFormReq.setLeaderAudit("未通过");
            }
            return MyResponseUtil.getResultMap(list,0,"success");
        }
        else throw new ServiceException("没有需要领导审核的季度报表");
    }

    /**
     * 获取某个企业最新的季度报表
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> getFormDetail(JSONObject map) throws ServiceException {
        String creditCode = map.getString("creditCode");
        String getTime = map.getString("getTime");

        FormReq formReq = leaderDao.getDetailForm(creditCode, getTime);
        if (formReq == null) throw new ServiceException("该数据不存在，请刷新重试");
        else return MyResponseUtil.getResultMap(formReq,0,"success");
    }

    /**
     * 获取某个企业的历史季度报表
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> getFormByCompany(JSONObject map) throws ServiceException {
        String creditCode = map.getString("creditCode");
        List<BriefFormReq> list = leaderDao.getCompanyForm(creditCode);

        if (list.size() != 0) {
            Iterator<BriefFormReq> iterator = list.iterator();
            while (iterator.hasNext()) {
                BriefFormReq briefFormReq = iterator.next();
                if (briefFormReq.getAdminAudit().equals("0")) briefFormReq.setAdminAudit("待审核");
                else if (briefFormReq.getAdminAudit().equals("1")) briefFormReq.setAdminAudit("未通过");
                else briefFormReq.setAdminAudit("已通过");

                if (briefFormReq.getLeaderAudit().equals("0")) briefFormReq.setLeaderAudit("待审核");
                else if (briefFormReq.getLeaderAudit().equals("1")) briefFormReq.setLeaderAudit("未通过");
                else briefFormReq.setLeaderAudit("已通过");
            }
            return MyResponseUtil.getResultMap(list,0,"success");
        } else throw new ServiceException("该企业未提交过季度报表");
    }

    @Override
    public Map<String, Object> getAllTechnologyApplying() {
        List<LeaderTechnologyApplyingReq> lists = leaderDao.getAllTechnologyApplying();
        for (LeaderTechnologyApplyingReq applyingReq : lists) {
            String id;
            List<String> oldNameList = leaderDao.getOldNameByCreditCode(applyingReq.getCreditCode());
            if (oldNameList.size() > 0) {
                id = leaderDao.selectOldIdByTimeAndCreditCode(applyingReq.getCreditCode(), applyingReq.getSubmitTime());
                if (applyingReq.getDescribe().equals("科技园")) applyingReq.setDescribe("3");
                else applyingReq.setDescribe("4");
                applyingReq.setName(oldNameList.get(0));
            } else {
                List<String> newNameList = leaderDao.getNewNameByCreditCode(applyingReq.getCreditCode());
                id = leaderDao.selectNewIdByTimeAndCreditCode(applyingReq.getCreditCode(),applyingReq.getSubmitTime());
                if (applyingReq.getDescribe().equals("科技园")) applyingReq.setDescribe("2");
                else applyingReq.setDescribe("4");
                applyingReq.setName(newNameList.get(0));
            }
            applyingReq.setId(id);
        }

        return MyResponseUtil.getResultMap(lists,0,"success");
    }

    @Override
    public Map<String, Object> getAllTechnologyApplied() {
        List<LeaderTechnologyApplyingReq> lists = leaderDao.getAllApplied();
        for (LeaderTechnologyApplyingReq applyingReq : lists) {
            String id;
            List<String> oldNameList = leaderDao.getOldNameByCreditCode(applyingReq.getCreditCode());
            if (oldNameList.size() > 0) {
                id = leaderDao.selectOldIdByTimeAndCreditCode(applyingReq.getCreditCode(), applyingReq.getSubmitTime());
                if (applyingReq.getDescribe().equals("科技园")) applyingReq.setDescribe("3");
                else applyingReq.setDescribe("4");
                applyingReq.setName(oldNameList.get(0));
            } else {
                List<String> newNameList = leaderDao.getNewNameByCreditCode(applyingReq.getCreditCode());
                id = leaderDao.selectNewIdByTimeAndCreditCode(applyingReq.getCreditCode(),applyingReq.getSubmitTime());
                if (applyingReq.getDescribe().equals("科技园")) applyingReq.setDescribe("2");
                else applyingReq.setDescribe("4");
                applyingReq.setName(newNameList.get(0));
            }
            applyingReq.setId(id);
        }

        return MyResponseUtil.getResultMap(lists,0,"success");
    }

    @Override
    public Map<String, Object> getAllApplying() {
        List<LeaderTechnologyApplyingReq> lists = leaderDao.getAllApplying();
        for (LeaderTechnologyApplyingReq applyingReq : lists) {
            String id;
            List<String> oldNameList = leaderDao.getOldNameByCreditCode(applyingReq.getCreditCode());
            if (oldNameList.size() > 0) {
                id = leaderDao.selectOldIdByTimeAndCreditCode(applyingReq.getCreditCode(), applyingReq.getSubmitTime());
                if (applyingReq.getDescribe().equals("科技园")) applyingReq.setDescribe("3");
                else applyingReq.setDescribe("4");
                applyingReq.setName(oldNameList.get(0));
            } else {
                List<String> newNameList = leaderDao.getNewNameByCreditCode(applyingReq.getCreditCode());
                id = leaderDao.selectNewIdByTimeAndCreditCode(applyingReq.getCreditCode(),applyingReq.getSubmitTime());
                if (applyingReq.getDescribe().equals("科技园")) applyingReq.setDescribe("2");
                else applyingReq.setDescribe("4");
                applyingReq.setName(newNameList.get(0));
            }
            applyingReq.setId(id);
        }

        return MyResponseUtil.getResultMap(lists,0,"success");
    }

    @Override
    public Map<String, Object> getOldTechnologyById(String id) throws ServiceException {
        Old old = leaderDao.getOld(id);
        if (old == null)
            throw new ServiceException("数据不存在");

        Map<String, Object> data = new HashMap<>();

        data.put("old", old);
        data.put("oldDemand", leaderDao.getOldDemandById(old.getOldDemandId()));
        data.put("oldShareholder", leaderDao.getOldShareholderById(old.getOldShareholderId()));
        data.put("oldMainPerson", leaderDao.getOldMainPeopleById(old.getOldMainpersonId()));
        data.put("oldProject", leaderDao.getOldProjectById(old.getOldProjectId()));
        data.put("oldIntellectual", leaderDao.getOldIntellectualById(old.getOldIntellectualId()));
        data.put("oldFunding", leaderDao.getOldFundingById(old.getOldFundingId()));

        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    @Override
    public Map<String, Object> getNewTechnologyById(String id) throws ServiceException {
        News news = leaderDao.getNew(id);
        if (news == null)
            throw new ServiceException("数据不存在");

        Map<String, Object> data = new HashMap<>();

        data.put("news", news);
        data.put("newDemand", leaderDao.getNewDemandById(news.getNewDemandId()));
        data.put("newShareholder", leaderDao.getNewShareholder(news.getNewShareholderId()));
        data.put("newMainPerson", leaderDao.getNewMainPerson(news.getNewMainpersonId()));
        data.put("newProject", leaderDao.getNewProject(news.getNewProjectId()));
        data.put("newIntellectual", leaderDao.getNewIntellectual(news.getNewIntellectualId()));

        return MyResponseUtil.getResultMap(data, 0, "success");
    }


}