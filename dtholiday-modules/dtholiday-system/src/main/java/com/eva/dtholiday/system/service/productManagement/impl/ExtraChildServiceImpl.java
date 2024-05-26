package com.eva.dtholiday.system.service.productManagement.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.productManagement.ExtraChildExpense;
import com.eva.dtholiday.commons.dao.entity.productManagement.TransitionHotel;
import com.eva.dtholiday.commons.dao.mapper.productManagement.ExtraChildExpenseMapper;
import com.eva.dtholiday.commons.dao.req.productManagement.ExtraChildExpensePageReq;
import com.eva.dtholiday.commons.dao.req.productManagement.ExtraChildExpenseQueryReq;
import com.eva.dtholiday.commons.dao.req.productManagement.ExtraChildExpenseReq;
import com.eva.dtholiday.commons.dao.resp.productManagement.ExtraChildExpenseResp;
import com.eva.dtholiday.commons.dao.resp.productManagement.TransitionHotelResp;
import com.eva.dtholiday.system.service.portalmanagement.IslandManagementService;
import com.eva.dtholiday.system.service.productManagement.ExtraChildService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/28 1:39
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Service
public class ExtraChildServiceImpl implements ExtraChildService {

    @Resource
    private ExtraChildExpenseMapper extraChildExpenseMapper;

    @Resource
    private IslandManagementService islandManagementService;

    @Override
    public ResponseApi add(ExtraChildExpenseReq req) {
        ExtraChildExpense extraChildExpense = new ExtraChildExpense();
        BeanUtils.copyProperties(req, extraChildExpense);
        extraChildExpense.setExtraChildExpenseId(null);
        extraChildExpenseMapper.insert(extraChildExpense);

        ExtraChildExpenseResp extraChildExpenseResp = new ExtraChildExpenseResp();
        BeanUtils.copyProperties(extraChildExpense, extraChildExpenseResp);
        return ResponseApi.ok(extraChildExpense);
    }

    @Override
    public ResponseApi update(ExtraChildExpenseReq req) {
        if (req.getExtraChildExpenseId() == null) {
            return ResponseApi.error("extraChildExpenseId is null");
        }
        ExtraChildExpense extraChildExpense = extraChildExpenseMapper.selectById(req.getExtraChildExpenseId());
        BeanUtils.copyProperties(req, extraChildExpense);
        extraChildExpenseMapper.updateById(extraChildExpense);
        ExtraChildExpenseResp extraChildExpenseResp = new ExtraChildExpenseResp();
        BeanUtils.copyProperties(extraChildExpense, extraChildExpenseResp);
        return ResponseApi.ok(extraChildExpense);
    }


    @Override
    public ResponseApi delete(ExtraChildExpenseQueryReq req) {
        // todo 订单管理做完之后，这里需要去查是否有关联订单
        extraChildExpenseMapper.deleteBatchIds(req.getExtraChildExpenseIdList());
        return ResponseApi.ok(req);
    }

    @Override
    public ResponseApi queryList(ExtraChildExpensePageReq req) {
        Page<ExtraChildExpense> entityPage = new Page<>(req.getCurrent(), req.getSize());

        entityPage = extraChildExpenseMapper.selectPage(entityPage, null);
        if (Objects.isNull(entityPage)) {
            return ResponseApi.ok(new Page<>(req.getCurrent(), 0));
        }
        List<ExtraChildExpenseResp> respList = entityPage.getRecords().stream().map(entity -> {
            ExtraChildExpenseResp resp = new ExtraChildExpenseResp();
            BeanUtils.copyProperties(entity, resp);
            resp.setIslandCnName(islandManagementService.getIslandName(entity.getIslandIndexCode()));
            return resp;
        }).collect(Collectors.toList());
        Page<ExtraChildExpenseResp> respPage = new Page<>(req.getCurrent(), req.getSize());
        respPage.setRecords(respList);
        respPage.setTotal(entityPage.getTotal());
        return ResponseApi.ok(respPage);
    }

    @Override
    public ResponseApi queryDetail(ExtraChildExpenseQueryReq req) {
        if (req.getExtraChildExpenseId() == null) {
            return ResponseApi.error("extraChildExpenseId is null");
        }
        // 查出当前的记录
        ExtraChildExpense extraChildExpense = extraChildExpenseMapper.selectById(req.getExtraChildExpenseId());
        if (extraChildExpense != null) {
            ExtraChildExpenseResp resp = new ExtraChildExpenseResp();
            BeanUtils.copyProperties(extraChildExpense, resp);
            resp.setIslandCnName(islandManagementService.getIslandName(extraChildExpense.getIslandIndexCode()));
            return ResponseApi.ok(resp);
        }
        return ResponseApi.error("no extraChildExpense found");
    }
}
