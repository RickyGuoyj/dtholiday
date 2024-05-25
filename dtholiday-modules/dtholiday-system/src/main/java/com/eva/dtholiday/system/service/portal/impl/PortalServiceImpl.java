package com.eva.dtholiday.system.service.portal.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eva.dtholiday.commons.dao.entity.productManagement.ExtraChildExpense;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandArticleQueryDto;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandQuotationQueryListReq;
import com.eva.dtholiday.commons.dao.resp.portal.*;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandArticleResp;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandQuotationQueryListResp;
import com.eva.dtholiday.commons.dao.resp.productManagement.ExtraChildExpenseResp;
import com.eva.dtholiday.commons.utils.DateUtils;
import com.eva.dtholiday.commons.utils.LocalCache;
import com.eva.dtholiday.system.service.portalmanagement.IslandManagementService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.dto.FileInfo;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.*;
import com.eva.dtholiday.commons.dao.mapper.portalmanagement.*;
import com.eva.dtholiday.commons.dao.req.portal.IslandQueryReq;
import com.eva.dtholiday.system.service.portal.PortalService;

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

    @Resource
    private IslandArticleMapper islandArticleMapper;

    @Resource
    private IslandManagementService islandManagementService;

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
            if (StringUtils.isNotBlank(islandManagement.getIslandImage())) {
                FileInfo fileInfo = JSONObject.parseObject(islandManagement.getIslandImage(), FileInfo.class);
                islandDetailResp.setBanner(fileInfo.getFilePath());
            }
            // islandDetailResp.setBanner(islandManagement.getIslandImage());
            islandDetailResp.setDatum_link(islandManagement.getIslandFile());
            islandDetailResp.setDesc(islandManagement.getIslandDesc());
            islandDetailResp.setIntro(islandManagement.getIslandIntro());
            islandDetailResp.setName(islandManagement.getIslandCnName());
            islandDetailResp.setNameEn(islandManagement.getIslandEnName());

            // 根据岛屿编码查询岛屿价格
            List<IslandQuotation> islandQuotations =
                    islandQuotationMapper.selectIslandQuotationByIslandIndexCode(islandIndexCode);
            if (!CollectionUtils.isEmpty(islandQuotations)) {
                List<String> pdfPrices =
                        islandQuotations.stream().map(IslandQuotation::getQuotationName).collect(Collectors.toList());
                islandDetailResp.setPdf_price(pdfPrices);
                // islandDetailResp.setImage_price(imagePrices);
            }

            // 根据岛屿编码查询岛屿标签
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
        List<IslandTagRelation> allIslandTagRelations =
                islandTagRelationMapper.selectList(null);
        // 默认查所有岛屿
        if (islandQueryReq.getTagIndexCode() == -1) {
            // 查询所有的岛屿信息
            List<IslandManagement> islandManagements = islandManagementMapper.selectList(null);
            return getIslandListInfo(islandManagements, allIslandTagRelations);
        } else {
            // 按需查询
            QueryWrapper<IslandTagRelation> islandTagRelationQueryWrapper = new QueryWrapper<>();
            islandTagRelationQueryWrapper.in(IslandTagRelation.TAG_INDEX_CODE, islandQueryReq.getTagIndexCode());
            List<IslandTagRelation> islandTagRelations =
                    islandTagRelationMapper.selectList(islandTagRelationQueryWrapper);
            // 洗出岛屿编码
            List<Integer> islandIndexCodes = islandTagRelations.stream().map(IslandTagRelation::getIslandIndexCode)
                    .distinct().collect(Collectors.toList());
            // 去查
            QueryWrapper<IslandManagement> islandManagementQueryWrapper = new QueryWrapper<>();
            if (!CollectionUtils.isEmpty(islandIndexCodes)) {
                islandManagementQueryWrapper.in(IslandManagement.ISLAND_INDEX_CODE, islandIndexCodes);
            }
            List<IslandManagement> islandManagements = islandManagementMapper.selectList(islandManagementQueryWrapper);
            return getIslandListInfo(islandManagements, allIslandTagRelations);
        }
    }

    @Override
    public ResponseApi getArticleList(IslandArticleQueryDto req) {
        List<IslandArticleResp> islandArticleRespList = new ArrayList<>();
        if (CollectionUtils.isEmpty(req.getType())) {
            return ResponseApi.error("文章类型不能为空");
        }
        QueryWrapper<IslandArticle> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("type", req.getType());
        if (org.springframework.util.StringUtils.hasText(req.getTitle())) {
            queryWrapper.like("title", req.getTitle());
        }
        List<IslandArticle> islandArticles = islandArticleMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(islandArticles)) {
            islandArticleRespList = islandArticles.stream().map(islandArticle -> {
                IslandArticleResp islandArticleResp = new IslandArticleResp();
                BeanUtils.copyProperties(islandArticle, islandArticleResp);
                List<FileInfo> fileInfos = JSONObject.parseArray(islandArticle.getArticleImages(), FileInfo.class);
                islandArticleResp.setPictures(fileInfos);
                islandArticleResp.setCreateTime(DateUtils.convertDateToLocalDateTime(islandArticle.getCreateTime()));
                islandArticleResp.setUpdateTime(DateUtils.convertDateToLocalDateTime(islandArticle.getUpdateTime()));
                return islandArticleResp;
            }).collect(Collectors.toList());
        }
        return ResponseApi.ok(islandArticleRespList);
    }

    @Override
    public ResponseApi getAllIslandNames() {
        List<IslandNameResp> islandNameRespList = new ArrayList<>();
        List<IslandManagement> islandManagements = islandManagementMapper.selectList(null);
        if (!CollectionUtils.isEmpty(islandManagements)) {
            islandNameRespList = islandManagements.stream().map(islandManagement -> {
                IslandNameResp islandNameResp = new IslandNameResp();
                islandNameResp.setIslandCnName(islandManagement.getIslandCnName());
                islandNameResp.setIslandIndexCode(islandManagement.getIslandIndexCode());
                return islandNameResp;
            }).collect(Collectors.toList());
        }
        return ResponseApi.ok(islandNameRespList);
    }

    @Override
    public ResponseApi getArticleDetail(IslandArticleQueryDto islandArticleQueryDto) {
        IslandArticleResp islandArticleResp = new IslandArticleResp();
        // 查出这条数据
        IslandArticle islandArticle = islandArticleMapper.selectById(islandArticleQueryDto.getArticleIndexCode());
        if (!Objects.isNull(islandArticle)) {
            //返回业务对象
            BeanUtils.copyProperties(islandArticle, islandArticleResp);
            List<FileInfo> fileInfos = JSONObject.parseArray(islandArticle.getArticleImages(), FileInfo.class);
            islandArticleResp.setPictures(fileInfos);
            islandArticleResp.setCreateTime(DateUtils.convertDateToLocalDateTime(islandArticle.getCreateTime()));
            islandArticleResp.setUpdateTime(DateUtils.convertDateToLocalDateTime(islandArticle.getUpdateTime()));
        }
        return ResponseApi.ok(islandArticleResp);
    }

    @Override
    public ResponseApi getIslandQuotationList(IslandQuotationQueryListReq islandQuotationQueryListReq) {
        Page<IslandQuotation> entityPage = new Page<>(islandQuotationQueryListReq.getCurrent(), islandQuotationQueryListReq.getSize());
        QueryWrapper<IslandQuotation> queryWrapper = new QueryWrapper<>();
        if (islandQuotationQueryListReq.getIslandIndexCode() != null) {
            queryWrapper.eq(IslandQuotation.ISLAND_INDEX_CODE, islandQuotationQueryListReq.getIslandIndexCode());
        }
        if (org.springframework.util.StringUtils.hasText(islandQuotationQueryListReq.getQuotationName())) {
            queryWrapper.like(IslandQuotation.QUOTATION_NAME, islandQuotationQueryListReq.getQuotationName());
        }
        entityPage = islandQuotationMapper.selectPage(entityPage, queryWrapper);
        if (Objects.isNull(entityPage)) {
            return ResponseApi.ok(new Page<>(islandQuotationQueryListReq.getCurrent(), 0));
        }
        List<IslandManagementQuotation> respList = entityPage.getRecords().stream().map(entity -> {
            IslandManagementQuotation resp = new IslandManagementQuotation();
            BeanUtils.copyProperties(entity, resp);
            resp.setIslandCnName(islandManagementService.getIslandName(resp.getIslandIndexCode()));
            return resp;
        }).collect(Collectors.toList());

        Page<IslandManagementQuotation> respPage = new Page<>(islandQuotationQueryListReq.getCurrent(), islandQuotationQueryListReq.getSize());
        respPage.setRecords(respList);
        respPage.setTotal(entityPage.getTotal());
        return ResponseApi.ok(respPage);
    }

    private ResponseApi getIslandListInfo(List<IslandManagement> islandManagements,
                                          List<IslandTagRelation> allIslandTagRelations) {
        if (!CollectionUtils.isEmpty(islandManagements)) {
            List<IslandListResp> islandManagementListResp = islandManagements.stream().map(islandManagement -> {
                IslandListResp islandListResp = new IslandListResp();
                islandListResp.setIsland_name(islandManagement.getIslandCnName());
                islandListResp.setIsland_desc(islandManagement.getIslandDesc());
                FileInfo islandImage = JSONObject.parseObject(islandManagement.getIslandImage(), FileInfo.class);
                if (islandImage != null){
                    islandListResp.setIsland_image(islandImage.getFilePath());
                }
                islandListResp.setIsland_id(islandManagement.getIslandIndexCode());
                islandListResp.setIsland_intro(islandManagement.getIslandIntro());
                List<Integer> tagIndexCodeList = allIslandTagRelations.stream()
                        .filter(
                                islandTagRelation -> islandTagRelation.getIslandIndexCode() == islandListResp.getIsland_id())
                        .map(IslandTagRelation::getTagIndexCode).collect(Collectors.toList());
                islandListResp.setIsland_tags(tagIndexCodeList);
                return islandListResp;
            }).collect(Collectors.toList());
            return ResponseApi.ok(islandManagementListResp);
        }
        return ResponseApi.error("未查询到数据");
    }

}
