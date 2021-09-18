package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmployeeDao {

    Employee getEmployeeById(String id);
    List<Employee> getAllEmployee();
    Integer addEmployee(Map<String, Object> map);
    Integer updateConnect(String enterpriseId, String employeeId);
    Integer updateEnterprise(Map<String, Object> map);
    Integer deleteEnterprise(String id);
    Map<String, Object> getEnterpriseDetails(String id);

}
