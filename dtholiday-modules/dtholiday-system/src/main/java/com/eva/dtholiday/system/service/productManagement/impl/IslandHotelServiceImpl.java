package com.eva.dtholiday.system.service.productManagement.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
import com.eva.dtholiday.commons.dao.dto.IslandHotelQueryInfo;
import com.eva.dtholiday.commons.dao.entity.productManagement.ExtraChildExpense;
import com.eva.dtholiday.commons.dao.entity.productManagement.IslandHotel;
import com.eva.dtholiday.commons.dao.mapper.productManagement.ExtraChildExpenseMapper;
import com.eva.dtholiday.commons.dao.mapper.productManagement.IslandHotelMapper;
import com.eva.dtholiday.commons.dao.req.productManagement.*;
import com.eva.dtholiday.commons.dao.resp.productManagement.IslandHotelResp;
import com.eva.dtholiday.commons.utils.DateUtils;
import com.eva.dtholiday.commons.utils.LocalCache;
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
    private ExtraChildExpenseMapper extraChildExpenseMapper;

    @Resource
    private IslandManagementService islandManagementService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi add(IslandHotelReq req) {
        Date startDate = req.getEffectiveDate();
        Date endDate = req.getExpiryDate();
        List<Date> dateList = DateUtils.getDateList(startDate, endDate);
        List<IslandHotel> islandHotels = new ArrayList<>();
        if (dateList.size() > 0) {
            for (int i = 0; i < dateList.size() - 1; i++) {
                IslandHotel islandHotel = new IslandHotel();
                BeanUtils.copyProperties(req, islandHotel);
                islandHotel.setRemainNum(req.getTotalNum());
                islandHotel.setIslandHotelId(null);
                islandHotel.setEffectiveDate(dateList.get(i));
                islandHotel.setExpiryDate(dateList.get(i + 1));
                islandHotels.add(islandHotel);
            }
        }
        if (islandHotels.size() > 0) {
            islandHotelMapper.batchInsert(islandHotels);
        }
        IslandHotelResp resp = new IslandHotelResp();
        BeanUtils.copyProperties(islandHotels.get(0), resp);
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
        if (StringUtils.hasText(req.getIslandHotelName())) {
            queryWrapper.like(IslandHotel.ISLAND_HOTEL_NAME, req.getIslandHotelName());
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
            return ResponseApi.ok(resp);
        }
        return ResponseApi.error("no islandHotel found");
    }

    @Override
    public ResponseApi queryAllHotelList(IslandHotelQueryAllReq req) {

        List<IslandHotelQueryInfo> islandHotelQueryInfos = islandHotelMapper.queryAllHotelInfo(req);
        Integer nights = DateUtils.getDateNum(req.getEffectiveDate(), req.getExpiryDate());
        if (islandHotelQueryInfos.size() > 0) {
            islandHotelQueryInfos = islandHotelQueryInfos.stream().filter(info -> info.getRemainingDays() >= nights)
                .collect(Collectors.toList());
            islandHotelQueryInfos.forEach(info -> {
                info.setMealName(LocalCache.getMealNameById(info.getMealType()));
                info.setTrafficName(LocalCache.getTrafficNameById(info.getTrafficType()));
                info.setIslandIndexCode(req.getIslandIndexCode());
                info.setEffectiveDate(req.getEffectiveDate());
                info.setExpiryDate(req.getExpiryDate());
                // 根据岛屿编码、房型、餐型、交通类型、延住房型、开始结束时间查
                QueryWrapper<IslandHotel> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq(IslandHotel.ISLAND_INDEX_CODE, info.getIslandIndexCode());
                queryWrapper.ge(IslandHotel.EFFECTIVE_DATE, info.getEffectiveDate());
                queryWrapper.le(IslandHotel.EXPIRY_DATE, info.getExpiryDate());
                queryWrapper.eq(IslandHotel.HOTEL_ROOM_TYPE, info.getHotelRoomType());
                queryWrapper.eq(IslandHotel.MEAL_TYPE, info.getMealType());
                queryWrapper.eq(IslandHotel.TRAFFIC_TYPE, info.getTrafficType());
                queryWrapper.eq(IslandHotel.DELAY_HOTEL_ROOM_TYPE, info.getDelayHotelRoomType());
                queryWrapper.orderByAsc(IslandHotel.ISLAND_HOTEL_ID);
                List<IslandHotel> islandHotels = islandHotelMapper.selectList(queryWrapper);
                info.setIslandHotelId(islandHotels.get(0).getIslandHotelId());
            });
            return ResponseApi.ok(islandHotelQueryInfos);
        }
        return ResponseApi.ok(Collections.emptyList());
        // Map<String, Object> queryMap = new HashMap<>();
        // queryMap.put("islandIndexCode", req.getIslandIndexCode());
        // queryMap.put("effectiveDate", req.getEffectiveDate());
        // queryMap.put("expiryDate", req.getExpiryDate());
        //
        // List<IslandHotelMainOrder> islandHotelMainOrders = islandHotelMapper.queryAllHotelInfo(queryMap);
        // // 封装数据
        // Map<Integer, Map<Date, IslandHotel>> map = new HashMap<>();
        // List<IslandHotelMainOrderResp> collect = islandHotelMainOrders.stream().map(entity -> {
        //
        // IslandHotelMainOrderResp resp = new IslandHotelMainOrderResp();
        // resp.setHotelRomType(entity.getHotelRoomType());
        // resp.setDelayHotelRoomType(entity.getDelayHotelRoomType());
        // resp.setIslandIndexCode(entity.getIslandIndexCode());
        // resp.setIslandHotelId(entity.getIslandHotelId());
        // resp.setIslandCnName(entity.getIslandCnName());
        // resp.setTrafficIndexCode(entity.getTrafficType());
        // resp.setTrafficName(entity.getTrafficName());
        // resp.setMealName(entity.getMealName());
        // resp.setMealIndexCode(entity.getMealType());
        // resp.setMealDesc(entity.getRemarks());
        // resp.setHasEnvironmentTax(entity.getHasEnvironmentTax());
        // return resp;
        // }).collect(Collectors.toList());
        // return ResponseApi.ok(collect);
    }

    @Override
    public ResponseApi calculateIslandHotelAmount(IslandHotelCalculateReq req) {

        IslandHotel islandHotel = islandHotelMapper.selectById(req.getIslandHotelId());
        long nights = calTwoDayBetween(req.getEffectiveDate(), req.getExpiryDate());
        long leftNights = nights - 4 < 0 ? 0 : nights - 4;
        BigDecimal totalPrice = new BigDecimal(0);

        // 计算两人价格
        BigDecimal packagePrice = new BigDecimal(islandHotel.getPackagePrice());
        BigDecimal extraExpense = new BigDecimal(islandHotel.getExtraExpense());
        BigDecimal delayHotelRoomPrice = new BigDecimal(islandHotel.getDelayHotelRoomPrice());
        BigDecimal twoPersonPrice =
            extraExpense.add(packagePrice).add(delayHotelRoomPrice.multiply(BigDecimal.valueOf(leftNights)));
        // 多余人数价格
        BigDecimal morePersonPrice = new BigDecimal(0);
        if (req.getAdultNum() > 2) {
            int moreAdultNum = req.getAdultNum() - 2;
            BigDecimal extraAdultPackagePrice = new BigDecimal(islandHotel.getExtraAdultPackagePrice());
            BigDecimal multiply = extraAdultPackagePrice.multiply(BigDecimal.valueOf(moreAdultNum));
            BigDecimal extraAdultDelayHotelRoomPrice = new BigDecimal(islandHotel.getExtraAdultDelayHotelRoomPrice());

            BigDecimal multiply1 = extraAdultDelayHotelRoomPrice.multiply(BigDecimal.valueOf(leftNights));
            morePersonPrice = extraExpense.multiply(morePersonPrice).add(multiply).add(multiply1);
        }
        // 儿童1价格
        BigDecimal firstChildPrice = new BigDecimal(0);
        if (req.getFirstChildAge() > 0) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.le("start_age", req.getFirstChildAge());
            queryWrapper.ge("end_age", req.getFirstChildAge());
            ExtraChildExpense extraChildExpense = extraChildExpenseMapper.selectOne(queryWrapper);
            if (Objects.nonNull(extraChildExpense)) {
                BigDecimal extraChildCharge = new BigDecimal(extraChildExpense.getExtraChildCharge());
                BigDecimal price = new BigDecimal(extraChildExpense.getPrice());
                BigDecimal extraChildDelayHotelPrice = new BigDecimal(extraChildExpense.getExtraChildDelayHotelPrice());
                firstChildPrice =
                    extraChildCharge.add(price).add(extraChildDelayHotelPrice.multiply(BigDecimal.valueOf(leftNights)));
            }
        }
        // 儿童2价格
        BigDecimal secondChildPrice = new BigDecimal(0);

        if (req.getSecondChildAge() > 0) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.le("start_age", req.getSecondChildAge());
            queryWrapper.ge("end_age", req.getSecondChildAge());
            ExtraChildExpense extraChildExpense = extraChildExpenseMapper.selectOne(queryWrapper);
            if (Objects.nonNull(extraChildExpense)) {
                BigDecimal extraChildCharge = new BigDecimal(extraChildExpense.getExtraChildCharge());
                BigDecimal price = new BigDecimal(extraChildExpense.getPrice());
                BigDecimal extraChildDelayHotelPrice = new BigDecimal(extraChildExpense.getExtraChildDelayHotelPrice());
                secondChildPrice =
                    extraChildCharge.add(price).add(extraChildDelayHotelPrice.multiply(BigDecimal.valueOf(leftNights)));
            }
        }
        BigDecimal total =
            totalPrice.add(twoPersonPrice).add(morePersonPrice).add(firstChildPrice).add(secondChildPrice);
        return ResponseApi.ok(total);
    }

    private long calTwoDayBetween(Date startTime, Date endTime) {
        return ChronoUnit.DAYS.between(convertToLocalDate(startTime), convertToLocalDate(endTime));
    }

    // 将 Date 转换为 LocalDate 的方法
    private static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
