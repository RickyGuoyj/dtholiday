package com.eva.dtholiday.commons.dao.resp.systemmanagement;

import java.util.List;

import com.eva.dtholiday.commons.dao.entity.systemmanagement.Meal;

import lombok.Data;

@Data
public class MealManagementQueryListResp {
    private List<Meal> mealList;
}
