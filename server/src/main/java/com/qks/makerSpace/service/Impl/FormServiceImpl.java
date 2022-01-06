package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.FormDao;
import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.FormService;
import com.qks.makerSpace.util.ChangeUtils;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import com.qks.makerSpace.util.WordChangeUtils;
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

//        if (mediumFile == null || highEnterpriseFile == null || contractFile == null || awardsFile == null)
//            throw new ServiceException("文件缺失");

        if (formDao.getCompanyByUserId(userId) == null)
            throw new ServiceException("请先填写入驻申请");

        Form form  = JSONObject.parseObject(map, Form.class);
        JSONObject json = JSONObject.parseObject(map);
        String highEnterpriseId = UUID.randomUUID().toString();
        String employmentId = UUID.randomUUID().toString();
        String awardsId = UUID.randomUUID().toString();
        String formId = UUID.randomUUID().toString();

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);

        form.setGetTime(time);
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

        if (mediumFile.getBytes().length != 0) {
            if (formDao.addMediumFile(mediumFile.getBytes(), creditCode) < 1)
                throw new ServiceException("填报数据失败:mediumFile");
        }

        if (headerFile.getBytes().length != 0) {
            if (formDao.addHeaderFile(headerFile.getBytes(), creditCode) < 1)
                throw new ServiceException("填报数据失败:headerFile");
        }
        if (form.getMediumSized().equals("是")) {
            if (mediumFile.getBytes().length != 0) {
                if (formDao.addMediumFile(mediumFile.getBytes(), creditCode) < 1)
                    throw new ServiceException("填报数据失败:mediumFile");
            } else {
                throw new ServiceException("请供科技型中小企业获批截屏");
            }
        }

        if (form.getHeaderKind().equals("大学生创业") || form.getHeaderKind().equals("高校科研院所人员")) {
            if (headerFile.getBytes().length != 0) {
                if (formDao.addHeaderFile(headerFile.getBytes(), creditCode) < 1)
                    throw new ServiceException("填报数据失败:headerFile");
            } else {
                throw new ServiceException("大学生创业和高校创业需分别提供毕业证或学生证复印件、教师资格证复印件");
            }
        }

        if (!Objects.equals(form.getEmployment(), "0")) {
            if (contractFile.length != 0 && contractFile.length != Integer.valueOf(form.getEmployment())) {
                for (MultipartFile multipartFile : contractFile) {
                    FormEmployment formEmployment = new FormEmployment();
                    formEmployment.setFormEmploymentId(employmentId);
                    formEmployment.setEmploymentId(UUID.randomUUID().toString());
                    formEmployment.setContractFile(multipartFile.getBytes());

                    if (formDao.addContractFile(formEmployment) < 1)
                        throw new ServiceException("填报数据失败:contractFile");
                }
            } else {
                throw new ServiceException("请提交对应数量的入职合同");
            }
        }


        if (!Objects.equals(form.getTotalAwards(), "0")) {
            if (awardsFile.length != 0 && awardsFile.length > Integer.valueOf(form.getTotalAwards())) {
                for (MultipartFile multipartFile : awardsFile) {
                    FormAwards formAwards = new FormAwards();
                    formAwards.setAwardsId(awardsId);
                    formAwards.setFormAwardsId(UUID.randomUUID().toString());
                    formAwards.setAwardsFile(multipartFile.getBytes());
                    if (formDao.addAwardsFile(formAwards) < 1)
                        throw new ServiceException("填报数据失败:awardsFile");
                }
            } else {
                throw new ServiceException("请提供对应数量的证书复印或扫描件");
            }
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
     * @param
     * @return
     */
    @Override
    public void downLoadWord(HttpServletResponse response, Map<String, Object> map) throws ServiceException {
        try {
            String fileName = Calendar.getInstance().get(Calendar.YEAR) + "年度" + map.get("${teamName}").toString() + "统计表";
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-download");
            response.setHeader("Content-disposition",String.format("attachment; filename=\"%s\"",fileName+".docx"));
            WordChangeUtils.searchAndReplace(response.getOutputStream(),map);
        } catch (IOException e) {
            throw new ServiceException("导出信息表失败");
        }
    }

    /**
     *
     * @param response
     * @param inApplyId
     * @throws ServiceException
     * @throws IllegalAccessException
     */
    @Override
    public void spaceDownLoad(HttpServletResponse response, String inApplyId) throws ServiceException, IllegalAccessException {
        Space space = formDao.selectSpace(inApplyId);
        Map<String, Object> spaceMap = ChangeUtils.getObjectToMap(space);

        List<SpacePerson> spacePeople = formDao.selectSpacePerson(inApplyId);
        List<Map<String, Object>> mapList = ChangeUtils.objConvertListMap(spacePeople);

        try {
            WordChangeUtils.expor(response.getOutputStream(),spaceMap,mapList);
        } catch (IOException e) {
            throw new ServiceException("导出信息表失败");
        }
    }
}