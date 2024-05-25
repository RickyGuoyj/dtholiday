package com.eva.dtholiday.system.service.portalmanagement.impl;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eva.dtholiday.commons.dao.dto.IslandManagementTagInfo;
import com.eva.dtholiday.commons.dao.resp.productManagement.PlaneTicketResp;
import com.eva.dtholiday.commons.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.dto.FileInfo;
import com.eva.dtholiday.commons.dao.dto.IslandManagementInfo;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.*;
import com.eva.dtholiday.commons.dao.mapper.portalmanagement.*;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandManagementReq;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandManagementQueryDetailResp;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandManagementQueryListResp;
import com.eva.dtholiday.commons.enums.BusinessErrorCodeEnum;
import com.eva.dtholiday.commons.utils.LocalCache;
import com.eva.dtholiday.system.service.portalmanagement.IslandManagementService;

@Service
public class IslandManagementServiceImpl implements IslandManagementService {

    @Resource
    private IslandManagementMapper islandManagementMapper;

    @Resource
    private IslandTagRelationMapper islandTagRelationMapper;
    @Autowired
    private IslandQuotationMapper islandQuotationMapper;
    @Autowired
    private IslandTagMapper islandTagMapper;
    @Autowired
    private IslandRecommendationMapper islandRecommendationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi islandManagementAdd(IslandManagementReq islandManagementReq) {
        IslandManagement islandManagement = new IslandManagement();
        // 重名校验
        IslandManagement oldIsland = islandManagementMapper.selectOne(
                new QueryWrapper<IslandManagement>().lambda().eq(IslandManagement::getIslandCnName,
                        islandManagementReq.getIslandCnName()));
        if (oldIsland != null) {
            throw new BusinessException(BusinessErrorCodeEnum.ISLAND_NAME_HAS_EXISTED.getMessageCN(), BusinessErrorCodeEnum.ISLAND_NAME_HAS_EXISTED.getCode());
        }
        // 插入主表
        convertManagementEntity(islandManagementReq, islandManagement);
        islandManagementMapper.insert(islandManagement);
        // 插入关联表
        if (CollectionUtils.isNotEmpty(islandManagementReq.getIslandTagList())) {
            List<IslandTagRelation> islandTagRelationList =
                    convertTagRelationEntityList(islandManagementReq, islandManagement);
            islandTagRelationMapper.insertBatch(islandTagRelationList);
        }
        // 插入pdf报价单
        if (CollectionUtils.isNotEmpty(islandManagementReq.getIslandQuotationPdfList())) {
            List<IslandQuotation> islandQuotationList =
                    convertQuotationEntityList(islandManagementReq, islandManagement);
            islandQuotationMapper.insertBatch(islandQuotationList);
        }
        LocalCache.putIslandName(islandManagement.getIslandIndexCode(), islandManagement.getIslandCnName());
        return islandManagementQueryDetail(islandManagement.getIslandIndexCode());
    }

    private void convertManagementEntity(IslandManagementReq islandManagementReq, IslandManagement islandManagement) {
        islandManagement.setIslandDesc(islandManagementReq.getIslandDesc());
        islandManagement.setIslandEnName(islandManagementReq.getIslandEnName());
        islandManagement.setIslandFile(islandManagementReq.getIslandFile());
        islandManagement.setIslandIntro(islandManagementReq.getIslandIntro());
        islandManagement.setIslandImage(JSONObject.toJSONString(islandManagementReq.getIslandImage()));
        islandManagement.setIslandCnName(islandManagementReq.getIslandCnName());
    }

    private List<IslandTagRelation> convertTagRelationEntityList(IslandManagementReq islandManagementReq, IslandManagement islandManagement) {
        List<IslandTagRelation> list = new ArrayList<>();
        islandManagementReq.getIslandTagList().forEach(item -> {
            IslandTagRelation islandTagRelation = new IslandTagRelation();
            islandTagRelation.setIslandIndexCode(islandManagement.getIslandIndexCode());
            islandTagRelation.setTagIndexCode(item);
            list.add(islandTagRelation);
        });
        return list;
    }

