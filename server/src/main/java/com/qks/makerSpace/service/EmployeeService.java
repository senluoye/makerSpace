package com.qks.makerSpace.service;


import java.util.Map;

public interface EmployeeService {

    Map<String, Object> getOneEmployee(String id);
    Map<String, Object> getAllEmployee();
    Map<String, Object> addEmployee(Map<String, Object> map);
    Map<String, Object> updateEmployee(Map<String, Object> map);
    Map<String, Object> deleteEmployee(String id);
}
