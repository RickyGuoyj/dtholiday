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
                                       `quotation_file` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                       `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                       `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                       PRIMARY KEY (`quotation_index_code`)
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
CREATE TABLE IF NOT EXISTS `dt_island_article` (
                                     `article_index_code` int NOT NULL AUTO_INCREMENT,
                                     `title` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                     `content` text COLLATE utf8mb4_general_ci,
                                     `is_display` int DEFAULT NULL,
                                     `article_images` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
                                     `island_index_code` int DEFAULT NULL,
                                     `create_time` timestamp  NULL DEFAULT CURRENT_TIMESTAMP,
                                     `update_time` timestamp  NULL DEFAULT CURRENT_TIMESTAMP,
                                     PRIMARY KEY (`article_index_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

--默认密码：123456
insert into sys_user(user_name,user_code,`PASSWORD`,nick_name)
VALUES
    ('admin','ca5fd62f004d457d970e75f6ecb21688','$2a$10$3e8/OFsPwc2hLOtUuwhmEe6Hl41BwEkrYc2gupfzWLdJSCuNAY.JO','admin');

CREATE TABLE IF NOT EXISTS sys_role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(64) UNIQUE NOT NULL ,
    role_code VARCHAR(64) NOT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

insert into sys_role(role_name,role_code)
VALUES
    ('管理员','d8a647352fe74cdbbb43090ea5e45f9d'),
    ('代理','927486beb0b44974ac0e1dd4f4b5c124'),
    ('代理主管','268b666c634b43f2879d869ea559fdbc'),
    ('销售','b7bd16c32de44e35abdd7c939dabb311'),
    ('销售主管','25523fbd6917410aa6f3c8b0c487ad43'),
    ('财务','20769fe45f374aa68e3c0afee3a98b08'),
    ('财务主管','8e23b763cea44afd960c9b72c60cc4f7'),
    ('产品经理','935a4c4b0cf641018404fd128276f53f')
;


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
insert into sys_menu(menu_name,menu_code,parent_code,type,url,perms)
VALUES
    ('应用菜单','root',null,'C','/erp',null),
    ('数据仪表盘','100000','root','C','/erp/dashboard',null),
    ('前台管理','200000','root','C','/erp/portalManagement',null),
    ('产品管理','300000','root','C','/erp/productManagement',null),
    ('订单管理','400000','root','C','/erp/orderManagement',null),
    ('用户管理','500000','root','C','/erp/userManagement',null),
    ('财务管理','600000','root','C','/erp/financeManagement',null),
    ('系统管理','700000','root','C','/erp/configManagement',null),

    ('岛屿管理','201000','200000','C','/erp/portalManagement/islandManagement',null),
    ('岛屿推荐','202000','200000','C','/erp/portalManagement/islandRecommendation',null),
    ('岛屿标签','203000','200000','C','/erp/portalManagement/islandTagManagement',null),
    ('文章管理','204000','200000','C','/erp/portalManagement/articleManagement',null),
    ('报价单','205000','200000','C','/erp/portalManagement/quotationManagement',null),

    ('岛屿产品管理','301000','300000','C','/erp/productManagement/islandManagement',null),
    ('额外儿童价格管理','302000','300000','C','/erp/productManagement/childrenManagement',null),
    ('机票产品管理','303000','300000','C','/erp/productManagement/planeTicketManagement',null),
    ('过度酒店产品管理','304000','300000','C','/erp/productManagement/transitHotelManagement',null),

    ('主订单管理','401000','400000','C','/erp/orderManagement/mainOrderManagement',null),
    ('岛屿订单管理','402000','400000','C','/erp/orderManagement/islandManagement',null),
    ('机票订单管理','403000','400000','C','/erp/orderManagement/planeTicketManagement',null),
    ('过度酒店订单管理','404000','400000','C','/erp/orderManagement/transitHotelManagement',null),

    ('用户管理','501000','500000','C','/erp/userManagement/sysUserManagement',null),
    ('角色管理','502000','500000','C','/erp/userManagement/sysRoleManagement',null),
    ('登录状态管理','503000','500000','C','/erp/userManagement/loginStatusManagement',null),

    ('支付记录管理','601000','600000','C','/erp/financeManagement/paymentManagement',null),

    ('币种管理','701000','700000','C','/erp/configManagement/currencyManagement',null),
    ('餐饮管理','702000','700000','C','/erp/configManagement/mealManagement',null),
    ('交通管理','703000','700000','C','/erp/configManagement/transportManagement',null)
;


CREATE TABLE IF NOT EXISTS sys_user_role (
    user_code VARCHAR(64),
    role_code VARCHAR(64)
    );
insert into sys_user_role(user_code, role_code)
VALUES
    ('ca5fd62f004d457d970e75f6ecb21688','d8a647352fe74cdbbb43090ea5e45f9d');

CREATE TABLE IF NOT EXISTS sys_role_menu (
    role_code VARCHAR(64),
    menu_code VARCHAR(64)
    );

insert into sys_role_menu(role_code, menu_code)
VALUES
    ('d8a647352fe74cdbbb43090ea5e45f9d','root'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','100000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','200000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','300000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','400000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','500000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','600000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','700000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','201000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','202000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','203000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','204000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','205000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','301000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','302000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','303000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','304000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','401000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','402000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','403000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','404000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','501000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','502000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','503000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','601000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','701000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','702000'),
    ('d8a647352fe74cdbbb43090ea5e45f9d','703000')
;





