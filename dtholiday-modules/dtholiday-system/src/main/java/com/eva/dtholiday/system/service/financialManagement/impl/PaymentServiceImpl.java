package com.eva.dtholiday.system.service.financialManagement.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.financialManagement.Payment;
import com.eva.dtholiday.commons.dao.entity.orderManagement.mainorder.MainOrder;
import com.eva.dtholiday.commons.dao.entity.orderManagement.planeTicket.PlaneTicketOrder;
import com.eva.dtholiday.commons.dao.mapper.financialManagement.PaymentMapper;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.IslandHotelOrderMapper;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.MainOrderMapper;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.PlaneTicketOrderMapper;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.TransitionHotelOrderMapper;
import com.eva.dtholiday.commons.dao.mapper.productManagement.IslandHotelMapper;
import com.eva.dtholiday.commons.dao.req.financialManagement.PaymentCheckReq;
import com.eva.dtholiday.commons.dao.req.financialManagement.PaymentPageReq;
import com.eva.dtholiday.commons.dao.resp.UserResp;
import com.eva.dtholiday.commons.dao.resp.financialManagement.PaymentResp;
import com.eva.dtholiday.commons.enums.BusinessErrorCodeEnum;
import com.eva.dtholiday.commons.enums.FinancialStatusEnum;
import com.eva.dtholiday.commons.exception.BusinessException;
import com.eva.dtholiday.system.service.financialManagement.PaymentService;
import com.eva.dtholiday.system.service.orderManagement.MainOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
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

    @Resource
    private MainOrderMapper mainOrderMapper;

    @Resource
    private IslandHotelOrderMapper islandHotelOrderMapper;

    @Resource
    private PlaneTicketOrderMapper planeTicketOrderMapper;

    @Resource
    private TransitionHotelOrderMapper transitionHotelOrderMapper;


    @Override
    public ResponseApi queryPaymentList(PaymentPageReq req) {
        IPage<Payment> entityPage = new Page<>(req.getCurrent(), req.getSize());
        QueryWrapper<Payment> queryWrapper = new QueryWrapper<>();
        if (CollectionUtils.isNotEmpty(req.getFinancialStatus())) {
            queryWrapper.in("financial_status", req.getFinancialStatus());
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
        if (Objects.nonNull(req.getCompanyName())) {
            queryWrapper.eq("company_name", req.getCompanyName());
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi updateFinancialStatusByFinancialMan(PaymentCheckReq req) {

        Payment payment = paymentMapper.selectById(req.getPaymentId());
        if (Objects.nonNull(payment)) {
            if (Objects.nonNull(req.getCheckStatus())) {
                if (req.getCheckStatus() == 1) {
                    payment.setFinancialStatus(FinancialStatusEnum.FINANCIAL_CHECK_SUCCESS.getCode());
                } else if (req.getCheckStatus() == 0) {
                    payment.setFinancialStatus(FinancialStatusEnum.WAIT_AGENT_RESUBMIT.getCode());
                }
                payment.setCheckRemark(req.getCheckRemark());
                payment.setUpdateTime(new Date());
                paymentMapper.updateById(payment);

                //同步主订单及子单财务状态
                int mainOrderId = payment.getMainOrderId();
                MainOrder mainOrder = mainOrderMapper.selectById(mainOrderId);
                if (Objects.nonNull(mainOrder)) {
                    mainOrder.setFinancialStatus(payment.getFinancialStatus());
                    mainOrderMapper.updateById(mainOrder);
                    if (Objects.nonNull(mainOrder.getIslandHotelOrderId())) {
                        islandHotelOrderMapper.updateFinancialStatusById(mainOrder.getIslandHotelOrderId(), payment.getFinancialStatus());
                    }
                    if (Objects.nonNull(mainOrder.getPlaneTicketOrderId())) {
                        planeTicketOrderMapper.updateFinancialStatusById(mainOrder.getPlaneTicketOrderId(), payment.getFinancialStatus());
                    }
                    if (Objects.nonNull(mainOrder.getTransitionHotelOrderId())) {
                        transitionHotelOrderMapper.updateFinancialStatusById(mainOrder.getTransitionHotelOrderId(), payment.getFinancialStatus());
                    }
                }
                return ResponseApi.ok(payment);
            } else {
                throw new BusinessException(BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getMessageCN(), BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getCode());
            }
        }
        return ResponseApi.ok();
    }
}
