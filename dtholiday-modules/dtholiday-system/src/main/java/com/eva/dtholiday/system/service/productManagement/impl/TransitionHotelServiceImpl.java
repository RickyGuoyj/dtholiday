package com.eva.dtholiday.system.service.productManagement.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.productManagement.TransitionHotel;
import com.eva.dtholiday.commons.dao.mapper.productManagement.TransitionHotelMapper;
import com.eva.dtholiday.commons.dao.req.productManagement.TransitionHotelPageReq;
import com.eva.dtholiday.commons.dao.req.productManagement.TransitionHotelQueryReq;
import com.eva.dtholiday.commons.dao.req.productManagement.TransitionHotelReq;
import com.eva.dtholiday.commons.dao.resp.productManagement.TransitionHotelResp;
import com.eva.dtholiday.system.service.productManagement.TransitionHotelService;
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
 * @create-time 2024/4/27 20:44
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Service
public class TransitionHotelServiceImpl implements TransitionHotelService {

    @Resource
    private TransitionHotelMapper transitionHotelMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi add(TransitionHotelReq req) {
        TransitionHotel transitionHotel = new TransitionHotel();
        BeanUtils.copyProperties(req, transitionHotel);
        transitionHotel.setRemainNum(req.getTotalNum());
        transitionHotel.setTransitionHotelId(null);
        transitionHotelMapper.insert(transitionHotel);
        TransitionHotelResp resp = new TransitionHotelResp();
        BeanUtils.copyProperties(transitionHotel, resp);
        return ResponseApi.ok(resp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi delete(TransitionHotelQueryReq req) {
        // todo 订单管理做完之后，这里需要去查是否有关联订单
        transitionHotelMapper.deleteBatchIds(req.getTransitionHotelIdList());
        return ResponseApi.ok(req);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi update(TransitionHotelReq req) {
        if (req.getTransitionHotelId() == null) {
            return ResponseApi.error("transitionHotelId is null");
        }
        // 查出当前的记录
        TransitionHotel transitionHotel = transitionHotelMapper.selectById(req.getTransitionHotelId());
        if (transitionHotel != null) {
            int oldTotalNum = transitionHotel.getTotalNum();
            int oldRemainNum = transitionHotel.getRemainNum();
            BeanUtils.copyProperties(req, transitionHotel);
            if (req.getTotalNum() != null) {
                transitionHotel.setRemainNum(Math.max((req.getTotalNum() - oldTotalNum + oldRemainNum), 0));
            }
            transitionHotelMapper.updateById(transitionHotel);
            TransitionHotelResp resp = new TransitionHotelResp();
            BeanUtils.copyProperties(transitionHotel, resp);
            return ResponseApi.ok(resp);
        }
        return ResponseApi.error("no transitionHotel found");
    }

    @Override
    public ResponseApi queryList(TransitionHotelPageReq req) {
        Page<TransitionHotel> entityPage = new Page<>(req.getCurrent(), req.getSize());
        QueryWrapper<TransitionHotel> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(req.getTransitionHotelName())) {
            queryWrapper.like(TransitionHotel.TRANSITION_HOTEL_NAME, req.getTransitionHotelName());
        }
        if (StringUtils.hasText(req.getTransitionHotelType())) {
            queryWrapper.like(TransitionHotel.TRANSITION_HOTEL_TYPE, req.getTransitionHotelType());
        }
        entityPage = transitionHotelMapper.selectPage(entityPage, queryWrapper);
        if (Objects.isNull(entityPage)) {
            return ResponseApi.ok(new Page<>(req.getCurrent(), 0));
        }
        List<TransitionHotelResp> respList = entityPage.getRecords().stream().map(entity -> {
            TransitionHotelResp resp = new TransitionHotelResp();
            BeanUtils.copyProperties(entity, resp);
            return resp;
        }).collect(Collectors.toList());
        Page<TransitionHotelResp> respPage = new Page<>(req.getCurrent(), req.getSize());
        respPage.setRecords(respList);
        respPage.setTotal(entityPage.getTotal());
        return ResponseApi.ok(respPage);
    }

    @Override
    public ResponseApi queryDetail(TransitionHotelQueryReq req) {
        if (req.getTransitionHotelId() == null) {
            return ResponseApi.error("transitionHotelId is null");
        }
        // 查出当前的记录
        TransitionHotel transitionHotel = transitionHotelMapper.selectById(req.getTransitionHotelId());
        if (transitionHotel != null) {
            TransitionHotelResp resp = new TransitionHotelResp();
            BeanUtils.copyProperties(transitionHotel, resp);
            return ResponseApi.ok(resp);
        }
        return ResponseApi.error("no transitionHotel found");
    }
}
