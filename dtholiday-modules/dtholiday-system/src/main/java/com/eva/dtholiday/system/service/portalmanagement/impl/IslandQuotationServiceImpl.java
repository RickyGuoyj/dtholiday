package com.eva.dtholiday.system.service.portalmanagement.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandQuotation;
import com.eva.dtholiday.commons.dao.mapper.portalmanagement.IslandQuotationMapper;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandQuotationReq;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandQuotationQueryListResp;
import com.eva.dtholiday.system.service.portalmanagement.IslandQuotationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IslandQuotationServiceImpl implements IslandQuotationService {
    @Resource
    private IslandQuotationMapper islandQuotationMapper;

    @Override
    public ResponseApi islandQuotationAdd(IslandQuotationReq islandQuotationReq) {
        int insert = islandQuotationMapper.insert(convertQuotationEntity(islandQuotationReq));
        return ResponseApi.ok(insert);
    }

    private IslandQuotation convertQuotationEntity(IslandQuotationReq islandQuotationReq) {
        IslandQuotation islandQuotation = new IslandQuotation();
        islandQuotation.setIslandIndexCode(islandQuotationReq.getIslandIndexCode());
        islandQuotation.setQuotationFile(islandQuotationReq.getQuotationFile());
        return islandQuotation;
    }

    @Override
    public ResponseApi islandQuotationQueryList(IslandQuotationReq islandQuotationReq) {
        Page<IslandQuotation> page = new Page<>(islandQuotationReq.getPage(), islandQuotationReq.getPageSize());
        Page<IslandQuotation> islandQuotationPage = islandQuotationMapper.selectPage(page, null);
        IslandQuotationQueryListResp resp = new IslandQuotationQueryListResp();
        resp.setIslandQuotationList(islandQuotationPage.getRecords());
        resp.setPage(islandQuotationReq.getPage());
        resp.setPageSize(islandQuotationReq.getPageSize());
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
    public ResponseApi islandQuotationDelete(int quotationIndexCode) {
        int i = islandQuotationMapper.deleteById(quotationIndexCode);
        return ResponseApi.ok(i);
    }
}
