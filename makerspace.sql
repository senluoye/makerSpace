/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : makerspace

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 06/12/2021 15:44:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lease
-- ----------------------------
DROP TABLE IF EXISTS `lease`;
CREATE TABLE `lease`  (
  `lease_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `room` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`lease_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 141 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lease
-- ----------------------------
INSERT INTO `lease` VALUES (1, '桂林市七星区创领产业服务中心', '1510', '众创空间');
INSERT INTO `lease` VALUES (2, '桂林市国坤建设管理有限公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (3, '桂林市国坤建设管理有限公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (4, '广西一点影视传媒有限公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (5, '广西一点影视传媒有限公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (6, '桂林市无尽文化传媒有限责任公司', '3201', '众创空间');
INSERT INTO `lease` VALUES (7, '桂林市无尽文化传媒有限责任公司', '3202', '众创空间');
INSERT INTO `lease` VALUES (8, '桂林智通交通科技有限公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (9, '桂林智通交通科技有限公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (10, '桂林金发明科技开发有限公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (11, '桂林金发明科技开发有限公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (12, '桂林金发明科技开发有限公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (13, '桂林市跟拍拍摄影有限公司', '3201', '众创空间');
INSERT INTO `lease` VALUES (14, '桂林市跟拍拍摄影有限公司', '3202', '众创空间');
INSERT INTO `lease` VALUES (15, '桂林市思涵教育科技有限公司', '3202', '众创空间');
INSERT INTO `lease` VALUES (16, '桂林市思涵教育科技有限公司', '3201', '众创空间');
INSERT INTO `lease` VALUES (17, '广西润信技术服务有限公司', '3201', '众创空间');
INSERT INTO `lease` VALUES (18, '广西润信技术服务有限公司', '3202', '众创空间');
INSERT INTO `lease` VALUES (19, '桂林风将信息技术有限公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (20, '桂林风将信息技术有限公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (21, '桂林塞弗恩汽车科技有限公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (22, '桂林塞弗恩汽车科技有限公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (23, '桂林海国志教育咨询有限责任公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (24, '桂林海国志教育咨询有限责任公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (25, '桂林微宠乐智能科技有限公司', '1509', '众创空间');
INSERT INTO `lease` VALUES (26, '李纳璺团队', '3205', '众创空间');
INSERT INTO `lease` VALUES (27, '李纳璺团队', '3206', '众创空间');
INSERT INTO `lease` VALUES (28, '桂林普创时空信息科技有限公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (29, '桂林普创时空信息科技有限公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (30, '广西红瓦网络科技有限公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (31, '广西红瓦网络科技有限公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (32, '桂林贝达信息科技有限公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (33, '桂林贝达信息科技有限公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (34, '桂林美格拉斯科技有限公司', '3201', '众创空间');
INSERT INTO `lease` VALUES (35, '桂林美格拉斯科技有限公司', '3202', '众创空间');
INSERT INTO `lease` VALUES (36, '广西树莓农业科技有限公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (37, '广西树莓农业科技有限公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (38, '广西青柠影视文化有限公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (39, '广西青柠影视文化有限公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (40, '桂林傅里叶电子科技有限责任公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (41, '桂林傅里叶电子科技有限责任公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (42, '还击营团队', '3206', '众创空间');
INSERT INTO `lease` VALUES (43, '还击营团队', '3205', '众创空间');
INSERT INTO `lease` VALUES (44, '桂林显晶质矿宝科技开发有限公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (45, '桂林显晶质矿宝科技开发有限公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (46, '桂林市翌信信息科技有限公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (47, '桂林市翌信信息科技有限公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (48, '广西抢工长电子商务有限公司', '1509', '众创空间');
INSERT INTO `lease` VALUES (49, '广西抢工长电子商务有限公司', '1510', '众创空间');
INSERT INTO `lease` VALUES (50, '广西抢工长电子商务有限公司', '1511', '众创空间');
INSERT INTO `lease` VALUES (51, '广西抢工长电子商务有限公司', '1512', '众创空间');
INSERT INTO `lease` VALUES (52, '广西抢工长电子商务有限公司', '1513', '众创空间');
INSERT INTO `lease` VALUES (53, '广西抢工长电子商务有限公司', '1514', '众创空间');
INSERT INTO `lease` VALUES (54, '广西抢工长电子商务有限公司', '1515', '众创空间');
INSERT INTO `lease` VALUES (55, '广西抢工长电子商务有限公司', '1516', '众创空间');
INSERT INTO `lease` VALUES (56, '桂林杰奥信息科技有限公司', '1506', '众创空间');
INSERT INTO `lease` VALUES (57, '桂林惠宇科技有限公司', '1509', '众创空间');
INSERT INTO `lease` VALUES (58, '桂林瀚凌科技有限责任公司', '1509', '众创空间');
INSERT INTO `lease` VALUES (59, '桂林小牛教育科技有限公司', '3201', '众创空间');
INSERT INTO `lease` VALUES (60, '桂林小牛教育科技有限公司', '3202', '众创空间');
INSERT INTO `lease` VALUES (61, '桂林显晶质矿宝科技开发有限公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (62, '桂林显晶质矿宝科技开发有限公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (63, '广西万维影视文化传媒有限责任公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (64, '广西万维影视文化传媒有限责任公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (65, '桂林麦克斯新能源科技有限公司', '3201', '众创空间');
INSERT INTO `lease` VALUES (66, '桂林麦克斯新能源科技有限公司', '3202', '众创空间');
INSERT INTO `lease` VALUES (67, '广西泽壕农业科技发展有限公司', '3201', '众创空间');
INSERT INTO `lease` VALUES (68, '广西泽壕农业科技发展有限公司', '3202', '众创空间');
INSERT INTO `lease` VALUES (69, '飞航团队', '3202', '众创空间');
INSERT INTO `lease` VALUES (70, '飞航团队', '3201', '众创空间');
INSERT INTO `lease` VALUES (71, '广西华颖科技有限公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (72, '广西华颖科技有限公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (73, '桂林市三宝生物科技有限公司', '3201', '众创空间');
INSERT INTO `lease` VALUES (74, '桂林市三宝生物科技有限公司', '3202', '众创空间');
INSERT INTO `lease` VALUES (75, '桂林启源电子科技有限公司', '3202', '众创空间');
INSERT INTO `lease` VALUES (76, '桂林启源电子科技有限公司', '3201', '众创空间');
INSERT INTO `lease` VALUES (77, '桂林精英科技有限公司', '3201', '众创空间');
INSERT INTO `lease` VALUES (78, '桂林精英科技有限公司', '3202', '众创空间');
INSERT INTO `lease` VALUES (79, '桂林研创半导体科技有限责任公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (80, '桂林研创半导体科技有限责任公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (81, '广西科才新能源科技有限公司', '3206', '众创空间');
INSERT INTO `lease` VALUES (82, '广西科才新能源科技有限公司', '3205', '众创空间');
INSERT INTO `lease` VALUES (83, '桂林歌者文明科技有限公司', '3201', '众创空间');
INSERT INTO `lease` VALUES (84, '桂林歌者文明科技有限公司', '3202', '众创空间');
INSERT INTO `lease` VALUES (85, '桂林大容文化科技有限公司', '1101', '科技园');
INSERT INTO `lease` VALUES (86, '桂林永成医疗科技有限公司', '1102', '科技园');
INSERT INTO `lease` VALUES (87, '桂林永成医疗科技有限公司', '1106', '科技园');
INSERT INTO `lease` VALUES (88, '广西海之韵节能科技有限公司', '1103', '科技园');
INSERT INTO `lease` VALUES (89, '广西海之韵节能科技有限公司', '1105', '科技园');
INSERT INTO `lease` VALUES (90, '广西正渝科技发展有限公司', '1107', '科技园');
INSERT INTO `lease` VALUES (91, '桂林市创琳电子科技发展有限公司', '1108-B', '科技园');
INSERT INTO `lease` VALUES (92, '桂林睿之菱医疗科技有限公司', '1119', '科技园');
INSERT INTO `lease` VALUES (93, '桂林睿之菱医疗科技有限公司', '1227-1', '科技园');
INSERT INTO `lease` VALUES (94, '桂林天工能源科技有限公司', '1120', '科技园');
INSERT INTO `lease` VALUES (95, '桂林宏联信息技术有限公司', '1121-B', '科技园');
INSERT INTO `lease` VALUES (96, '桂林宏联信息技术有限公司', '1232', '科技园');
INSERT INTO `lease` VALUES (97, '桂林海博泰克电子科技有限公司', '1123', '科技园');
INSERT INTO `lease` VALUES (98, '桂林海博泰克电子科技有限公司', '1125', '科技园');
INSERT INTO `lease` VALUES (99, '桂林就业力培训学校', '1201-1', '科技园');
INSERT INTO `lease` VALUES (100, '桂林就业力培训学校', '1206-1', '科技园');
INSERT INTO `lease` VALUES (101, '桂林瀚辰科技有限公司', '1201-1', '科技园');
INSERT INTO `lease` VALUES (102, '桂林瀚辰科技有限公司', '1205-1', '科技园');
INSERT INTO `lease` VALUES (103, '桂林市卓鸿科技有限公司', '1202', '科技园');
INSERT INTO `lease` VALUES (104, '桂林市晶准测控技术有限公司', '1203', '科技园');
INSERT INTO `lease` VALUES (105, '桂林易华科技有限公司', '1205-1', '科技园');
INSERT INTO `lease` VALUES (106, '桂林泡面电子科技有限公司', '1205-3', '科技园');
INSERT INTO `lease` VALUES (107, '桂林智谷睿森网络科技有限公司', '1206-2', '科技园');
INSERT INTO `lease` VALUES (108, '广西智度信息科技有限公司', '1227-2', '科技园');
INSERT INTO `lease` VALUES (109, '广西智度信息科技有限公司', '1207', '科技园');
INSERT INTO `lease` VALUES (110, '桂林环宇文化传播有限公司', '1209', '科技园');
INSERT INTO `lease` VALUES (111, '桂林明辉信息科技有限公司', '1210', '科技园');
INSERT INTO `lease` VALUES (112, '桂林市国创朝阳信息科技有限公司', '1211', '科技园');
INSERT INTO `lease` VALUES (113, '桂林市腾为通信技术有限公司', '1221', '科技园');
INSERT INTO `lease` VALUES (114, '桂林恒昌电子科技有限公司', '1222', '科技园');
INSERT INTO `lease` VALUES (115, '桂林数联自动化设备有限责任公司', '1223', '科技园');
INSERT INTO `lease` VALUES (116, '桂林大智科技有限公司', '1229', '科技园');
INSERT INTO `lease` VALUES (117, '桂林市汉辰数码通信有限责任公司', '1236', '科技园');
INSERT INTO `lease` VALUES (118, '桂林市汉辰数码通信有限责任公司', '1237', '科技园');
INSERT INTO `lease` VALUES (119, '广西中政桂创科技有限公司', '1512', '科技园');
INSERT INTO `lease` VALUES (120, '桂林市鑫广隆财务信息咨询有限责任公司', '2204-A', '科技园');
INSERT INTO `lease` VALUES (121, '广西伊科环境科技有限公司', '2204-B', '科技园');
INSERT INTO `lease` VALUES (122, '桂林市华谊智测科技有限责任公司', '2301', '科技园');
INSERT INTO `lease` VALUES (123, '桂林市华谊智测科技有限责任公司', '2302', '科技园');
INSERT INTO `lease` VALUES (124, '桂林市华谊智测科技有限责任公司', '2303', '科技园');
INSERT INTO `lease` VALUES (125, '桂林市华谊智测科技有限责任公司', '2304', '科技园');
INSERT INTO `lease` VALUES (126, '广西桂林绿帆环保膜科技有限公司', '2305', '科技园');
INSERT INTO `lease` VALUES (127, '桂林行友网络科技有限公司', '2402', '科技园');
INSERT INTO `lease` VALUES (128, '桂林灵动科技有限公司', '2405', '科技园');
INSERT INTO `lease` VALUES (129, '桂林灵动科技有限公司', '2406', '科技园');
INSERT INTO `lease` VALUES (130, '桂林荣谷电子商务有限公司', '3301', '科技园');
INSERT INTO `lease` VALUES (131, '桂林安维科技有限公司', '3302-A', '科技园');
INSERT INTO `lease` VALUES (132, '桂林安维科技有限公司', '3404', '科技园');
INSERT INTO `lease` VALUES (133, '广西浩宸医药科技有限公司', '3302-B', '科技园');
INSERT INTO `lease` VALUES (134, '桂林高德科技有限责任公司', '3303-A', '科技园');
INSERT INTO `lease` VALUES (135, '桂林广云科技有限公司', '3303-B', '科技园');
INSERT INTO `lease` VALUES (136, '桂林市三汇信息技术有限公司', '3304', '科技园');
INSERT INTO `lease` VALUES (137, '桂林仁立达科技有限责任公司', '3305', '科技园');
INSERT INTO `lease` VALUES (138, '桂林瑞威赛德科技有限公司', '3401-A', '科技园');
INSERT INTO `lease` VALUES (139, '桂林尼威智能科技有限公司', '3402', '科技园');
INSERT INTO `lease` VALUES (140, '桂林尼威智能科技有限公司', '3403', '科技园');

-- ----------------------------
-- Table structure for new
-- ----------------------------
DROP TABLE IF EXISTS `new`;
CREATE TABLE `new`  (
  `new_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `credit_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `organization_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `picture` mediumblob NULL,
  `represent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `represent_card` mediumblob NULL,
  `represent_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `represent_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `agent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `agent_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `agent_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `register_capital` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `real_capital` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `origin_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `register_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `certificate` mediumblob NULL,
  `involved` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `main_business` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `business` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `new_demand_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `new_shareholder_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `new_mainperson_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `new_project_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `new_intellectual_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `suggestion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `submit_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `room` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `inapply_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `outapply_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`new_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '新成立企业或非独立注册企业' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of new
-- ----------------------------

-- ----------------------------
-- Table structure for new_demand
-- ----------------------------
DROP TABLE IF EXISTS `new_demand`;
CREATE TABLE `new_demand`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `new_demand_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `lease_area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `lease` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `floor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `electric` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `water` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `web` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `others` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of new_demand
-- ----------------------------

-- ----------------------------
-- Table structure for new_intellectual
-- ----------------------------
DROP TABLE IF EXISTS `new_intellectual`;
CREATE TABLE `new_intellectual`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `new_intellectual_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `kind` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `apply_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `approval_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `intellectual_file` mediumblob NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of new_intellectual
-- ----------------------------

-- ----------------------------
-- Table structure for new_mainperson
-- ----------------------------
DROP TABLE IF EXISTS `new_mainperson`;
CREATE TABLE `new_mainperson`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `new_shareholder_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `born` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `job` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `school` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `background` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `professional` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of new_mainperson
-- ----------------------------

-- ----------------------------
-- Table structure for new_project
-- ----------------------------
DROP TABLE IF EXISTS `new_project`;
CREATE TABLE `new_project`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `new_project_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `project_brief` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `advantage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `market` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `energy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pollution` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `noise` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `others` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of new_project
-- ----------------------------

-- ----------------------------
-- Table structure for new_shareholder
-- ----------------------------
DROP TABLE IF EXISTS `new_shareholder`;
CREATE TABLE `new_shareholder`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `new_shareholder_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stake` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of new_shareholder
-- ----------------------------

-- ----------------------------
-- Table structure for old
-- ----------------------------
DROP TABLE IF EXISTS `old`;
CREATE TABLE `old`  (
  `old_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `credit_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organization_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `represent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `represent_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `register_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `represent_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agent_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agent_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `license` mediumblob NULL,
  `register_capital` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `real_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `real_capital` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_income` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_tax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `employees` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `origin_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `set_date` timestamp(0) NULL DEFAULT NULL,
  `nature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `certificate` mediumblob NULL,
  `involved` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `main_business` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `business` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `old_demand_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `old_shareholder_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `old_mainperson_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `old_project_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `old_intellectual_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `old_funding_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cooperation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `suggestion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `submit_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `room` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inapply_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `outapply_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`old_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '迁入和独立注册企业信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of old
-- ----------------------------

-- ----------------------------
-- Table structure for old_demand
-- ----------------------------
DROP TABLE IF EXISTS `old_demand`;
CREATE TABLE `old_demand`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lease_area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lease` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `floor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `electric` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `water` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `web` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `others` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `old_demand_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of old_demand
-- ----------------------------

-- ----------------------------
-- Table structure for old_funding
-- ----------------------------
DROP TABLE IF EXISTS `old_funding`;
CREATE TABLE `old_funding`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grants` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `award` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `funding_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of old_funding
-- ----------------------------

-- ----------------------------
-- Table structure for old_intellectual
-- ----------------------------
DROP TABLE IF EXISTS `old_intellectual`;
CREATE TABLE `old_intellectual`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `kind` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `apply_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `approval_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `intellectual_file` mediumblob NULL,
  `old_intellectual_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of old_intellectual
-- ----------------------------

-- ----------------------------
-- Table structure for old_mainperson
-- ----------------------------
DROP TABLE IF EXISTS `old_mainperson`;
CREATE TABLE `old_mainperson`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `born` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `school` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `background` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `professional` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `old_mainperson_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of old_mainperson
-- ----------------------------

-- ----------------------------
-- Table structure for old_project
-- ----------------------------
DROP TABLE IF EXISTS `old_project`;
CREATE TABLE `old_project`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `project_brief` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `advantage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `market` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `energy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pollution` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `noise` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `others` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `old_project_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of old_project
-- ----------------------------

-- ----------------------------
-- Table structure for old_shareholder
-- ----------------------------
DROP TABLE IF EXISTS `old_shareholder`;
CREATE TABLE `old_shareholder`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stake` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `old_shareholder_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of old_shareholder
-- ----------------------------

-- ----------------------------
-- Table structure for place
-- ----------------------------
DROP TABLE IF EXISTS `place`;
CREATE TABLE `place`  (
  `room` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`room`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '存储所有场地的表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of place
-- ----------------------------
INSERT INTO `place` VALUES ('1103', '0');
INSERT INTO `place` VALUES ('1105', '0');
INSERT INTO `place` VALUES ('1108-A', '0');
INSERT INTO `place` VALUES ('1109', '0');
INSERT INTO `place` VALUES ('1112', '0');
INSERT INTO `place` VALUES ('1121-A', '0');
INSERT INTO `place` VALUES ('1208', '0');
INSERT INTO `place` VALUES ('1220', '0');
INSERT INTO `place` VALUES ('2201', '0');
INSERT INTO `place` VALUES ('2202', '0');
INSERT INTO `place` VALUES ('2203', '0');
INSERT INTO `place` VALUES ('2306', '0');
INSERT INTO `place` VALUES ('2401', '0');
INSERT INTO `place` VALUES ('2403', '0');
INSERT INTO `place` VALUES ('2404', '0');
INSERT INTO `place` VALUES ('3306', '0');
INSERT INTO `place` VALUES ('3307', '0');
INSERT INTO `place` VALUES ('3401-B', '0');
INSERT INTO `place` VALUES ('3405', '0');
INSERT INTO `place` VALUES ('3406', '0');
INSERT INTO `place` VALUES ('3407', '0');

-- ----------------------------
-- Table structure for space
-- ----------------------------
DROP TABLE IF EXISTS `space`;
CREATE TABLE `space`  (
  `in_apply_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `apply_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `team_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `space_person_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `brief` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `help` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`in_apply_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of space
-- ----------------------------

-- ----------------------------
-- Table structure for space_person
-- ----------------------------
DROP TABLE IF EXISTS `space_person`;
CREATE TABLE `space_person`  (
  `person_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `person_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `person_qq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `person_wechat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`person_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of space_person
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
