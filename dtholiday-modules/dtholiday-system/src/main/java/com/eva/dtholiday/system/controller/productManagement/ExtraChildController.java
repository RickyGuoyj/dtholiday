package com.eva.dtholiday.system.controller.productManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandArticleQueryDto;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandArticleReq;
import com.eva.dtholiday.commons.dao.req.productManagement.ExtraChildExpensePageReq;
import com.eva.dtholiday.commons.dao.req.productManagement.ExtraChildExpenseQueryReq;
import com.eva.dtholiday.commons.dao.req.productManagement.ExtraChildExpenseReq;
import com.eva.dtholiday.system.service.productManagement.ExtraChildService;
import com.eva.dtholiday.system.service.productManagement.PlaneTicketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/27 18:03
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@RestController
@RequestMapping("/erp/productManagement/extraChild")
public class ExtraChildController {

    @Resource
    private ExtraChildService extraChildService;

    @PostMapping("/add")
    public ResponseApi extraChildAdd(@RequestBody ExtraChildExpenseReq req) {
        return extraChildService.add(req);
    }

    @PostMapping("/delete")
    public ResponseApi extraChildDelete(@RequestBody ExtraChildExpenseQueryReq req) {
        return extraChildService.delete(req);
    }

    @PostMapping("/update")
    public ResponseApi extraChildUpdate(@RequestBody ExtraChildExpenseReq req) {
        return extraChildService.update(req);
    }

    @PostMapping("/queryList")
    public ResponseApi extraChildQueryList(@RequestBody ExtraChildExpensePageReq req) {
        return extraChildService.queryList(req);
    }

    @PostMapping("/queryDetail")
    public ResponseApi extraChildQueryDetail(@RequestBody ExtraChildExpenseQueryReq req) {
        return extraChildService.queryDetail(req);
    }
}
