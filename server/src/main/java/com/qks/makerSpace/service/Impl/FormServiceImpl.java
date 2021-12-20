package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.qks.makerSpace.dao.FormDao;
import com.qks.makerSpace.entity.Temp.HighEnterpriseData;
import com.qks.makerSpace.entity.database.Form;
import com.qks.makerSpace.entity.database.FormAwards;
import com.qks.makerSpace.entity.database.FormEmployment;
import com.qks.makerSpace.entity.database.FormHighEnterprise;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.FormService;
import com.qks.makerSpace.util.ChangeUtils;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import com.qks.makerSpace.util.WordChangeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

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
     * @param mediumFile
     * @param highEnterpriseFile
     * @param headerFile
     * @param contractFile
     * @param awardsFile
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> setTechnologyForm(String token,
                                                 String map,
                                                 MultipartFile mediumFile,
                                                 MultipartFile highEnterpriseFile,
                                                 MultipartFile headerFile,
                                                 MultipartFile[] contractFile,
                                                 MultipartFile[] awardsFile) throws ServiceException, IOException {
        String userId = JWTUtils.parser(token).get("userId").toString();

        if (mediumFile == null || highEnterpriseFile == null || headerFile == null || contractFile == null || awardsFile == null)
            throw new ServiceException("文件缺失");

        if (formDao.getCompanyByUserId(userId) == null)
            throw new ServiceException("请先填写入驻申请");

        Form form = JSONObject.parseObject(map, Form.class);
        JSONObject json = JSONObject.parseObject(map);
        String highEnterpriseId = UUID.randomUUID().toString();
        String employmentId = UUID.randomUUID().toString();
        String awardsId = UUID.randomUUID().toString();


        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String time = dateFormat.format(date);

        form.setHighEnterpriseId(highEnterpriseId);
        form.setAwardsId(awardsId);
        form.setEmploymentId(employmentId);

        if (formDao.addForm(form) < 1)
            throw new ServiceException("填报数据失败");

        String creditCode = form.getCreditCode();

        /**
         * 下面是填写文件
         */
        if (formDao.addMediumFile(mediumFile.getBytes(), creditCode) < 1)
            throw new ServiceException("填报数据失败:mediumFile");

        if (formDao.addHeaderFile(headerFile.getBytes(), creditCode) < 1)
            throw new ServiceException("填报数据失败:headerFile");

        FormHighEnterprise formHighEnterprise = new FormHighEnterprise();
        JSONObject jsonObject = json.getJSONObject("highEnterpriseData");

        formHighEnterprise.setHighEnterpriseId(highEnterpriseId);
        formHighEnterprise.setHighEnterpriseFile(highEnterpriseFile.getBytes());
        formHighEnterprise.setCertificateCode(jsonObject.getString("certificateCode"));
        formHighEnterprise.setGetTime(jsonObject.getString("getTime"));

        if (formDao.addHighEnterpriseFile(formHighEnterprise) < 1)
            throw new ServiceException("填报数据失败:highEnterpriseFile");

        for (int i = 0 ; i < contractFile.length; i++) {
            FormEmployment formEmployment = new FormEmployment();
            formEmployment.setFormEmploymentId(employmentId);
            formEmployment.setEmploymentId(UUID.randomUUID().toString());
            formEmployment.setContractFile(contractFile[i].getBytes());

            if (formDao.addContractFile(formEmployment) < 1)
                throw new ServiceException("填报数据失败:contractFile");
        }

        for (int i = 0 ; i < awardsFile.length; i++) {
            FormAwards formAwards = new FormAwards();
            formAwards.setFormAwardsId(awardsId);
            formAwards.setFormAwardsId(UUID.randomUUID().toString());
            formAwards.setAwardsFile(awardsFile[i].getBytes());
            if (formDao.addAwardsFile(formAwards) < 1)
                throw new ServiceException("填报数据失败:awardsFile");
        }




        return MyResponseUtil.getResultMap(creditCode, 0, "success");
    }

    /**
     * 获取导出表的信息
     * @param
     * @return
     */
    @Override
    public Map<String, Object> getDownLoadForm(String creditCode) throws IllegalAccessException {
        Form form = formDao.getAllInformation(creditCode);
        Map<String, Object> map = ChangeUtils.getObjectToMap(form);
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