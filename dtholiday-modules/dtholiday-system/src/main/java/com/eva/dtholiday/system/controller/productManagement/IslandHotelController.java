package com.eva.dtholiday.system.controller.productManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.productManagement.*;
import com.eva.dtholiday.system.service.productManagement.IslandHotelService;
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
@RequestMapping("/erp/productManagement/islandHotel")
public class IslandHotelController {

    @Resource
    private IslandHotelService islandHotelService;

    @PostMapping("/add")
    public ResponseApi islandHotelAdd(@RequestBody IslandHotelReq req) {
        return islandHotelService.add(req);
    }

    @PostMapping("/delete")
    public ResponseApi islandHotelDelete(@RequestBody IslandHotelQueryReq req) {
        return islandHotelService.delete(req);
    }

    @PostMapping("/update")
    public ResponseApi islandHotelUpdate(@RequestBody IslandHotelReq req) {
        return islandHotelService.update(req);
    }

    @PostMapping("/queryList")
    public ResponseApi islandHotelQueryList(@RequestBody IslandHotelPageReq req) {
        return islandHotelService.queryList(req);
    }

    @PostMapping("/queryDetail")
    public ResponseApi islandHotelQueryDetail(@RequestBody IslandHotelQueryReq req) {
        return islandHotelService.queryDetail(req);
    }
}
