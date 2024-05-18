package com.eva.dtholiday.system.service.orderManagement.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.orderManagement.CustomerInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.mainorder.MainOrder;
import com.eva.dtholiday.commons.dao.entity.orderManagement.planeTicket.PlaneTicketInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.planeTicket.PlaneTicketOrder;
import com.eva.dtholiday.commons.dao.entity.orderManagement.transitionHotel.TransitionHotelInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.transitionHotel.TransitionHotelOrder;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.MainOrderMapper;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.TransitionHotelOrderMapper;
import com.eva.dtholiday.commons.dao.req.orderManagement.*;
import com.eva.dtholiday.commons.dao.resp.UserResp;
import com.eva.dtholiday.commons.dao.resp.orderManagement.PlaneTicketOrderResp;
import com.eva.dtholiday.commons.dao.resp.orderManagement.TransitionHotelOrderResp;
import com.eva.dtholiday.commons.enums.OrderStatusEnum;
import com.eva.dtholiday.system.service.UserService;
import com.eva.dtholiday.system.service.orderManagement.TransitionHotelOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/17 2:34
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Service
public class TransitionHotelOrderServiceImpl implements TransitionHotelOrderService {

    @Resource
    private UserService userService;

    @Resource
    private TransitionHotelOrderMapper transitionHotelOrderMapper;

    @Resource
    private MainOrderMapper mainOrderMapper;

    @Override
    public ResponseApi createTransitionHotelOrder(TransitionHotelOrderReq req) {
        return null;
    }

