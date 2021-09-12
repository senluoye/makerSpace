package com.qks.makerSpace.service;


import com.qks.makerSpace.entity.User;

import java.util.Map;

public interface EmployeeService {

    Map<String, Object> getOneEmployee(String id);
    Map<String, Object> getAllEmployee();
    Map<String, Object> AddEmployee(Map<String, Object> map);
    Map<String, Object> UpdateEmployee(Map<String, Object> map);
    Map<String, Object> DeleteEmployee(String id);
}
