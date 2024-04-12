package com.eva.dtholiday.commons.dao.resp.portal;

import lombok.Data;

import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/13 1:03
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class IslandDetailResp {

    /**
     * 岛屿图片
     */
    private String banner;

    /**
     * 资料链接
     */
    private String datum_link;

    /**
     * 描述
     */
    private String desc;

    /**
     * 图片报价单
     */
    private List<String> image_price;

    /**
     * 介绍
     */
    private String intro;

    /**
     * 岛屿id
     */
    private Integer island_id;

    /**
     * 岛屿名称
     */
    private String name;

    /**
     * 岛屿英文名称
     */
    private String nameEn;

    /**
     * pdf报价单
     */
    private List<String> pdf_price;

    /**
     * 岛屿标签
     */
    private List<String> tags;



}
