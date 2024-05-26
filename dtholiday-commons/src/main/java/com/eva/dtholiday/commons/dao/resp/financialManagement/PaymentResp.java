package com.eva.dtholiday.commons.dao.resp.financialManagement;

import com.eva.dtholiday.commons.dao.dto.FileInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/19 15:11
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class PaymentResp {
    private int paymentId;

    /**
     * 主订单id
     */
    private int mainOrderId;

    /**
     * 收款日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date paymentDate;

    /**
     * 收款金额USD
     */
    private double paymentAmountUSD;

    /**
     * 汇率
     */
    private double exchangeRate;

    /**
     * 收款金额USDtoCNY
     */
    private double paymentAmountUSDToCNY;

    /**
     * 收款金额CNY
     */
    private double paymentAmountCNY;

    /**
     * 收款总金额
     */
    private double paymentTotal;

    /**
     * 收款货币类型
     */
    private int currencyType;

    /**
     * 收款方式，支付宝，微信等
     */
    private String paymentType;

    /**
     * 收款备注
     */
    private String paymentRemarks;

    private String saleMan;

    private String financialMan;

    /**
     * 付款公司名称
     */
    private String companyName;

    /**
     * 0-待审核 1-销售审核通过 2-销售审核失败 3-财务审核通过 4-财务审核失败
     */
    private int financialStatus;

    private List<FileInfo> paymentPics;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
