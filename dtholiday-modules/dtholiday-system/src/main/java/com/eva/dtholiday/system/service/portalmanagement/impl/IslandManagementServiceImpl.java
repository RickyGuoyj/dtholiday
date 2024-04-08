package com.eva.dtholiday.system.service.portalmanagement.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.dto.IslandManagementTagInfo;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandManagement;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandManagementTag;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandQuotation;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandTagRelation;
import com.eva.dtholiday.commons.dao.mapper.portalmanagement.IslandManagementMapper;
import com.eva.dtholiday.commons.dao.mapper.portalmanagement.IslandQuotationMapper;
import com.eva.dtholiday.commons.dao.mapper.portalmanagement.IslandTagRelationMapper;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandManagementReq;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi islandManagementAdd(IslandManagementReq islandManagementReq) {
        ResponseApi response = ResponseApi.ok();
        IslandManagement islandManagement = new IslandManagement();
        try {
            // 插入主表
            convertManagementEntity(islandManagementReq, islandManagement);
            int insert = islandManagementMapper.insert(islandManagement);
            // 插入关联表
            List<IslandTagRelation> islandTagRelationList = convertTagRelationEntityList(islandManagementReq, islandManagement);
            islandTagRelationMapper.insertBatch(islandTagRelationList);
            // 插入pdf报价单
            List<IslandQuotation> islandQuotationList = convertQuotationEntityList(islandManagementReq, islandManagement);
            islandQuotationMapper.insertBatch(islandQuotationList);
            response.setData(insert);
        } catch (Exception e) {
            response = ResponseApi.error(null, BusinessErrorCodeEnum.ISLAND_MANAGEMENT_ADD_FAIL.getCode(),
                BusinessErrorCodeEnum.ISLAND_MANAGEMENT_ADD_FAIL.getMessageCN());
        }
        return response;
    }

    private void convertManagementEntity(IslandManagementReq islandManagementReq, IslandManagement islandManagement) {
        islandManagement.setIslandDesc(islandManagementReq.getIslandDesc());
        islandManagement.setIslandEnName(islandManagementReq.getIslandEnName());
        islandManagement.setIslandFile(islandManagementReq.getIslandFile());
        islandManagement.setIslandIntro(islandManagementReq.getIslandIntro());
        islandManagement.setIslandImage(islandManagementReq.getIslandImage());
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
        return response.setData(i);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi islandManagementDelete(int islandIndexCode) {
        ResponseApi response = ResponseApi.ok();
        try {
            // 删除主表
            int i = islandManagementMapper.deleteById(islandIndexCode);
            // 删除关联表
            islandTagRelationMapper
                .delete(new QueryWrapper<IslandTagRelation>().eq(IslandTagRelation.ISLAND_INDEX_CODE, islandIndexCode));
            // 更新pdf报价单
            response.setData(i);
        } catch (Exception e) {
            response = ResponseApi.error(null, BusinessErrorCodeEnum.ISLAND_MANAGEMENT_DELETE_FAIL.getCode(),
                BusinessErrorCodeEnum.ISLAND_MANAGEMENT_DELETE_FAIL.getMessageCN());
        }
        return response;
    }

    @Override
    public ResponseApi<IslandManagementQueryListResp>
        islandManagementQueryList(IslandManagementReq islandManagementReq) {
        if (islandManagementReq.getPage() > 0 && islandManagementReq.getPageSize() > 0) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("page", islandManagementReq.getPage());
            map.put("pageSize", islandManagementReq.getPageSize());
            map.put("islandCnName", islandManagementReq.getIslandCnName());
            map.put("islandEnName", islandManagementReq.getIslandEnName());
            map.put("tagIndexCodeList",islandManagementReq.getIslandIndexCode());
            List<IslandManagementTag> islandManagementTagInfos =
                islandManagementMapper.queryIslandManagementList(map);
            // 按照islandIndexCode分组,其中的islandCnName进行拼接
            List<IslandManagementTagInfo> islandManagementTagInfoList =
               new ArrayList<>();
            Map<Integer,IslandManagementTagInfo> islandManagementTagInfoMap = new HashMap<>();
            islandManagementTagInfos.forEach(item -> {
                if(islandManagementTagInfoMap.containsKey(item.getIslandIndexCode())){
                    IslandManagementTagInfo islandManagementTagInfo = islandManagementTagInfoMap.get(item.getIslandIndexCode());
                    islandManagementTagInfo.setIslandCnName(islandManagementTagInfo.getIslandCnName()+","+item.getIslandCnName());
                }else{
                    IslandManagementTagInfo info = new IslandManagementTagInfo();
                    info.setIslandFile(item.getIslandFile());
                    info.setIslandCnName(item.getIslandCnName());
                    info.setIslandDesc(item.getIslandDesc());
                    info.setIslandEnName(item.getIslandEnName());
                    info.setTagName(item.getTagName());
                    info.setIslandIndexCode(item.getIslandIndexCode());
                    islandManagementTagInfoMap.put(item.getIslandIndexCode(),info);
                }
            });
            //map转list
            islandManagementTagInfoMap.forEach((k,v)->{
                islandManagementTagInfoList.add(v);
            });

            IslandManagementQueryListResp islandManagementQueryListResp = new IslandManagementQueryListResp();
            islandManagementQueryListResp.setPage(islandManagementReq.getPage());
            islandManagementQueryListResp.setPageSize(islandManagementReq.getPageSize());
            islandManagementQueryListResp.setIslandManagementTagInfoList(islandManagementTagInfoList);
            return ResponseApi.ok(islandManagementQueryListResp);
        }
        return ResponseApi.error();
    }

    @Override
    public ResponseApi islandManagementQueryDetail(int islandIndexCode) {
        ResponseApi response = ResponseApi.ok();
        IslandManagement islandManagement = islandManagementMapper.selectById(islandIndexCode);
        if (islandManagement != null) {
            response.setData(islandManagement);
        }
        return response;
    }
}
