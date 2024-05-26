package com.eva.dtholiday.system.service.systemmanagement.impl;

import java.util.List;

import javax.annotation.Resource;

import com.eva.dtholiday.commons.utils.LocalCache;
import org.springframework.stereotype.Service;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.systemmanagement.Meal;
import com.eva.dtholiday.commons.dao.mapper.systemmanagement.MealMapper;
import com.eva.dtholiday.commons.dao.req.systemmanagement.MealDeleteReq;
import com.eva.dtholiday.commons.dao.req.systemmanagement.MealReq;
import com.eva.dtholiday.commons.dao.resp.systemmanagement.MealManagementQueryListResp;
import com.eva.dtholiday.system.service.systemmanagement.MealManagementService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MealManagementServiceImpl implements MealManagementService {
    @Resource
    private MealMapper mealMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi<Integer> addMeal(MealReq req) {
        Meal meal = new Meal();
        meal.setMealName(req.getMealName());
        int insert = mealMapper.insert(meal);
        LocalCache.putMealName(meal.getMealIndexCode(), meal.getMealName());
        return ResponseApi.ok(insert);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi<Integer> updateMeal(MealReq req) {
        Meal meal = new Meal();
        meal.setMealName(req.getMealName());
        meal.setMealIndexCode(req.getMealIndexCode());
        int update = mealMapper.updateById(meal);
        LocalCache.putMealName(meal.getMealIndexCode(), meal.getMealName());
        return ResponseApi.ok(update);
    }

    @Override
    public ResponseApi<Integer> deleteMeal(MealDeleteReq req) {
        int i = mealMapper.deleteBatchIds(req.getMealIndexCodeList());
        return ResponseApi.ok(i);
    }

    @Override
    public ResponseApi<MealManagementQueryListResp> queryMeallist() {
        List<Meal> records = mealMapper.selectList(null);
        MealManagementQueryListResp resp = new MealManagementQueryListResp();
        resp.setMealList(records);
        return ResponseApi.ok(resp);
    }

    @Override
    public ResponseApi<Meal> queryMealDetail(MealReq req) {
        Meal meal = mealMapper.selectById(req.getMealIndexCode());
        return ResponseApi.ok(meal);
    }

    @Override
    public void loadAllMealName() {
        List<Meal> records = mealMapper.selectList(null);
        if (records.size() > 0) {
            records.forEach(meal -> {
                String mealName = meal.getMealName();
                Integer mealIndexCode = meal.getMealIndexCode();
                LocalCache.putMealName(mealIndexCode, mealName);
            });
        }
    }
}
