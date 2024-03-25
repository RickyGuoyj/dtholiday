-- 创建数据库
CREATE DATABASE IF NOT EXISTS dtholiday;

-- 选择数据库
USE dtholiday;

-- 创建表（缺少注释）
CREATE TABLE IF NOT EXISTS dt_island(
    island_index_code  VARCHAR(64) NOT NULL PRIMARY KEY,
    island_cn_name VARCHAR(64),
    island_en_name VARCHAR(64),
    island_tags VARCHAR(255),
    island_desc text,
    island_intro text,
    island_pic VARCHAR(255),
    island_file VARCHAR(255),
    island_quotation_pdf_index_codes VARCHAR(255),
    island_quotation_pic_index_codes VARCHAR(255),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );


CREATE TABLE IF NOT EXISTS dt_island_tag(
    tag_index_code VARCHAR(64) NOT NULL PRIMARY KEY,
    tag_name  VARCHAR(64) NOT NULL,
    tag_url VARCHAR(255),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );


CREATE TABLE IF NOT EXISTS dt_island_quotation(
    quotation_index_code VARCHAR(64) NOT NULL PRIMARY KEY,
    island_index_code  VARCHAR(64) NOT NULL,
    quotation_file VARCHAR(255),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );


CREATE TABLE IF NOT EXISTS dt_recommendation(
    id INT AUTO_INCREMENT PRIMARY KEY,
    island_cn_name  VARCHAR(64),
    island_en_name VARCHAR(64),
    island_index_code VARCHAR(64),
    recommendation_file VARCHAR(255),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );


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
    menu_name VARCHAR(64) UNIQUE NOT NULL ,
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




