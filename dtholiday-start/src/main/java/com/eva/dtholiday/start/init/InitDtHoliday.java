package com.eva.dtholiday.start.init;

import com.eva.dtholiday.system.service.portalmanagement.IslandManagementService;
import com.eva.dtholiday.system.service.portalmanagement.IslandTagService;
import com.eva.dtholiday.system.service.systemmanagement.MealManagementService;
import com.eva.dtholiday.system.service.systemmanagement.TrafficManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class InitDtHoliday {

    @Resource
    private IslandManagementService islandManagementService;

    @Resource
    private IslandTagService islandTagService;

    @Resource
    private MealManagementService mealManagementService;

    @Resource
    private TrafficManagementService trafficManagementService;

    @PostConstruct
    public void init() {
        // 加载数据
        islandManagementService.loadAllIslandName();

        islandTagService.loadAllTagName();

        mealManagementService.loadAllMealName();

        trafficManagementService.loadAllTrafficName();

    }
}
   