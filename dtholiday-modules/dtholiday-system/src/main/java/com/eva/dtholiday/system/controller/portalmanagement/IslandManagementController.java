package com.eva.dtholiday.system.controller.portalmanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Autowired
    private IslandManagementService islandManagementService;

    @PostMapping("/add")
    public ResponseApi islandManagementAdd(@RequestBody IslandManagementReq req) {
        return islandManagementService.islandManagementAdd(req);
    }

    @PostMapping("/delete")
    public ResponseApi islandManagementDelete(@RequestBody IslandManagementReq req) {
        return islandManagementService.islandManagementDelete(req.getIslandIndexCode());
    }

    @PostMapping("/update")
    public ResponseApi islandManagementUpdate(@RequestBody IslandManagementReq req) {
        return islandManagementService.islandManagementUpdate(req);
    }

    @PostMapping("/querylist")
    public ResponseApi<List<IslandManagement>> islandManagementQueryList(@RequestBody  IslandManagementReq islandManagementReq) {
        return islandManagementService.islandManagementQueryList(islandManagementReq);
    }

    @PostMapping("/querydetail")
    public ResponseApi<IslandManagement> islandManagementQueryDetail(@RequestBody IslandManagementReq req) {
        return islandManagementService.islandManagementQueryDetail(req.getIslandIndexCode());
    }

}
