package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.EmployeeDao;
import com.qks.makerSpace.entity.Employee;
import com.qks.makerSpace.service.EmployeeService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Map<String, Object> getOneEmployee(String id) {
        String employeeId = employeeDao.getEmployeeIdByEnterpriseId(id);

        if (employeeId != null){
            Employee employee = employeeDao.getEmployeeById(employeeId);

            if (employee != null){
                Map<String, Object> data = new HashMap<>();
                data.put("employee", employee);
                data.put("enterprise", employeeDao.getEnterpriseDetails(id));

                return MyResponseUtil.getResultMap(data, 0, "success");

            } else return MyResponseUtil.getResultMap(null, -1, "employee doesn't exist");

        } else return MyResponseUtil.getResultMap(null, -2, "employeeId doesn't exist");

    }

    @Override
    public Map<String, Object> getAllEmployee() {
        List<Employee> list = employeeDao.getAllEmployee()
                .parallelStream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return MyResponseUtil.getResultMap(list, 0, "success");
    }

    @Override
    public Map<String, Object> addEmployee(Map<String, Object> map) {
        String enterpriseId = map.get("enterpriseId").toString();
        String employeeId = employeeDao.getEmployeeIdByEnterpriseId(enterpriseId);

        if (employeeId == null){
            employeeId = UUID.randomUUID().toString();
            map.put("employeeId", employeeId);

            if (employeeDao.addEmployee(map) > 0 &&
                    employeeDao.updateConnect(enterpriseId, employeeId) > 0) {
                return MyResponseUtil.getResultMap(employeeId, 0, "success");

            } else return MyResponseUtil.getResultMap(null, -1, "add failure");

        } else return MyResponseUtil.getResultMap(null, -2, "employeeId was exist or enterpriseId was null");

    }

    @Override
    public Map<String, Object> updateEmployee(Map<String, Object> map) {
        String enterpriseId = map.get("enterpriseId").toString();
        if (employeeDao.updateEnterprise(map) > 0)
            return MyResponseUtil.getResultMap(new HashMap<>().put("enterpriseId", enterpriseId), 0, "success");
        else
            return MyResponseUtil.getResultMap(null, -1, "update failure");

    }

    @Override
    public Map<String, Object> deleteEmployee(String id) {
        if (employeeDao.deleteEnterprise(id) > 0)
            return MyResponseUtil.getResultMap(new HashMap<>().put("enterpriseId", id), 0, "success");
        else
            return MyResponseUtil.getResultMap(null, -1, "enterpriseID doesn't exist");

    }
}
