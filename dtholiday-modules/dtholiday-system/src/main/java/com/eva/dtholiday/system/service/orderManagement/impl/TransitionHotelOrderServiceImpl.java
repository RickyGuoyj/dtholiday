package com.eva.dtholiday.system.service.orderManagement.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.orderManagement.CustomerInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.TotalPriceInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.mainorder.MainOrder;
import com.eva.dtholiday.commons.dao.entity.orderManagement.transitionHotel.TransitionHotelInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.transitionHotel.TransitionHotelOrder;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.MainOrderMapper;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.TransitionHotelOrderMapper;
import com.eva.dtholiday.commons.dao.req.orderManagement.*;
import com.eva.dtholiday.commons.dao.resp.UserResp;
import com.eva.dtholiday.commons.dao.resp.orderManagement.TransitionHotelOrderResp;
import com.eva.dtholiday.commons.enums.OrderStatusEnum;
import com.eva.dtholiday.system.service.UserService;
import com.eva.dtholiday.system.service.convert.OrderConvert;
import com.eva.dtholiday.system.service.orderManagement.TransitionHotelOrderService;

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
    public ResponseApi queryTransitionHotelOrderList(TransitionHotelOrderPageReq req) {
        IPage<TransitionHotelOrder> entityPage = new Page<>(req.getCurrent(), req.getSize());
        UserResp currentUserInfo = userService.getCurrentUserDetail();
        QueryWrapper<TransitionHotelOrder> queryWrapper = new QueryWrapper<>();
        if (currentUserInfo != null) {
            setQueryWrapper(queryWrapper, currentUserInfo, req);
        } else {
            return ResponseApi.ok(new Page<>(req.getCurrent(), 0));
        }
        entityPage = transitionHotelOrderMapper.selectPage(entityPage, queryWrapper);
        if (Objects.isNull(entityPage)) {
            return ResponseApi.ok(new Page<>(req.getCurrent(), 0));
        }
        String roleInfo = currentUserInfo.getRoleInfo().getName();
        List<TransitionHotelOrderResp> orderRespList = entityPage.getRecords().stream().map(order -> {
            TransitionHotelOrderResp transitionHotelOrderResp = new TransitionHotelOrderResp();
            convertTransitionHotelOrderEntityToResp(order, transitionHotelOrderResp, roleInfo);
            return transitionHotelOrderResp;
        }).collect(Collectors.toList());
        IPage<TransitionHotelOrderResp> respPage = new Page<>(req.getCurrent(), req.getSize());
        respPage.setRecords(orderRespList);
        respPage.setTotal(entityPage.getTotal());
        return ResponseApi.ok(respPage);
    }

    private void setQueryWrapper(QueryWrapper<TransitionHotelOrder> queryWrapper, UserResp currentUserInfo,
                                 TransitionHotelOrderPageReq req) {
        if (req.getTransitionHotelOrderId() != null) {
            queryWrapper.eq("transition_hotel_order_id", req.getTransitionHotelOrderId());
        }
        if (req.getOrderStatus() != null) {
            queryWrapper.eq("order_status", req.getOrderStatus());
        }
        if (req.getFinancialStatus() != null) {
            queryWrapper.eq("financial_status", req.getFinancialStatus());
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

        // 根据角色特殊化处理
        String roleInfo = currentUserInfo.getRoleInfo().getName();
        switch (roleInfo) {
            case "代理":
                queryWrapper.eq("order_creator", currentUserInfo.getUserName());
                if (StringUtils.hasText(req.getSaleMan())) {
                    queryWrapper.eq("sale_man", req.getSaleMan());
                }
                break;
            case "代理主管": {
                List<String> userName = userService.getUserNameByParentUserName(currentUserInfo.getUserName());
                queryWrapper.in("order_creator", userName);
                if (StringUtils.hasText(req.getSaleMan())) {
                    queryWrapper.eq("sale_man", req.getSaleMan());
                }
                break;
            }
            case "销售":
                queryWrapper.eq("sale_man", currentUserInfo.getUserName());
                break;
            case "销售主管": {
                List<String> userName = userService.getUserNameByParentUserName(currentUserInfo.getUserName());
                queryWrapper.in("sale_man", userName);
                break;
            }
            default:
                if (StringUtils.hasText(req.getSaleMan())) {
                    queryWrapper.eq("sale_man", req.getSaleMan());
                }
                break;
        }
        queryWrapper.orderByDesc("transition_hotel_order_id");

    }

    private void convertTransitionHotelOrderEntityToResp(TransitionHotelOrder order,
                                                         TransitionHotelOrderResp transitionHotelOrderResp, String roleInfo) {
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
        String roleInfo;
        QueryWrapper<TransitionHotelOrder> queryWrapper = new QueryWrapper<>();
        if (currentUserInfo != null) {
            roleInfo = currentUserInfo.getRoleInfo().getName();
            if (roleInfo.equals("代理") || roleInfo.equals("代理主管")) {
                queryWrapper.eq("order_creator", currentUserInfo.getUserName());
            } else if (roleInfo.equals("销售") || roleInfo.equals("销售主管")) {
                queryWrapper.eq("sale_man", currentUserInfo.getUserName());
            }
            if (req.getTransitionHotelOrderId() != null) {
                queryWrapper.eq("transition_hotel_order_id", req.getTransitionHotelOrderId());
            }
        } else {
            return null;
        }
        TransitionHotelOrder order = transitionHotelOrderMapper.selectOne(queryWrapper);
        TransitionHotelOrderResp transitionHotelOrderResp = new TransitionHotelOrderResp();
        if (order != null) {
            convertTransitionHotelOrderEntityToResp(order, transitionHotelOrderResp, roleInfo);
        }
        return transitionHotelOrderResp;
    }

    @Override
    public ResponseApi updateTransitionHotelOrderByAgent(TransitionHotelOrderReq req) {
        // 查询历史数据
        TransitionHotelOrder oldEntity = transitionHotelOrderMapper.selectById(req.getTransitionHotelOrderId());
        Double oldTotalPrice = oldEntity.getTotalPrice();
        Integer oldCurrencyType = oldEntity.getCurrencyType();

        // 更新子订单数据
        UserResp currentUserDetail = userService.getCurrentUserDetail();
        TransitionHotelOrder transitionHotelOrder =
                OrderConvert.convertTransitionHotelInfoToEntity(req, currentUserDetail.getUserName());
        transitionHotelOrder.setTransitionHotelOrderId(req.getTransitionHotelOrderId());
        transitionHotelOrder.setOrderStatus(oldEntity.getOrderStatus());
        transitionHotelOrder.setFinancialStatus(oldEntity.getFinancialStatus());
        transitionHotelOrder.setSaleMan(oldEntity.getSaleMan());
        int count = transitionHotelOrderMapper.updateById(transitionHotelOrder);

        // 更新主订单的总价
        QueryWrapper<MainOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("transition_hotel_order_id", req.getTransitionHotelOrderId());
        MainOrder mainOrder = mainOrderMapper.selectOne(queryWrapper);
        TotalPriceInfo mainOrderTotalPriceInfo =
                JSONObject.parseObject(mainOrder.getTotalPrice(), TotalPriceInfo.class);
        // 减掉旧的

        mainOrderTotalPriceInfo.setUsd(mainOrderTotalPriceInfo.getUsd() - oldTotalPrice);

        // 加上修改后的
        Double reqTotalPrice = req.getTotalPrice();

        mainOrderTotalPriceInfo.setUsd(mainOrderTotalPriceInfo.getUsd() + reqTotalPrice);

        String totalPriceInfo = JSONObject.toJSONString(mainOrderTotalPriceInfo);
        UpdateWrapper<MainOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("total_price", totalPriceInfo).eq("main_order_id", mainOrder.getMainOrderId());
        mainOrderMapper.update(null, updateWrapper);
        return ResponseApi.ok(count);
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
            if (transitionHotelOrder.getOrderStatus() == OrderStatusEnum.WAIT_SALE_CHECK.getCode()) {
                QueryWrapper<MainOrder> mainOrderQueryWrapper = new QueryWrapper<>();
                mainOrderQueryWrapper.eq("transition_hotel_order_id", transitionHotelOrder.getTransitionHotelOrderId());
                MainOrder mainOrder = mainOrderMapper.selectOne(mainOrderQueryWrapper);
                if (req.getCheckStatus() == 1) {
                    transitionHotelOrder.setOrderStatus(OrderStatusEnum.WAIT_FINANCIAL_CHECK.getCode());
                    transitionHotelOrder.setCostPrice(req.getCostPrice());
                    transitionHotelOrder.setDiscount(req.getDiscount());
                    transitionHotelOrder.setBookingCode(req.getBookingCode());
                    transitionHotelOrder.setFinancialMan(req.getFinancialMan());
                    // 计算金额
                    transitionHotelOrder
                            .setDiscountPrice(transitionHotelOrder.getTotalPrice() - transitionHotelOrder.getDiscount());
                    // todo 主订单金额重新计算
                } else {
                    transitionHotelOrder.setOrderStatus(OrderStatusEnum.WAIT_AGENT_RESUBMIT.getCode());
                    transitionHotelOrder.setRemarks(req.getCheckRemark());
                }
                if (mainOrder != null) {
                    mainOrder.setTransitionHotelOrderStatus(transitionHotelOrder.getOrderStatus());
                    Integer orderStatus = mainOrder.getTransitionHotelOrderStatus();
                    // 计算三个值中最小的，需要判空
                    if (mainOrder.getIslandHotelOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getIslandHotelOrderStatus(), orderStatus);
                    }
                    if (mainOrder.getPlaneTicketOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getPlaneTicketOrderStatus(), orderStatus);
                    }
                    mainOrder.setOrderStatus(orderStatus);
                    mainOrder.setFinancialMan(req.getFinancialMan());
                    mainOrderMapper.updateById(mainOrder);
                }
                transitionHotelOrderMapper.updateById(transitionHotelOrder);
                return ResponseApi.ok("审核成功");
            } else {
                return ResponseApi.error("未到你的流程");
            }
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
            if (transitionHotelOrder.getOrderStatus() == OrderStatusEnum.WAIT_FINANCIAL_CHECK.getCode()) {
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
                    mainOrder.setTransitionHotelOrderStatus(transitionHotelOrder.getOrderStatus());
                    Integer orderStatus = mainOrder.getTransitionHotelOrderStatus();
                    // 计算三个值中最小的，需要判空
                    if (mainOrder.getIslandHotelOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getIslandHotelOrderStatus(), orderStatus);
                    }
                    if (mainOrder.getPlaneTicketOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getPlaneTicketOrderStatus(), orderStatus);
                    }
                    mainOrder.setOrderStatus(orderStatus);
                    mainOrderMapper.updateById(mainOrder);
                }
                transitionHotelOrderMapper.updateById(transitionHotelOrder);
                return ResponseApi.ok("审核成功");
            } else {
                return ResponseApi.error("未到你的流程");
            }
        } else {
            return ResponseApi.error("请选择审核状态");
        }
    }

    @Override
    public ResponseApi updateCheckInfo(CheckInfoReq req) {
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
            if (transitionHotelOrder.getOrderStatus() == OrderStatusEnum.WAIT_HOTEL_CONFIRM.getCode()) {
                QueryWrapper<MainOrder> mainOrderQueryWrapper = new QueryWrapper<>();
                mainOrderQueryWrapper.eq("transition_hotel_order_id", req.getTransitionHotelOrderId());
                MainOrder mainOrder = mainOrderMapper.selectOne(mainOrderQueryWrapper);
                if (req.getCheckStatus() == 1) {
                    transitionHotelOrder.setOrderStatus(OrderStatusEnum.HOTEL_CONFIRM_SUCCESS.getCode());
                } else {
                    transitionHotelOrder.setOrderStatus(OrderStatusEnum.HOTEL_CONFIRM_FAIL.getCode());
                    transitionHotelOrder.setRemarks(req.getCheckRemark());
                }
                if (mainOrder != null) {
                    mainOrder.setTransitionHotelOrderStatus(transitionHotelOrder.getOrderStatus());
                    Integer orderStatus = mainOrder.getTransitionHotelOrderStatus();
                    // 计算三个值中最小的，需要判空
                    if (mainOrder.getIslandHotelOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getIslandHotelOrderStatus(), orderStatus);
                    }
                    if (mainOrder.getPlaneTicketOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getPlaneTicketOrderStatus(), orderStatus);
                    }
                    mainOrder.setOrderStatus(orderStatus);
                    mainOrderMapper.updateById(mainOrder);
                }
                transitionHotelOrderMapper.updateById(transitionHotelOrder);
                return ResponseApi.ok("流程结束");
            } else {
                return ResponseApi.error("未到该流程");
            }
        } else {
            return ResponseApi.error("请选择确认状态");
        }
    }
}
