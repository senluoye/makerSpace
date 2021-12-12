package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.AdminDao;
import com.qks.makerSpace.service.AdminService;
import com.qks.makerSpace.util.WordChangeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
=======
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
>>>>>>> 153a5693583a5e2e059012dc3ed938c61eecf199
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

<<<<<<< HEAD
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
=======
    /**
     * 获取导出表的信息
     * @param
     * @return
     */
    @Override
    public Map<String, Object> getDownLoadForm() {
        return null;
    }

    /**
     * 导出文件
     * @param bytes 经过处理后的word二进制模板
     * @return
     */
    @Autowired
    WordChangeUtils wordChangeUtils;

    @Override
    public void downLoadWord(HttpServletResponse response,
                             Map<String , Object> map) throws Exception {

        try {
            String fileName = Calendar.getInstance().get(Calendar.YEAR) + "年度" + map.get("teamName").toString() + "统计表";
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-download");
            response.setHeader("Content-disposition",String.format("attachment; filename=\"%s\"",fileName+".docx"));
            wordChangeUtils.searchAndReplace(response.getOutputStream(),map);
        } catch (IOException e) {
            e.printStackTrace();
        }
>>>>>>> 153a5693583a5e2e059012dc3ed938c61eecf199
    }
}
