package com.eva.dtholiday.system.constant;

public interface ErpConstant {
    /**
     * 订单状态
     */
    interface ORDER_STATUS {
        /**
         * 等待销售审核
         */
        int WAIT_SALE_REVIEW = 0;
        /**
         * 销售驳回
         */
        int SALE_REJECT = 1;
        /**
         * 待财务审核
         */
        int WAIT_FINANCIAL_REVIEW = 2;
        /**
         * 财务驳回
         */
        int FINANCIAL_REJECT = 3;
        /**
         * 待酒店确认
         */
        int WAIT_HOTEL_CONFIRM = 4;
        /**
         * 确认成功
         */
        int CONFIRM_SUCCESS = 5;
        /**
         * 确认失败
         */
        int CONFIRM_FAIL = 6;

    }
}
