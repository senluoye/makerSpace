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
import java.util.*;

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

        if (mediumFile == null || highEnterpriseFile == null || contractFile == null || awardsFile == null)
            throw new ServiceException("文件缺失");

        if (formDao.getCompanyByUserId(userId) == null)
            throw new ServiceException("请先填写入驻申请");

        Form form  = JSONObject.parseObject(map, Form.class);
        JSONObject json = JSONObject.parseObject(map);
        String highEnterpriseId = UUID.randomUUID().toString();
        String employmentId = UUID.randomUUID().toString();
        String awardsId = UUID.randomUUID().toString();
        String formId = UUID.randomUUID().toString();

//        Date date = new Date();
//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
//        String time = dateFormat.format(date);

        form.setAwardsId(awardsId);
        form.setEmploymentId(employmentId);
        form.setFormId(formId);
        String creditCode = form.getCreditCode();

        /**
         * 下面是填报数据
         */
        FormHighEnterprise formHighEnterprise = new FormHighEnterprise();

        if (form.getHighEnterprise().equals("是")) {
            form.setHighEnterpriseId(highEnterpriseId);

            JSONObject jsonObject = json.getJSONObject("highEnterpriseData");

            formHighEnterprise.setHighEnterpriseId(highEnterpriseId);
            formHighEnterprise.setHighEnterpriseFile(highEnterpriseFile.getBytes());
            formHighEnterprise.setCertificateCode(jsonObject.getString("certificateCode"));
            formHighEnterprise.setGetTime(jsonObject.getString("getTime"));

            if (formDao.addHighEnterpriseFile(formHighEnterprise) < 1)
                throw new ServiceException("填报数据失败:highEnterpriseFile");
        }

        if (formDao.addForm(form) < 1)
            throw new ServiceException("填报数据失败");

        if (formDao.addMediumFile(mediumFile.getBytes(), creditCode) < 1)
            throw new ServiceException("填报数据失败:mediumFile");

        if (formDao.addHeaderFile(headerFile.getBytes(), creditCode) < 1)
            throw new ServiceException("填报数据失败:headerFile");

        for (MultipartFile multipartFile : contractFile) {
            FormEmployment formEmployment = new FormEmployment();
            formEmployment.setFormEmploymentId(employmentId);
            formEmployment.setEmploymentId(UUID.randomUUID().toString());
            formEmployment.setContractFile(multipartFile.getBytes());

            if (formDao.addContractFile(formEmployment) < 1)
                throw new ServiceException("填报数据失败:contractFile");
        }

        for (MultipartFile multipartFile : awardsFile) {
            FormAwards formAwards = new FormAwards();
            formAwards.setAwardsId(awardsId);
            formAwards.setFormAwardsId(UUID.randomUUID().toString());
            formAwards.setAwardsFile(multipartFile.getBytes());
            if (formDao.addAwardsFile(formAwards) < 1)
                throw new ServiceException("填报数据失败:awardsFile");
        }



        Map<String, Object> data = new HashMap<>();
        data.put("creditCode", creditCode);

        return MyResponseUtil.getResultMap(data, 0, "success");
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
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("${year}",String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) + "年度");
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        Map.Entry<String, Object> entry;
        while (iterator.hasNext()){
            entry = iterator.next();
            // 放入新的Entry
            hashMap.put("${"+entry.getKey()+"}", entry.getValue());
        }
        System.out.println(hashMap);
        return hashMap;
    }

    /**
     * 导出文件
     * @param bytes 经过处理后的word二进制模板
     * @return
     */
    @Override
    public void downLoadWord(HttpServletResponse response, Map<String, Object> map,int kind) throws ServiceException {
        try {
            String fileName = Calendar.getInstance().get(Calendar.YEAR) + "年度" + map.get("${teamName}").toString() + "统计表";
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-download");
            response.setHeader("Content-disposition",String.format("attachment; filename=\"%s\"",fileName+".docx"));
            WordChangeUtils.searchAndReplace(response.getOutputStream(),map,kind);
        } catch (IOException e) {
            throw new ServiceException("导出信息表失败");
        }
    }
}