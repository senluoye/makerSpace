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

    @Override
    public Map<String, Object> getAllOldDetails() {
        return null;
    }

    @Override
    public Map<String, Object> getOldById(String id) {
        return null;
    }

    @Override
    public Map<String, Object> deleteOldById(JSONObject map) {
        return null;
    }

    @Override
    public Map<String, Object> getAllDetails() {

        List<All> dataOne = adminDao.getAllOldDetails();
        List<All> dataTwo = adminDao.getAllNewDetails();
        List<All> data = new ArrayList<>(dataOne);
        data.addAll(dataTwo);

        return MyResponseUtil.getResultMap(data, 0, "success");
    }

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

    @Override
    public Map<String, Object> getSpaceById(String id) {
        return null;
    }

    @Override
    public Map<String, Object> deletetechnologyById(String id) {


        return MyResponseUtil.getResultMap(id, 0, "success");
    }

    @Override
    public Map<String, Object> deleteSpaceById(String id) {
        return null;
    }


    /**
     * 获取导出表的信息
     * @param
     * @return
     */
    @Override
    public Map<String, Object> getDownLoadForm () {
        return null;
    }

    @Override
    public Map<String, Object> agreeById(JSONObject map) {
        String creditCode = map.getString("creditCode");

        if (adminDao.updateAuditById(creditCode) > 0) {

        }

        return MyResponseUtil.getResultMap(new HashMap<>().put("creditCode", creditCode), 0, "success");
    }

    /**
     * 导出文件
     * @param bytes 经过处理后的word二进制模板
     * @return
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