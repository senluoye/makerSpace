package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.FormDao;
import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.request.FormReq;
import com.qks.makerSpace.entity.response.AllForm;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.FormService;
import com.qks.makerSpace.util.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
                                                 JSONObject map,
                                                 MultipartFile mediumFile,
                                                 MultipartFile highEnterpriseFile,
                                                 MultipartFile headerFile,
                                                 MultipartFile[] contractFile,
                                                 MultipartFile[] awardsFile) throws ServiceException, IOException {

        // 先判断有没有填写入驻申请
        String userId = JWTUtils.parser(token).get("userId").toString();
        if (formDao.getCompanyByUserId(userId) == null)
            throw new ServiceException("请先填写入驻申请");

        // 初始化一些数据
        String temp = map.getString("map");
        FormReq form = FormParserUtils.parser(map);

        String highEnterpriseId = UUID.randomUUID().toString();
        String employmentId = UUID.randomUUID().toString();
        String awardsId = UUID.randomUUID().toString();
        String formId = UUID.randomUUID().toString();
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);

        form.setFormId(formId); // 注意，这个才是主键
        form.setGetTime(time);
        form.setAwardsId(awardsId);
        form.setEmploymentId(employmentId);
        String creditCode = form.getCreditCode();

        /**
         * 下面是填报数据
         */
        // 首先判断是否为 高新技术企业
        if (form.getHighEnterprise().equals("是")) {
            String aa = map.getString("map");
            JSONObject mapJson = JSONObject.parseObject(aa);
//            System.out.println(mapJson);
            FormHighEnterprise formHighEnterprise = new FormHighEnterprise();
            String strTemp = mapJson.getString("highEnterpriseData");
            JSONObject highEnterpriseData = JSONObject.parseObject(strTemp);
//            System.out.println(highEnterpriseData);
            formHighEnterprise.setCertificateCode(highEnterpriseData.getString("getTime"));
            formHighEnterprise.setHighEnterpriseId(highEnterpriseData.getString("certificateCode"));
            // 在form中插入高新技术企业表id
            form.setHighEnterpriseId(highEnterpriseId);

            formHighEnterprise.setHighEnterpriseId(highEnterpriseId);
            formHighEnterprise.setHighEnterpriseFile(highEnterpriseFile.getBytes());
            System.out.println(formHighEnterprise);
            // 插入记录到高新技术企业表
            if (formDao.addHighEnterpriseFile(formHighEnterprise) < 1)
                throw new ServiceException("填报数据失败:highEnterpriseFile");
        }
        System.out.println("444444");
        // 填报form表
        if (formDao.addForm(form) < 1)
            throw new ServiceException("填报数据失败");

        // 判断是否为 科技型中小企业
        if (form.getMediumSized().equals("是")) {
            if (mediumFile != null) {
                // 根据formId，在Form表里更新
                if (formDao.updateMediumFile(mediumFile.getBytes(), formId) < 1)
                    throw new ServiceException("填报数据失败:mediumFile");
            } else {
                throw new ServiceException("请供科技型中小企业获批截屏");
            }
        }
        System.out.println("555555");
        // 判断是否为 大学生创业 或 高校科研院所人员
        if (form.getHeaderKind().equals("大学生创业") || form.getHeaderKind().equals("高校科研院所人员")) {
//            System.out.println(headerFile);
            if (headerFile != null) {
                // 根据formId，在Form表里更新
                if (formDao.updateHeaderFile(headerFile.getBytes(), formId) < 1)
                    throw new ServiceException("填报数据失败:headerFile");
            }
            else
                throw new ServiceException("大学生创业和高校创业需分别提供毕业证或学生证复印件、教师资格证复印件");
        }
        System.out.println("66666666");
        // 接纳 应届生毕业就业人员 不为 0 时
        if (Integer.parseInt(form.getEmployment()) != 0) {
//            System.out.println(contractFile.length + " " + Integer.parseInt(form.getEmployment()));
            if (contractFile.length != 0 && contractFile.length == Integer.parseInt(form.getEmployment())) {
                for (MultipartFile multipartFile : contractFile) {
                    FormEmployment formEmployment = new FormEmployment();

                    formEmployment.setFormEmploymentId(employmentId); // 对应Form表
                    formEmployment.setEmploymentId(UUID.randomUUID().toString()); // 主键
                    formEmployment.setContractFile(multipartFile.getBytes());

                    if (formDao.addContractFile(formEmployment) < 1)
                        throw new ServiceException("填报数据失败:contractFile");
                }
            } else {
                throw new ServiceException("请提交对应数量的入职合同");
            }
        }
        System.out.println("777777");
        // 当年 参赛获奖情况 不为 0 时
        if (Integer.parseInt(form.getTotalAwards()) != 0) {
            if (awardsFile.length != 0 && awardsFile.length == Integer.parseInt(form.getTotalAwards())) {
                for (MultipartFile multipartFile : awardsFile) {
                    FormAwards formAwards = new FormAwards();

                    formAwards.setFormAwardsId(awardsId); // 对应Form表
                    formAwards.setAwardsId(UUID.randomUUID().toString()); // 主键
                    formAwards.setAwardsFile(multipartFile.getBytes());

                    if (formDao.addAwardsFile(formAwards) < 1)
                        throw new ServiceException("填报数据失败:awardsFile");
                }
            } else {
                throw new ServiceException("请提供对应数量的证书复印或扫描件");
            }
        }

        // 定义返回体
        Map<String, Object> result = new HashMap<>();
        result.put("creditCode", creditCode);

        return MyResponseUtil.getResultMap(result, 0, "success");
    }

    /**
     * @description 获取所有企业的所有季度报表(管理员)
     * @return Hashmap
     */
    @Override
    public Map<String, Object> adminGetTechnologyForm(String token) throws ServiceException {
        if (!JWTUtils.parser(token).get("name").toString().equals("admin"))
            throw new ServiceException("请求主体错误");

        List<AllForm> data = formDao.getOldForm();
        List<AllForm> newMap = formDao.getNewForm();

        data.addAll(newMap);
        return MyResponseUtil.getResultMap(data, 0, "success");
    }
    /**
     * @description 获取某一个企业的所有季度报表(用户)
     * @return Hashmap
     */
    @Override
    public Map<String, Object> userGetTechnologyForm(String token) throws ServiceException {
        String userId = JWTUtils.parser(token).get("userId").toString();
        String creditCode = formDao.getCreditCodeByUserId(userId);
        System.out.println(creditCode);
        List<Old> olds = formDao.getOldByCreditCode(creditCode);
        List<AllForm> data;

        if (olds.size() == 0)
            data = formDao.getFormByNewCreditCode(creditCode);
        else
            data = formDao.getFormByOldCreditCode(creditCode);

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
        List<Map<String, Object>> mapList = ChangeUtils.objToListMap(spacePeople);

        try {
            WordChangeUtils.expor(response.getOutputStream(),spaceMap,mapList);
        } catch (IOException e) {
            throw new ServiceException("导出信息表失败");
        }
    }
}