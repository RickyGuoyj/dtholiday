package com.eva.dtholiday.system.controller.easyexcel;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.system.service.easyexcel.EasyExcelStrategy;
import com.eva.dtholiday.system.service.easyexcel.EasyExcelStrategyFactory;

@RestController
@RequestMapping("/excel")
public class EasyExcelController {
    @Autowired
    private EasyExcelStrategyFactory easyExcelStrategyFactory;

    @PostMapping("/import/{type}")
    public ResponseApi importExcel(@RequestPart(value = "file") MultipartFile file, @PathVariable("type") int type) {

        EasyExcelStrategy strategy = easyExcelStrategyFactory.getStrategy(type);
        return strategy.importExcel(file);
    }

    @PostMapping("/export/{type}")
    public void exportExcel(@PathVariable("type") int type, HttpServletResponse response) {
        EasyExcelStrategy strategy = easyExcelStrategyFactory.getStrategy(type);
        strategy.exportExcel(response);
    }


}
