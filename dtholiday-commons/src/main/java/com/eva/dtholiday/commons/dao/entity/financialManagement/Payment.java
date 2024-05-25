package com.eva.dtholiday.commons.dao.entity.financialManagement;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/19 14:33
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dt_payment")
public class Payment extends Model<Payment> {

    private static final long serialVersionUID = 1L;

    /**
     * 支付单号
     */
    @TableId(value = "payment_id", type = IdType.AUTO)
    private int paymentId;

    /**
     * 主订单id
     */
    private int mainOrderId;

    /**
     * 收款日期
     */
    private Date paymentDate;

    /**
     * 收款金额USD
     */
    private double paymentAmountUsd;

    /**
     * 汇率
     */
    private double exchangeRate;

    /**
     * 收款金额USDtoCNY
     */
    private double paymentAmountUsdToCny;

    /**
     * 收款金额CNY
     */
    private double paymentAmountCny;

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

    private String companyName;

    /**
     * 0-待审核 1-销售审核通过 2-销售审核失败 3-财务审核通过 4-财务审核失败
     */
    private int financialStatus;

    private Date createTime;

    private Date updateTime;
}
