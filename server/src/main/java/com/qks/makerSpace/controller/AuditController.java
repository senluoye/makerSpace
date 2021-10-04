package com.qks.makerSpace.controller;

import com.qks.makerSpace.service.AuditService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    private Map<String, Object> getAuditById(@PathVariable String id) {
        return auditService.getAuditById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    private Map<String, Object> changeAuditById(Map<String, Object> map) {
        return auditService.changeAuditById(map);
    }
}
