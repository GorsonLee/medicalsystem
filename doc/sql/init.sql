CREATE SCHEMA IF NOT EXISTS medical_system DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

# 1.用户表
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
                        `id` varchar(100) NOT NULL COMMENT '主键 ID',
                        `phone` varchar(50) NOT NULL COMMENT '电话号码',
                        `pwd` varchar(100) NOT NULL COMMENT '登陆密码',
                        `identity` varchar(50) NULL DEFAULT NULL COMMENT '身份证',
                        `name` varchar(100) NULL DEFAULT NULL COMMENT '名字',
                        `birth` int(8) NULL DEFAULT NULL COMMENT '生日',
                        `sex` int(4) NULL DEFAULT NULL COMMENT '性别',
                        `nation` varchar(50) NULL DEFAULT NULL COMMENT '民族',
                        `image` varchar(500) NULL DEFAULT NULL COMMENT '头像 url',
                        `old_type` int(0) NULL DEFAULT NULL COMMENT '老人类别',
                        `marital_status` int(4) NULL DEFAULT NULL COMMENT '婚姻状况',
                        `live_status` int(4) NULL DEFAULT NULL COMMENT '居住状况',
                        `address` varchar(500) NULL DEFAULT NULL COMMENT '家庭地址',
                        `province` varchar(50) NULL DEFAULT NULL COMMENT '省份',
                        `city` varchar(50) NULL DEFAULT NULL COMMENT '城市',
                        `country` varchar(50) NULL DEFAULT NULL COMMENT '乡县',
                        `community` varchar(50) NULL DEFAULT NULL COMMENT '社区',
                        `organization` varchar(50) NULL DEFAULT NULL COMMENT '机构组织',
                        `emergency_contact` varchar(100) NULL DEFAULT NULL COMMENT '紧急联系人',
                        `emergency_phone` varchar(50) NULL DEFAULT NULL COMMENT '紧急联系电话',
                        `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                        `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 2.家庭关系表
