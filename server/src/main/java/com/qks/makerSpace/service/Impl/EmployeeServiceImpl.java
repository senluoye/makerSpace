package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.EmployeeDao;
import com.qks.makerSpace.dao.EnterpriseDao;
import com.qks.makerSpace.entity.Employee;
import com.qks.makerSpace.service.EmployeeService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;
    private final EnterpriseDao enterpriseDao;
    private final MyResponseUtil myResponseUtil;

    public EmployeeServiceImpl(EmployeeDao employeeDao, EnterpriseDao enterpriseDao, MyResponseUtil myResponseUtil) {
        this.employeeDao = employeeDao;
        this.enterpriseDao = enterpriseDao;
        this.myResponseUtil = myResponseUtil;
    }

    @Override
    public Map<String, Object> getOneEmployee(String id) {
        Employee employeeTemp = employeeDao.getEmployeeById(id);
        if (employeeTemp != null){
            Map<String, Object> data = new HashMap<>();
            data.put("employee", employeeTemp);
            data.put("enterprise", enterpriseDao.getDetails());

            return myResponseUtil.getResultMap(data, 0, "success");
        } else
            return myResponseUtil.getResultMap(null, -1, "employeeID doesn't exist");
    }

    @Override
    public Map<String, Object> getAllEmployee() {

    }

    @Override
    public Map<String, Object> AddEmployee(Map<String, Object> map) {

    }

    @Override
    public Map<String, Object> UpdateEmployee(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<String, Object> DeleteEmployee(String id) {
        return null;
    }
}
