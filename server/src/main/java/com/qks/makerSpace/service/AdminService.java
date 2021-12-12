package com.qks.makerSpace.service;

<<<<<<< HEAD
import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;

=======
import javax.servlet.http.HttpServletResponse;
>>>>>>> 153a5693583a5e2e059012dc3ed938c61eecf199
import java.util.Map;

public interface AdminService {

<<<<<<< HEAD
    Map<String, Object> getAllOldDetails();
    Map<String, Object> getOldById(String id);
    Map<String, Object> deleteOldById(JSONObject map);

=======
    Map<String, Object> getDownLoadForm();
    void downLoadWord(HttpServletResponse response, Map<String , Object> map) throws Exception;
>>>>>>> 153a5693583a5e2e059012dc3ed938c61eecf199
}