    @Override
    public List<TransitionHotelOrderResp> queryTransitionHotelOrderList(TransitionHotelOrderPageReq req) {
        UserResp currentUserInfo = userService.getCurrentUserDetail();
        QueryWrapper<TransitionHotelOrder> queryWrapper = new QueryWrapper<>();
        if (currentUserInfo != null) {
            queryWrapper.eq("order_creator", currentUserInfo.getUserName());
            if (req.getOrderStatus() != null) {
                queryWrapper.eq("order_status", req.getOrderStatus());
            }
            if (req.getFinancialStatus() != null) {
                queryWrapper.eq("financial_status", req.getFinancialStatus());
            }
            if (req.getSaleMan() != null) {
                queryWrapper.eq("sale_man", req.getSaleMan());
            }
            if (Objects.nonNull(req.getEffectiveDate())) {
                queryWrapper.ge("effective_date", req.getEffectiveDate());
            }
            if (Objects.nonNull(req.getExpiryDate())) {
                queryWrapper.le("expiry_date", req.getExpiryDate());
            }
            if (StringUtils.hasText(req.getTransitionHotelName())) {
                queryWrapper.like("transition_hotel_name", req.getTransitionHotelName());
            }
            if (StringUtils.hasText(req.getCustomerName())) {
                queryWrapper.like("customer_name", req.getCustomerName());
            }
        } else {
            return Collections.emptyList();
        }
        List<TransitionHotelOrder> orderList = transitionHotelOrderMapper.selectList(queryWrapper);
        List<TransitionHotelOrderResp> orderRespList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(orderList)) {
            String roleInfo = currentUserInfo.getRoleInfo().getName();
            orderRespList = orderList.stream().map(order -> {
                TransitionHotelOrderResp transitionHotelOrderResp = new TransitionHotelOrderResp();
                convertTransitionHotelOrderEntityToResp(order, transitionHotelOrderResp, roleInfo);
                return transitionHotelOrderResp;
            }).collect(Collectors.toList());
        }
        return orderRespList;
    }

    private void convertTransitionHotelOrderEntityToResp(TransitionHotelOrder order, TransitionHotelOrderResp transitionHotelOrderResp, String roleInfo) {
        BeanUtils.copyProperties(order, transitionHotelOrderResp);
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCustomerName(order.getCustomerName());
        customerInfo.setAdultNum(order.getAdultNum());
        customerInfo.setChildNum(order.getChildNum());

        TransitionHotelInfo transitionHotelInfo = new TransitionHotelInfo();
        transitionHotelInfo.setTransitionHotelName(order.getTransitionHotelName());
        transitionHotelInfo.setTransitionHotelType(order.getTransitionHotelType());
        transitionHotelInfo.setEffectiveDate(order.getEffectiveDate());
        transitionHotelInfo.setExpiryDate(order.getExpiryDate());
        transitionHotelInfo.setTransitionHotelId(order.getTransitionHotelId());

        transitionHotelOrderResp.setCustomerInfo(customerInfo);
        transitionHotelOrderResp.setTransitionHotelInfo(transitionHotelInfo);
        if (roleInfo.equals("代理") || roleInfo.equals("代理主管")) {
            transitionHotelOrderResp.setCostPrice(null);
            transitionHotelOrderResp.setDiscount(null);
            transitionHotelOrderResp.setBookingCode(null);
            transitionHotelOrderResp.setFinancialMan(null);
        }
    }

    @Override
    public TransitionHotelOrderResp queryTransitionHotelOrderDetail(TransitionHotelOrderDetailReq req) {
        UserResp currentUserInfo = userService.getCurrentUserDetail();
        QueryWrapper<TransitionHotelOrder> queryWrapper = new QueryWrapper<>();
        if (currentUserInfo != null) {
            queryWrapper.eq("order_creator", currentUserInfo.getUserName());
            if (req.getTransitionHotelOrderId() != null) {
                queryWrapper.eq("transition_hotel_order_id", req.getTransitionHotelOrderId());
            }
        } else {
            return null;
        }
        TransitionHotelOrder order = transitionHotelOrderMapper.selectOne(queryWrapper);
        TransitionHotelOrderResp transitionHotelOrderResp = new TransitionHotelOrderResp();
        if (order != null) {
            String roleInfo = currentUserInfo.getRoleInfo().getName();
            convertTransitionHotelOrderEntityToResp(order, transitionHotelOrderResp, roleInfo);
        }
        return transitionHotelOrderResp;
    }

    @Override
    public ResponseApi updateTransitionHotelOrderByAgent(TransitionHotelOrderReq req) {
        return null;
    }

    @Override
    public ResponseApi updateTransitionHotelOrderBySaleMan(TransitionHotelOrderSalesmanReq req) {
        if (req.getCheckStatus() != null) {
            QueryWrapper<TransitionHotelOrder> queryWrapper = new QueryWrapper<>();
            if (req.getTransitionHotelOrderId() == null) {
                return ResponseApi.error("请选择过度酒店订单");
            }
            queryWrapper.eq("transition_hotel_order_id", req.getTransitionHotelOrderId());
            TransitionHotelOrder transitionHotelOrder = transitionHotelOrderMapper.selectOne(queryWrapper);
            if (transitionHotelOrder == null) {
                return ResponseApi.error("过度酒店订单不存在");
            }
            transitionHotelOrder.setCostPrice(req.getCostPrice());
            transitionHotelOrder.setDiscount(req.getDiscount());
            transitionHotelOrder.setBookingCode(req.getBookingCode());
            transitionHotelOrder.setFinancialMan(req.getFinancialMan());
            transitionHotelOrder.setRemarks(req.getCheckRemark());
            // 计算金额
            transitionHotelOrder.setDiscountPrice(transitionHotelOrder.getTotalPrice() - transitionHotelOrder.getDiscount());
            // todo 主订单金额重新计算
            QueryWrapper<MainOrder> mainOrderQueryWrapper = new QueryWrapper<>();
            mainOrderQueryWrapper.eq("transition_hotel_order_id", transitionHotelOrder.getTransitionHotelOrderId());
            MainOrder mainOrder = mainOrderMapper.selectOne(mainOrderQueryWrapper);
            if (req.getCheckStatus() == 1) {
                transitionHotelOrder.setOrderStatus(OrderStatusEnum.WAIT_FINANCIAL_CHECK.getCode());
            } else {
                transitionHotelOrder.setOrderStatus(OrderStatusEnum.WAIT_AGENT_RESUBMIT.getCode());
            }
            if (mainOrder != null) {
                //计算三个值中最小的
                mainOrder.setOrderStatus(Math.min(Math.min(mainOrder.getIslandOrderStatus(), mainOrder.getTransitionHotelOrderStatus()), transitionHotelOrder.getOrderStatus()));
            }
            transitionHotelOrderMapper.updateById(transitionHotelOrder);
            mainOrderMapper.updateById(mainOrder);
            return ResponseApi.ok("审核成功");
        } else {
            return ResponseApi.error("请选择审核状态");
        }
    }

    @Override
    public ResponseApi updateTransitionHotelOrderByFinancialMan(TransitionHotelOrderFinancialManReq req) {
        if (req.getCheckStatus() != null) {
            QueryWrapper<TransitionHotelOrder> queryWrapper = new QueryWrapper<>();
            if (req.getTransitionHotelOrderId() == null) {
                return ResponseApi.error("请选择过度酒店订单");
            }
            queryWrapper.eq("transition_hotel_order_id", req.getTransitionHotelOrderId());
            TransitionHotelOrder transitionHotelOrder = transitionHotelOrderMapper.selectOne(queryWrapper);
            if (transitionHotelOrder == null) {
                return ResponseApi.error("过度酒店订单不存在");
            }
            QueryWrapper<MainOrder> mainOrderQueryWrapper = new QueryWrapper<>();
            mainOrderQueryWrapper.eq("transition_hotel_order_id", req.getTransitionHotelOrderId());
            MainOrder mainOrder = mainOrderMapper.selectOne(mainOrderQueryWrapper);
            if (req.getCheckStatus() == 1) {
                transitionHotelOrder.setOrderStatus(OrderStatusEnum.WAIT_HOTEL_CONFIRM.getCode());
                transitionHotelOrder.setConfirmInfo(req.getConfirmInfo());
            } else {
                transitionHotelOrder.setOrderStatus(OrderStatusEnum.WAIT_AGENT_RESUBMIT2.getCode());
                transitionHotelOrder.setRemarks(req.getCheckRemark());
            }
            if (mainOrder != null) {
                //计算三个值中最小的
                mainOrder.setOrderStatus(Math.min(Math.min(mainOrder.getIslandOrderStatus(), mainOrder.getTransitionHotelOrderStatus()), transitionHotelOrder.getOrderStatus()));
            }
            transitionHotelOrderMapper.updateById(transitionHotelOrder);
            mainOrderMapper.updateById(mainOrder);
            return ResponseApi.ok("审核成功");
        } else {
            return ResponseApi.error("请选择审核状态");
        }
    }
}
