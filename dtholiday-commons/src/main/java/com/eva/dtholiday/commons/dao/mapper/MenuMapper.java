package com.eva.dtholiday.commons.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eva.dtholiday.commons.dao.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/27 23:03
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> selectMenuByRoleCode(String roleCode);
}
