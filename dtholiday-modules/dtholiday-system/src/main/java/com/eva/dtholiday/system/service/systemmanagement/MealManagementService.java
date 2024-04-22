package com.eva.dtholiday.system.service.systemmanagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.systemmanagement.Meal;
import com.eva.dtholiday.commons.dao.req.systemmanagement.MealDeleteReq;
import com.eva.dtholiday.commons.dao.req.systemmanagement.MealReq;
import com.eva.dtholiday.commons.dao.resp.systemmanagement.MealManagementQueryListResp;

public interface MealManagementService {
    /**
     * 新增餐型
     *
     * @param req 入参
     * @return 响应
     */
    ResponseApi<Integer> addMeal(MealReq req);

    /**
     * 更新餐型
     *
     * @param req 入参
     * @return 返回
     */
    ResponseApi<Integer> updateMeal(MealReq req);

    /**
     * 删除餐型
     *
     * @param req 入参
     * @return 返回
     */
    ResponseApi<Integer> deleteMeal(MealDeleteReq req);

    /**
     * 查询餐型列表
     *
     * @return 返回
     */
    ResponseApi<MealManagementQueryListResp> queryMeallist();

    /**
     * 查询餐型详情
     *
     * @param req 入参
     * @return 返回
     */
    ResponseApi<Meal> queryMealDetail(MealReq req);
}
