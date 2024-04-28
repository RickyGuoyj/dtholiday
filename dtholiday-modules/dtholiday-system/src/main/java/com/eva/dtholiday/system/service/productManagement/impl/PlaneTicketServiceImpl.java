package com.eva.dtholiday.system.service.productManagement.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.productManagement.PlaneTicket;
import com.eva.dtholiday.commons.dao.entity.productManagement.TransitionHotel;
import com.eva.dtholiday.commons.dao.mapper.productManagement.PlaneTicketMapper;
import com.eva.dtholiday.commons.dao.req.productManagement.PlaneTicketPageReq;
import com.eva.dtholiday.commons.dao.req.productManagement.PlaneTicketQueryReq;
import com.eva.dtholiday.commons.dao.req.productManagement.PlaneTicketReq;
import com.eva.dtholiday.commons.dao.resp.productManagement.PlaneTicketResp;
import com.eva.dtholiday.commons.dao.resp.productManagement.TransitionHotelResp;
import com.eva.dtholiday.system.service.productManagement.PlaneTicketService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/27 21:54
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Service
public class PlaneTicketServiceImpl implements PlaneTicketService {


    @Resource
    private PlaneTicketMapper planeTicketMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi add(PlaneTicketReq req) {
        PlaneTicket planeTicket = new PlaneTicket();
        BeanUtils.copyProperties(req, planeTicket);
        planeTicketMapper.insert(planeTicket);
        PlaneTicketResp resp = new PlaneTicketResp();
        BeanUtils.copyProperties(planeTicket, resp);
        return ResponseApi.ok(resp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi delete(PlaneTicketQueryReq req) {
        // todo 订单管理做完之后，这里需要去查是否有关联订单
        planeTicketMapper.deleteBatchIds(req.getPlaneTicketIdList());
        return ResponseApi.ok(req);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi update(PlaneTicketReq req) {
        if (req.getPlaneTicketId() == null) {
            return ResponseApi.error("planeTicketId is null");
        }
        // 查出当前的记录
        PlaneTicket planeTicket = planeTicketMapper.selectById(req.getPlaneTicketId());
        if (planeTicket != null) {
            int oldTotalNum = planeTicket.getTotalNum();
            int oldRemainNum = planeTicket.getRemainNum();
            BeanUtils.copyProperties(req, planeTicket);
            if (req.getTotalNum() != null) {
                planeTicket.setRemainNum(Math.max((req.getTotalNum() - oldTotalNum + oldRemainNum), 0));
            }
            planeTicketMapper.updateById(planeTicket);
            PlaneTicketResp resp = new PlaneTicketResp();
            BeanUtils.copyProperties(planeTicket, resp);
            return ResponseApi.ok(resp);
        }
        return ResponseApi.error("no planeTicket found");
    }

    @Override
    public ResponseApi queryList(PlaneTicketPageReq req) {
        IPage<PlaneTicket> entityPage = new Page<>(req.getCurrent(), req.getSize());
        QueryWrapper<PlaneTicket> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(req.getAirlineCompanyName())) {
            queryWrapper.like(PlaneTicket.AIRLINE_COMPANY_NAME, req.getAirlineCompanyName());
        }
        if (StringUtils.hasText(req.getDeparturePlace())) {
            queryWrapper.like(PlaneTicket.DEPARTURE_PLACE, req.getDeparturePlace());
        }
        if (StringUtils.hasText(req.getArrivalPlace())) {
            queryWrapper.like(PlaneTicket.ARRIVAL_PLACE, req.getArrivalPlace());
        }
        if (Objects.nonNull(req.getDepartureDate())) {
            queryWrapper.eq(PlaneTicket.DEPARTURE_DATE, req.getDepartureDate());
        }
        if (Objects.nonNull(req.getReturnDate())) {
            queryWrapper.eq(PlaneTicket.RETURN_DATE, req.getReturnDate());
        }
        if (StringUtils.hasText(req.getReturnFlight())){
            queryWrapper.eq(PlaneTicket.RETURN_FLIGHT, req.getReturnFlight());
        }
        if (StringUtils.hasText(req.getDepartureFlight())){
            queryWrapper.eq(PlaneTicket.DEPARTURE_FLIGHT, req.getDepartureFlight());
        }
        entityPage = planeTicketMapper.selectPage(entityPage, queryWrapper);
        if (Objects.isNull(entityPage)) {
            return ResponseApi.ok(new Page<>(req.getCurrent(), 0));
        }
        List<PlaneTicketResp> respList = entityPage.getRecords().stream().map(entity -> {
            PlaneTicketResp resp = new PlaneTicketResp();
            BeanUtils.copyProperties(entity, resp);
            return resp;
        }).collect(Collectors.toList());
        IPage<PlaneTicketResp> respPage = new Page<>(req.getCurrent(), req.getSize());
        respPage.setRecords(respList);
        respPage.setTotal(entityPage.getTotal());
        return ResponseApi.ok(respPage);
    }

    @Override
    public ResponseApi queryDetail(PlaneTicketQueryReq req) {
        if (req.getPlaneTicketId() == null) {
            return ResponseApi.error("planeTicketId is null");
        }
        // 查出当前的记录
        PlaneTicket planeTicket = planeTicketMapper.selectById(req.getPlaneTicketId());
        if (planeTicket != null) {
            PlaneTicketResp resp = new PlaneTicketResp();
            BeanUtils.copyProperties(planeTicket, resp);
            return ResponseApi.ok(resp);
        }
        return ResponseApi.error("no planeTicket found");
    }
}
