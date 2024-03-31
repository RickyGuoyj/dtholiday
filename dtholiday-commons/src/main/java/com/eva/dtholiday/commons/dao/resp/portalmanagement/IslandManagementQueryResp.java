package com.eva.dtholiday.commons.dao.resp.portalmanagement;

import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandManagement;
import lombok.Data;

import java.util.List;

/**
 * @author fengsuohua
 */
@Data
public class IslandManagementQueryResp {
    private List<IslandManagement> islandManagementList;
}
