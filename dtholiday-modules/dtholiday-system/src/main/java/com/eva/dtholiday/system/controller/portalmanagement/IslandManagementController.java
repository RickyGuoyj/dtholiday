package com.eva.dtholiday.system.controller.portalmanagement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandManagement;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandManagementQueryResp;
import com.eva.dtholiday.system.service.portalmanagement.IslandManagementService;

@RestController
@RequestMapping("/erp/portalManagement")
public class IslandManagementController {
    private IslandManagementService islandManagementService;

    @RequestMapping("/islandManagement/add")
    public String islandManagementAdd() {
        return "hello";
    }

    @RequestMapping("/islandManagement/delete")
    public void islandManagementDelete() {

    }

    @RequestMapping("/islandManagement/update")
    public void islandManagementUpdate() {

    }

    @RequestMapping("/islandManagement/querylist")
    public ResponseApi<IslandManagementQueryResp> islandManagementQueryList() {
        return null;
    }
    @RequestMapping("/islandManagement/querydetail")
    public ResponseApi<IslandManagement> islandManagementQueryDetail() {
        return null;
    }

}
