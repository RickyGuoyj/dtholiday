package com.eva.dtholiday.system.controller.portalmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandQuotation;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandQuotationReq;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandQuotationQueryListResp;
import com.eva.dtholiday.system.service.portalmanagement.IslandQuotationService;

@RestController
@RequestMapping("/erp/portalManagement/islandQuotation")
public class IslandQuotationController {
    @Autowired
    private IslandQuotationService islandQuotationService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public ResponseApi islandQuotationAdd(@RequestBody IslandQuotationReq req) {
        return islandQuotationService.islandQuotationAdd(req);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public ResponseApi islandQuotationDelete(@RequestBody IslandQuotationReq islandQuotationReq) {
        return islandQuotationService.islandQuotationDelete(islandQuotationReq.getQuotationIndexCode());
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    public ResponseApi islandQuotationUpdate(@RequestBody IslandQuotationReq req) {
        return islandQuotationService.islandQuotationUpdate(req);
    }

    /**
     * 查询列表
     */
    @PostMapping("/querylist")
    public ResponseApi<IslandQuotationQueryListResp> islandQuotationQueryList(@RequestBody IslandQuotationReq req) {
        return islandQuotationService.islandQuotationQueryList(req);
    }

    /**
     * 查询详情
     */
    @PostMapping("/querydetail")
    public ResponseApi<IslandQuotation> islandQuotationQueryDetail(@RequestBody IslandQuotationReq req) {
        return islandQuotationService.islandQuotationQueryDetail(req.getQuotationIndexCode());
    }
}
