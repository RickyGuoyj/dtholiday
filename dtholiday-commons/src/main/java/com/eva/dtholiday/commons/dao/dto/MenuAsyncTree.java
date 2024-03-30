package com.eva.dtholiday.commons.dao.dto;

import lombok.Data;

import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/30 13:10
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class MenuAsyncTree {

    private String name;

    private String code;

    private String url;

    private List<MenuAsyncTree> children;
}
