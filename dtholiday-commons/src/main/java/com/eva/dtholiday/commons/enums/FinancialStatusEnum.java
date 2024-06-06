package com.eva.dtholiday.commons.enums;

import com.eva.dtholiday.commons.dao.dto.FinancialStatusDto;

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
public enum FinancialStatusEnum {

    WAIT_AGENT_PAY(0, "待付款"),

    //代理已提交，待财务审核
    WAIT_FINANCIAL_CHECK(1, "待审核"),
    //财务驳回，待代理重新提交
    WAIT_AGENT_RESUBMIT(2, "驳回，重新提交"),
    //财务已审核，通过
    FINANCIAL_CHECK_SUCCESS(3, "已审核，通过"),

    ;

    private final int code;
    private final String message;

    FinancialStatusEnum(int code, String message) {
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
    public static List<FinancialStatusDto> getAllFinancialStatus() {
        List<FinancialStatusDto> financialStatusDtos = new ArrayList<>();
        for (FinancialStatusEnum orderStatusEnum : FinancialStatusEnum.values()) {
            FinancialStatusDto orderStatusDto = new FinancialStatusDto();
            orderStatusDto.setFinancialStatus(orderStatusEnum.getCode());
            orderStatusDto.setFinancialStatusName(orderStatusEnum.getMessage());
            financialStatusDtos.add(orderStatusDto);
        }
        return financialStatusDtos;
    }
}
