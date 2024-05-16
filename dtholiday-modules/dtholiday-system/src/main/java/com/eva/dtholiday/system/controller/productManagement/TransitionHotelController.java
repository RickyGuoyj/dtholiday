package com.eva.dtholiday.system.controller.productManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.productManagement.PlaneTicketPageReq;
import com.eva.dtholiday.commons.dao.req.productManagement.TransitionHotelPageReq;
import com.eva.dtholiday.commons.dao.req.productManagement.TransitionHotelQueryReq;
import com.eva.dtholiday.commons.dao.req.productManagement.TransitionHotelReq;
import com.eva.dtholiday.system.service.productManagement.TransitionHotelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/27 16:40
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@RestController
@RequestMapping("/erp/productManagement/transitionHotel")
public class TransitionHotelController {

    @Resource
    private TransitionHotelService transitionHotelService;

    @PostMapping("/add")
    public ResponseApi transitionHotelAdd(@RequestBody TransitionHotelReq req) {
        return transitionHotelService.add(req);
    }

    @PostMapping("/delete")
    public ResponseApi transitionHotelDelete(@RequestBody TransitionHotelQueryReq req) {
        return transitionHotelService.delete(req);
    }

    @PostMapping("/update")
    public ResponseApi transitionHotelUpdate(@RequestBody TransitionHotelReq req) {
        return transitionHotelService.update(req);
    }

    @PostMapping("/queryList")
    public ResponseApi transitionHotelQueryList(@RequestBody TransitionHotelPageReq req) {
        return transitionHotelService.queryList(req);
    }

    @PostMapping("/queryDetail")
    public ResponseApi transitionHotelQueryDetail(@RequestBody TransitionHotelQueryReq req) {
        return transitionHotelService.queryDetail(req);
    }
    @GetMapping("/getAllTransitionHotel")
    public ResponseApi getAllTransitionHotel() {
        return transitionHotelService.getAllTransitionHotel();
    }

    @PostMapping("/queryTransitionHotelList")
    public ResponseApi queryPlaneTicketList(@RequestBody TransitionHotelPageReq req) {
        return transitionHotelService.queryTransitionHotelList(req);
    }
}
