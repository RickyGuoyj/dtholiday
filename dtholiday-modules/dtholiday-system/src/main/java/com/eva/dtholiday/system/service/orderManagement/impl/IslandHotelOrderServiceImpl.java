package com.eva.dtholiday.system.service.orderManagement.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eva.dtholiday.commons.dao.entity.orderManagement.islandhotelorder.IslandHotelOrder;
import com.eva.dtholiday.commons.dao.entity.orderManagement.mainorder.MainOrder;
import com.eva.dtholiday.commons.dao.entity.orderManagement.planeTicket.PlaneTicketOrder;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.MainOrderMapper;
import com.eva.dtholiday.commons.dao.req.orderManagement.*;
import com.eva.dtholiday.commons.enums.OrderStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.orderManagement.islandhotelorder.IslandHotelOrderInfo;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.IslandHotelOrderMapper;
import com.eva.dtholiday.commons.dao.resp.orderManagement.IslandHotelOrderQueryListResp;
import com.eva.dtholiday.system.service.orderManagement.IslandHotelOrderService;

import javax.annotation.Resource;

@Service
public class IslandHotelOrderServiceImpl implements IslandHotelOrderService {

    @Resource
    private IslandHotelOrderMapper islandHotelOrderMapper;

    @Resource
    private MainOrderMapper mainOrderMapper;

    public ResponseApi queryIslandHotelOrderList(IslandHotelOrderQueryListReq req) {
        Map<String, Object> map = new HashMap<>();
        map.put("customerName", req.getCustomerName());
        map.put("islandCnName", req.getIslandCnName());
        map.put("orderStatus", req.getOrderStatus());
        map.put("financialStatus", req.getFinancialStatus());
        map.put("islandHotelOrderId",req.getIslandHotelOrderId());
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
    public ResponseApi updateIslandHotelOrderByAgent(IslandHotelOrderReq req) {
        return null;
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
                    islandHotelOrder.setDiscountPrice(islandHotelOrder.getTotalPrice() - islandHotelOrder.getDiscount());
                    // todo 主订单金额重新计算
                } else {
                    islandHotelOrder.setOrderStatus(OrderStatusEnum.WAIT_AGENT_RESUBMIT.getCode());
                    islandHotelOrder.setRemarks(req.getCheckRemark());
                }
                if (mainOrder != null) {
                    //计算三个值中最小的
                    mainOrder.setOrderStatus(Math.min(Math.min(mainOrder.getIslandOrderStatus(), mainOrder.getTransitionHotelOrderStatus()), islandHotelOrder.getOrderStatus()));
                }
                islandHotelOrderMapper.updateById(islandHotelOrder);
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
                    //计算三个值中最小的
                    mainOrder.setOrderStatus(Math.min(Math.min(mainOrder.getIslandOrderStatus(), mainOrder.getTransitionHotelOrderStatus()), islandHotelOrder.getOrderStatus()));
                }
                islandHotelOrderMapper.updateById(islandHotelOrder);
                mainOrderMapper.updateById(mainOrder);
                return ResponseApi.ok("审核成功");
            } else {
                return ResponseApi.error("未到你的流程");
            }
        } else {
            return ResponseApi.error("请选择审核状态");
        }
    }
}
