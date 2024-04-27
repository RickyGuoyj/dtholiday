package com.eva.dtholiday.system.service.portalmanagement.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.dto.FileInfo;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandArticle;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandManagement;
import com.eva.dtholiday.commons.dao.mapper.portalmanagement.IslandArticleMapper;
import com.eva.dtholiday.commons.dao.mapper.portalmanagement.IslandManagementMapper;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandArticleQueryDto;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandArticleReq;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandArticleResp;
import com.eva.dtholiday.commons.utils.DateUtils;
import com.eva.dtholiday.commons.utils.MyStringUtils;
import com.eva.dtholiday.system.service.portalmanagement.IslandArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/14 4:23
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Service
public class IslandArticleServiceImpl implements IslandArticleService {

    @Resource
    private IslandArticleMapper islandArticleMapper;

    @Resource
    private IslandManagementMapper islandManagementMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi islandArticleAdd(IslandArticleReq req) {
        IslandArticle islandArticle = new IslandArticle();
        BeanUtils.copyProperties(req, islandArticle);
        if (!CollectionUtils.isEmpty(req.getPictures())) {
            islandArticle.setArticleImages(JSONObject.toJSONString(req.getPictures()));
        }
        // 根据岛屿编码获取岛屿名称
        IslandManagement island = islandManagementMapper.selectOne(new QueryWrapper<IslandManagement>().eq(IslandManagement.ISLAND_INDEX_CODE, req.getIslandIndexCode()));
        if (Objects.nonNull(island)) {
            islandArticle.setIslandCnName(island.getIslandCnName());
        }
        islandArticleMapper.insert(islandArticle);
        //返回业务对象
        IslandArticleResp islandArticleResp = new IslandArticleResp();
        BeanUtils.copyProperties(islandArticle, islandArticleResp);
        List<FileInfo> fileInfos = JSONObject.parseArray(islandArticle.getArticleImages(), FileInfo.class);
        islandArticleResp.setPictures(fileInfos);
        islandArticleResp.setCreateTime(DateUtils.convertDateToLocalDateTime(islandArticle.getCreateTime()));
        islandArticleResp.setUpdateTime(DateUtils.convertDateToLocalDateTime(islandArticle.getUpdateTime()));
        return ResponseApi.ok(islandArticleResp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi islandArticleDelete(IslandArticleQueryDto req) {
        if (!CollectionUtils.isEmpty(req.getArticleIndexCodes())) {
            //根据articleIndexCodes删除文章
            List<IslandArticle> articleList = islandArticleMapper.selectBatchIds(req.getArticleIndexCodes());
            List<Integer> ids = articleList.stream().map(IslandArticle::getArticleIndexCode).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(articleList)) {
                islandArticleMapper.deleteBatchIds(ids);
            }
        }
        return ResponseApi.ok(req);
    }

    @Override
    public ResponseApi islandArticleUpdate(IslandArticleReq req) {
        // 根据articleIndexCode查出数据库中的那一条
        IslandArticle islandArticle = islandArticleMapper.selectById(req.getArticleIndexCode());
        if (Objects.nonNull(islandArticle)) {
            BeanUtils.copyProperties(req, islandArticle);
            if (!CollectionUtils.isEmpty(req.getPictures())) {
                islandArticle.setArticleImages(JSONObject.toJSONString(req.getPictures()));
            }
            // 根据岛屿编码获取岛屿名称
            IslandManagement island = islandManagementMapper.selectOne(new QueryWrapper<IslandManagement>().eq(IslandManagement.ISLAND_INDEX_CODE, req.getIslandIndexCode()));
            if (Objects.nonNull(island)) {
                islandArticle.setIslandCnName(island.getIslandCnName());
            }
            islandArticleMapper.updateById(islandArticle);
        }
        //返回业务对象
        IslandArticleResp islandArticleVo = new IslandArticleResp();
        BeanUtils.copyProperties(req, islandArticleVo);
        return ResponseApi.ok(islandArticleVo);
    }

    @Override
    public ResponseApi islandArticleQueryDetail(Integer articleIndexCode) {
        IslandArticleResp islandArticleResp = new IslandArticleResp();
        // 查出这条数据
        IslandArticle islandArticle = islandArticleMapper.selectById(articleIndexCode);
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
    public ResponseApi islandArticleQueryList(IslandArticleQueryDto req) {
        List<IslandArticleResp> islandArticleRespList = new ArrayList<>();
        if (CollectionUtils.isEmpty(req.getType())) {
            return ResponseApi.error("文章类型不能为空");
        }
        QueryWrapper<IslandArticle> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("type", req.getType());
        if (StringUtils.hasText(req.getTitle())) {
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
}
