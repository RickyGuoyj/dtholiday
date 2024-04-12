package com.eva.dtholiday.system.service.portal.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.*;
import com.eva.dtholiday.commons.dao.mapper.portalmanagement.*;
import com.eva.dtholiday.commons.dao.req.portal.IslandQueryReq;
import com.eva.dtholiday.commons.dao.resp.portal.IslandDetailResp;
import com.eva.dtholiday.commons.dao.resp.portal.IslandListResp;
import com.eva.dtholiday.commons.dao.resp.portal.RecommendIslandResp;
import com.eva.dtholiday.commons.dao.resp.portal.TagResp;
import com.eva.dtholiday.system.service.portal.PortalService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PortalServiceImpl implements PortalService {
    @Resource
    private IslandRecommendationMapper islandRecommendationMapper;

    @Resource
    private IslandManagementMapper islandManagementMapper;

    @Resource
    private IslandTagMapper islandTagMapper;

    @Resource
    private IslandQuotationMapper islandQuotationMapper;

    @Resource
    private IslandTagRelationMapper islandTagRelationMapper;

    @Override
    public ResponseApi<List<RecommendIslandResp>> getRecommendIsland() {
        List<IslandRecommendation> islandRecommendations = islandRecommendationMapper.selectList(null);
        List<RecommendIslandResp> recommendIslandRespList = new ArrayList<>();
        if (islandRecommendations != null && islandRecommendations.size() > 0) {
            for (IslandRecommendation islandRecommendation : islandRecommendations) {
                RecommendIslandResp recommendIslandResp = new RecommendIslandResp();
                recommendIslandResp.setIsland_desc(islandRecommendation.getIslandCnName());
                recommendIslandResp.setIsland_id(islandRecommendation.getIslandIndexCode());
                recommendIslandResp.setIsland_image(islandRecommendation.getRecommendationImage());
                recommendIslandResp.setIsland_name(islandRecommendation.getIslandCnName());
                recommendIslandRespList.add(recommendIslandResp);
            }
        }
        return ResponseApi.ok(recommendIslandRespList);
    }

    @Override
    public ResponseApi<List<TagResp>> getTags() {
        List<IslandTag> islandTags = islandTagMapper.selectList(null);
        List<TagResp> tagRespList = new ArrayList<>();
        if (islandTags != null && islandTags.size() > 0) {
            for (IslandTag islandTag : islandTags) {
                TagResp tagResp = new TagResp();
                tagResp.setTag_index_code(islandTag.getTagIndexCode());
                tagResp.setTag_image(islandTag.getTagImage());
                tagResp.setTag_name(islandTag.getTagName());
                tagRespList.add(tagResp);
            }
        }
        return ResponseApi.ok(tagRespList);
    }

    @Override
    public ResponseApi getIslandDetail(Integer islandIndexCode) {
        IslandManagement islandManagement = islandManagementMapper.selectById(islandIndexCode);
        if (islandManagement != null) {
            IslandDetailResp islandDetailResp = new IslandDetailResp();
            islandDetailResp.setIsland_id(islandManagement.getIslandIndexCode());
            islandDetailResp.setBanner(islandManagement.getIslandImage());
            islandDetailResp.setDatum_link(islandManagement.getIslandFile());
            islandDetailResp.setDesc(islandManagement.getIslandDesc());
            islandDetailResp.setIntro(islandDetailResp.getIntro());
            islandDetailResp.setName(islandManagement.getIslandCnName());
            islandDetailResp.setNameEn(islandManagement.getIslandEnName());

            //根据岛屿编码查询岛屿价格
            List<IslandQuotation> islandQuotations = islandQuotationMapper.selectIslandQuotationByIslandIndexCode(islandIndexCode);
            if (!CollectionUtils.isEmpty(islandQuotations)) {
                List<String> pdfPrices = islandQuotations.stream().filter(islandQuotation -> islandQuotation.getQuotationType() == 1).map(IslandQuotation::getQuotationName).collect(Collectors.toList());
                List<String> imagePrices = islandQuotations.stream().filter(islandQuotation -> islandQuotation.getQuotationType() == 0).map(IslandQuotation::getQuotationName).collect(Collectors.toList());
                islandDetailResp.setPdf_price(pdfPrices);
                islandDetailResp.setImage_price(imagePrices);
            }

            //根据岛屿编码查询岛屿标签
            List<IslandTag> islandTags = islandTagMapper.selectTagsByIslandIndexCode(islandIndexCode);
            if (!CollectionUtils.isEmpty(islandTags)) {
                List<String> tagNames = islandTags.stream().map(IslandTag::getTagName).collect(Collectors.toList());
                islandDetailResp.setTags(tagNames);
            }
            return ResponseApi.ok().setData(islandDetailResp);
        }
        return ResponseApi.error("未查询到数据");
    }

    @Override
    public ResponseApi getAllIslands(IslandQueryReq islandQueryReq) {
        // 这个是共性的必须要的
        QueryWrapper<IslandTagRelation> allIslandTagRelationQueryWrapper = new QueryWrapper<>();
        List<IslandTagRelation> allIslandTagRelations = islandTagRelationMapper.selectList(allIslandTagRelationQueryWrapper);
        // 默认查所有岛屿
        if (CollectionUtils.isEmpty(islandQueryReq.getTagIndexCodes())) {
            // 查询所有的岛屿信息
            List<IslandManagement> islandManagements = islandManagementMapper.selectList(null);
            return getIslandListInfo(islandManagements, allIslandTagRelations);
        } else {
            // 按需查询
            QueryWrapper<IslandTagRelation> islandTagRelationQueryWrapper = new QueryWrapper<>();
            islandTagRelationQueryWrapper.in(IslandTagRelation.TAG_INDEX_CODE, islandQueryReq.getTagIndexCodes());
            List<IslandTagRelation> islandTagRelations = islandTagRelationMapper.selectList(islandTagRelationQueryWrapper);
            // 洗出岛屿编码
            List<Integer> islandIndexCodes = islandTagRelations.stream().map(IslandTagRelation::getIslandIndexCode).distinct().collect(Collectors.toList());
            // 去查
            QueryWrapper<IslandManagement> islandManagementQueryWrapper = new QueryWrapper<>();
            if (!CollectionUtils.isEmpty(islandIndexCodes)) {
                islandManagementQueryWrapper.in(IslandManagement.ISLAND_INDEX_CODE, islandIndexCodes);
            }
            List<IslandManagement> islandManagements = islandManagementMapper.selectList(islandManagementQueryWrapper);
            return getIslandListInfo(islandManagements, allIslandTagRelations);
        }
    }

    private ResponseApi getIslandListInfo(List<IslandManagement> islandManagements, List<IslandTagRelation> allIslandTagRelations) {
        if (!CollectionUtils.isEmpty(islandManagements)) {
            List<IslandListResp> islandManagementListResp = islandManagements.stream().map(islandManagement -> {
                IslandListResp islandListResp = new IslandListResp();
                islandListResp.setIsland_name(islandManagement.getIslandCnName());
                islandListResp.setIsland_desc(islandManagement.getIslandDesc());
                islandListResp.setIsland_image(islandManagement.getIslandImage());
                islandListResp.setIsland_id(islandManagement.getIslandIndexCode());
                islandListResp.setIsland_intro(islandManagement.getIslandIntro());
                List<Integer> tagIndexCodeList = allIslandTagRelations.stream().filter(islandTagRelation -> islandTagRelation.getIslandIndexCode() == islandListResp.getIsland_id())
                        .map(IslandTagRelation::getTagIndexCode).collect(Collectors.toList());
                islandListResp.setIsland_tags(tagIndexCodeList);
                return islandListResp;
            }).collect(Collectors.toList());
            return ResponseApi.ok(islandManagementListResp);
        }
        return ResponseApi.error("未查询到数据");
    }

}
