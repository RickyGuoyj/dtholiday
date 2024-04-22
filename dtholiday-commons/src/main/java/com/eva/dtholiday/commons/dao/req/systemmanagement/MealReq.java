package com.eva.dtholiday.commons.dao.req.systemmanagement;

import lombok.Data;

/**
 * 新增、修改、查询详情餐型请求
 */
@Data
public class MealReq {
    private Integer mealIndexCode;
    private String mealName;
}
