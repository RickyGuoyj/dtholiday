package com.eva.dtholiday.system.controller.systemmanagement;

import com.eva.dtholiday.system.service.systemmanagement.MealManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.systemmanagement.MealDeleteReq;
import com.eva.dtholiday.commons.dao.req.systemmanagement.MealReq;

@RestController
@RequestMapping("/erp/systemManagement/mealManagement")
public class MealManagementController {
    @Autowired
    private MealManagementService mealManagementService;

    @PostMapping("/add")
    public ResponseApi addMeal(@RequestBody MealReq req) {
        return mealManagementService.addMeal(req);
    }

    @PostMapping("/update")
    public ResponseApi updateMeal(@RequestBody MealReq req) {
        return mealManagementService.updateMeal(req);
    }

    @PostMapping("/delete")
    public ResponseApi deleteMeal(@RequestBody MealDeleteReq req) {
        return mealManagementService.deleteMeal(req);
    }

    @PostMapping("/querylist")
    public ResponseApi queryMeallist() {
        return mealManagementService.queryMeallist();
    }

    @PostMapping("/querydetail")
    public ResponseApi queryMealDetail(@RequestBody MealReq req) {
        return mealManagementService.queryMealDetail(req);
    }
}
