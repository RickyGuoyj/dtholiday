package com.eva.dtholiday.system.service.convert;

import java.sql.Timestamp;

import com.alibaba.fastjson.JSONObject;
import com.eva.dtholiday.commons.dao.entity.orderManagement.CustomerInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.islandhotelorder.IslandHotelInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.islandhotelorder.IslandHotelOrder;
import com.eva.dtholiday.commons.dao.entity.orderManagement.mainorder.MainOrder;
import com.eva.dtholiday.commons.dao.entity.orderManagement.planeTicket.PlaneTicketInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.planeTicket.PlaneTicketOrder;
import com.eva.dtholiday.commons.dao.entity.orderManagement.transitionHotel.TransitionHotelInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.transitionHotel.TransitionHotelOrder;
import com.eva.dtholiday.commons.dao.req.orderManagement.IslandHotelOrderReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.MainOrderReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.PlaneTicketOrderReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.TransitionHotelOrderReq;

/**
 * 订单转换类
 */
public class OrderConvert {

    public static IslandHotelOrder convertIslandHotelInfoToEntity(IslandHotelOrderReq req, String userName) {
        IslandHotelOrder islandHotelOrder = new IslandHotelOrder();
        islandHotelOrder.setOrderType(req.getOrderType());
        CustomerInfo customerInfo = req.getCustomerInfo();
        islandHotelOrder.setCustomerName(customerInfo.getCustomerName());
        islandHotelOrder.setNights(customerInfo.getNights());
        islandHotelOrder.setAdultNum(customerInfo.getAdultNum());
        islandHotelOrder.setChildNum(customerInfo.getChildNum());
        islandHotelOrder.setSecondChildAge(customerInfo.getSecondChildAge());
        islandHotelOrder.setFirstChildAge(customerInfo.getFirstChildAge());
        IslandHotelInfo hotelInfo = req.getHotelInfo();
        islandHotelOrder.setIslandHotelId(hotelInfo.getIslandHotelId());
        islandHotelOrder.setEffectiveDate(hotelInfo.getEffectiveDate());
        islandHotelOrder.setExpiryDate(hotelInfo.getExpiryDate());
        islandHotelOrder.setTotalPrice(req.getTotalPrice());
        islandHotelOrder.setCurrencyType(req.getCurrencyType());
        islandHotelOrder.setSaleMan(req.getSaleMan());
        islandHotelOrder.setOrderCreator(userName);
        islandHotelOrder.setCreateTime(new Timestamp(System.currentTimeMillis()));
        islandHotelOrder.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return islandHotelOrder;
    }

    /**
     * 机票订单info转数据库
     *
     * @param req info
     * @return entity
     */
    public static PlaneTicketOrder convertPlaneTicketInfoToEntity(PlaneTicketOrderReq req, String userName) {
        PlaneTicketOrder planeTicketOrder = new PlaneTicketOrder();
        CustomerInfo customerInfo = req.getCustomerInfo();
        planeTicketOrder.setCustomerName(customerInfo.getCustomerName());
        planeTicketOrder.setAdultNum(customerInfo.getAdultNum());
        planeTicketOrder.setChildNum(customerInfo.getChildNum());
        PlaneTicketInfo planeTicketInfo = req.getPlaneTicketInfo();

        planeTicketOrder.setPlaneTicketId(planeTicketInfo.getPlaneTicketId());
        planeTicketOrder.setAirlineCompanyName(planeTicketInfo.getAirlineCompanyName());
        planeTicketOrder.setDepartureDate(planeTicketInfo.getDepartureDate());
        planeTicketOrder.setReturnDate(planeTicketInfo.getReturnDate());
        planeTicketOrder.setDays(planeTicketInfo.getDays());
        planeTicketOrder.setDepartureFlight(planeTicketInfo.getDepartureFlight());
        planeTicketOrder.setDeparturePlace(planeTicketInfo.getDeparturePlace());
        planeTicketOrder.setTicketNumber(planeTicketInfo.getTicketNumber());
        planeTicketOrder.setReturnFlight(planeTicketInfo.getReturnFlight());
        planeTicketOrder.setArrivalPlace(planeTicketInfo.getArrivalPlace());
        planeTicketOrder.setPrice(planeTicketInfo.getPrice());
        planeTicketOrder.setTotalPrice(req.getTotalPrice());
        planeTicketOrder.setCurrencyType(req.getCurrencyType());
        planeTicketOrder.setOrderType(req.getOrderType());
        planeTicketOrder.setSaleMan(req.getSaleMan());
        planeTicketOrder.setOrderCreator(userName);
        planeTicketOrder.setCreateTime(new Timestamp(System.currentTimeMillis()));
        planeTicketOrder.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return planeTicketOrder;
    }

    public static TransitionHotelOrder convertTransitionHotelInfoToEntity(TransitionHotelOrderReq req,
        String userName) {
        TransitionHotelOrder transitionHotelOrder = new TransitionHotelOrder();
        CustomerInfo customerInfo = req.getCustomerInfo();
        transitionHotelOrder.setCustomerName(customerInfo.getCustomerName());
        transitionHotelOrder.setAdultNum(customerInfo.getAdultNum());
        transitionHotelOrder.setChildNum(customerInfo.getChildNum());
        transitionHotelOrder.setRoomNum(customerInfo.getRoomNum());
        transitionHotelOrder.setNights(customerInfo.getNights());
        TransitionHotelInfo transitionHotelInfo = req.getTransitionHotelInfo();

        transitionHotelOrder.setTransitionHotelId(transitionHotelInfo.getTransitionHotelId());
        transitionHotelOrder.setTransitionHotelName(transitionHotelInfo.getTransitionHotelName());
        transitionHotelOrder.setTransitionHotelType(transitionHotelInfo.getTransitionHotelType());
        transitionHotelOrder.setEffectiveDate(transitionHotelInfo.getEffectiveDate());
        transitionHotelOrder.setExpiryDate(transitionHotelInfo.getExpiryDate());
        transitionHotelOrder.setTotalPrice(req.getTotalPrice());
        transitionHotelOrder.setCurrencyType(req.getCurrencyType());
        transitionHotelOrder.setOrderType(req.getOrderType());
        transitionHotelOrder.setSaleMan(req.getSaleMan());
        transitionHotelOrder.setOrderCreator(userName);
        transitionHotelOrder.setCreateTime(new Timestamp(System.currentTimeMillis()));
        transitionHotelOrder.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return transitionHotelOrder;
    }

    public static MainOrder convertMainOrderInfoToEntity(MainOrderReq req, String userName, Integer islandOrderId,
        Integer planeTicketOrderId, Integer transitionHotelOrderId) {
        MainOrder mainOrder = new MainOrder();
        mainOrder.setIslandOrderId(islandOrderId);
        mainOrder.setPlaneTicketOrderId(planeTicketOrderId);
        mainOrder.setTransitionHotelOrderId(transitionHotelOrderId);
        mainOrder.setOrderCreator(userName);
        mainOrder.setTotalPrice(JSONObject.toJSONString(req.getTotalPrice()));
        mainOrder.setSaleMan(req.getSaleMan());
        mainOrder.setCreateTime(new Timestamp(System.currentTimeMillis()));
        mainOrder.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return mainOrder;
    }
}
