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
    belong_company VARCHAR(255),
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




