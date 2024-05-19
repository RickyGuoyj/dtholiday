package com.eva.dtholiday.system.service.financialManagement.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.financialManagement.Payment;
import com.eva.dtholiday.commons.dao.entity.orderManagement.planeTicket.PlaneTicketOrder;
import com.eva.dtholiday.commons.dao.mapper.financialManagement.PaymentMapper;
import com.eva.dtholiday.commons.dao.req.financialManagement.PaymentPageReq;
import com.eva.dtholiday.commons.dao.resp.financialManagement.PaymentResp;
import com.eva.dtholiday.commons.dao.resp.orderManagement.PlaneTicketOrderResp;
import com.eva.dtholiday.system.service.financialManagement.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/19 15:13
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public ResponseApi queryPaymentList(PaymentPageReq req) {
        IPage<Payment> entityPage = new Page<>(req.getCurrent(), req.getSize());
        QueryWrapper<Payment> queryWrapper = new QueryWrapper<>();
        if (req.getFinancialStatus() != null) {
            queryWrapper.eq("financial_status", req.getFinancialStatus());
        }
        if (req.getPaymentType() != null) {
            queryWrapper.eq("payment_type", req.getPaymentType());
        }
        if (req.getSaleMan() != null) {
            queryWrapper.eq("sale_man", req.getSaleMan());
        }
        if (Objects.nonNull(req.getStartTime())) {
            queryWrapper.ge("payment_date", req.getStartTime());
        }
        if (Objects.nonNull(req.getEndTime())) {
            queryWrapper.le("payment_date", req.getEndTime());
        }
        entityPage = paymentMapper.selectPage(entityPage, queryWrapper);
        if (Objects.isNull(entityPage)) {
            return ResponseApi.ok(new Page<>(req.getCurrent(), 0));
        }
        List<PaymentResp> paymentRespList = entityPage.getRecords().stream().map(payment -> {
            PaymentResp paymentResp = new PaymentResp();
            BeanUtils.copyProperties(payment, paymentResp);
            return paymentResp;
        }).collect(Collectors.toList());
        IPage<PaymentResp> respPage = new Page<>(req.getCurrent(), req.getSize());
        respPage.setRecords(paymentRespList);
        respPage.setTotal(entityPage.getTotal());
        return ResponseApi.ok(respPage);
    }
}
