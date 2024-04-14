package com.eva.dtholiday.commons.dao.mapper.portalmanagement;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandManagement;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandManagementTag;

/**
 * @author fengsuohua
 */
@Mapper
public interface IslandManagementMapper extends BaseMapper<IslandManagement> {
    /**
     * 获取所有海岛信息
     *
     * @return
     */
    List<IslandManagementTag> queryIslandManagementList(@Param("map") Map<String, Object> map);

    int  queryIslandManagementListCount(@Param("map") Map<String, Object> map);


}
