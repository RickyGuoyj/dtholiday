package com.eva.dtholiday.system.service.orderManagement.impl;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.financialManagement.Payment;
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
import com.eva.dtholiday.commons.dao.req.orderManagement.MainOrderQueryListReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.MainOrderReq;
import com.eva.dtholiday.commons.dao.req.financialManagement.PaymentReq;
import com.eva.dtholiday.commons.dao.resp.UserResp;
import com.eva.dtholiday.commons.dao.resp.orderManagement.MainOrderQueryListResp;
import com.eva.dtholiday.system.constant.ErpConstant;
import com.eva.dtholiday.system.service.UserService;
import com.eva.dtholiday.system.service.convert.OrderConvert;
import com.eva.dtholiday.system.service.orderManagement.MainOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        IslandHotelOrder islandHotelOrder = new IslandHotelOrder();
        PlaneTicketOrder planeTicketOrder = new PlaneTicketOrder();
        TransitionHotelOrder transitionHotelOrder = new TransitionHotelOrder();
        // 岛屿订单
        if (Objects.nonNull(req.getIslandHotelOrder()) && Objects.nonNull(req.getIslandHotelOrder().getHotelInfo())) {
            islandHotelOrder =
                OrderConvert.convertIslandHotelInfoToEntity(req.getIslandHotelOrder(), currentUserDetail.getUserName());
            islandHotelOrder.setOrderStatus(ErpConstant.ORDER_STATUS.WAIT_SALE_REVIEW);
            islandHotelOrder.setFinancialStatus(0);
            islandHotelOrderMapper.insert(islandHotelOrder);
        }
        // 机票订单
        if (Objects.nonNull(req.getPlaneTicketOrder()) && Objects.nonNull(req.getPlaneTicketOrder().getPlaneTicketInfo())) {
            planeTicketOrder =
                OrderConvert.convertPlaneTicketInfoToEntity(req.getPlaneTicketOrder(), currentUserDetail.getUserName());
            planeTicketOrder.setOrderStatus(ErpConstant.ORDER_STATUS.WAIT_SALE_REVIEW);
            planeTicketOrder.setFinancialStatus(0);
            planeTicketOrderMapper.insert(planeTicketOrder);
        }
        // 过度酒店订单
        if (Objects.nonNull(req.getTransitionHotelOrder()) && Objects.nonNull(req.getTransitionHotelOrder().getTransitionHotelInfo())) {
            transitionHotelOrder = OrderConvert.convertTransitionHotelInfoToEntity(req.getTransitionHotelOrder(),
                currentUserDetail.getUserName());
            transitionHotelOrder.setOrderStatus(ErpConstant.ORDER_STATUS.WAIT_SALE_REVIEW);
            transitionHotelOrder.setFinancialStatus(0);
            transitionHotelOrderMapper.insert(transitionHotelOrder);
        }
        // 写主表
        MainOrder mainOrder = OrderConvert.convertMainOrderInfoToEntity(req, currentUserDetail.getUserName(),
                islandHotelOrder.getIslandHotelOrderId(), planeTicketOrder.getPlaneTicketOrderId(),
                transitionHotelOrder.getTransitionHotelOrderId());
        mainOrder.setOrderStatus(ErpConstant.ORDER_STATUS.WAIT_SALE_REVIEW);
        mainOrder.setFinancialStatus(0);
        int insert = mainOrderMapper.insert(mainOrder);
        return ResponseApi.ok(insert);
    }

    @Override
    public ResponseApi<MainOrderQueryListResp> queryMainOrderList(MainOrderQueryListReq req) {
        Map<String,Object> map = new HashMap<>();
        map.put("mainOrderId",req.getMainOrderId());
        map.put("orderCreator",req.getOrderCreator());
        map.put("saleMan",req.getSaleMan());
        map.put("islandHotelOrderId",req.getIslandHotelOrderId());
        map.put("planeTicketOrderId",req.getPlaneTicketOrderId());
        map.put("transitionHotelOrderId",req.getTransitionHotelOrderId());
        int count = mainOrderMapper.countMainOrderList(map);
        map.put("from", (req.getPage() - 1) * req.getPageSize());
        map.put("to", req.getPageSize());
        List<MainOrderListInfo> mainOrderListInfos = mainOrderMapper.queryMainOrderList(map);
        if (Objects.nonNull(mainOrderListInfos)) {
            MainOrderQueryListResp mainOrderQueryListResp = new MainOrderQueryListResp();
            mainOrderQueryListResp.setTotal(count);
            mainOrderQueryListResp.setPage(req.getPage());
            mainOrderQueryListResp.setPageSize(req.getPageSize());
            mainOrderQueryListResp.setMainOrderListInfoList(mainOrderListInfos);
            return ResponseApi.ok(mainOrderQueryListResp);
        }
        return ResponseApi.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi agentPay(PaymentReq req) {
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

        MainOrder mainOrder = mainOrderMapper.selectById(req.getMainOrderId());
        if (mainOrder != null){
            if (mainOrder.getFinancialStatus() == 0){
                payment.setFinancialStatus(0);
                payment.setFinancialMan(mainOrder.getFinancialMan());
                payment.setSaleMan(mainOrder.getSaleMan());
                mainOrder.setFinancialStatus(0);
                paymentMapper.insert(payment);
                mainOrderMapper.updateById(mainOrder);
            }
        }else{
            return ResponseApi.error("订单不存在");
        }
        return ResponseApi.ok();
    }
}
