package com.eva.dtholiday.system.service.productManagement.impl;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.productManagement.IslandHotel;
import com.eva.dtholiday.commons.dao.entity.productManagement.IslandHotelMainOrder;
import com.eva.dtholiday.commons.dao.mapper.productManagement.IslandHotelMapper;
import com.eva.dtholiday.commons.dao.req.productManagement.IslandHotelPageReq;
import com.eva.dtholiday.commons.dao.req.productManagement.IslandHotelQueryAllReq;
import com.eva.dtholiday.commons.dao.req.productManagement.IslandHotelQueryReq;
import com.eva.dtholiday.commons.dao.req.productManagement.IslandHotelReq;
import com.eva.dtholiday.commons.dao.resp.productManagement.IslandHotelMainOrderResp;
import com.eva.dtholiday.commons.dao.resp.productManagement.IslandHotelResp;
import com.eva.dtholiday.system.service.portalmanagement.IslandManagementService;
import com.eva.dtholiday.system.service.productManagement.IslandHotelService;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/30 1:26
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Service
public class IslandHotelServiceImpl implements IslandHotelService {

    @Resource
    private IslandHotelMapper islandHotelMapper;

    @Resource
    private IslandManagementService islandManagementService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi add(IslandHotelReq req) {
        IslandHotel islandHotel = new IslandHotel();
        BeanUtils.copyProperties(req, islandHotel);
        islandHotel.setRemainNum(req.getTotalNum());
        islandHotel.setIslandHotelId(null);
        islandHotelMapper.insert(islandHotel);
        IslandHotelResp resp = new IslandHotelResp();
        BeanUtils.copyProperties(islandHotel, resp);
        return ResponseApi.ok(resp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi delete(IslandHotelQueryReq req) {
        // todo 订单管理做完之后，这里需要去查是否有关联订单
        islandHotelMapper.deleteBatchIds(req.getIslandHotelIdList());
        return ResponseApi.ok(req);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi update(IslandHotelReq req) {
        if (req.getIslandHotelId() == null) {
            return ResponseApi.error("islandHotelId is null");
        }
        // 查出当前的记录
        IslandHotel islandHotel = islandHotelMapper.selectById(req.getIslandHotelId());
        if (islandHotel != null) {
            int oldTotalNum = islandHotel.getTotalNum();
            int oldRemainNum = islandHotel.getRemainNum();
            BeanUtils.copyProperties(req, islandHotel);
            if (req.getTotalNum() != null) {
                islandHotel.setRemainNum(Math.max((req.getTotalNum() - oldTotalNum + oldRemainNum), 0));
            }
            islandHotelMapper.updateById(islandHotel);
            IslandHotelResp resp = new IslandHotelResp();
            BeanUtils.copyProperties(islandHotel, resp);
            return ResponseApi.ok(resp);
        }
        return ResponseApi.error("no islandHotel found");
    }

    @Override
    public ResponseApi queryList(IslandHotelPageReq req) {
        IPage<IslandHotel> entityPage = new Page<>(req.getCurrent(), req.getSize());
        QueryWrapper<IslandHotel> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(req.getIslandCnName())) {
            queryWrapper.like(IslandHotel.ISLAND_CN_NAME, req.getIslandCnName());
        }
        if (Objects.nonNull(req.getEffectiveDate())) {
            queryWrapper.ge(IslandHotel.EFFECTIVE_DATE, req.getEffectiveDate());
        }
        if (Objects.nonNull(req.getExpiryDate())) {
            queryWrapper.le(IslandHotel.EXPIRY_DATE, req.getExpiryDate());
        }
        if (StringUtils.hasText(req.getHotelRoomType())) {
            queryWrapper.like(IslandHotel.HOTEL_ROOM_TYPE, req.getHotelRoomType());
        }
        entityPage = islandHotelMapper.selectPage(entityPage, queryWrapper);
        if (Objects.isNull(entityPage)) {
            return ResponseApi.ok(new Page<>(req.getCurrent(), 0));
        }
        List<IslandHotelResp> respList = entityPage.getRecords().stream().map(entity -> {
            IslandHotelResp resp = new IslandHotelResp();
            BeanUtils.copyProperties(entity, resp);
            resp.setIslandCnName(islandManagementService.getIslandName(entity.getIslandIndexCode()));
            return resp;
        }).collect(Collectors.toList());
        IPage<IslandHotelResp> respPage = new Page<>(req.getCurrent(), req.getSize());
        respPage.setRecords(respList);
        respPage.setTotal(entityPage.getTotal());
        return ResponseApi.ok(respPage);
    }

    @Override
    public ResponseApi queryDetail(IslandHotelQueryReq req) {
        if (req.getIslandHotelId() == null) {
            return ResponseApi.error("islandHotelId is null");
        }
        // 查出当前的记录
        IslandHotel islandHotel = islandHotelMapper.selectById(req.getIslandHotelId());
        if (islandHotel != null) {
            IslandHotelResp resp = new IslandHotelResp();
            BeanUtils.copyProperties(islandHotel, resp);
            resp.setIslandCnName(islandManagementService.getIslandName(islandHotel.getIslandIndexCode()));
            return ResponseApi.ok(resp);
        }
        return ResponseApi.error("no islandHotel found");
    }


    @Override
    public ResponseApi queryAllHotelList(IslandHotelQueryAllReq req) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("islandIndexCode", req.getIslandIndexCode());
        queryMap.put("effectiveDate",req.getEffectiveDate());
        queryMap.put("expiryDate",req.getExpiryDate());

        List<IslandHotelMainOrder> islandHotelMainOrders = islandHotelMapper.queryAllHotelInfo(queryMap);
        // 封装数据
        Map<Integer, Map<Date, IslandHotel>> map = new HashMap<>();
        List<IslandHotelMainOrderResp> collect = islandHotelMainOrders.stream().map(entity -> {

            IslandHotelMainOrderResp resp = new IslandHotelMainOrderResp();
            resp.setHotelRomType(entity.getHotelRoomType());
            resp.setDelayHotelRoomType(entity.getDelayHotelRoomType());
            resp.setIslandIndexCode(entity.getIslandIndexCode());
            resp.setIslandHotelId(entity.getIslandHotelId());
            resp.setIslandCnName(entity.getIslandCnName());
            resp.setTrafficIndexCode(entity.getTrafficType());
            resp.setTrafficName(entity.getTrafficName());
            resp.setMealName(entity.getMealName());
            resp.setMealIndexCode(entity.getMealType());
            resp.setMealDesc(entity.getRemarks());
            resp.setHasEnvironmentTax(entity.getHasEnvironmentTax());
            return resp;
        }).collect(Collectors.toList());
        return ResponseApi.ok(collect);
    }
}
