package com.eva.dtholiday.system.service.portalmanagement.impl;

import org.springframework.stereotype.Service;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandManagementReq;
import com.eva.dtholiday.system.service.portalmanagement.IslandManagementService;

@Service
public class IslandManagementServiceImpl implements IslandManagementService {

    @Override
    public void islandManagementAdd(IslandManagementReq IslandManagementReq) {

    }

    @Override
    public void islandManagementUpdate(IslandManagementReq IslandManagementReq) {

    }

    @Override
    public void islandManagementDelete(String islandIndexCode) {

    }

    @Override
    public ResponseApi islandManagementQueryList() {
        return null;
    }

    @Override
    public ResponseApi islandManagementQueryDetail(String islandIndexCode) {
        return null;
    }
}
