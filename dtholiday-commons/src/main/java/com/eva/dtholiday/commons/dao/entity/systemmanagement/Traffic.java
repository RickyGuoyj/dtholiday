package com.eva.dtholiday.commons.dao.entity.systemmanagement;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dt_traffic_management")
public class Traffic extends Model<Traffic> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "traffic_index_code", type = IdType.AUTO)
    private Integer trafficIndexCode;
    private String trafficName;
    private Timestamp createTime;
    private Timestamp updateTime;
}
