package com.qks.makerSpace.controller;

import com.qks.makerSpace.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    private Map<String, Object> getOneEmployee(@PathVariable String id) {
        return employeeService.getOneEmployee(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    private Map<String, Object> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    private Map<String, Object> AddEmployee(@RequestBody Map<String, Object> map) {
        return employeeService.addEmployee(map);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    private Map<String, Object> UpdateEmployee(@RequestBody Map<String, Object> map) {
        return employeeService.updateEmployee(map);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    private Map<String, Object> DeleteEmployee(@RequestBody Map<String, Object> map) {
        return employeeService.deleteEmployee(map.get("enterpriseId").toString());
    }

}
