package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.qks.makerSpace.dao.FormDao;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.FormService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import com.qks.makerSpace.util.WordChangeUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

@Service
public class FormServiceImpl implements FormService {

    private final FormDao formDao;

    public FormServiceImpl(FormDao formDao) {
        this.formDao = formDao;

    }

    /**
     * 填写科技园季度报表
     * @param token
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> setTechnologyForm(String token, JSONObject map) throws ServiceException {
        String userId = JWTUtils.parser(token).get("userId").toString();

        if (formDao.getCompanyByUserId(userId) == null)
            throw new ServiceException("请先填写入驻申请");



        String creditCode = map.getString("creditCode");



        return MyResponseUtil.getResultMap(creditCode, 0, "success");
    }

    /**
     * 获取导出表的信息
     * @param
     * @return
     */
    @Override
    public Map<String, Object> getDownLoadForm(String creditCode) {
        Map<String, Object> map = formDao.getAllInformation(creditCode);
        return map;
    }

    /**
     * 导出文件
     * @param bytes 经过处理后的word二进制模板
     * @return
     */
    @Override
    public void downLoadWord(HttpServletResponse response, Map<String, Object> map,int kind) throws ServiceException {
        try {
            String fileName = Calendar.getInstance().get(Calendar.YEAR) + "年度" + map.get("teamName").toString() + "统计表";
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-download");
            response.setHeader("Content-disposition",String.format("attachment; filename=\"%s\"",fileName+".docx"));
            WordChangeUtils.searchAndReplace(response.getOutputStream(),map,kind);
        } catch (IOException e) {
            throw new ServiceException("导出信息表失败");
        }
    }
}