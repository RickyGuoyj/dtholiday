package com.eva.dtholiday.system.service.portalmanagement.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandTag;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandTagRelation;
import com.eva.dtholiday.commons.dao.mapper.portalmanagement.IslandTagMapper;
import com.eva.dtholiday.commons.dao.mapper.portalmanagement.IslandTagRelationMapper;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandTagReq;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandTagQueryListResp;
import com.eva.dtholiday.system.service.portalmanagement.IslandTagService;

@Service
public class IslandTagServiceImpl implements IslandTagService {
    @Resource
    private IslandTagMapper islandTagMapper;

    @Resource
    private IslandTagRelationMapper islandTagRelationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)

    public ResponseApi islandTagQueryDetail(int tagIndexCode) {
        IslandTag islandTag = islandTagMapper.selectById(tagIndexCode);
        return ResponseApi.ok(islandTag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi islandTagQueryList(IslandTagReq islandTagReq) {
        Page<IslandTag> page = new Page<>(islandTagReq.getPage(), islandTagReq.getPageSize());
        Page<IslandTag> result = islandTagMapper.selectPage(page, null);
        IslandTagQueryListResp islandTagQueryListResp = new IslandTagQueryListResp();
        islandTagQueryListResp.setIslandTagList(result.getRecords());
        islandTagQueryListResp.setPage(islandTagReq.getPage());
        islandTagQueryListResp.setPageSize(islandTagReq.getPageSize());
        return ResponseApi.ok(islandTagQueryListResp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi islandTagAdd(IslandTagReq islandTagReq) {
        IslandTag islandTag = new IslandTag();
        convertTagEntity(islandTagReq, islandTag);
        int insert = islandTagMapper.insert(islandTag);
        return ResponseApi.ok(insert);
    }

    private void convertTagEntity(IslandTagReq islandTagReq, IslandTag islandTag) {
        islandTag.setTagName(islandTagReq.getTagName());
        islandTag.setTagImage(islandTagReq.getTagImage());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi islandTagUpdate(IslandTagReq islandTagReq) {
        if (islandTagReq.getTagIndexCode() > 0) {
            Long count = islandTagMapper.selectCount(
                new QueryWrapper<IslandTag>().eq(IslandTag.TAG_INDEX_CODE, islandTagReq.getTagIndexCode()));
            if (count > 0) {
                IslandTag islandTag = new IslandTag();
                convertTagEntity(islandTagReq, islandTag);
                islandTag.setTagIndexCode(islandTagReq.getTagIndexCode());
                Timestamp updateTime = new Timestamp(System.currentTimeMillis());
                islandTag.setUpdateTime(updateTime);
                int update = islandTagMapper.updateById(islandTag);
                return ResponseApi.ok(update);
            }
        }
        return ResponseApi.error();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi islandTagDelete(int tagIndexCode) {
        // 删除主表
        int i = islandTagMapper.deleteById(tagIndexCode);
        // 删除关联表
        islandTagRelationMapper
            .delete(new QueryWrapper<IslandTagRelation>().eq(IslandTagRelation.TAG_INDEX_CODE, tagIndexCode));
        return ResponseApi.ok(i);
    }
}
