package com.eva.dtholiday.start.init;

import com.eva.dtholiday.system.service.portalmanagement.IslandManagementService;
import com.eva.dtholiday.system.service.portalmanagement.IslandTagService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class InitDtHoliday {

    @Resource
    private IslandManagementService islandManagementService;

    @Resource
    private IslandTagService islandTagService;

    @PostConstruct
    public void init() {
        // 加载数据
        islandManagementService.loadAllIslandName();

        islandTagService.loadAllTagName();
    }
}
   