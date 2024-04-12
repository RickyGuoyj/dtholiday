package com.eva.dtholiday.commons.dao.mapper.portalmanagement;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandManagement;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandTag;

@Mapper
public interface IslandTagMapper extends BaseMapper<IslandTag> {

    List<IslandTag> selectTagsByIslandIndexCode(@Param("islandIndexCode")Integer islandIndexCode);
}
