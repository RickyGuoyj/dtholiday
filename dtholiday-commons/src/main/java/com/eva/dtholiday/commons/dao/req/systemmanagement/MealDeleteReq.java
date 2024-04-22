package com.eva.dtholiday.commons.dao.req.systemmanagement;

import java.util.List;

import lombok.Data;

@Data
public class MealDeleteReq {
    private List<Integer> mealIndexCodeList;
}
