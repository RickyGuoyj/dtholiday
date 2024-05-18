package com.eva.dtholiday.system.controller;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.enums.OrderStatusEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/19 0:51
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@RestController
@RequestMapping("/erp/commons")
public class CommonController {

    /**
     * 获取所有的订单状态
     */
    @GetMapping("/getAllOrderStatus")
    public ResponseApi getAllOrderStatus(){
        return ResponseApi.ok(OrderStatusEnum.getAllOrderStatus());
    }
}
