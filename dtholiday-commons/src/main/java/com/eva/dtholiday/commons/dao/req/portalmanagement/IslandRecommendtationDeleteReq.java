package com.eva.dtholiday.commons.dao.req.portalmanagement;


import java.util.List;

import lombok.Data;

@Data
public class IslandRecommendtationDeleteReq {
    List<Integer> recommendationIndexCodeList;
}
