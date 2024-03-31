package com.eva.dtholiday.commons.dao.mapper.portalmanagement;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandManagement;

import java.util.List;

/**
 * @author fengsuohua
 */
@Mapper
public interface IslandManagementMapper extends BaseMapper<IslandManagement> {
    /**
     * 查询列表
     */
    List<IslandManagement> queryIslandManagementList();

    /**
     * 查询详情
     */
    IslandManagement queryIslandManagementDetail(String islandIndexCode);

    /**
     * 添加
     */
    int addIslandManagement(IslandManagement islandManagement);

    /**
     * 修改
     */
    int updateIslandManagement(IslandManagement islandManagement);

    /**
     * 删除
     */
    int deleteIslandManagement(String islandIndexCode);

}
