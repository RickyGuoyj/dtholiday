package com.eva.dtholiday.system.service.portalmanagement.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.eva.dtholiday.system.service.portalmanagement.IslandManagementService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandManagementQuotation;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandQuotation;
import com.eva.dtholiday.commons.dao.mapper.portalmanagement.IslandQuotationMapper;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandQuotationReq;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandQuotationQueryListResp;
import com.eva.dtholiday.system.service.portalmanagement.IslandQuotationService;
import org.springframework.util.CollectionUtils;

@Service
public class IslandQuotationServiceImpl implements IslandQuotationService {
    @Resource
    private IslandQuotationMapper islandQuotationMapper;

    @Resource
    private IslandManagementService islandManagementService;

    @Override
    public ResponseApi islandQuotationAdd(IslandQuotationReq islandQuotationReq) {
        int insert = islandQuotationMapper.insert(convertQuotationEntity(islandQuotationReq));
        return ResponseApi.ok(insert);
    }

    private IslandQuotation convertQuotationEntity(IslandQuotationReq islandQuotationReq) {
        IslandQuotation islandQuotation = new IslandQuotation();
        islandQuotation.setQuotationName(islandQuotationReq.getQuotationName());
        islandQuotation.setIslandIndexCode(islandQuotationReq.getIslandIndexCode());
        islandQuotation.setQuotationFile(islandQuotationReq.getQuotationFile());
        return islandQuotation;
    }

    @Override
    public ResponseApi islandQuotationQueryList(IslandQuotationReq islandQuotationReq) {
        // Page<IslandQuotation> page = new Page<>(islandQuotationReq.getPage(), islandQuotationReq.getPageSize());
        // Page<IslandQuotation> islandQuotationPage = islandQuotationMapper.selectPage(page, null);
        int total = islandQuotationMapper.selectIslandManagementQuotationCount();
        Map<String, Integer> map = new HashMap<>();
        map.put("from", (islandQuotationReq.getPage() - 1) * islandQuotationReq.getPageSize());
        map.put("to", islandQuotationReq.getPageSize());
        List<IslandManagementQuotation> islandManagementQuotationEntities =
                islandQuotationMapper.selectIslandManagementQuotationList(map);
        IslandQuotationQueryListResp resp = new IslandQuotationQueryListResp();
        if (!CollectionUtils.isEmpty(islandManagementQuotationEntities)) {
            islandManagementQuotationEntities.forEach(entity -> {
                entity.setIslandCnName(islandManagementService.getIslandName(entity.getIslandIndexCode()));
            });
        }
        resp.setIslandQuotationList(islandManagementQuotationEntities);
        resp.setPage(islandQuotationReq.getPage());
        resp.setPageSize(islandQuotationReq.getPageSize());
        resp.setTotal(total);
        return ResponseApi.ok(resp);
    }

    @Override
    public ResponseApi islandQuotationQueryDetail(int quotationIndexCode) {
        IslandQuotation islandQuotation = islandQuotationMapper.selectById(quotationIndexCode);
        return ResponseApi.ok(islandQuotation);
    }

    @Override
    public ResponseApi islandQuotationUpdate(IslandQuotationReq islandQuotationReq) {
        if (islandQuotationReq.getQuotationIndexCode() > 0) {
            Long count = islandQuotationMapper.selectCount(new QueryWrapper<IslandQuotation>()
                    .eq("quotation_index_code", islandQuotationReq.getQuotationIndexCode()));
            if (count > 0) {
                IslandQuotation islandQuotation = convertQuotationEntity(islandQuotationReq);
                islandQuotation.setQuotationIndexCode(islandQuotationReq.getQuotationIndexCode());
                int i = islandQuotationMapper.updateById(islandQuotation);
                return ResponseApi.ok(i);
            }
        }
        return ResponseApi.ok();
    }

    @Override
    public ResponseApi islandQuotationDelete(List<Integer> quotationIndexCodeList) {
        int i = islandQuotationMapper.deleteBatchIds(quotationIndexCodeList);
        return ResponseApi.ok(i);
    }
}
