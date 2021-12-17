package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.AdminDao;
import com.qks.makerSpace.entity.response.All;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.AdminService;
import com.qks.makerSpace.util.MyResponseUtil;
import com.qks.makerSpace.util.WordChangeUtils;
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
        List<All> dataOne = adminDao.getAllOldDetails();
        List<All> dataTwo = adminDao.getAllNewDetails();
        List<All> data = new ArrayList<>(dataOne);
        data.addAll(dataTwo);

        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 获取某一个企业入园申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> getTechnologyById(String id) {
        Map<String, Object> data;
        Map<String, Object> temp = adminDao.getOld(id);

        if (temp != null){
            data = temp;

            data.put("Demand", adminDao.getOldDemandById(id));
            data.put("Shareholder", adminDao.getOldShareholderById(id));
            data.put("MainPerson", adminDao.getOldMainPeopleById(id));
            data.put("Project", adminDao.getOldProjectById(id));
            data.put("Intellectual", adminDao.getOldIntellectualById(id));
            data.put("Funding", adminDao.getOldFundingById(id));
        } else {
            data = adminDao.getNew(id);

            data.put("Demand", adminDao.getNewDemandById(id));
            data.put("Shareholder", adminDao.getNewShareholder(id));
            data.put("MainPerson", adminDao.getNewMainPerson(id));
            data.put("Project", adminDao.getNewProject(id));
            data.put("Intellectual", adminDao.getNewIntellectual(id));
        }

        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 获取某一个企业众创空间申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> getSpaceById(String InApplyId) {
        return null;
    }

    /**
     * 获取全部众创空间企业的申请信息
     */
    @Override
    public Map<String, Object> getAllSpaceDetails() {
        return null;
    }

    /**
     * 删除某一个企业入园申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> deleteByCreditCode(String creditCode) throws ServiceException {

        if (adminDao.selectCreditCodeFromNewByCreditCode(creditCode) != null)

        if (adminDao.deleteOldByCreditCode(creditCode) < 1)
            throw new ServiceException("删除失败");

        return MyResponseUtil.getResultMap(creditCode, 0, "success");
    }

    /**
     * 删除一个众创空间的申请
     * @param String
     * @return HashMap
     */
    @Override
    public Map<String, Object> deleteSpaceById(String creditCode) throws ServiceException {
        if (adminDao.deleteSpaceByCreditCode(creditCode) < 1)
            throw new ServiceException("删除信息失败");

        return MyResponseUtil.getResultMap(creditCode, 0, "success");
    }

    /**
     * 同意某一个企业科技园申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> agreeTechnologyById(JSONObject map) throws ServiceException {
        String creditCode = map.getString("creditCode");

        if (adminDao.agreeById(creditCode) < 1)
            throw new ServiceException("管理员审核失败");

        return MyResponseUtil.getResultMap(creditCode, 0, "success");
    }

    /**
     * 取消某一个企业科技园申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> disagreeTechnologyById(JSONObject map) throws ServiceException {
        String creditCode = map.getString("creditCode");

        if (adminDao.disagreeById(creditCode) < 1)
            throw new ServiceException("管理员审核失败");

        return MyResponseUtil.getResultMap(creditCode, 0, "success");
    }

    /**
     * 同意某一个企业众创空间申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> agreeSpaceById(JSONObject map) throws ServiceException {
        String creditCode = map.getString("creditCode");

        if (adminDao.agreeById(creditCode) < 1)
            throw new ServiceException("管理员审核失败");

        return MyResponseUtil.getResultMap(creditCode, 0, "success");
    }

    /**
     * 取消某一个企业众创空间申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> disagreeSpaceById(JSONObject map) throws ServiceException {
        String creditCode = map.getString("creditCode");

        if (adminDao.disagreeById(creditCode) < 1)
            throw new ServiceException("管理员审核失败");

        return MyResponseUtil.getResultMap(creditCode, 0, "success");
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
     * @param HttpServletResponse 经过处理后的word二进制模板
     * @param HashMap
     */
    @Override
    public void downLoadWord(HttpServletResponse response, Map<String, Object> map) throws ServiceException {
        try {
            String fileName = Calendar.getInstance().get(Calendar.YEAR) + "年度" + map.get("teamName").toString() + "统计表";
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-download");
            response.setHeader("Content-disposition",String.format("attachment; filename=\"%s\"",fileName+".docx"));
            WordChangeUtils.searchAndReplace(response.getOutputStream(), map, 1);
        } catch (IOException e) {
            throw new ServiceException("导出信息表失败");
        }
    }
}