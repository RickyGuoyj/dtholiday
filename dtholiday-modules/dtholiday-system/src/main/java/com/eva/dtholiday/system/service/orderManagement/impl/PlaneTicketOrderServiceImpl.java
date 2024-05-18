package com.eva.dtholiday.system.service.orderManagement.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.orderManagement.CustomerInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.planeTicket.PlaneTicketInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.planeTicket.PlaneTicketOrder;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.PlaneTicketOrderMapper;
import com.eva.dtholiday.commons.dao.req.orderManagement.PlaneTicketOrderDetailReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.PlaneTicketOrderPageReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.PlaneTicketOrderReq;
import com.eva.dtholiday.commons.dao.resp.UserResp;
import com.eva.dtholiday.commons.dao.resp.orderManagement.PlaneTicketOrderResp;
import com.eva.dtholiday.system.service.UserService;
import com.eva.dtholiday.system.service.orderManagement.PlaneTicketOrderService;
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
 * @create-time 2024/5/17 2:35
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Service
public class PlaneTicketOrderServiceImpl implements PlaneTicketOrderService {

    @Resource
    private UserService userService;

    @Resource
    private PlaneTicketOrderMapper planeTicketOrderMapper;

    @Override
    public ResponseApi createPlaneTicketOrder(PlaneTicketOrderReq req) {
        return null;
    }

    @Override
    public ResponseApi updatePlaneTicketOrder(PlaneTicketOrderReq req) {
        return null;
    }

    @Override
    public List<PlaneTicketOrderResp> queryPlaneTicketOrderList(PlaneTicketOrderPageReq req) {
        UserResp currentUserInfo = userService.getCurrentUserDetail();
        QueryWrapper<PlaneTicketOrder> queryWrapper = new QueryWrapper<>();
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
            if (Objects.nonNull(req.getDepartureDate())) {
                queryWrapper.ge("departure_date", req.getDepartureDate());
            }
            if (Objects.nonNull(req.getReturnDate())) {
                queryWrapper.le("return_date", req.getReturnDate());
            }
            if (StringUtils.hasText(req.getAirlineCompanyName())) {
                queryWrapper.like("airline_company_name", req.getAirlineCompanyName());
            }
            if (StringUtils.hasText(req.getCustomerName())) {
                queryWrapper.like("customer_name", req.getCustomerName());
            }
        } else {
            return Collections.emptyList();
        }
        List<PlaneTicketOrder> orderList = planeTicketOrderMapper.selectList(queryWrapper);
        List<PlaneTicketOrderResp> orderRespList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(orderList)) {
            String roleInfo = currentUserInfo.getRoleInfo().getName();
            orderRespList = orderList.stream().map(order -> {
                PlaneTicketOrderResp planeTicketOrderResp = new PlaneTicketOrderResp();
                convertPlaneTicketOrderEntityToResp(order, planeTicketOrderResp, roleInfo);
                return planeTicketOrderResp;
            }).collect(Collectors.toList());
            if (currentUserInfo.getRoleInfo().getName().equals("代理")) {
                orderRespList = orderRespList.stream().map(resp -> {
                    resp.setCostPrice(null);
                    resp.setDiscount(null);
                    resp.setTicketNumber(null);
                    resp.setFinancialMan(null);
                    return resp;
                }).collect(Collectors.toList());
            }
        }
        return orderRespList;
    }

    private void convertPlaneTicketOrderEntityToResp(PlaneTicketOrder order, PlaneTicketOrderResp planeTicketOrderResp, String roleInfo) {
        BeanUtils.copyProperties(order, planeTicketOrderResp);
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCustomerName(order.getCustomerName());
        customerInfo.setAdultNum(order.getAdultNum());
        customerInfo.setChildNum(order.getChildNum());

        PlaneTicketInfo planeTicketInfo = new PlaneTicketInfo();
        planeTicketInfo.setPlaneTicketId(order.getPlaneTicketId());
        planeTicketInfo.setAirlineCompanyName(order.getAirlineCompanyName());
        planeTicketInfo.setDays(order.getDays());
        planeTicketInfo.setDepartureDate(order.getDepartureDate());
        planeTicketInfo.setDepartureFlight(order.getDepartureFlight());
        planeTicketInfo.setDeparturePlace(order.getDeparturePlace());
        planeTicketInfo.setReturnDate(order.getReturnDate());
        planeTicketInfo.setReturnFlight(order.getReturnFlight());
        planeTicketInfo.setArrivalPlace(order.getArrivalPlace());
        planeTicketInfo.setPrice(order.getPrice());
        planeTicketInfo.setCurrencyType(order.getCurrencyType());

        planeTicketOrderResp.setCustomerInfo(customerInfo);
        planeTicketOrderResp.setPlaneTicketInfo(planeTicketInfo);
        if (roleInfo.equals("代理") || roleInfo.equals("代理主管")) {
            planeTicketOrderResp.setCostPrice(null);
            planeTicketOrderResp.setDiscount(null);
            planeTicketOrderResp.setTicketNumber(null);
            planeTicketOrderResp.setFinancialMan(null);
        }

    }

    @Override
    public PlaneTicketOrderResp queryPlaneTicketOrderDetail(PlaneTicketOrderDetailReq req) {
        UserResp currentUserInfo = userService.getCurrentUserDetail();
        QueryWrapper<PlaneTicketOrder> queryWrapper = new QueryWrapper<>();
        if (currentUserInfo != null) {
            queryWrapper.eq("order_creator", currentUserInfo.getUserName());
            if (req.getPlaneTicketOrderId() != null) {
                queryWrapper.eq("plane_ticket_order_id", req.getPlaneTicketOrderId());
            }
        } else {
            return null;
        }
        PlaneTicketOrder order = planeTicketOrderMapper.selectOne(queryWrapper);
        PlaneTicketOrderResp planeTicketOrderResp = new PlaneTicketOrderResp();
        if (order != null) {
            String roleInfo = currentUserInfo.getRoleInfo().getName();
            convertPlaneTicketOrderEntityToResp(order, planeTicketOrderResp, roleInfo);
        }
        return planeTicketOrderResp;
    }
}
