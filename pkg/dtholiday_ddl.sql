-- 创建数据库
CREATE DATABASE IF NOT EXISTS dtholiday;

-- 选择数据库
USE dtholiday;

-- 创建表（缺少注释）
-- 岛屿管理表
CREATE TABLE IF NOT EXISTS `dt_island` (
                             `island_index_code` int NOT NULL AUTO_INCREMENT,
                             `island_cn_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                             `island_en_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                             `island_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
                             `island_intro` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
                             `island_image` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                             `island_file` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                             `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                             `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                             PRIMARY KEY (`island_index_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 岛屿标签表
CREATE TABLE `dt_island_tag` (
                                 `tag_index_code` int NOT NULL AUTO_INCREMENT,
                                 `tag_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                 `tag_image` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                 `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`tag_index_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 岛屿管理-=便签关联表
CREATE TABLE IF NOT EXISTS `dt_island_tag_relation` (
                                          `relation_index_code` int NOT NULL AUTO_INCREMENT,
                                          `island_index_code` int NOT NULL,
                                          `tag_index_code` int NOT NULL,
                                          `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                          `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                          PRIMARY KEY (`relation_index_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 岛屿报价单表
CREATE TABLE `dt_island_quotation` (
                                       `quotation_index_code` int NOT NULL AUTO_INCREMENT,
                                       `island_index_code` int NOT NULL,
                                       `quotation_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                       `quotation_type` int NOT NULL,
                                       `quotation_file` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                       `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                       `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                       PRIMARY KEY (`quotation_index_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 岛屿报价单关联表
CREATE TABLE IF NOT EXISTS `dt_island_quotation_relation` (
                                                              `relation_index_code` int NOT NULL AUTO_INCREMENT,
                                                              `island_index_code` int NOT NULL,
                                                              `quotation_index_code` int NOT NULL,
                                                              `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                                              `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                                              PRIMARY KEY (`relation_index_code`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 岛屿推荐表
CREATE TABLE IF NOT EXISTS `dt_island_recommendation` (
                                            `recommendation_index_code` int NOT NULL AUTO_INCREMENT,
                                            `island_cn_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                            `island_en_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                            `island_index_code` int DEFAULT NULL,
                                            `recommendation_image` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                            `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                            `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                            PRIMARY KEY (`recommendation_index_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 岛屿文章表

CREATE TABLE IF NOT EXISTS dt_island_article (
    article_index_code INT NOT NULL AUTO_INCREMENT,
    title VARCHAR ( 255 ),
    subtitle VARCHAR ( 255 ),
    type INT,
    content text,
    is_display VARCHAR ( 8 ),
    article_images text,
    island_index_code INT,
    island_cn_name VARCHAR ( 64 ),
    links VARCHAR ( 255 ),
    create_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY ( `article_index_code` ) USING BTREE
    ) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS sys_user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(64) UNIQUE NOT NULL ,
    user_code VARCHAR(64) NOT NULL,
    password VARCHAR(64) NOT NULL,
    nick_name VARCHAR(64),
    email_addr VARCHAR(255),
    phone_num VARCHAR(64),
    gender INT,
    dept_leader_name VARCHAR(64),
    status INT DEFAULT 1,
    belong_company VARCHAR(255) not null,
    belong_company_phone VARCHAR(255),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS sys_role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(64) UNIQUE NOT NULL ,
    role_code VARCHAR(64) NOT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS sys_menu (
    id INT AUTO_INCREMENT PRIMARY KEY,
    menu_name VARCHAR(64) NOT NULL ,
    menu_code VARCHAR(64) NOT NULL,
    parent_code VARCHAR(64),
    type VARCHAR(64),
    url VARCHAR(64),
    perms VARCHAR(64),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS sys_user_role (
    user_code VARCHAR(64),
    role_code VARCHAR(64)
    );

CREATE TABLE IF NOT EXISTS sys_role_menu (
    role_code VARCHAR(64),
    menu_code VARCHAR(64)
    );

-- 币种管理
CREATE TABLE `dt_currency_management` (
                                          `currency_index_code` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                          `country_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '国家名',
                                          `currency_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '币种名称',
                                          `currency_code` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '币种代码',
                                          `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                          `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                          PRIMARY KEY (`currency_index_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 餐型管理
CREATE TABLE `dt_meal_management` (
                                      `meal_index_code` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                      `meal_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '餐型名称 ',
                                      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      PRIMARY KEY (`meal_index_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 交通工具管理
CREATE TABLE `dt_traffic_management` (
                                         `traffic_index_code` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                         `traffic_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '交通工具名称',
                                         `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                         PRIMARY KEY (`traffic_index_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `dt_transition_hotel`
(
    `transition_hotel_id`   INT(8) NOT NULL AUTO_INCREMENT,
    `transition_hotel_name` VARCHAR(255),
    `transition_hotel_type` VARCHAR(255),
    `effective_date`        DATE,
    `expiry_date`           DATE,
    `total_num`             INT(8) NOT NULL DEFAULT 0,
    `remain_num`            INT(8) NOT NULL DEFAULT 0,
    `remark`                TEXT,
    `create_time`           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_time`           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`transition_hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `dt_plane_ticket`
(
    `plane_ticket_id`      INT(8) NOT NULL AUTO_INCREMENT,
    `airline_company_name` VARCHAR(255),
    `departure_date`       DATE,
    `return_date`          DATE,
    `days`                 VARCHAR(50),
    `departure_flight`     VARCHAR(20),
    `return_flight`        VARCHAR(20),
    `departure_place`      VARCHAR(255),
    `arrival_place`        VARCHAR(255),
    `price`                DECIMAL(10, 2) NOT NULL DEFAULT 0,
    `total_num`            INT(8) NOT NULL DEFAULT 0,
    `remain_num`           INT(8) NOT NULL DEFAULT 0,
    `currency_type`        TINYINT(4),
    `create_time`          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_time`          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`plane_ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `dt_extra_child_expense`
(
    `extra_child_expense_id`          INT(8) NOT NULL AUTO_INCREMENT COMMENT '额外儿童费用ID',
    `start_age`                       VARCHAR(32) COMMENT '开始年龄',
    `end_age`                         VARCHAR(32) COMMENT '结束年龄',
    `price`                           DOUBLE    NOT NULL DEFAULT 0 COMMENT '费用价格',
    `currency_type`                   TINYINT(4) COMMENT '货币类型',
    `extra_child_delay_hotel_price`   DOUBLE    NOT NULL DEFAULT 0 COMMENT '额外儿童延迟酒店价格',
    `extra_child_delay_currency_type` TINYINT(4) COMMENT '额外儿童延迟货币类型',
    `extra_child_hotel_price`         DOUBLE    NOT NULL DEFAULT 0 COMMENT '额外儿童酒店价格',
    `extra_child_traffic_price`       DOUBLE    NOT NULL DEFAULT 0 COMMENT '额外儿童交通价格',
    `extra_child_meal_price`          DOUBLE    NOT NULL DEFAULT 0 COMMENT '额外儿童餐饮价格',
    `extra_child_env_tax`             DOUBLE    NOT NULL DEFAULT 0 COMMENT '额外儿童环保税',
    `extra_child_charge`              DOUBLE    NOT NULL DEFAULT 0 COMMENT '额外儿童收费',
    `island_index_code`               INT(8) COMMENT '岛屿索引代码',
    `island_cn_name`                  VARCHAR(255) COMMENT '岛屿中文名称',
    `remark`                          TEXT COMMENT '备注信息',
    `create_time`                     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`                     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`extra_child_expense_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='额外儿童费用表';

CREATE TABLE IF NOT EXISTS  `dt_island_hotel`
(
                                   `island_hotel_id` INT AUTO_INCREMENT COMMENT '岛屿酒店ID',
                                   `island_index_code` INT COMMENT '岛屿编码',
                                   `price_type` INT COMMENT '价格类型：1-打包价，2-合同价',
                                   `traffic_type` INT COMMENT '上岛工具类型',
                                   `meal_type` INT COMMENT '餐型类型',
                                   `hotel_room_type` VARCHAR(255) COMMENT '房型描述',
                                   `has_environment_tax` INT COMMENT '是否含环境税：0-不含，1-含',
                                   `effective_date` DATE COMMENT '生效日期',
                                   `expiry_date` DATE COMMENT '失效日期',
                                   `total_num` INT COMMENT '总量',
                                   `remain_num` INT COMMENT '余量',
                                   `package_nights` INT COMMENT '打包间夜数',
                                   `currency_type` INT COMMENT '币种类型',
                                   `package_price` DECIMAL(10, 2) COMMENT '打包价格',
                                   `traffic_price` DECIMAL(10, 2) COMMENT '往返交通费',
                                   `meal_price` DECIMAL(10, 2) COMMENT '餐饮价格',
                                   `environment_tax` DECIMAL(10, 2) COMMENT '环境税',
                                   `extra_expense` DECIMAL(10, 2) COMMENT '附加费',
                                   `extra_adult_hotel_price` DECIMAL(10, 2) COMMENT '额外成人酒店价格',
                                   `extra_adult_traffic_price` DECIMAL(10, 2) COMMENT '额外成人交通价格',
                                   `extra_adult_meal_price` DECIMAL(10, 2) COMMENT '额外成人餐饮价格',
                                   `extra_adult_environment_tax` DECIMAL(10, 2) COMMENT '额外成人环境税',
                                   `extra_adult_package_price` DECIMAL(10, 2) COMMENT '额外成人打包价',
                                   `extra_adult_currency_type` INT COMMENT '额外成人币种',
                                   `delay_hotel_room_type` VARCHAR(255) COMMENT '本身的2人延住房型',
                                   `delay_hotel_room_price` DECIMAL(10, 2) COMMENT '2人的延住1天的价格',
                                   `delay_currency_type` INT COMMENT '延住价格的币种',
                                   `extra_adult_delay_hotel_room_price` DECIMAL(10, 2) COMMENT '额外成人延住价格',
                                   `extra_adult_delay_currency_type` INT COMMENT '额外成人延住币种',
                                   `special_price` DECIMAL(10, 2) COMMENT '特殊费用（如圣诞元旦）',
                                   `remarks` TEXT COMMENT '备注信息',
                                   PRIMARY KEY (`island_hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岛屿酒店信息表';

CREATE TABLE IF NOT EXISTS `dt_order_plane_ticket`  (
    `plane_ticket_order_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '机票订单id',
    `order_type` INT NOT NULL COMMENT '订单类型',
    `customer_name` VARCHAR(255) NOT NULL COMMENT '客户信息',
    `adult_num` INT  COMMENT '成人数量',
    `child_num` INT  COMMENT '儿童数量',
    `plane_ticket_id` INT(11) NOT NULL COMMENT '机票id',
    `airline_company_name` VARCHAR(255) NOT NULL COMMENT '航空公司名称',
    `departure_date` DATE NOT NULL COMMENT '出发日期',
    `return_date` DATE NOT NULL COMMENT '返程日期',
    `days` VARCHAR(50) COMMENT '航班天数',
    `departure_flight` VARCHAR(255) NOT NULL COMMENT '出发航班',
    `return_flight` VARCHAR(255) NOT NULL COMMENT '返程航班',
    `departure_place` VARCHAR(255) NOT NULL COMMENT '出发地点',
    `arrival_place` VARCHAR(255) NOT NULL COMMENT '到达地点',
    `price` DECIMAL(10, 2) NOT NULL COMMENT '机票价格',
    `currency_type` INT NOT NULL COMMENT '货币类型',
    `total_price` DECIMAL(10, 2) NOT NULL COMMENT '机票订单总价格',
    `discount_price` DECIMAL(10, 2) COMMENT '优惠后价格',
    `cost_price` DECIMAL(10, 2) COMMENT '成本价',
    `discount` DECIMAL(10, 2) COMMENT '折扣金额',
    `ticket_number` VARCHAR(30)  COMMENT '机票号',
    `order_status` INT DEFAULT 0 COMMENT '订单状态',
    `financial_status` INT DEFAULT 0 COMMENT '财务状态',
    `sale_man` VARCHAR(255) COMMENT '销售员',
    `financial_man` VARCHAR(255) COMMENT '财务员',
    `confirm_info` VARCHAR(1024) COMMENT '确认号',
    `remarks` VARCHAR(1024) COMMENT '备注',
    `order_creator` VARCHAR(255) NOT NULL COMMENT '订单创建人',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`plane_ticket_order_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='机票订单表';

CREATE TABLE IF NOT EXISTS `dt_order_transition_hotel`  (
    `transition_hotel_order_id` INT NOT NULL AUTO_INCREMENT COMMENT '订单ID',
    `order_type` INT NOT NULL COMMENT '订单类型',
    `customer_name` VARCHAR(255) COMMENT '客人名称',
    `adult_num` INT  COMMENT '成人数量',
    `child_num` INT  COMMENT '孩子数量',
    `room_num` INT  COMMENT '房间数量',
    `nights` INT  COMMENT '间夜数',
    `first_child_age` INT COMMENT '第一个孩子的年龄',
    `second_child_age` INT COMMENT '第二个孩子的年龄',
    `transition_hotel_id` INT NOT NULL COMMENT '过度酒店产品ID',
    `transition_hotel_name` VARCHAR(255) COMMENT '过度酒店产品名称',
    `transition_hotel_type` VARCHAR(255) COMMENT '过度酒店产品房型',
    `effective_date` DATE COMMENT '过度酒店产品生效日期',
    `expiry_date` DATE COMMENT '过度酒店产品失效日期',
    `total_price` DECIMAL(10, 2) COMMENT '订单总价',
    `discount_price` DECIMAL(10, 2) COMMENT '订单折扣后价格',
    `cost_price` DECIMAL(10, 2)  COMMENT '订单成本价',
    `discount` DECIMAL(10, 2) COMMENT '订单折扣',
    `booking_code` VARCHAR(255) COMMENT '过度酒店确认号',
    `currency_type` INT NOT NULL COMMENT '订单货币类型',
    `order_status` INT DEFAULT 0 COMMENT '订单状态',
    `financial_status` INT DEFAULT 0 COMMENT '财务状态',
    `sale_man` VARCHAR(255) COMMENT '销售',
    `financial_man` VARCHAR(255) COMMENT '财务',
    `confirm_info` VARCHAR(1024) COMMENT '确认号',
    `remarks` VARCHAR(1024) COMMENT '备注',
    `order_creator` VARCHAR(255) COMMENT '订单创建人',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`transition_hotel_order_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 主订单表
CREATE TABLE IF NOT EXISTS  `dt_order_main` (
                                 `main_order_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主订单主键id',
                                 `island_hotel_order_id` int DEFAULT NULL COMMENT '酒店订单id',
                                 `plane_ticket_order_id` int DEFAULT NULL COMMENT '机票订单id',
                                 `transition_hotel_order_id` int DEFAULT NULL COMMENT '过度酒店订单id',
                                 `total_price` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '总价',
                                 `island_hotel_order_status` int DEFAULT null COMMENT '岛屿酒店订单状态',
                                 `plane_ticket_order_status` int DEFAULT null COMMENT '机票订单状态',
                                 `transition_hotel_order_status` int DEFAULT null COMMENT '过度酒店订单状态',
                                 `order_status` int DEFAULT 0 COMMENT '主订单状态',
                                 `financial_status` int DEFAULT 0 COMMENT '财务状态',
                                 `order_creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '订单创建人',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 `sale_man` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '销售员',
                                 `financial_man` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '财务',
                                 `remarks` VARCHAR(1024) COMMENT '备注',
                                 `payment_time` datetime DEFAULT NULL COMMENT '收款时间',
                                `main_order_cancel_status` int DEFAULT NULL COMMENT '主订单取消状态',
                                PRIMARY KEY (`main_order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='主订单表';

--  岛屿订单表
CREATE TABLE IF NOT EXISTS `dt_order_island_hotel` (
                                   `island_hotel_order_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '岛屿酒店订单主键',
                                   `order_type` int DEFAULT NULL COMMENT '订单类型',
                                   `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客人姓名',
                                   `nights` int DEFAULT NULL COMMENT '入住间夜数',
                                   `adult_num` int DEFAULT NULL COMMENT '成人数量',
                                   `child_num` int DEFAULT NULL COMMENT '儿童数量',
                                   `first_child_age` int DEFAULT NULL COMMENT '第一个儿童年龄',
                                   `second_child_age` int DEFAULT NULL COMMENT '第二个儿童年龄',
                                   `effective_date` date DEFAULT NULL COMMENT '入住日期',
                                   `expiry_date` date DEFAULT NULL COMMENT '离开日期',
                                   `total_price` decimal(10,2) DEFAULT NULL COMMENT '酒店订单总价',
                                   `discount_price` decimal(10,2) DEFAULT NULL COMMENT '优惠后总价',
                                   `currency_type` int DEFAULT NULL COMMENT '货币种类',
                                   `cost_price` decimal(10,2) DEFAULT NULL COMMENT '成本价',
                                   `discount` decimal(10,2) DEFAULT NULL COMMENT '折扣',
                                   `booking_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '确认号',
                                   `order_status` int DEFAULT '0' COMMENT '订单状态',
                                   `financial_status` int DEFAULT '0' COMMENT '财务状态',
                                   `sale_man` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '销售员',
                                   `financial_man` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '财务',
                                   `order_creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '订单创建人',
                                   `confirm_info` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '确认号',
                                   `remarks` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                   `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                   `island_index_code` int DEFAULT NULL COMMENT '岛屿id',
                                   `island_cn_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '岛屿名称',
                                   `traffic_type` int DEFAULT NULL COMMENT '上岛工具',
                                   `meal_type` int DEFAULT NULL COMMENT '餐型',
                                   `hotel_room_type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '房型',
                                   `delay_hotel_room_type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '延住房型',
                                   `has_environment_tax` int DEFAULT NULL COMMENT '是否包含环境税',
                                   PRIMARY KEY (`island_hotel_order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='岛屿酒店订单表';

CREATE TABLE IF NOT EXISTS `dt_payment` (
                           `payment_id` INT NOT NULL AUTO_INCREMENT, -- 支付ID
                           `main_order_id` INT NOT NULL, -- 主订单ID
                           `payment_date` DATE NOT NULL, -- 支付日期
                           `payment_amount_usd` DECIMAL(10, 2) NOT NULL, -- USD支付金额
                           `exchange_rate` DECIMAL(5, 4) NOT NULL, -- 汇率
                           `payment_amount_usd_to_cny` DECIMAL(10, 2) NOT NULL, -- USD转换为CNY的金额
                           `payment_amount_cny` DECIMAL(10, 2) NOT NULL, -- CNY支付金额
                           `payment_total` DECIMAL(10, 2) NOT NULL, -- 总支付金额
                           `currency_type` TINYINT NOT NULL, -- 货币类型（假设为整数编码）
                           `payment_type` VARCHAR(255) NOT NULL, -- 支付方式
                           `payment_remarks` VARCHAR(255), -- 支付备注
                           `sale_man` VARCHAR(50) NOT NULL, -- 销售员
                           `financial_man` VARCHAR(50) , -- 财务人员
                           `financial_status` TINYINT NOT NULL, -- 财务状态（假设为整数编码）
                           `company_name` VARCHAR(255) NOT NULL, -- 公司名称`
                           `check_remark` VARCHAR(255) NOT NULL, -- 审核备注`
                           `payment_pics` text not null, -- 支付图片
                           `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='订单付款表';





