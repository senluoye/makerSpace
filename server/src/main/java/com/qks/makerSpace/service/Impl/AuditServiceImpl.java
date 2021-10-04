package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.AuditDao;
import com.qks.makerSpace.entity.Audit;
import com.qks.makerSpace.service.AuditService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

@Service
public class AuditServiceImpl implements AuditService {

    private final AuditDao auditDao;

    public AuditServiceImpl(AuditDao auditDao) {
        this.auditDao = auditDao;
    }


    @Override
    public Map<String, Object> getAuditById(String id) {
        if (id != null){
            Audit audit = auditDao.getAuditById(id);
            if (audit != null)
                return MyResponseUtil.getResultMap(audit, 0, "success");
        }

        return MyResponseUtil.getResultMap(null, 0, "id不能为空");
    }

    @Override
    public Map<String, Object> changeAuditById(Map<String, Object> map) {
        String id = map.get("auditId").toString();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        if (id != null){
            map.put("auditTime", timestamp);
            Audit audit = auditDao.changeAuditById(map);
            if (audit != null)
                return MyResponseUtil.getResultMap(audit, 0, "success");
        }

        return MyResponseUtil.getResultMap(null, 0, "审核失败");    }
}
