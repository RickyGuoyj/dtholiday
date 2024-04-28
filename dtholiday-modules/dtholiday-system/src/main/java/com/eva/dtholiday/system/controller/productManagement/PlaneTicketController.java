package com.eva.dtholiday.system.controller.productManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandArticleQueryDto;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandArticleReq;
import com.eva.dtholiday.commons.dao.req.productManagement.PlaneTicketPageReq;
import com.eva.dtholiday.commons.dao.req.productManagement.PlaneTicketQueryReq;
import com.eva.dtholiday.commons.dao.req.productManagement.PlaneTicketReq;
import com.eva.dtholiday.system.service.productManagement.PlaneTicketService;
import com.eva.dtholiday.system.service.productManagement.TransitionHotelService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/27 17:29
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@RestController
@RequestMapping("/erp/productManagement/planeTicket")
public class PlaneTicketController {

    @Resource
    private PlaneTicketService planeTicketService;

    @PostMapping("/add")
    public ResponseApi planeTicketAdd(@RequestBody PlaneTicketReq req) {
        return planeTicketService.add(req);
    }

    @PostMapping("/delete")
    public ResponseApi planeTicketDelete(@RequestBody PlaneTicketQueryReq req) {
        return planeTicketService.delete(req);
    }

    @PostMapping("/update")
    public ResponseApi planeTicketUpdate(@RequestBody PlaneTicketReq req) {
        return planeTicketService.update(req);
    }

    @PostMapping("/queryList")
    public ResponseApi planeTicketQueryList(@RequestBody PlaneTicketPageReq req) {
        return planeTicketService.queryList(req);
    }

    @PostMapping("/queryDetail")
    public ResponseApi planeTicketQueryDetail(@RequestBody PlaneTicketQueryReq req) {
        return planeTicketService.queryDetail(req);
    }
}
