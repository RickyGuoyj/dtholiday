package com.eva.dtholiday.commons.dao.mapper.portalmanagement;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandTagRelation;

@Mapper
public interface IslandTagRelationMapper extends BaseMapper<IslandTagRelation> {
    int insertBatch(@Param("list") List<IslandTagRelation> list);

    int deleteBatchByIslandIndexCode(@Param("list") List<Integer> islandIndexCodeList);

    int deleteBatchByTagIndexCode(@Param("list") List<Integer> tagIndexCodeList);
}
