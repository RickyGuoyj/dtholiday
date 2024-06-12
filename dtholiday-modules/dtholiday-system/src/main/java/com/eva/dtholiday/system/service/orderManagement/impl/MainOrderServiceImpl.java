package com.eva.dtholiday.system.service.orderManagement.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.financialManagement.Payment;
import com.eva.dtholiday.commons.dao.entity.orderManagement.TotalPriceInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.mainorder.MainOrderListInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.islandhotelorder.IslandHotelOrder;
import com.eva.dtholiday.commons.dao.entity.orderManagement.mainorder.MainOrder;
import com.eva.dtholiday.commons.dao.entity.orderManagement.planeTicket.PlaneTicketOrder;
import com.eva.dtholiday.commons.dao.entity.orderManagement.transitionHotel.TransitionHotelOrder;
import com.eva.dtholiday.commons.dao.mapper.financialManagement.PaymentMapper;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.IslandHotelOrderMapper;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.MainOrderMapper;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.PlaneTicketOrderMapper;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.TransitionHotelOrderMapper;
import com.eva.dtholiday.commons.dao.req.orderManagement.MainOrderCancelReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.MainOrderQueryListReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.MainOrderReq;
import com.eva.dtholiday.commons.dao.req.financialManagement.PaymentReq;
import com.eva.dtholiday.commons.dao.resp.UserResp;
import com.eva.dtholiday.commons.dao.resp.orderManagement.MainOrderDetailResp;
import com.eva.dtholiday.commons.dao.resp.orderManagement.MainOrderQueryListResp;
import com.eva.dtholiday.commons.enums.CancelStatusEnum;
import com.eva.dtholiday.commons.enums.FinancialStatusEnum;
import com.eva.dtholiday.system.constant.ErpConstant;
import com.eva.dtholiday.system.service.UserService;
import com.eva.dtholiday.system.service.convert.OrderConvert;
import com.eva.dtholiday.system.service.orderManagement.MainOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MainOrderServiceImpl implements MainOrderService {
    @Resource
    private PlaneTicketOrderMapper planeTicketOrderMapper;
    @Resource
    private IslandHotelOrderMapper islandHotelOrderMapper;
    @Resource
    private TransitionHotelOrderMapper transitionHotelOrderMapper;
    @Resource
    private MainOrderMapper mainOrderMapper;
    @Resource
    private PaymentMapper paymentMapper;
    @Resource
    private UserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi addMainOrder(MainOrderReq req) {
        UserResp currentUserDetail = userService.getCurrentUserDetail();
        IslandHotelOrder islandHotelOrder;
        PlaneTicketOrder planeTicketOrder;
        TransitionHotelOrder transitionHotelOrder;
        MainOrder mainOrder = new MainOrder();
        // 岛屿订单
        if (Objects.nonNull(req.getIslandHotelOrder()) && Objects.nonNull(req.getIslandHotelOrder().getHotelInfo())) {
            islandHotelOrder =
                OrderConvert.convertIslandHotelInfoToEntity(req.getIslandHotelOrder(), currentUserDetail.getUserName());
            islandHotelOrder.setOrderStatus(ErpConstant.ORDER_STATUS.WAIT_SALE_REVIEW);
            islandHotelOrder.setFinancialStatus(0);
            islandHotelOrderMapper.insert(islandHotelOrder);
            mainOrder.setIslandHotelOrderId(islandHotelOrder.getIslandHotelOrderId());
            mainOrder.setIslandHotelOrderStatus(ErpConstant.ORDER_STATUS.WAIT_SALE_REVIEW);
        }
        // 机票订单
        if (Objects.nonNull(req.getPlaneTicketOrder())
            && Objects.nonNull(req.getPlaneTicketOrder().getPlaneTicketInfo())) {
            planeTicketOrder =
                OrderConvert.convertPlaneTicketInfoToEntity(req.getPlaneTicketOrder(), currentUserDetail.getUserName());
            planeTicketOrder.setOrderStatus(ErpConstant.ORDER_STATUS.WAIT_SALE_REVIEW);
            planeTicketOrder.setFinancialStatus(0);
            planeTicketOrderMapper.insert(planeTicketOrder);
            mainOrder.setPlaneTicketOrderId(planeTicketOrder.getPlaneTicketOrderId());
            mainOrder.setPlaneTicketOrderStatus(ErpConstant.ORDER_STATUS.WAIT_SALE_REVIEW);
        }
        // 过度酒店订单
        if (Objects.nonNull(req.getTransitionHotelOrder())
            && Objects.nonNull(req.getTransitionHotelOrder().getTransitionHotelInfo())) {
            transitionHotelOrder = OrderConvert.convertTransitionHotelInfoToEntity(req.getTransitionHotelOrder(),
                currentUserDetail.getUserName());
            transitionHotelOrder.setOrderStatus(ErpConstant.ORDER_STATUS.WAIT_SALE_REVIEW);
            transitionHotelOrder.setFinancialStatus(0);
            transitionHotelOrderMapper.insert(transitionHotelOrder);
            mainOrder.setTransitionHotelOrderId(transitionHotelOrder.getTransitionHotelOrderId());
            mainOrder.setTransitionHotelOrderStatus(ErpConstant.ORDER_STATUS.WAIT_SALE_REVIEW);
        }
        // 写主表
        mainOrder.setOrderCreator(currentUserDetail.getUserName());
        mainOrder.setTotalPrice(JSONObject.toJSONString(req.getTotalPrice()));
        mainOrder.setSaleMan(req.getSaleMan());
        mainOrder.setCreateTime(new Timestamp(System.currentTimeMillis()));
        mainOrder.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        mainOrder.setOrderStatus(ErpConstant.ORDER_STATUS.WAIT_SALE_REVIEW);
        mainOrder.setFinancialStatus(FinancialStatusEnum.WAIT_AGENT_PAY.getCode());
        int insert = mainOrderMapper.insert(mainOrder);
        return ResponseApi.ok(insert);
    }

    @Override
    public ResponseApi<MainOrderQueryListResp> queryMainOrderList(MainOrderQueryListReq req) {
        UserResp currentUserInfo = userService.getCurrentUserDetail();
        Map<String, Object> map = new HashMap<>();
        map.put("mainOrderId", req.getMainOrderId());
        map.put("islandHotelOrderId", req.getIslandHotelOrderId());
        map.put("planeTicketOrderId", req.getPlaneTicketOrderId());
        map.put("transitionHotelOrderId", req.getTransitionHotelOrderId());
        // 特殊化处理
        // map.put("orderCreator", req.getOrderCreator());
        // map.put("saleMan", req.getSaleMan());
        String roleInfo = currentUserInfo.getRoleInfo().getName();
        switch (roleInfo) {
            case "代理":
                map.put("orderCreator", Collections.singletonList(currentUserInfo.getUserName()));
                if (StringUtils.hasText(req.getSaleMan())) {
                    map.put("saleMan", Collections.singletonList(req.getSaleMan()));
                }
                break;
            case "代理主管": {
                List<String> userName = userService.getUserNameByParentUserName(currentUserInfo.getUserName());
                map.put("orderCreator", userName);
                if (StringUtils.hasText(req.getSaleMan())) {
                    map.put("saleMan", Collections.singletonList(req.getSaleMan()));
                }
                break;
            }
            case "销售":
                map.put("saleMan", Collections.singletonList(currentUserInfo.getUserName()));
                break;
            case "销售主管": {
                List<String> userName = userService.getUserNameByParentUserName(currentUserInfo.getUserName());
                map.put("saleMan", userName);
                break;
            }
            default:
                if (StringUtils.hasText(req.getSaleMan())) {
                    map.put("saleMan", Collections.singletonList(req.getSaleMan()));
                }
                break;
        }
        int count = mainOrderMapper.countMainOrderList(map);
        map.put("from", (req.getPage() - 1) * req.getPageSize());
        map.put("to", req.getPageSize());
        List<MainOrderListInfo> mainOrderListInfos = mainOrderMapper.queryMainOrderList(map);
        if (Objects.nonNull(mainOrderListInfos)) {
            List<MainOrderDetailResp> respList = mainOrderListInfos.stream().map(mainOrderListInfo -> {
                MainOrderDetailResp resp = new MainOrderDetailResp();
                BeanUtils.copyProperties(mainOrderListInfo, resp);
                resp.setTotalPrice(JSONObject.parseObject(mainOrderListInfo.getTotalPrice(), TotalPriceInfo.class));
                return resp;
            }).collect(Collectors.toList());
            MainOrderQueryListResp mainOrderQueryListResp = new MainOrderQueryListResp();
            mainOrderQueryListResp.setTotal(count);
            mainOrderQueryListResp.setPage(req.getPage());
            mainOrderQueryListResp.setPageSize(req.getPageSize());
            mainOrderQueryListResp.setMainOrderDetailRespList(respList);
            return ResponseApi.ok(mainOrderQueryListResp);
        }
        return ResponseApi.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi agentPay(PaymentReq req) {
        UserResp currentUserDetail = userService.getCurrentUserDetail();

        Payment payment = new Payment();
        payment.setMainOrderId(req.getMainOrderId());
        payment.setCurrencyType(req.getCurrencyType());
        payment.setPaymentAmountCny(req.getPaymentAmountCNY());
        payment.setPaymentAmountUsd(req.getPaymentAmountUSD());
        payment.setPaymentAmountUsdToCny(req.getPaymentAmountUSDToCNY());
        payment.setPaymentDate(req.getPaymentDate());
        payment.setPaymentRemarks(req.getPaymentRemarks());
        payment.setPaymentType(req.getPaymentType());
        payment.setExchangeRate(req.getExchangeRate());
        payment.setPaymentTotal(req.getPaymentTotal());
        payment.setPaymentPics(JSONArray.toJSONString(req.getPaymentPics()));
        if (!Objects.isNull(currentUserDetail)) {
            payment.setOrderCreator(currentUserDetail.getUserName());
            payment.setCompanyName(currentUserDetail.getBelongCompany());
        }
        MainOrder mainOrder = mainOrderMapper.selectById(req.getMainOrderId());
        if (mainOrder != null) {
            if (mainOrder.getFinancialStatus() == 0) {
                payment.setFinancialStatus(FinancialStatusEnum.WAIT_FINANCIAL_CHECK.getCode());
                payment.setFinancialMan(mainOrder.getFinancialMan());
                payment.setSaleMan(mainOrder.getSaleMan());
                mainOrder.setFinancialStatus(FinancialStatusEnum.WAIT_FINANCIAL_CHECK.getCode());
                paymentMapper.insert(payment);
                mainOrderMapper.updateById(mainOrder);
            }
        } else {
            return ResponseApi.error("订单不存在");
        }
        return ResponseApi.ok();
    }

    @Override
    public ResponseApi cancelMainOrderByAgent(MainOrderCancelReq req) {
        UpdateWrapper<MainOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("main_order_id", req.getMainOrderId()).set("main_order_cancel_status",
            CancelStatusEnum.WAIT_SALE_CHECK.getCode());
        int count = mainOrderMapper.update(null, updateWrapper);
        return ResponseApi.ok(count);
    }

    @Override
    public ResponseApi cancelMainOrderBySalesman(MainOrderCancelReq req) {
        UpdateWrapper<MainOrder> updateWrapper = new UpdateWrapper<>();

        if (req.getOperType() == 1) {
            updateWrapper.eq("main_order_id", req.getMainOrderId()).set("main_order_cancel_status",
                CancelStatusEnum.WAIT_FINANCIAL_CHECK.getCode());
        } else {
            updateWrapper.eq("main_order_id", req.getMainOrderId()).set("main_order_cancel_status",
                CancelStatusEnum.SALE_NOT_PASS.getCode());
        }
        int count = mainOrderMapper.update(null, updateWrapper);
        return ResponseApi.ok(count);
    }

    @Override
    public ResponseApi cancelMainOrderByFinancialMan(MainOrderCancelReq req) {
        UpdateWrapper<MainOrder> updateWrapper = new UpdateWrapper<>();

        if (req.getOperType() == 1) {
            updateWrapper.eq("main_order_id", req.getMainOrderId()).set("main_order_cancel_status",
                CancelStatusEnum.FINANCIAL_PASS.getCode());
        } else {
            updateWrapper.eq("main_order_id", req.getMainOrderId()).set("main_order_cancel_status",
                CancelStatusEnum.FINANCIAL_NOT_PASS.getCode());
        }
        int count = mainOrderMapper.update(null, updateWrapper);
        return ResponseApi.ok(count);
    }
}
