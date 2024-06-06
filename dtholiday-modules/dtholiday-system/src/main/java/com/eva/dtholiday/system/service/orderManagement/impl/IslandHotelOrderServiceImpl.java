package com.eva.dtholiday.system.service.orderManagement.impl;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.orderManagement.TotalPriceInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.islandhotelorder.IslandHotelOrder;
import com.eva.dtholiday.commons.dao.entity.orderManagement.islandhotelorder.IslandHotelOrderInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.mainorder.MainOrder;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.IslandHotelOrderMapper;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.MainOrderMapper;
import com.eva.dtholiday.commons.dao.req.orderManagement.*;
import com.eva.dtholiday.commons.dao.resp.UserResp;
import com.eva.dtholiday.commons.dao.resp.orderManagement.IslandHotelOrderQueryListResp;
import com.eva.dtholiday.commons.enums.OrderStatusEnum;
import com.eva.dtholiday.system.service.UserService;
import com.eva.dtholiday.system.service.convert.OrderConvert;
import com.eva.dtholiday.system.service.orderManagement.IslandHotelOrderService;
import org.springframework.util.StringUtils;

@Service
public class IslandHotelOrderServiceImpl implements IslandHotelOrderService {

    @Resource
    private IslandHotelOrderMapper islandHotelOrderMapper;

    @Resource
    private MainOrderMapper mainOrderMapper;

    @Resource
    private UserService userService;

