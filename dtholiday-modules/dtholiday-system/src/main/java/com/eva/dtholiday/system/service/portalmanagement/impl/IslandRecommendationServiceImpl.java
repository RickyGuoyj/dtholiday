package com.eva.dtholiday.system.service.portalmanagement.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandRecommendation;
import com.eva.dtholiday.commons.dao.mapper.portalmanagement.IslandRecommendationMapper;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandRecommendationReq;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandRecommendationQueryListResp;
import com.eva.dtholiday.system.service.portalmanagement.IslandRecommendationService;

@Service
public class IslandRecommendationServiceImpl implements IslandRecommendationService {
    @Resource
    private IslandRecommendationMapper islandRecommendationMapper;

    @Override
    public ResponseApi islandRecommendationAdd(IslandRecommendationReq islandRecommendationReq) {
        int insert = islandRecommendationMapper.insert(convertRecommendationEntity(islandRecommendationReq));
        return ResponseApi.ok(insert);
    }

    private IslandRecommendation convertRecommendationEntity(IslandRecommendationReq islandRecommendationReq) {
        IslandRecommendation islandRecommendation = new IslandRecommendation();
        islandRecommendation.setIslandCnName(islandRecommendationReq.getIslandCnName());
        islandRecommendation.setIslandEnName(islandRecommendationReq.getIslandEnName());
        islandRecommendation.setIslandIndexCode(islandRecommendationReq.getIslandIndexCode());
        islandRecommendation.setRecommendationImage(islandRecommendationReq.getRecommendationImage());
        return islandRecommendation;
    }

    @Override
    public ResponseApi islandRecommendationDelete(int recommendationIndexCode) {
        int delete = islandRecommendationMapper.deleteById(recommendationIndexCode);
        return ResponseApi.ok(delete);
    }

    @Override
    public ResponseApi islandRecommendationUpdate(IslandRecommendationReq islandRecommendationReq) {
        IslandRecommendation islandRecommendation = convertRecommendationEntity(islandRecommendationReq);
        islandRecommendation.setRecommendationIndexCode(islandRecommendationReq.getRecommendationIndexCode());
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        islandRecommendation.setUpdateTime(updateTime);
        int update = islandRecommendationMapper.updateById(islandRecommendation);
        return ResponseApi.ok(update);
    }

    @Override
    public ResponseApi<IslandRecommendation> islandRecommendationQueryDetail(int recommendationIndexCode) {
        IslandRecommendation islandRecommendation = islandRecommendationMapper.selectById(recommendationIndexCode);
        return ResponseApi.ok(islandRecommendation);
    }

    @Override
    public ResponseApi<IslandRecommendationQueryListResp>
        islandRecommendationQueryList(IslandRecommendationReq islandRecommendationReq) {
        Page<IslandRecommendation> page =
            new Page<>(islandRecommendationReq.getPage(), islandRecommendationReq.getPageSize());
        Page<IslandRecommendation> islandRecommendationPage = islandRecommendationMapper.selectPage(page, null);
        if (islandRecommendationPage != null) {
            IslandRecommendationQueryListResp islandRecommendationQueryListResp =
                new IslandRecommendationQueryListResp();
            islandRecommendationQueryListResp.setPage(islandRecommendationReq.getPage());
            islandRecommendationQueryListResp.setPageSize(islandRecommendationReq.getPageSize());
            islandRecommendationQueryListResp.setIslandRecommendationList(islandRecommendationPage.getRecords());
            return ResponseApi.ok(islandRecommendationQueryListResp);
        }
        return ResponseApi.ok();
    }
}