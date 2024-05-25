package com.eva.dtholiday.system.service.easyexcel.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.productManagement.IslandHotel;
import com.eva.dtholiday.commons.dao.mapper.productManagement.IslandHotelMapper;
import com.eva.dtholiday.commons.dao.req.productManagement.IslandHotelReq;
import com.eva.dtholiday.system.service.easyexcel.EasyExcelStrategy;
import com.eva.dtholiday.system.service.easyexcel.EasyExcelStrategyFactory;
import com.eva.dtholiday.system.service.productManagement.IslandHotelService;

@Service
public class IslandHotelExcelStrategyImpl implements EasyExcelStrategy {

    @Autowired
    private IslandHotelMapper islandHotelMapper;

    @Autowired
    private IslandHotelService islandHotelService;

    @PostConstruct
    public void register() {
        EasyExcelStrategyFactory.register(1, this);
    }

    @Override
    public void exportExcel(HttpServletResponse response) {
        try {
            String fileName = "岛屿酒店价格";
            setExcelResponseProp(response, fileName);
            List<IslandHotelReq> islandHotelReqList = new ArrayList<>();
            List<IslandHotel> islandHotels = islandHotelMapper.selectList(null);
            islandHotels.stream().forEach(islandHotel -> {
                IslandHotelReq islandHotelReq = new IslandHotelReq();
                BeanUtils.copyProperties(islandHotel, islandHotelReq);
                islandHotelReqList.add(islandHotelReq);
            });
            EasyExcel.write(response.getOutputStream()).head(IslandHotelReq.class).autoCloseStream(Boolean.FALSE)
                .sheet(fileName).doWrite(islandHotelReqList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseApi importExcel(MultipartFile file) {
        try {
            List<IslandHotelReq> islandHotelReqList =
                EasyExcel.read(file.getInputStream()).head(IslandHotelReq.class).sheet().doReadSync();
            islandHotelReqList.stream().forEach(islandHotelReq -> {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("island_hotel_id", islandHotelReq.getIslandHotelId());
                Long l = islandHotelMapper.selectCount(queryWrapper);
                if (l > 0) {
                    // 更新
                    islandHotelService.update(islandHotelReq);
                } else {
                    // 新增
                    islandHotelService.add(islandHotelReq);
                }
            });
            return ResponseApi.ok(islandHotelReqList.size());
        } catch (IOException e) {
            return ResponseApi.error("导入失败");
        }
    }
}