    private List<IslandQuotation> convertQuotationEntityList(IslandManagementReq islandManagementReq, IslandManagement islandManagement) {
        List<IslandQuotation> list = new ArrayList<>();
        islandManagementReq.getIslandQuotationPdfList().forEach(item -> {
            IslandQuotation islandQuotation = new IslandQuotation();
            islandQuotation.setIslandIndexCode(islandManagement.getIslandIndexCode());
            islandQuotation.setQuotationFile(item.getQuotationFile());
            islandQuotation.setQuotationName(item.getQuotationName());
            list.add(islandQuotation);
        });
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi islandManagementUpdate(IslandManagementReq islandManagementReq) {
        // 岛屿存在性校验
        IslandManagement islandManagement = islandManagementMapper.selectOne(
                new QueryWrapper<IslandManagement>().lambda().eq(IslandManagement::getIslandIndexCode,
                        islandManagementReq.getIslandIndexCode()));
        if (islandManagement == null) {
            throw new BusinessException(BusinessErrorCodeEnum.ISLAND_NOT_EXISTED.getMessageCN(), BusinessErrorCodeEnum.ISLAND_NOT_EXISTED.getCode());
        }
        IslandManagement oldNameIsland = islandManagementMapper.selectOne(
                new QueryWrapper<IslandManagement>().lambda().eq(IslandManagement::getIslandCnName,
                        islandManagementReq.getIslandCnName()));
        // 更新时重名校验
        if (!oldNameIsland.getIslandIndexCode().equals(islandManagementReq.getIslandIndexCode())) {
            throw new BusinessException(BusinessErrorCodeEnum.ISLAND_NAME_HAS_EXISTED.getMessageCN(), BusinessErrorCodeEnum.ISLAND_NAME_HAS_EXISTED.getCode());
        }
        convertManagementEntity(islandManagementReq, islandManagement);
        islandManagement.setIslandIndexCode(islandManagementReq.getIslandIndexCode());
        islandManagementMapper.updateById(islandManagement);
        // 处理关联表
        // 删除关联表
        islandTagRelationMapper.delete(new QueryWrapper<IslandTagRelation>().eq(IslandTagRelation.ISLAND_INDEX_CODE,
                islandManagementReq.getIslandIndexCode()));

        if (CollectionUtils.isNotEmpty(islandManagementReq.getIslandTagList())) {
            // 插入关联表
            List<IslandTagRelation> islandTagRelationList =
                    convertTagRelationEntityList(islandManagementReq, islandManagement);
            islandTagRelationMapper.insertBatch(islandTagRelationList);
        }
        // todo 更新报价单
        // List<IslandQuotation> oldQuotationList = islandQuotationMapper.selectList(new QueryWrapper<IslandQuotation>()
        // .eq(IslandQuotation.ISLAND_INDEX_CODE, islandManagementReq.getIslandIndexCode()));
        // List<IslandQuotation> needAddList = new ArrayList<>();
        // List<IslandQuotation> needDeleteList = new ArrayList<>();
        // handleQuotationList(oldQuotationList, islandManagementReq.getIslandQuotationPdfList(), needAddList,
        // needDeleteList);
        // List<IslandQuotation>
        // List<IslandQuotation> islandQuotationList = convertQuotationEntityList(islandManagementReq,
        // islandManagement);
        // islandQuotationMapper.insertBatch(islandQuotationList);
        LocalCache.putIslandName(islandManagement.getIslandIndexCode(), islandManagement.getIslandCnName());
        return islandManagementQueryDetail(islandManagement.getIslandIndexCode());
    }

    private void handleQuotationList(List<IslandQuotation> oldQuotationList, List<IslandQuotation> requestQuotationList,
                                     List<IslandQuotation> needAddList, List<IslandQuotation> needDeleteList) {
        // oldQuotationList转map且key为quotationFile
        Map<String, IslandQuotation> oldQuotationMap = new HashMap<>();
        Map<String, IslandQuotation> requestQuotationMap = new HashMap<>();
        oldQuotationList.forEach(item -> {
            oldQuotationMap.put(item.getQuotationFile(), item);
        });
        requestQuotationList.forEach(item -> {
            requestQuotationMap.put(item.getQuotationFile(), item);
        });
        // 比较requestQuotationList 在 oldQuotationList不存在的
        for (String key : requestQuotationMap.keySet()) {
            if (!oldQuotationMap.containsKey(key)) {
                needAddList.add(requestQuotationMap.get(key));
            }
        }
        for (String key : oldQuotationMap.keySet()) {
            if (!requestQuotationMap.containsKey(key)) {
                needDeleteList.add(oldQuotationMap.get(key));
            }
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi islandManagementDelete(List<Integer> islandIndexCodeList) {
        ResponseApi response = ResponseApi.ok();
        try {
            // 删除主表
            int i = islandManagementMapper.deleteBatchIds(islandIndexCodeList);
            // 删除标签关联表
            islandTagRelationMapper.deleteBatchByIslandIndexCode(islandIndexCodeList);
            // 删除推荐表
            QueryWrapper<IslandRecommendation> wrapper = new QueryWrapper<>();
            wrapper.in(IslandRecommendation.ISLAND_INDEX_CODE, islandIndexCodeList);
            islandRecommendationMapper.delete(wrapper);
            // 取消关联报价单
            unlinkIslandPdfQuotation(islandIndexCodeList);
            islandIndexCodeList.forEach(LocalCache::deleteIslandName);
            response.setData(i);
        } catch (Exception e) {
            response = ResponseApi.error(null, BusinessErrorCodeEnum.ISLAND_MANAGEMENT_DELETE_FAIL.getCode(),
                    BusinessErrorCodeEnum.ISLAND_MANAGEMENT_DELETE_FAIL.getMessageCN());
        }
        return response;
    }

    /**
     * pdf报价单和岛屿取消关联
     *
     * @param islandIndexCodeList
     */
    private void unlinkIslandPdfQuotation(List<Integer> islandIndexCodeList) {
        UpdateWrapper<IslandQuotation> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in(IslandQuotation.ISLAND_INDEX_CODE, islandIndexCodeList);
        IslandQuotation islandQuotation = new IslandQuotation();
        islandQuotation.setIslandIndexCode(0);
        islandQuotationMapper.update(islandQuotation, updateWrapper);
    }

    @Override
    public ResponseApi islandManagementQueryList(IslandManagementReq req) {
        IPage<IslandManagement> entityPage = new Page<>(req.getPage(), req.getPageSize());
        QueryWrapper<IslandManagement> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(req.getIslandCnName())) {
            queryWrapper.like(IslandManagement.ISLAND_CN_NAME, req.getIslandCnName());
        }
        if (StringUtils.isNotBlank(req.getIslandEnName())) {
            queryWrapper.like(IslandManagement.ISLAND_EN_NAME, req.getIslandEnName());
        }
        if (CollectionUtils.isNotEmpty(req.getIslandTagList())) {
            List<IslandTagRelation> islandTagRelationList = islandTagRelationMapper.selectList(
                    new QueryWrapper<IslandTagRelation>().in(IslandTagRelation.TAG_INDEX_CODE, req.getIslandTagList()));
            if (CollectionUtils.isNotEmpty(islandTagRelationList)) {
                List<Integer> islandIndexCodeList = islandTagRelationList.stream().distinct().map(IslandTagRelation::getIslandIndexCode).collect(Collectors.toList());
                queryWrapper.in(IslandManagement.ISLAND_INDEX_CODE, islandIndexCodeList);
            }
        }
        entityPage = islandManagementMapper.selectPage(entityPage, queryWrapper);
        if (CollectionUtils.isEmpty(entityPage.getRecords())) {
            return ResponseApi.ok(Collections.emptyList());
        }
        IslandManagementQueryListResp islandManagementQueryListResp = new IslandManagementQueryListResp();
        List<IslandManagementTagInfo> islandManagementTagInfoList = entityPage.getRecords().stream().map(entity -> {
            IslandManagementTagInfo islandManagementTagInfo = new IslandManagementTagInfo();
            islandManagementTagInfo.setIslandIndexCode(entity.getIslandIndexCode());
            islandManagementTagInfo.setIslandCnName(entity.getIslandCnName());
            islandManagementTagInfo.setIslandEnName(entity.getIslandEnName());
            islandManagementTagInfo.setIslandDesc(entity.getIslandDesc());
            islandManagementTagInfo.setIslandFile(entity.getIslandFile());
            List<IslandTagRelation> islandTagRelationList = islandTagRelationMapper.selectList(
                    new QueryWrapper<IslandTagRelation>().in(IslandTagRelation.ISLAND_INDEX_CODE, islandManagementTagInfo.getIslandIndexCode()));
            if (CollectionUtils.isNotEmpty(islandTagRelationList)) {
                List<Integer> tagIndexCodeList = islandTagRelationList.stream().distinct().map(IslandTagRelation::getTagIndexCode).collect(Collectors.toList());
                List<String> tagNameList = LocalCache.getTagNamesByTagIndexCodes(tagIndexCodeList);
                islandManagementTagInfo.setTagName(tagNameList);
            }
            return islandManagementTagInfo;

        }).collect(Collectors.toList());

        islandManagementQueryListResp.setPage(req.getPage());
        islandManagementQueryListResp.setPageSize(req.getPageSize());
        islandManagementQueryListResp.setIslandManagementTagInfoList(islandManagementTagInfoList);
        islandManagementQueryListResp.setTotal((int) entityPage.getTotal());
        return ResponseApi.ok(islandManagementQueryListResp);

//        if (islandManagementReq.getPage() > 0 && islandManagementReq.getPageSize() > 0) {
//            HashMap<String, Object> map = new HashMap<>();
//
//            map.put("islandCnName", islandManagementReq.getIslandCnName());
//            map.put("islandEnName", islandManagementReq.getIslandEnName());
//            map.put("tagIndexCodeList", islandManagementReq.getIslandTagList());
//            int count = islandManagementMapper.queryIslandManagementListCount(map);
//            // 计算分页
//            map.put("page", (islandManagementReq.getPage() - 1) * islandManagementReq.getPageSize());
//            map.put("pageSize", islandManagementReq.getPageSize());
//            List<IslandManagementTag> islandManagementTagInfos = islandManagementMapper.queryIslandManagementList(map);
//            // 按照islandIndexCode分组,其中的islandCnName进行拼接
//            List<IslandManagementTagInfo> islandManagementTagInfoList = new ArrayList<>();
//            Map<Integer, IslandManagementTagInfo> islandManagementTagInfoMap = new HashMap<>();
//            islandManagementTagInfos.forEach(item -> {
//                if (islandManagementTagInfoMap.containsKey(item.getIslandIndexCode())) {
//                    IslandManagementTagInfo islandManagementTagInfo =
//                            islandManagementTagInfoMap.get(item.getIslandIndexCode());
//                    islandManagementTagInfo
//                            .setIslandCnName(islandManagementTagInfo.getIslandCnName() + "," + item.getIslandCnName());
//                } else {
//                    IslandManagementTagInfo info = new IslandManagementTagInfo();
//                    info.setIslandFile(item.getIslandFile());
//                    info.setIslandCnName(item.getIslandCnName());
//                    info.setIslandDesc(item.getIslandDesc());
//                    info.setIslandEnName(item.getIslandEnName());
//                    info.setTagName(item.getTagName());
//                    info.setIslandIndexCode(item.getIslandIndexCode());
//                    islandManagementTagInfoMap.put(item.getIslandIndexCode(), info);
//                }
//            });
//            // map转list
//            islandManagementTagInfoMap.forEach((k, v) -> {
//                islandManagementTagInfoList.add(v);
//            });
//
//            IslandManagementQueryListResp islandManagementQueryListResp = new IslandManagementQueryListResp();
//            islandManagementQueryListResp.setPage(islandManagementReq.getPage());
//            islandManagementQueryListResp.setPageSize(islandManagementReq.getPageSize());
//            islandManagementQueryListResp.setIslandManagementTagInfoList(islandManagementTagInfoList);
//            islandManagementQueryListResp.setTotal(count);
//            return ResponseApi.ok(islandManagementQueryListResp);
//        }
//        return ResponseApi.error();
    }

    @Override
    public ResponseApi islandManagementQueryDetail(int islandIndexCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("tagIndexCodeList", Collections.singletonList(islandIndexCode));
        IslandManagement islandManagement = islandManagementMapper.selectById(islandIndexCode);
        // 根据岛屿主键查询关联的岛屿标签
        List<IslandTag> islandTags = islandTagMapper.selectTagsByIslandIndexCode(islandIndexCode);
        List<Integer> tagIndexCodeList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(islandTags)) {
            List<Integer> collect =
                    islandTags.stream().map(item -> item.getTagIndexCode()).collect(Collectors.toList());
            tagIndexCodeList.addAll(collect);
        }
        // 补齐数据
        IslandManagementQueryDetailResp resp = new IslandManagementQueryDetailResp();
        resp.setIslandIndexCode(islandManagement.getIslandIndexCode());
        resp.setIslandIntro(islandManagement.getIslandIntro());
        resp.setIslandCnName(islandManagement.getIslandCnName());
        resp.setIslandEnName(islandManagement.getIslandEnName());
        resp.setIslandDesc(islandManagement.getIslandDesc());
        resp.setIslandTagList(tagIndexCodeList);
        resp.setIslandFile(islandManagement.getIslandFile());
        if (StringUtils.isNotBlank(islandManagement.getIslandImage())) {
            FileInfo fileInfo = JSONObject.parseObject(islandManagement.getIslandImage(), FileInfo.class);
            resp.setIslandImage(fileInfo);
        }
        List<IslandQuotation> islandQuotations = islandQuotationMapper
                .selectList(new QueryWrapper<IslandQuotation>().eq(IslandQuotation.ISLAND_INDEX_CODE, islandIndexCode));
        resp.setIslandQuotationPdfList(islandQuotations);
        return ResponseApi.ok(resp);
    }

    @Override
    public String getIslandName(Integer islandIndexCode) {
        String islandName = LocalCache.getIslandName(islandIndexCode);
        if (StringUtils.isBlank(islandName)) {
            IslandManagement islandManagement = islandManagementMapper.selectById(islandIndexCode);
            if (islandManagement != null) {
                LocalCache.putIslandName(islandIndexCode, islandManagement.getIslandCnName());
                return islandManagement.getIslandCnName();
            }
        }
        return islandName;
    }

    @Override
    public ResponseApi<List<IslandManagementInfo>> queryAllIsland() {
        List<IslandManagement> islandManagementList = islandManagementMapper.selectList(null);
        List<IslandManagementInfo> infoList = islandManagementList.stream().map(item -> {
            IslandManagementInfo islandManagementInfo = new IslandManagementInfo();
            islandManagementInfo.setIslandIndexCode(item.getIslandIndexCode());
            islandManagementInfo.setIslandCnName(item.getIslandCnName());
            return islandManagementInfo;
        }).collect(Collectors.toList());
        return ResponseApi.ok(infoList);
    }

    @Override
    public void loadAllIslandName() {
        List<IslandManagement> islandManagementList = islandManagementMapper.selectList(null);
        if (!CollectionUtils.isEmpty(islandManagementList)) {
            islandManagementList.forEach(item -> {
                LocalCache.putIslandName(item.getIslandIndexCode(), item.getIslandCnName());
            });
        }
    }
}
