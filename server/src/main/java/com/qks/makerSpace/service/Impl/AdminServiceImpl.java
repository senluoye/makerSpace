package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.AdminDao;
import com.qks.makerSpace.dao.SpaceDao;
import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.response.AdminSuggestion;
import com.qks.makerSpace.entity.response.AllForm;
import com.qks.makerSpace.entity.response.AllSpace;
import com.qks.makerSpace.entity.response.AllTechnology;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.AdminService;
import com.qks.makerSpace.util.ChangeUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import com.qks.makerSpace.util.WordChangeUtils;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao;

    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    /**
     * 获取全部科技园企业的申请信息
     */
    @Override
    public Map<String, Object> getAllDetails() {
        List<AllTechnology> dataOne = adminDao.getAllOldDetails();

        Iterator<AllTechnology> iterator_one = dataOne.iterator();
        while (iterator_one.hasNext()) {
            AllTechnology allTechnology = iterator_one.next();
            allTechnology.setCompanyKind("old");
        }

        List<AllTechnology> dataTwo = adminDao.getAllNewDetails();

        Iterator<AllTechnology> iterator_two = dataOne.iterator();
        while (iterator_one.hasNext()) {
            AllTechnology allTechnology = iterator_two.next();
            allTechnology.setCompanyKind("new");
        }

        List<AllTechnology> data = new ArrayList<>(dataOne);
        data.addAll(dataTwo);

        Iterator<AllTechnology> iterator = data.iterator();
        while (iterator.hasNext()) {
            AllTechnology allTechnology = iterator.next();
            if(allTechnology.isAdministratorAudit() == true) {
                allTechnology.setAudit("已通过");
            } else allTechnology.setAudit("未通过");
        }

        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 获取某一个旧企业入园申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> getOldTechnologyById(String creditCode) throws ServiceException {
        Old old = adminDao.getOld(creditCode);
        if (old == null)
            throw new ServiceException("数据不存在");

        Map<String, Object> data = new HashMap<>();

//        data.put("creditCode", old.getCreditCode());
//        data.put("registerAddress", old.getRegisterAddress());
//        data.put("registerCapital", old.getRealCapital());
//        data.put("realAddress", old.getRealAddress());
//        data.put("realCapital", old.getRealCapital());
//        data.put("lastIncome", old.getLastIncome());
//        data.put("lastTax", old.getLastTax());
//        data.put("employees", old.getEmployees());
//        data.put("originNumber", old.getOriginNumber());
//        data.put("setDate", old.getSetDate());
//        data.put("nature", old.getNature());
//        data.put("involved", old.getInvolved());
//        data.put("mainBusiness", old.getMainBusiness());
//        data.put("way", old.getWay());
//        data.put("business", old.getBusiness());

        data.put("old", old);
        data.put("oldDemand", adminDao.getOldDemandById(old.getOldDemandId()));
        data.put("oldShareholder", adminDao.getOldShareholderById(old.getOldShareholderId()));
        data.put("oldMainPerson", adminDao.getOldMainPeopleById(old.getOldMainpersonId()));
        data.put("oldProject", adminDao.getOldProjectById(old.getOldProjectId()));
        data.put("oldIntellectual", adminDao.getOldIntellectualById(old.getOldIntellectualId()));
        data.put("oldFunding", adminDao.getOldFundingById(old.getOldFundingId()));

        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 获取某一个新企业入园申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> getNewTechnologyById(String creditCode) throws ServiceException {
        News news = adminDao.getNew(creditCode);
        if (news == null)
            throw new ServiceException("数据不存在");

        Map<String, Object> data = new HashMap<>();

        data.put("news", news);
        data.put("newDemand", adminDao.getNewDemandById(news.getNewDemandId()));
        data.put("newShareholder", adminDao.getNewShareholder(news.getNewShareholderId()));
        data.put("newMainPerson", adminDao.getNewMainPerson(news.getNewMainpersonId()));
        data.put("newProject", adminDao.getNewProject(news.getNewProjectId()));
        data.put("newIntellectual", adminDao.getNewIntellectual(news.getNewIntellectualId()));

        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 获取某一个企业众创空间申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> getSpaceById(String InApplyId) throws IllegalAccessException {
        Map<String, Object> data = new HashMap<>();
        Space space = adminDao.getSpaceById(InApplyId);
        data = ChangeUtils.getObjectToMap(space);
        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 获取全部众创空间企业的申请信息
     */
    @Override
    public Map<String, Object> getAllSpaceDetails() {
        List<Space> spaces = adminDao.getAllSpaceDetails();
        List<AllSpace> allSpaces = new ArrayList<>();

        for (Space space : spaces) {
            AllSpace allSpace = new AllSpace();
            Audit audit= adminDao.getAuditById(space.getInApplyId());
            String inApplyId = space.getInApplyId();
            List<SpacePerson> spacePeople = adminDao.getSpacePeopleById(inApplyId);

            allSpace.setInApplyId(inApplyId);
            allSpace.setAdministratorAudit(audit.getAdministratorAudit());
            allSpace.setCreateName(space.getCreateName());
            allSpace.setApplyTime(space.getApplyTime());
            allSpace.setTeamNumber(space.getTeamNumber());
            allSpace.setDescribe(space.getDescribe());
            allSpace.setHelp(space.getHelp());
            allSpace.setPerson(spacePeople);

            allSpaces.add(allSpace);
        }

        return MyResponseUtil.getResultMap(allSpaces, 0, "success");
    }

    /**
     * 删除某一个企业入园申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> deleteByCreditCode(String creditCode) throws ServiceException {
        if (adminDao.selectCreditCodeFromNewByCreditCode(creditCode) == null)
            throw new ServiceException("表中不存在该项数据");
        else if (adminDao.deleteOldByCreditCode(creditCode) < 1)
                throw new ServiceException("删除失败");

        return MyResponseUtil.getResultMap(creditCode, 0, "success");
    }

    /**
     * 删除一个众创空间的申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> deleteSpaceById(String inApplyId) throws ServiceException {
        if (adminDao.deleteSpaceByCreditCode(inApplyId) < 1)
            throw new ServiceException("删除信息失败");

        return MyResponseUtil.getResultMap(inApplyId, 0, "success");
    }

    /**
     * 同意某一个企业科技园申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> agreeTechnologyById(JSONObject map) throws ServiceException {
        String creditCode = map.getString("creditCode");

        AdminSuggestion adminSuggestion = new AdminSuggestion();

        adminSuggestion.setCreditCode(creditCode);
        adminSuggestion.setSuggestion(map.getString("suggestion"));
        adminSuggestion.setNote(map.getString("note"));

        if (adminDao.agreeById(creditCode, "通过") < 1) {
            throw new ServiceException("管理员审核失败");
        } else {
            if (adminDao.selectCreditCodeFromNewByCreditCode(creditCode) != null) {
                if (adminDao.updateNewSuggestion(adminSuggestion) < 0)
                    throw new ServiceException("更新new失败");
            }
            else if (adminDao.selectCreditCodeFromOldByCreditCode(creditCode) != null) {
                if (adminDao.updateOldSuggestion(adminSuggestion) < 0)
                    throw new ServiceException("更新old失败");
            }
            else throw new ServiceException("表中不存在该信息");
        }

        return MyResponseUtil.getResultMap(creditCode, 0, "success");
    }

    /**
     * 取消某一个企业科技园申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> disagreeTechnologyById(JSONObject map) throws ServiceException {
        String creditCode = map.getString("creditCode");

        AdminSuggestion adminSuggestion = new AdminSuggestion();

        adminSuggestion.setCreditCode(creditCode);
        adminSuggestion.setSuggestion(map.getString("suggestion"));
        adminSuggestion.setNote(map.getString("note"));

        if (adminDao.disagreeById(creditCode, "不通过") < 1) {
            throw new ServiceException("管理员审核失败");
        } else {
            if (adminDao.selectCreditCodeFromNewByCreditCode(creditCode) != null) {
                if (adminDao.updateNewSuggestion(adminSuggestion) < 0)
                    throw new ServiceException("更新new失败");
            }
            else if (adminDao.selectCreditCodeFromOldByCreditCode(creditCode) != null) {
                if (adminDao.updateOldSuggestion(adminSuggestion) < 0)
                    throw new ServiceException("更新old失败");
            }
            else throw new ServiceException("表中不存在该信息");
        }

        return MyResponseUtil.getResultMap(creditCode, 0, "success");
    }

    /**
     * 同意某一个企业众创空间申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> agreeSpaceById(JSONObject map) throws ServiceException {
        String inApplyId = map.getString("inApplyId");

        if (adminDao.agreeById(inApplyId, "通过") < 1)
            throw new ServiceException("管理员审核失败");

        return MyResponseUtil.getResultMap(inApplyId, 0, "success");
    }

    /**
     * 取消某一个企业众创空间申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> disagreeSpaceById(JSONObject map) throws ServiceException {
        String inApplyId = map.getString("inApplyId");

        if (adminDao.disagreeById(inApplyId, "不通过") < 1)
            throw new ServiceException("管理员审核失败");

        return MyResponseUtil.getResultMap(inApplyId, 0, "success");
    }

    @Override
    public Map<String, Object> getTechnologyForm(JSONObject jsonObject) throws ServiceException {

        List<AllForm> data = adminDao.getOldFormByCreditCode();
        List<AllForm> newMap = adminDao.getNewFormByCreditCode();

        data.addAll(newMap);

        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 获取导出表的信息
     * @return HashMap
     */
    @Override
    public Map<String, Object> getDownLoadForm () {
        return null;
    }

    /**
     * 导出文件
     * @param
     * @param
     */
    @Override
    public void downLoadWord(HttpServletResponse response, Map<String, Object> map) throws ServiceException {
        try {
            String fileName = Calendar.getInstance().get(Calendar.YEAR) + "年度" + map.get("teamName").toString() + "统计表";
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-download");
            response.setHeader("Content-disposition",String.format("attachment; filename=\"%s\"",fileName+".docx"));
            WordChangeUtils.searchAndReplace(response.getOutputStream(), map);
        } catch (IOException e) {
            throw new ServiceException("导出信息表失败");
        }
    }
}