package com.eva.dtholiday.system.controller.portalmanagement;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandManagement;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandManagementReq;
import com.eva.dtholiday.system.service.portalmanagement.IslandManagementService;

@RestController
@RequestMapping("/erp/portalManagement/islandManagement")
public class IslandManagementController {
    private IslandManagementService islandManagementService;

    @RequestMapping("/add")
    public ResponseApi islandManagementAdd(@RequestBody IslandManagementReq req) {
        ResponseApi<Object> response = ResponseApi.ok();
        return response;
    }

    @RequestMapping("/delete")
    public ResponseApi islandManagementDelete(@RequestBody IslandManagementReq req) {
        ResponseApi<Object> response = ResponseApi.ok();
        return response;
    }

    @RequestMapping("/update")
    public ResponseApi islandManagementUpdate(@RequestBody IslandManagementReq req) {
        ResponseApi<Object> response = ResponseApi.ok();
        return response;
    }

    @RequestMapping("/querylist")
    public ResponseApi<List<IslandManagement>> islandManagementQueryList() {
        return null;
    }

    @RequestMapping("/querydetail")
    public ResponseApi<IslandManagement> islandManagementQueryDetail(@RequestBody IslandManagementReq req) {
        return null;
    }

}
