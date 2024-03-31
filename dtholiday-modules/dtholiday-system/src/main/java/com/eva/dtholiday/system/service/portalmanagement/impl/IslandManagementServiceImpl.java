package com.eva.dtholiday.system.service.portalmanagement.impl;

import org.springframework.stereotype.Service;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandManagementReq;
import com.eva.dtholiday.system.service.portalmanagement.IslandManagementService;

@Service
public class IslandManagementServiceImpl implements IslandManagementService {
    @Override
    public void addIslandManagement(IslandManagementReq islandManagementReq) {

    }

    @Override
    public void updateIslandManagement(IslandManagementReq islandManagementReq) {

    }

    @Override
    public void deleteIslandManagement(String islandIndexCode) {

    }

    @Override
    public ResponseApi queryIslandManagementList() {
        return null;
    }

    @Override
    public ResponseApi queryIslandManagementDetail(String islandIndexCode) {
        return null;
    }
}
