package com.eva.dtholiday.commons.enums;

import com.eva.dtholiday.commons.dao.dto.OrderStatusDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/19 0:45
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public enum OrderStatusEnum {

    WAIT_SALE_CHECK(0, "代理已提交，待销售审核"),
    WAIT_AGENT_RESUBMIT(1, "销售驳回，待代理重新提交"),
    WAIT_FINANCIAL_CHECK(2, "销售已审核，待财务审核"),
    WAIT_AGENT_RESUBMIT2(3, "财务驳回，待代理重新提交"),
    WAIT_HOTEL_CONFIRM(4, "财务已审核，待酒店确认"),
    HOTEL_CONFIRM_SUCCESS(5, "确认成功"),
    HOTEL_CONFIRM_FAIL(6, "确认失败"),
    ;

    private final int code;
    private final String message;

    OrderStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    //获取所有的订单状态
    public static List<OrderStatusDto> getAllOrderStatus() {
        List<OrderStatusDto> orderStatusDtoList = new ArrayList<>();
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            OrderStatusDto orderStatusDto = new OrderStatusDto();
            orderStatusDto.setOrderStatus(orderStatusEnum.getCode());
            orderStatusDto.setOrderStatusName(orderStatusEnum.getMessage());
            orderStatusDtoList.add(orderStatusDto);
        }
        return orderStatusDtoList;
    }
}
