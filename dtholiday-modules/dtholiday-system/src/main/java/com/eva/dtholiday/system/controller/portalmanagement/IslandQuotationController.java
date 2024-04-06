package com.eva.dtholiday.system.controller.portalmanagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandQuotation;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandQuotationReq;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/erp/portalManagement/islandQuotation")
public class IslandQuotationController {
    /**
     * 新增
     */
    @RequestMapping("/add")
    public ResponseApi islandQuotationAdd(@RequestBody IslandQuotationReq req) {
        ResponseApi<Object> response = ResponseApi.ok();
        return response;
    }
    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseApi islandQuotationDelete(@RequestBody String quotationIndexCode) {
        ResponseApi<Object> response = ResponseApi.ok();
        return response;
    }
    /**
     * 更新
     */
    @RequestMapping("/update")
    public ResponseApi islandQuotationUpdate(@RequestBody IslandQuotationReq req) {
        ResponseApi<Object> response = ResponseApi.ok();
        return response;
    }
    /**
     * 查询列表
     */
    @RequestMapping("/querylist")
    public ResponseApi<List<IslandQuotation>> islandQuotationQueryList() {
        ResponseApi<List<IslandQuotation>> response = ResponseApi.ok();
        return response;
    }
    /**
     * 查询详情
     */
    @RequestMapping("/querydetail")
    public ResponseApi<IslandQuotation> islandQuotationQueryDetail(@RequestBody String quotationIndexCode) {
        ResponseApi<IslandQuotation> response = ResponseApi.ok();
        return response;
    }
}
