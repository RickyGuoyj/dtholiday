package com.eva.dtholiday.commons.enums;

import java.util.ArrayList;
import java.util.List;

import com.eva.dtholiday.commons.dao.dto.CancelStatusDto;

/**
 * 订单取消状态
 */
public enum CancelStatusEnum {

    CANCEL_INIT_STATUS(0, "未取消"),
    WAIT_SALE_CHECK(1, "代理提交，待销售审核"),
    WAIT_FINANCIAL_CHECK(2, "销售通过，待财务审核"),
    SALE_NOT_PASS(3, "销售不通过"),
    FINANCIAL_PASS(4, "财务通过，取消成功"),
    FINANCIAL_NOT_PASS(5, "财务不通过");

    private final int code;
    private final String message;

    CancelStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    // 获取所有的订单状态
    public static List<CancelStatusDto> getAllCancelStatus() {
        List<CancelStatusDto> cancelStatusDtoList = new ArrayList<>();
        for (CancelStatusEnum cancelStatusEnum : CancelStatusEnum.values()) {
            CancelStatusDto cancelStatusDto = new CancelStatusDto();
            cancelStatusDto.setCancelStatus(cancelStatusEnum.getCode());
            cancelStatusDto.setCancelStatusName(cancelStatusEnum.getMessage());
            cancelStatusDtoList.add(cancelStatusDto);
        }
        return cancelStatusDtoList;
    }
}