    public ResponseApi queryIslandHotelOrderList(IslandHotelOrderQueryListReq req) {
        Map<String, Object> map = new HashMap<>();
        map.put("customerName", req.getCustomerName());
        map.put("islandCnName", req.getIslandCnName());
        map.put("orderStatus", req.getOrderStatus());
        map.put("financialStatus", req.getFinancialStatus());
        map.put("islandHotelOrderId", req.getIslandHotelOrderId());
        UserResp currentUserInfo = userService.getCurrentUserDetail();
        // 特殊化处理
        // map.put("orderCreator", req.getOrderCreator());
        // map.put("saleMan", req.getSaleMan());
        String roleInfo = currentUserInfo.getRoleInfo().getName();
        if (roleInfo.equals("代理") || roleInfo.equals("代理主管")) {
            map.put("orderCreator", currentUserInfo.getUserName());
        } else if (roleInfo.equals("销售") || roleInfo.equals("销售主管")) {
            map.put("saleMan", currentUserInfo.getUserName());
        }
        switch (roleInfo) {
            case "代理":
                map.put("orderCreator", Collections.singletonList(currentUserInfo.getUserName()));
                break;
            case "代理主管": {
                List<String> userName = userService.getUserNameByParentUserName(currentUserInfo.getUserName());
                map.put("orderCreator", userName);
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
                break;
        }

        int count = islandHotelOrderMapper.countIslandHotelOrderList(map);
        map.put("from", (req.getPage() - 1) * req.getPageSize());
        map.put("to", req.getPageSize());
        List<IslandHotelOrderInfo> islandHotelOrderInfos = islandHotelOrderMapper.queryIslandHotelOrderList(map);
        if (Objects.nonNull(islandHotelOrderInfos)) {
            IslandHotelOrderQueryListResp islandHotelOrderQueryListResp = new IslandHotelOrderQueryListResp();
            islandHotelOrderQueryListResp.setTotal(count);
            islandHotelOrderQueryListResp.setPage(req.getPage());
            islandHotelOrderQueryListResp.setPageSize(req.getPageSize());
            islandHotelOrderQueryListResp.setIslandHotelOrderListInfo(islandHotelOrderInfos);
            return ResponseApi.ok(islandHotelOrderQueryListResp);
        }
        return ResponseApi.ok();
    }

    @Override
    public ResponseApi queryIslandHotelOrderDetail(IslandHotelOrderQueryDetailReq req) {
        IslandHotelOrderInfo islandHotelOrderInfo =
                islandHotelOrderMapper.queryIslandHotelOrderById(req.getIslandHotelOrderId());
        return ResponseApi.ok(islandHotelOrderInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi updateIslandHotelOrderByAgent(IslandHotelOrderReq req) {
        // 查询历史数据
        IslandHotelOrder oldEntity = islandHotelOrderMapper.selectById(req.getIslandHotelOrderId());
        Double oldTotalPrice = oldEntity.getTotalPrice();
        Integer oldCurrencyType = oldEntity.getCurrencyType();

        // 更新子订单数据
        UserResp currentUserDetail = userService.getCurrentUserDetail();
        IslandHotelOrder islandHotelOrder =
                OrderConvert.convertIslandHotelInfoToEntity(req, currentUserDetail.getUserName());
        islandHotelOrder.setIslandHotelOrderId(req.getIslandHotelOrderId());
        islandHotelOrder.setOrderStatus(oldEntity.getOrderStatus());
        islandHotelOrder.setFinancialStatus(oldEntity.getFinancialStatus());
        islandHotelOrder.setSaleMan(oldEntity.getSaleMan());
        int count = islandHotelOrderMapper.updateById(islandHotelOrder);

        // 更新主订单的总价
        QueryWrapper<MainOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("island_hotel_order_id", req.getIslandHotelOrderId());
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
    public ResponseApi updateIslandHotelOrderBySaleMan(IslandHotelOrderSalesmanReq req) {
        if (req.getCheckStatus() != null) {
            QueryWrapper<IslandHotelOrder> queryWrapper = new QueryWrapper<>();
            if (req.getIslandHotelOrderId() == null) {
                return ResponseApi.error("请选择岛屿酒店订单");
            }
            queryWrapper.eq("island_hotel_order_id", req.getIslandHotelOrderId());
            IslandHotelOrder islandHotelOrder = islandHotelOrderMapper.selectOne(queryWrapper);
            if (islandHotelOrder == null) {
                return ResponseApi.error("岛屿酒店订单不存在");
            }
            if (islandHotelOrder.getOrderStatus() == OrderStatusEnum.WAIT_SALE_CHECK.getCode()) {
                QueryWrapper<MainOrder> mainOrderQueryWrapper = new QueryWrapper<>();
                mainOrderQueryWrapper.eq("island_hotel_order_id", req.getIslandHotelOrderId());
                MainOrder mainOrder = mainOrderMapper.selectOne(mainOrderQueryWrapper);
                if (req.getCheckStatus() == 1) {
                    islandHotelOrder.setOrderStatus(OrderStatusEnum.WAIT_FINANCIAL_CHECK.getCode());
                    islandHotelOrder.setCostPrice(req.getCostPrice());
                    islandHotelOrder.setDiscount(req.getDiscount());
                    islandHotelOrder.setBookingCode(req.getBookingCode());
                    islandHotelOrder.setFinancialMan(req.getFinancialMan());
                    // 计算金额
                    islandHotelOrder
                            .setDiscountPrice(islandHotelOrder.getTotalPrice() - islandHotelOrder.getDiscount());
                    // todo 主订单金额重新计算
                } else {
                    islandHotelOrder.setOrderStatus(OrderStatusEnum.WAIT_AGENT_RESUBMIT.getCode());
                    islandHotelOrder.setRemarks(req.getCheckRemark());
                }
                if (mainOrder != null) {
                    mainOrder.setIslandHotelOrderStatus(islandHotelOrder.getOrderStatus());
                    Integer orderStatus = mainOrder.getIslandHotelOrderStatus();
                    // 计算三个值中最小的，需要判空
                    if (mainOrder.getPlaneTicketOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getPlaneTicketOrderStatus(), orderStatus);
                    }
                    if (mainOrder.getTransitionHotelOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getTransitionHotelOrderStatus(), orderStatus);
                    }
                    mainOrder.setOrderStatus(orderStatus);
                    mainOrder.setFinancialMan(req.getFinancialMan());
                    mainOrderMapper.updateById(mainOrder);
                }
                islandHotelOrderMapper.updateById(islandHotelOrder);
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
    public ResponseApi updateIslandHotelOrderByFinancialMan(IslandHotelOrderFinancialManReq req) {
        if (req.getCheckStatus() != null) {
            QueryWrapper<IslandHotelOrder> queryWrapper = new QueryWrapper<>();
            if (req.getIslandHotelOrderId() == null) {
                return ResponseApi.error("请选择岛屿酒店订单");
            }
            queryWrapper.eq("island_hotel_order_id", req.getIslandHotelOrderId());
            IslandHotelOrder islandHotelOrder = islandHotelOrderMapper.selectOne(queryWrapper);
            if (islandHotelOrder == null) {
                return ResponseApi.error("岛屿酒店订单不存在");
            }
            if (islandHotelOrder.getOrderStatus() == OrderStatusEnum.WAIT_FINANCIAL_CHECK.getCode()) {
                QueryWrapper<MainOrder> mainOrderQueryWrapper = new QueryWrapper<>();
                mainOrderQueryWrapper.eq("island_hotel_order_id", islandHotelOrder.getIslandHotelOrderId());
                MainOrder mainOrder = mainOrderMapper.selectOne(mainOrderQueryWrapper);
                if (req.getCheckStatus() == 1) {
                    islandHotelOrder.setOrderStatus(OrderStatusEnum.WAIT_HOTEL_CONFIRM.getCode());
                    islandHotelOrder.setConfirmInfo(req.getConfirmInfo());
                } else {
                    islandHotelOrder.setOrderStatus(OrderStatusEnum.WAIT_AGENT_RESUBMIT2.getCode());
                    islandHotelOrder.setRemarks(req.getCheckRemark());
                }
                if (mainOrder != null) {
                    mainOrder.setIslandHotelOrderStatus(islandHotelOrder.getOrderStatus());
                    Integer orderStatus = mainOrder.getIslandHotelOrderStatus();
                    // 计算三个值中最小的，需要判空
                    if (mainOrder.getPlaneTicketOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getPlaneTicketOrderStatus(), orderStatus);
                    }
                    if (mainOrder.getTransitionHotelOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getTransitionHotelOrderStatus(), orderStatus);
                    }
                    mainOrder.setOrderStatus(orderStatus);
                    mainOrderMapper.updateById(mainOrder);
                }
                islandHotelOrderMapper.updateById(islandHotelOrder);
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
            QueryWrapper<IslandHotelOrder> queryWrapper = new QueryWrapper<>();
            if (req.getIslandHotelOrderId() == null) {
                return ResponseApi.error("请选择岛屿酒店订单");
            }
            queryWrapper.eq("island_hotel_order_id", req.getIslandHotelOrderId());
            IslandHotelOrder islandHotelOrder = islandHotelOrderMapper.selectOne(queryWrapper);
            if (islandHotelOrder == null) {
                return ResponseApi.error("岛屿酒店订单不存在");
            }
            if (islandHotelOrder.getOrderStatus() == OrderStatusEnum.WAIT_HOTEL_CONFIRM.getCode()) {
                QueryWrapper<MainOrder> mainOrderQueryWrapper = new QueryWrapper<>();
                mainOrderQueryWrapper.eq("island_hotel_order_id", islandHotelOrder.getIslandHotelOrderId());
                MainOrder mainOrder = mainOrderMapper.selectOne(mainOrderQueryWrapper);
                if (req.getCheckStatus() == 1) {
                    islandHotelOrder.setOrderStatus(OrderStatusEnum.HOTEL_CONFIRM_SUCCESS.getCode());
                } else {
                    islandHotelOrder.setOrderStatus(OrderStatusEnum.HOTEL_CONFIRM_FAIL.getCode());
                    islandHotelOrder.setRemarks(req.getCheckRemark());
                }
                if (mainOrder != null) {
                    mainOrder.setIslandHotelOrderStatus(islandHotelOrder.getOrderStatus());
                    Integer orderStatus = mainOrder.getIslandHotelOrderStatus();
                    // 计算三个值中最小的，需要判空
                    if (mainOrder.getPlaneTicketOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getPlaneTicketOrderStatus(), orderStatus);
                    }
                    if (mainOrder.getTransitionHotelOrderId() != null) {
                        orderStatus = Math.min(mainOrder.getTransitionHotelOrderStatus(), orderStatus);
                    }
                    mainOrder.setOrderStatus(orderStatus);
                    mainOrderMapper.updateById(mainOrder);
                }
                islandHotelOrderMapper.updateById(islandHotelOrder);
                return ResponseApi.ok("流程结束");
            } else {
                return ResponseApi.error("未到该流程");
            }
        } else {
            return ResponseApi.error("请选择确认状态");
        }
    }
}
