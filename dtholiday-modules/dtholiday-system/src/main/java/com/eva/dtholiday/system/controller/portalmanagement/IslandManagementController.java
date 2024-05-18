package com.eva.dtholiday.system.controller.portalmanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.dto.IslandManagementInfo;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandManagementDeleteReq;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandManagementReq;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandManagementQueryDetailResp;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandManagementQueryListResp;
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
    public ResponseApi islandManagementDelete(@RequestBody IslandManagementDeleteReq req) {
        return islandManagementService.islandManagementDelete(req.getIslandIndexCodeList());
    }

    @PostMapping("/update")
    public ResponseApi islandManagementUpdate(@RequestBody IslandManagementReq req) {
        return islandManagementService.islandManagementUpdate(req);
    }

    @PostMapping("/querylist")
    public ResponseApi<IslandManagementQueryListResp>
        islandManagementQueryList(@RequestBody IslandManagementReq islandManagementReq) {
        return islandManagementService.islandManagementQueryList(islandManagementReq);
    }

    @PostMapping("/querydetail")
    public ResponseApi<IslandManagementQueryDetailResp>
        islandManagementQueryDetail(@RequestBody IslandManagementReq req) {
        return islandManagementService.islandManagementQueryDetail(req.getIslandIndexCode());
    }

    /**
     * 查询所有岛屿
     * @return 结果
     */
    @GetMapping("/queryAllIslandInfo")
    public ResponseApi<List<IslandManagementInfo>> queryAllIsland() {
        return islandManagementService.queryAllIsland();
    }

}
