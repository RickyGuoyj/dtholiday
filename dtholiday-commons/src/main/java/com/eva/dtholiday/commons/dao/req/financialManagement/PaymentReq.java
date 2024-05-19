package com.eva.dtholiday.commons.dao.req.financialManagement;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/19 14:29
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class PaymentReq {

    private int mainOrderId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date paymentDate;

    private double paymentAmountUSD;

    private double exchangeRate;

    private double paymentAmountUSDToCNY;

    private double paymentAmountCNY;

    private double paymentTotal;

    private int currencyType;

    private String paymentType;

    private String paymentRemarks;
}
