package com.eva.dtholiday.system.service.orderManagement.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import com.eva.dtholiday.commons.dao.entity.orderManagement.planeTicket.PlaneTicketInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.planeTicket.PlaneTicketOrder;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.MainOrderMapper;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.PlaneTicketOrderMapper;
import com.eva.dtholiday.commons.dao.req.orderManagement.*;
import com.eva.dtholiday.commons.dao.resp.UserResp;
import com.eva.dtholiday.commons.dao.resp.orderManagement.PlaneTicketOrderResp;
import com.eva.dtholiday.commons.enums.OrderStatusEnum;
import com.eva.dtholiday.system.service.UserService;
import com.eva.dtholiday.system.service.convert.OrderConvert;
import com.eva.dtholiday.system.service.orderManagement.PlaneTicketOrderService;

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

    @Resource
    private MainOrderMapper mainOrderMapper;

    @Override
    public ResponseApi createPlaneTicketOrder(PlaneTicketOrderReq req) {
        return null;
    }

    @Override
    public ResponseApi queryPlaneTicketOrderList(PlaneTicketOrderPageReq req) {
        IPage<PlaneTicketOrder> entityPage = new Page<>(req.getCurrent(), req.getSize());
        UserResp currentUserInfo = userService.getCurrentUserDetail();
        QueryWrapper<PlaneTicketOrder> queryWrapper = new QueryWrapper<>();
        if (currentUserInfo != null) {
            setQueryWrapper(queryWrapper, currentUserInfo, req);
        } else {
            return ResponseApi.ok(new Page<>(req.getCurrent(), 0));
        }
        entityPage = planeTicketOrderMapper.selectPage(entityPage, queryWrapper);
        if (Objects.isNull(entityPage)) {
            return ResponseApi.ok(new Page<>(req.getCurrent(), 0));
        }
        String roleInfo = currentUserInfo.getRoleInfo().getName();
        List<PlaneTicketOrderResp> orderRespList = entityPage.getRecords().stream().map(order -> {
            PlaneTicketOrderResp planeTicketOrderResp = new PlaneTicketOrderResp();
            convertPlaneTicketOrderEntityToResp(order, planeTicketOrderResp, roleInfo);
            return planeTicketOrderResp;
        }).collect(Collectors.toList());
        IPage<PlaneTicketOrderResp> respPage = new Page<>(req.getCurrent(), req.getSize());
        respPage.setRecords(orderRespList);
        respPage.setTotal(entityPage.getTotal());
        return ResponseApi.ok(respPage);
    }

    private void setQueryWrapper(QueryWrapper<PlaneTicketOrder> queryWrapper, UserResp currentUserInfo,
                                 PlaneTicketOrderPageReq req) {
        if (req.getPlaneTicketOrderId() != null) {
            queryWrapper.eq("plane_ticket_order_id", req.getPlaneTicketOrderId());
        }
        if (req.getOrderStatus() != null) {
            queryWrapper.eq("order_status", req.getOrderStatus());
        }
        if (req.getFinancialStatus() != null) {
            queryWrapper.eq("financial_status", req.getFinancialStatus());
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

        // 根据角色特殊化处理
        String roleInfo = currentUserInfo.getRoleInfo().getName();
        if (roleInfo.equals("代理") || roleInfo.equals("代理主管")) {
            queryWrapper.eq("order_creator", currentUserInfo.getUserName());
            if (StringUtils.hasText(req.getSaleMan())) {
                queryWrapper.eq("sale_man", req.getSaleMan());
            }
        } else if (roleInfo.equals("销售") || roleInfo.equals("销售主管")) {
            queryWrapper.eq("sale_man", currentUserInfo.getUserName());
        } else {
            if (StringUtils.hasText(req.getSaleMan())) {
                queryWrapper.eq("sale_man", req.getSaleMan());
            }
        }

    }

    private void convertPlaneTicketOrderEntityToResp(PlaneTicketOrder order, PlaneTicketOrderResp planeTicketOrderResp,
                                                     String roleInfo) {
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
        String roleInfo;
        QueryWrapper<PlaneTicketOrder> queryWrapper = new QueryWrapper<>();
        if (currentUserInfo != null) {
            roleInfo = currentUserInfo.getRoleInfo().getName();
            if (roleInfo.equals("代理") || roleInfo.equals("代理主管")) {
                queryWrapper.eq("order_creator", currentUserInfo.getUserName());
            } else if (roleInfo.equals("销售") || roleInfo.equals("销售主管")) {
                queryWrapper.eq("sale_man", currentUserInfo.getUserName());
            }
            if (req.getPlaneTicketOrderId() != null) {
                queryWrapper.eq("plane_ticket_order_id", req.getPlaneTicketOrderId());
            }
        } else {
            return null;
        }
        PlaneTicketOrder order = planeTicketOrderMapper.selectOne(queryWrapper);
        PlaneTicketOrderResp planeTicketOrderResp = new PlaneTicketOrderResp();
        if (order != null) {
            convertPlaneTicketOrderEntityToResp(order, planeTicketOrderResp, roleInfo);
        }
        return planeTicketOrderResp;
    }

    @Override
    public ResponseApi updatePlaneTicketOrderByFinancialMan(PlaneTicketOrderFinancialManReq req) {
        if (req.getCheckStatus() != null) {
            QueryWrapper<PlaneTicketOrder> queryWrapper = new QueryWrapper<>();
            if (req.getPlaneTicketOrderId() == null) {
                return ResponseApi.error("请选择机票订单");
            }
            queryWrapper.eq("plane_ticket_order_id", req.getPlaneTicketOrderId());
            PlaneTicketOrder planeTicketOrder = planeTicketOrderMapper.selectOne(queryWrapper);
            if (planeTicketOrder == null) {
                return ResponseApi.error("机票订单不存在");
            }
            if (planeTicketOrder.getOrderStatus() == OrderStatusEnum.WAIT_FINANCIAL_CHECK.getCode()) {
                QueryWrapper<MainOrder> mainOrderQueryWrapper = new QueryWrapper<>();
                mainOrderQueryWrapper.eq("plane_ticket_order_id", planeTicketOrder.getPlaneTicketOrderId());
                MainOrder mainOrder = mainOrderMapper.selectOne(mainOrderQueryWrapper);
                if (req.getCheckStatus() == 1) {
                    planeTicketOrder.setOrderStatus(OrderStatusEnum.WAIT_HOTEL_CONFIRM.getCode());
                    planeTicketOrder.setConfirmInfo(req.getConfirmInfo());
                } else {
                    planeTicketOrder.setOrderStatus(OrderStatusEnum.WAIT_AGENT_RESUBMIT2.getCode());
                    planeTicketOrder.setRemarks(req.getCheckRemark());
                }
                if (mainOrder != null) {
                    mainOrder.setPlaneTicketOrderStatus(planeTicketOrder.getOrderStatus());
                    Integer orderStatus = mainOrder.getPlaneTicketOrderStatus();
                    // 计算三个值中最小的，需要判空
                    if (mainOrder.getIslandHotelOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getIslandHotelOrderStatus(), orderStatus);
                    }
                    if (mainOrder.getTransitionHotelOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getTransitionHotelOrderStatus(), orderStatus);
                    }
                    mainOrder.setOrderStatus(orderStatus);
                }
                planeTicketOrderMapper.updateById(planeTicketOrder);
                mainOrderMapper.updateById(mainOrder);
                return ResponseApi.ok("审核成功");
            } else {
                return ResponseApi.error("未到你的流程");
            }
        } else {
            return ResponseApi.error("请选择审核状态");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi updatePlaneTicketOrderBySaleMan(PlaneTicketOrderSalesmanReq req) {
        if (req.getCheckStatus() != null) {
            QueryWrapper<PlaneTicketOrder> queryWrapper = new QueryWrapper<>();
            if (req.getPlaneTicketOrderId() == null) {
                return ResponseApi.error("请选择机票订单");
            }
            queryWrapper.eq("plane_ticket_order_id", req.getPlaneTicketOrderId());
            PlaneTicketOrder planeTicketOrder = planeTicketOrderMapper.selectOne(queryWrapper);
            if (planeTicketOrder == null) {
                return ResponseApi.error("机票订单不存在");
            }
            if (planeTicketOrder.getOrderStatus() == OrderStatusEnum.WAIT_SALE_CHECK.getCode()) {
                QueryWrapper<MainOrder> mainOrderQueryWrapper = new QueryWrapper<>();
                mainOrderQueryWrapper.eq("plane_ticket_order_id", planeTicketOrder.getPlaneTicketOrderId());
                MainOrder mainOrder = mainOrderMapper.selectOne(mainOrderQueryWrapper);
                if (req.getCheckStatus() == 1) {
                    planeTicketOrder.setOrderStatus(OrderStatusEnum.WAIT_FINANCIAL_CHECK.getCode());
                    planeTicketOrder.setCostPrice(req.getCostPrice());
                    planeTicketOrder.setDiscount(req.getDiscount());
                    planeTicketOrder.setTicketNumber(req.getTicketNumber());
                    planeTicketOrder.setFinancialMan(req.getFinancialMan());
                    // 计算金额
                    planeTicketOrder
                            .setDiscountPrice(planeTicketOrder.getTotalPrice() - planeTicketOrder.getDiscount());
                    // todo 主订单金额重新计算
                } else {
                    planeTicketOrder.setOrderStatus(OrderStatusEnum.WAIT_AGENT_RESUBMIT.getCode());
                    planeTicketOrder.setRemarks(req.getCheckRemark());
                }
                if (mainOrder != null) {
                    mainOrder.setPlaneTicketOrderStatus(planeTicketOrder.getOrderStatus());
                    Integer orderStatus = mainOrder.getPlaneTicketOrderStatus();
                    // 计算三个值中最小的，需要判空
                    if (mainOrder.getIslandHotelOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getIslandHotelOrderStatus(), orderStatus);
                    }
                    if (mainOrder.getTransitionHotelOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getTransitionHotelOrderStatus(), orderStatus);
                    }
                    mainOrder.setOrderStatus(orderStatus);
                }
                planeTicketOrderMapper.updateById(planeTicketOrder);
                mainOrderMapper.updateById(mainOrder);
                return ResponseApi.ok("审核成功");
            } else {
                return ResponseApi.error("未到你的流程");
            }

        } else {
            return ResponseApi.error("请选择审核状态");
        }
    }

    @Override
    public ResponseApi updatePlaneTicketOrderByAgent(PlaneTicketOrderReq req) {
        // 查询历史数据
        PlaneTicketOrder oldEntity = planeTicketOrderMapper.selectById(req.getPlaneTicketOrderId());
        Double oldTotalPrice = oldEntity.getTotalPrice();
        Integer oldCurrencyType = oldEntity.getCurrencyType();

        // 更新子订单数据
        UserResp currentUserDetail = userService.getCurrentUserDetail();
        PlaneTicketOrder planeTicketOrder =
                OrderConvert.convertPlaneTicketInfoToEntity(req, currentUserDetail.getUserName());
        planeTicketOrder.setPlaneTicketOrderId(req.getPlaneTicketOrderId());
        planeTicketOrder.setOrderStatus(oldEntity.getOrderStatus());
        planeTicketOrder.setFinancialStatus(oldEntity.getFinancialStatus());
        planeTicketOrder.setSaleMan(oldEntity.getSaleMan());
        int count = planeTicketOrderMapper.updateById(planeTicketOrder);

        // 更新主订单的总价
        QueryWrapper<MainOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("plane_ticket_order_id", req.getPlaneTicketOrderId());
        MainOrder mainOrder = mainOrderMapper.selectOne(queryWrapper);
        TotalPriceInfo mainOrderTotalPriceInfo =
                JSONObject.parseObject(mainOrder.getTotalPrice(), TotalPriceInfo.class);
        // 减掉旧的
            mainOrderTotalPriceInfo.setCny(mainOrderTotalPriceInfo.getCny() - oldTotalPrice);

        // 加上修改后的
        Double reqTotalPrice = req.getTotalPrice();
        Integer reqCurrencyType = req.getCurrencyType();
            mainOrderTotalPriceInfo.setCny(mainOrderTotalPriceInfo.getCny() + reqTotalPrice);

        String totalPriceInfo = JSONObject.toJSONString(mainOrderTotalPriceInfo);
        UpdateWrapper<MainOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("total_price", totalPriceInfo).eq("main_order_id", mainOrder.getMainOrderId());
        mainOrderMapper.update(null, updateWrapper);
        return ResponseApi.ok(count);
    }

    @Override
    public ResponseApi updateCheckInfo(CheckInfoReq req) {
        if (req.getCheckStatus() != null) {
            QueryWrapper<PlaneTicketOrder> queryWrapper = new QueryWrapper<>();
            if (req.getPlaneTicketOrderId() == null) {
                return ResponseApi.error("请选择机票订单");
            }
            queryWrapper.eq("plane_ticket_order_id", req.getPlaneTicketOrderId());
            PlaneTicketOrder planeTicketOrder = planeTicketOrderMapper.selectOne(queryWrapper);
            if (planeTicketOrder == null) {
                return ResponseApi.error("机票订单不存在");
            }
            if (planeTicketOrder.getOrderStatus() == OrderStatusEnum.WAIT_HOTEL_CONFIRM.getCode()) {
                QueryWrapper<MainOrder> mainOrderQueryWrapper = new QueryWrapper<>();
                mainOrderQueryWrapper.eq("plane_ticket_order_id", planeTicketOrder.getPlaneTicketOrderId());
                MainOrder mainOrder = mainOrderMapper.selectOne(mainOrderQueryWrapper);
                if (req.getCheckStatus() == 1) {
                    planeTicketOrder.setOrderStatus(OrderStatusEnum.HOTEL_CONFIRM_SUCCESS.getCode());
                } else {
                    planeTicketOrder.setOrderStatus(OrderStatusEnum.HOTEL_CONFIRM_FAIL.getCode());
                    planeTicketOrder.setRemarks(req.getCheckRemark());
                }
                if (mainOrder != null) {
                    mainOrder.setPlaneTicketOrderStatus(planeTicketOrder.getOrderStatus());
                    Integer orderStatus = mainOrder.getPlaneTicketOrderStatus();
                    // 计算三个值中最小的，需要判空
                    if (mainOrder.getIslandHotelOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getIslandHotelOrderStatus(), orderStatus);
                    }
                    if (mainOrder.getTransitionHotelOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getTransitionHotelOrderStatus(), orderStatus);
                    }
                    mainOrder.setOrderStatus(orderStatus);
                }
                planeTicketOrderMapper.updateById(planeTicketOrder);
                mainOrderMapper.updateById(mainOrder);
                return ResponseApi.ok("流程结束");
            } else {
                return ResponseApi.error("未到该流程");
            }
        } else {
            return ResponseApi.error("请选择确认状态");
        }
    }
}
