package com.eva.dtholiday.system.service.portalmanagement.impl;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.dto.FileInfo;
import com.eva.dtholiday.commons.dao.dto.IslandManagementTagInfo;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.*;
import com.eva.dtholiday.commons.dao.mapper.portalmanagement.*;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandManagementReq;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandManagementQueryDetailResp;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandManagementQueryListResp;
import com.eva.dtholiday.commons.enums.BusinessErrorCodeEnum;
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
        ResponseApi response = ResponseApi.ok();
        IslandManagement islandManagement = new IslandManagement();
        // 插入主表
        convertManagementEntity(islandManagementReq, islandManagement);
        int insert = islandManagementMapper.insert(islandManagement);
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
        response.setData(insert);
        return response;
    }

    private void convertManagementEntity(IslandManagementReq islandManagementReq, IslandManagement islandManagement) {
        islandManagement.setIslandDesc(islandManagementReq.getIslandDesc());
        islandManagement.setIslandEnName(islandManagementReq.getIslandEnName());
        islandManagement.setIslandFile(islandManagementReq.getIslandFile());
        islandManagement.setIslandIntro(islandManagementReq.getIslandIntro());
        islandManagement.setIslandImage(JSONObject.toJSONString(islandManagementReq.getIslandImage()));
        islandManagement.setIslandCnName(islandManagementReq.getIslandCnName());
    }

    private List<IslandTagRelation> convertTagRelationEntityList(IslandManagementReq islandManagementReq,
        IslandManagement islandManagement) {
        List<IslandTagRelation> list = new ArrayList<>();
        islandManagementReq.getIslandTagList().forEach(item -> {
            IslandTagRelation islandTagRelation = new IslandTagRelation();
            islandTagRelation.setIslandIndexCode(islandManagement.getIslandIndexCode());
            islandTagRelation.setTagIndexCode(item);
            list.add(islandTagRelation);
        });
        return list;
    }

    private List<IslandQuotation> convertQuotationEntityList(IslandManagementReq islandManagementReq,
        IslandManagement islandManagement) {
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
        ResponseApi response = ResponseApi.ok();
        // 更新主表
        IslandManagement islandManagement = new IslandManagement();
        convertManagementEntity(islandManagementReq, islandManagement);
        islandManagement.setIslandIndexCode(islandManagementReq.getIslandIndexCode());
        int i = islandManagementMapper.updateById(islandManagement);
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
         // 更新报价单
//         List<IslandQuotation> oldQuotationList = islandQuotationMapper.selectList(new QueryWrapper<IslandQuotation>()
//         .eq(IslandQuotation.ISLAND_INDEX_CODE, islandManagementReq.getIslandIndexCode()));
//         List<IslandQuotation> needAddList = new ArrayList<>();
//         List<IslandQuotation> needDeleteList = new ArrayList<>();
//         handleQuotationList(oldQuotationList, islandManagementReq.getIslandQuotationPdfList(), needAddList,
//         needDeleteList);
//         List<IslandQuotation>
//         List<IslandQuotation> islandQuotationList = convertQuotationEntityList(islandManagementReq,
//         islandManagement);
//         islandQuotationMapper.insertBatch(islandQuotationList);
        return response.setData(i);
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
            response.setData(i);
        } catch (Exception e) {
            response = ResponseApi.error(null, BusinessErrorCodeEnum.ISLAND_MANAGEMENT_DELETE_FAIL.getCode(),
                BusinessErrorCodeEnum.ISLAND_MANAGEMENT_DELETE_FAIL.getMessageCN());
        }
        return response;
    }

    /**
     *pdf报价单和岛屿取消关联
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
    public ResponseApi<IslandManagementQueryListResp>
        islandManagementQueryList(IslandManagementReq islandManagementReq) {
        if (islandManagementReq.getPage() > 0 && islandManagementReq.getPageSize() > 0) {
            HashMap<String, Object> map = new HashMap<>();

            map.put("islandCnName", islandManagementReq.getIslandCnName());
            map.put("islandEnName", islandManagementReq.getIslandEnName());
            map.put("tagIndexCodeList", islandManagementReq.getIslandTagList());
            int count = islandManagementMapper.queryIslandManagementListCount(map);
            // 计算分页
            map.put("page", (islandManagementReq.getPage() - 1) * islandManagementReq.getPageSize());
            map.put("pageSize", islandManagementReq.getPageSize());
            List<IslandManagementTag> islandManagementTagInfos = islandManagementMapper.queryIslandManagementList(map);
            // 按照islandIndexCode分组,其中的islandCnName进行拼接
            List<IslandManagementTagInfo> islandManagementTagInfoList = new ArrayList<>();
            Map<Integer, IslandManagementTagInfo> islandManagementTagInfoMap = new HashMap<>();
            islandManagementTagInfos.forEach(item -> {
                if (islandManagementTagInfoMap.containsKey(item.getIslandIndexCode())) {
                    IslandManagementTagInfo islandManagementTagInfo =
                        islandManagementTagInfoMap.get(item.getIslandIndexCode());
                    islandManagementTagInfo
                        .setIslandCnName(islandManagementTagInfo.getIslandCnName() + "," + item.getIslandCnName());
                } else {
                    IslandManagementTagInfo info = new IslandManagementTagInfo();
                    info.setIslandFile(item.getIslandFile());
                    info.setIslandCnName(item.getIslandCnName());
                    info.setIslandDesc(item.getIslandDesc());
                    info.setIslandEnName(item.getIslandEnName());
                    info.setTagName(item.getTagName());
                    info.setIslandIndexCode(item.getIslandIndexCode());
                    islandManagementTagInfoMap.put(item.getIslandIndexCode(), info);
                }
            });
            // map转list
            islandManagementTagInfoMap.forEach((k, v) -> {
                islandManagementTagInfoList.add(v);
            });

            IslandManagementQueryListResp islandManagementQueryListResp = new IslandManagementQueryListResp();
            islandManagementQueryListResp.setPage(islandManagementReq.getPage());
            islandManagementQueryListResp.setPageSize(islandManagementReq.getPageSize());
            islandManagementQueryListResp.setIslandManagementTagInfoList(islandManagementTagInfoList);
            islandManagementQueryListResp.setTotal(count);
            return ResponseApi.ok(islandManagementQueryListResp);
        }
        return ResponseApi.error();
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
}
