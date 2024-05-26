--默认密码：123456
insert into sys_user(user_name,user_code,`PASSWORD`,nick_name,belong_company)
VALUES
    ('admin','ca5fd62f004d457d970e75f6ecb21688','$2a$10$3e8/OFsPwc2hLOtUuwhmEe6Hl41BwEkrYc2gupfzWLdJSCuNAY.JO','admin','dtholiday');


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

insert into sys_user_role(user_code, role_code)
VALUES
    ('ca5fd62f004d457d970e75f6ecb21688','d8a647352fe74cdbbb43090ea5e45f9d');

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
    ('d8a647352fe74cdbbb43090ea5e45f9d','703000'),
    ('927486beb0b44974ac0e1dd4f4b5c124','root'),
    ('927486beb0b44974ac0e1dd4f4b5c124','400000'),
    ('927486beb0b44974ac0e1dd4f4b5c124','401000'),
    ('927486beb0b44974ac0e1dd4f4b5c124','402000'),
    ('927486beb0b44974ac0e1dd4f4b5c124','403000'),
    ('927486beb0b44974ac0e1dd4f4b5c124','404000'),
    ('268b666c634b43f2879d869ea559fdbc','root'),
    ('268b666c634b43f2879d869ea559fdbc','400000'),
    ('268b666c634b43f2879d869ea559fdbc','401000'),
    ('268b666c634b43f2879d869ea559fdbc','402000'),
    ('268b666c634b43f2879d869ea559fdbc','403000'),
    ('268b666c634b43f2879d869ea559fdbc','404000'),
    ('b7bd16c32de44e35abdd7c939dabb311','root'),
    ('b7bd16c32de44e35abdd7c939dabb311','400000'),
    ('b7bd16c32de44e35abdd7c939dabb311','401000'),
    ('b7bd16c32de44e35abdd7c939dabb311','402000'),
    ('b7bd16c32de44e35abdd7c939dabb311','403000'),
    ('b7bd16c32de44e35abdd7c939dabb311','404000'),
    ('25523fbd6917410aa6f3c8b0c487ad43','root'),
    ('25523fbd6917410aa6f3c8b0c487ad43','400000'),
    ('25523fbd6917410aa6f3c8b0c487ad43','401000'),
    ('25523fbd6917410aa6f3c8b0c487ad43','402000'),
    ('25523fbd6917410aa6f3c8b0c487ad43','403000'),
    ('25523fbd6917410aa6f3c8b0c487ad43','404000'),
    ('20769fe45f374aa68e3c0afee3a98b08','root'),
    ('20769fe45f374aa68e3c0afee3a98b08','400000'),
    ('20769fe45f374aa68e3c0afee3a98b08','401000'),
    ('20769fe45f374aa68e3c0afee3a98b08','402000'),
    ('20769fe45f374aa68e3c0afee3a98b08','403000'),
    ('20769fe45f374aa68e3c0afee3a98b08','404000'),
    ('20769fe45f374aa68e3c0afee3a98b08','600000'),
    ('20769fe45f374aa68e3c0afee3a98b08','601000'),
    ('8e23b763cea44afd960c9b72c60cc4f7','root'),
    ('8e23b763cea44afd960c9b72c60cc4f7','400000'),
    ('8e23b763cea44afd960c9b72c60cc4f7','401000'),
    ('8e23b763cea44afd960c9b72c60cc4f7','402000'),
    ('8e23b763cea44afd960c9b72c60cc4f7','403000'),
    ('8e23b763cea44afd960c9b72c60cc4f7','404000'),
    ('8e23b763cea44afd960c9b72c60cc4f7','600000'),
    ('8e23b763cea44afd960c9b72c60cc4f7','601000'),
    ('935a4c4b0cf641018404fd128276f53f','root'),
    ('935a4c4b0cf641018404fd128276f53f','200000'),
    ('935a4c4b0cf641018404fd128276f53f','300000'),
    ('935a4c4b0cf641018404fd128276f53f','201000'),
    ('935a4c4b0cf641018404fd128276f53f','202000'),
    ('935a4c4b0cf641018404fd128276f53f','203000'),
    ('935a4c4b0cf641018404fd128276f53f','204000'),
    ('935a4c4b0cf641018404fd128276f53f','205000'),
    ('935a4c4b0cf641018404fd128276f53f','301000'),
    ('935a4c4b0cf641018404fd128276f53f','302000'),
    ('935a4c4b0cf641018404fd128276f53f','303000'),
    ('935a4c4b0cf641018404fd128276f53f','304000')
;