DROP TABLE IF EXISTS `t_relationship`;
CREATE TABLE `t_relationship` (
                                `id` varchar(100) NOT NULL COMMENT '主键 ID',
                                `younger_id` varchar(100) NOT NULL COMMENT '晚辈 ID',
                                `elder_id` varchar(100) NOT NULL COMMENT '长辈 ID',
                                `type` int(4) NOT NULL COMMENT '关系类型：1父亲，2母亲，3爷爷，4奶奶',
                                `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                                `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 3.体征测量表
DROP TABLE IF EXISTS `t_body_sign`;
CREATE TABLE `t_body_sign` (
                             `id` varchar(100) NOT NULL COMMENT '主键 ID',
                             `user_id` varchar(100) NOT NULL COMMENT '用户 ID',
                             `breath_rate` int(8) NULL DEFAULT NULL COMMENT '呼吸率',
                             `systolic` int(8) NULL DEFAULT NULL COMMENT '收缩压，单位mmHg',
                             `diastolic` int(8) NULL DEFAULT NULL COMMENT '舒张压，单位mmHg',
                             `heart_rate` int(8) NULL DEFAULT NULL COMMENT '心率，单位次/分',
                             `spo2` int(8) NULL DEFAULT NULL COMMENT '血氧饱和度，单位SaO2',
                             `sleep_time` int(8) NULL DEFAULT NULL COMMENT '睡眠时间，单位min',
                             `bg` int(8) NULL DEFAULT NULL COMMENT '血糖，单位mmol/L，数值放大10倍',
                             `temperature` int(8) NULL DEFAULT NULL COMMENT '体温，单位°C，数值放大10倍',
                             `uric_acid` int(8) NULL DEFAULT NULL COMMENT '尿酸，单位umol/L',
                             `blood_fat` int(8) NULL DEFAULT NULL COMMENT '血脂，单位mmol/L',
                             `weight` int(8) NULL DEFAULT NULL COMMENT '体重，单位Kg',
                             `waist` int(8) NULL DEFAULT NULL COMMENT '腰围，单位cm',
                             `hip` int(8) NULL DEFAULT NULL COMMENT '臀围，单位cm',
                             `description` varchar(100) NULL DEFAULT NULL COMMENT '健康描述信息',
                             `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                             `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 4.健康档案表
DROP TABLE IF EXISTS `t_health_document`;
CREATE TABLE `t_health_document` (
                                   `id` varchar(100) NOT NULL COMMENT '主键 ID',
                                   `user_id` varchar(100) NOT NULL COMMENT '主键 ID',
                                   `basic_sign` varchar(2000) NULL DEFAULT NULL COMMENT '基本体征',
                                   `lifestyle` varchar(2000) NULL DEFAULT NULL COMMENT '生活方式',
                                   `organ` varchar(1000) NULL DEFAULT NULL COMMENT '脏器功能',
                                   `health_history` varchar(1000) NULL DEFAULT NULL COMMENT '健康史',
                                   `health_record` varchar(1000) NULL DEFAULT NULL COMMENT '健康记录',
                                   `health_report` varchar(1000) NULL DEFAULT NULL COMMENT '健康评估报告',
                                   `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                                   `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 5.医生表
DROP TABLE IF EXISTS `t_doctor`;
CREATE TABLE `t_doctor` (
                          `id` varchar(100) NOT NULL COMMENT '主键 ID',
                          `phone` varchar(50) NOT NULL COMMENT '电话号码',
                          `pwd` varchar(100) NOT NULL COMMENT '登陆密码',
                          `identity` varchar(50) NULL DEFAULT NULL COMMENT '身份证',
                          `name` varchar(100) NULL DEFAULT NULL COMMENT '名字',
                          `sex` int(4) NULL DEFAULT NULL COMMENT '性别',
                          `birth` int(8) NULL DEFAULT NULL COMMENT '生日',
                          `hospital` varchar(100) NULL DEFAULT NULL COMMENT '医院机构',
                          `department` varchar(100) NULL DEFAULT NULL COMMENT '一级科室',
                          `second_department` varchar(100) NULL DEFAULT NULL COMMENT '二级科室',
                          `job_title` varchar(100) NULL DEFAULT NULL COMMENT '职称',
                          `introduction` varchar(100) NULL DEFAULT NULL COMMENT '简介',
                          `skill` varchar(100) NULL DEFAULT NULL COMMENT '擅长技能',
                          `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                          `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 6.签约表
DROP TABLE IF EXISTS `t_contract`;
CREATE TABLE `t_contract` (
                            `id` varchar(100) NOT NULL COMMENT '主键 ID',
                            `user_id` varchar(100) NOT NULL COMMENT '用户 ID',
                            `doctor_id` varchar(100) NOT NULL COMMENT '医生 ID',
                            `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                            `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 7.用户咨询表
DROP TABLE IF EXISTS `t_consult`;
CREATE TABLE `t_consult` (
                           `id` varchar(100) NOT NULL COMMENT '主键 ID',
                           `user_id` varchar(100) NOT NULL COMMENT '咨询的用户 ID',
                           `doctor_id` varchar(100) NULL DEFAULT NULL COMMENT '回复的医生 ID',
                           `symptoms` varchar(100) NULL DEFAULT NULL COMMENT '症状',
                           `description` varchar(500) NULL DEFAULT NULL COMMENT '病情主诉描述',
                           `record_urls` varchar(1000) NULL DEFAULT NULL COMMENT '病历地址',
                           `doctor_reply` varchar(500) NULL DEFAULT NULL COMMENT '医生回复',
                           `state` int(4) NULL DEFAULT NULL COMMENT '状态，0表示未回复，1表示已回复',
                           `reply_time` datetime(0) NULL DEFAULT NULL COMMENT '医生回复时间',
                           `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                           `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 8.医生预约表
DROP TABLE IF EXISTS `t_reservation`;
CREATE TABLE `t_reservation` (
                               `id` varchar(100) NOT NULL COMMENT '主键 ID',
                               `user_id` varchar(100) NOT NULL COMMENT '用户 ID',
                               `doctor_id` varchar(100) NOT NULL COMMENT '医生 ID',
                               `department` varchar(100) NULL DEFAULT NULL COMMENT '预约科室',
                               `instructions` varchar(1000) NULL DEFAULT NULL COMMENT '就诊说明',
                               `state` int(4) NULL DEFAULT NULL COMMENT '预约状态，1表示预约中，2表示预约成功，3表示预约失败',
                               `reserve_time` datetime(0) NULL DEFAULT NULL COMMENT '预约时间',
                               `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                               `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 9.处方表
DROP TABLE IF EXISTS `t_prescription`;
CREATE TABLE `t_prescription` (
                                `id` varchar(100) NOT NULL COMMENT '主键 ID',
                                `user_id` varchar(100) NOT NULL COMMENT '用户 ID',
                                `doctor_id` varchar(100) NOT NULL COMMENT '医生 ID',
                                `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                                `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 10.设备表
DROP TABLE IF EXISTS `t_device`;
CREATE TABLE `t_device` (
                          `id` varchar(100) NOT NULL COMMENT '主键 ID',
                          `user_id` varchar(100) NOT NULL COMMENT '用户 ID',
                          `device_id` varchar(100) NOT NULL COMMENT '设备 ID',
                          `category` varchar(50) NULL DEFAULT NULL COMMENT '设备分类',
                          `type` varchar(50) NULL DEFAULT NULL COMMENT '设备类型',
                          `state` int(4) NULL DEFAULT NULL COMMENT '设备状态，1表示启用，2表示停用，3表示回收',
                          `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                          `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 11.贴心服务，商城和资讯（表数据未确定）
DROP TABLE IF EXISTS `t_service_center`;
CREATE TABLE `t_service_center` (
                                  `id` varchar(100) NOT NULL COMMENT '主键 ID',
                                  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                                  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 12.提醒管理表
DROP TABLE IF EXISTS `t_remind`;
CREATE TABLE `t_remind` (
                          `id` varchar(100) NOT NULL COMMENT '主键 ID',
                          `user_id` varchar(100) NOT NULL COMMENT '用户 ID',
                          `event` varchar(100) NOT NULL COMMENT '提醒事项',
                          `method` int(4) NULL DEFAULT NULL COMMENT '提醒方式',
                          `frequency` int(4) NULL DEFAULT NULL COMMENT '提醒频率',
                          `content` varchar(0) NULL DEFAULT NULL COMMENT '提醒内容',
                          `remind_time` datetime(0) NOT NULL COMMENT '提醒时间',
                          `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                          `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 13.关联老人表
DROP TABLE IF EXISTS `t_association`;
CREATE TABLE `t_association` (
                               `id` varchar(100) NOT NULL COMMENT '主键 ID',
                               `user_id` varchar(100) NOT NULL COMMENT '用户 ID',
                               `associate_id` varchar(100) NOT NULL COMMENT '被关联的老人ID',
                               `name` varchar(100) NULL DEFAULT NULL COMMENT '被关联的老人姓名',
                               `account` varchar(100) NULL DEFAULT NULL COMMENT '被关联的老人账号',
                               `identity` varchar(50) NULL DEFAULT NULL COMMENT '被关联的老人身份证',
                               `state` int(4) NULL DEFAULT NULL COMMENT '关联状态',
                               `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                               `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;