package com.eva.dtholiday.system.service.easyexcel;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EasyExcelStrategyFactory {
    private  static Map<Integer, EasyExcelStrategy> strategyMap = new HashMap<>();

//    public EasyExcelStrategyFactory() {
//        strategyMap.put(1, ClassPathXmlApplicationContext.(IslandHotelExcelStrategyImpl.class));
//        strategyMap.put(2, new ExtraChildExcelStrategyImpl());
//    }

    public static void register(int type, EasyExcelStrategy strategy){
        strategyMap.put(type, strategy);
    }
    public EasyExcelStrategy getStrategy(int type) {
        return strategyMap.get(type);
    }
}
