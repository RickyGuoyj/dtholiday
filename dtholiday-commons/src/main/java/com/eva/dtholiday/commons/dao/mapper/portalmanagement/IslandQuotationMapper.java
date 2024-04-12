package com.eva.dtholiday.commons.dao.mapper.portalmanagement;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandQuotation;

@Mapper
public interface IslandQuotationMapper extends BaseMapper<IslandQuotation> {
    int  insertBatch(@Param("list") List<IslandQuotation> list );

    List<IslandQuotation> selectIslandQuotationByIslandIndexCode(@Param("islandIndexCode") Integer islandIndexCode);
}
