package com.eva.dtholiday.system.service.portalmanagement.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandManagement;
import com.eva.dtholiday.commons.enums.BusinessErrorCodeEnum;
import com.eva.dtholiday.commons.exception.BusinessException;
import com.eva.dtholiday.commons.utils.LocalCache;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandTag;
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
        Long count = islandTagMapper.selectCount(null);
        Page<IslandTag> page = new Page<>(islandTagReq.getPage(), islandTagReq.getPageSize());
        Page<IslandTag> result = islandTagMapper.selectPage(page, null);
        IslandTagQueryListResp islandTagQueryListResp = new IslandTagQueryListResp();
        islandTagQueryListResp.setIslandTagList(result.getRecords());
        islandTagQueryListResp.setPage(islandTagReq.getPage());
        islandTagQueryListResp.setPageSize(islandTagReq.getPageSize());
        islandTagQueryListResp.setTotal(count);
        return ResponseApi.ok(islandTagQueryListResp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi islandTagAdd(IslandTagReq islandTagReq) {
        IslandTag islandTag = new IslandTag();
        // 重名校验
        IslandTag oldIslandTag = islandTagMapper.selectOne(
                new QueryWrapper<IslandTag>().lambda().eq(IslandTag::getTagName,
                        islandTagReq.getTagName()));
        if (oldIslandTag != null) {
            throw new BusinessException(BusinessErrorCodeEnum.ISLAND_TAG_NAME_HAS_EXISTED.getMessageCN(), BusinessErrorCodeEnum.ISLAND_TAG_NAME_HAS_EXISTED.getCode());
        }
        convertTagEntity(islandTagReq, islandTag);
        islandTagMapper.insert(islandTag);
        return islandTagQueryDetail(islandTag.getTagIndexCode());
    }

    private void convertTagEntity(IslandTagReq islandTagReq, IslandTag islandTag) {
        islandTag.setTagName(islandTagReq.getTagName());
        islandTag.setTagImage(islandTagReq.getTagImage());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi islandTagUpdate(IslandTagReq islandTagReq) {
        if (islandTagReq.getTagIndexCode() > 0) {
            IslandTag islandTag = islandTagMapper.selectById(islandTagReq.getTagIndexCode());
            // 存在性校验
            if (islandTag == null ){
                throw new BusinessException(BusinessErrorCodeEnum.ISLAND_TAG_NOT_EXISTED.getMessageCN(), BusinessErrorCodeEnum.ISLAND_TAG_NOT_EXISTED.getCode());
            }
            IslandTag oldNameTag  = islandTagMapper.selectOne(
                    new QueryWrapper<IslandTag>().lambda().eq(IslandTag::getTagName,
                            islandTagReq.getTagName()));
            if (oldNameTag!=null && !oldNameTag.getTagIndexCode().equals(islandTagReq.getTagIndexCode())){
                throw new BusinessException(BusinessErrorCodeEnum.ISLAND_TAG_NAME_HAS_EXISTED.getMessageCN(), BusinessErrorCodeEnum.ISLAND_TAG_NAME_HAS_EXISTED.getCode());
            }
            BeanUtils.copyProperties(islandTagReq, islandTag);
            islandTag.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            islandTagMapper.updateById(islandTag);
            LocalCache.putIslandTagName(islandTag.getTagIndexCode(), islandTag.getTagName());
            return ResponseApi.ok(islandTag);
        } else {
            throw new BusinessException(BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getMessageCN(), BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getCode());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi islandTagDelete(List<Integer> tagIndexCodeList) {
        // 删除主表
        int i = islandTagMapper.deleteBatchIds(tagIndexCodeList);
        // 删除关联表
        islandTagRelationMapper.deleteBatchByTagIndexCode(tagIndexCodeList);
        return ResponseApi.ok(i);
    }

    @Override
    public void loadAllTagName() {
        List<IslandTag> islandTagList = islandTagMapper.selectList(null);
        if (CollectionUtils.isNotEmpty(islandTagList)) {
            islandTagList.forEach(islandTag -> {
                LocalCache.putIslandTagName(islandTag.getTagIndexCode(), islandTag.getTagName());
            });
        }
    }
}
