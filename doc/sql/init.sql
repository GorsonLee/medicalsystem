CREATE SCHEMA IF NOT EXISTS medical_system DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

# 晚辈表
DROP TABLE IF EXISTS `t_younger`;
CREATE TABLE `t_younger` (
                        `id` varchar(100) NOT NULL COMMENT '主键 ID',
                        `phone` varchar(50) NOT NULL COMMENT '电话号码',
                        `pwd` varchar(100) NOT NULL COMMENT '登陆密码',
                        `identity` varchar(50) NULL DEFAULT NULL COMMENT '身份证',
                        `name` varchar(100) NULL DEFAULT NULL COMMENT '名字',
                        `birth` int(8) NULL DEFAULT NULL COMMENT '生日',
                        `sex` int(4) NULL DEFAULT NULL COMMENT '性别',
                        `image` varchar(500) NULL DEFAULT NULL COMMENT '头像 url',
                        `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                        `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 长辈表
DROP TABLE IF EXISTS `t_elder`;
/*CREATE TABLE `t_elder` (
                        `id` varchar(100) NOT NULL COMMENT '主键 ID',
                        `phone` varchar(50) NOT NULL COMMENT '电话号码',
                        `pwd` varchar(100) NOT NULL COMMENT '登陆密码',
                        `identity` varchar(50) NULL DEFAULT NULL COMMENT '身份证',
                        `name` varchar(100) NULL DEFAULT NULL COMMENT '名字',
                        `birth` int(8) NULL DEFAULT NULL COMMENT '生日',
                        `sex` int(4) NULL DEFAULT NULL COMMENT '性别',
                        `nation` varchar(50) NULL DEFAULT NULL COMMENT '民族',
                        `image` varchar(500) NULL DEFAULT NULL COMMENT '头像 url',
                        `old_type` int(4) NULL DEFAULT NULL COMMENT '老人类别',
                        `marital_status` int(4) NULL DEFAULT NULL COMMENT '婚姻状况',
                        `live_status` int(4) NULL DEFAULT NULL COMMENT '居住状况',
                        `address` varchar(500) NULL DEFAULT NULL COMMENT '家庭地址',
                        `province` varchar(50) NULL DEFAULT NULL COMMENT '省份',
                        `city` varchar(50) NULL DEFAULT NULL COMMENT '城市',
                        `country` varchar(50) NULL DEFAULT NULL COMMENT '县',
                        `town` varchar(50) NULL DEFAULT NULL COMMENT '乡',
                        `community` varchar(50) NULL DEFAULT NULL COMMENT '社区',
                        `agency_id` varchar(100) NULL DEFAULT NULL COMMENT '机构组织ID',
                        `emergency_contact` varchar(100) NULL DEFAULT NULL COMMENT '紧急联系人',
                        `emergency_phone` varchar(50) NULL DEFAULT NULL COMMENT '紧急联系电话',
                        `provide_state` int(4) NULL DEFAULT NULL COMMENT '养老类型：0未知；1机构养老；2以房养老',
                        `verify_state` int(4) NULL DEFAULT NULL COMMENT '审核状态：0未知；1通过；2拒绝',
                        `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                        `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;*/
CREATE TABLE `t_elder` (
  `id` varchar(100) NOT NULL COMMENT '主键 ID',
  `phone` varchar(50) NOT NULL COMMENT '电话号码',
  `pwd` varchar(100) NOT NULL COMMENT '登陆密码',
  `identity` varchar(50) DEFAULT NULL COMMENT '身份证',
  `name` varchar(100) DEFAULT NULL COMMENT '名字',
  `birth` int(8) DEFAULT NULL COMMENT '生日 格式：19550606',
  `sex` int(4) DEFAULT NULL COMMENT '性别 0-男 1-女',
  `nation` varchar(50) DEFAULT NULL COMMENT '民族 0-汉族 1-回族 2-其它',
  `image` varchar(500) DEFAULT NULL COMMENT '头像 url',
  `old_type` int(4) DEFAULT NULL COMMENT '老人类型 0-孤寡老人 1-有子女 2-其它',
  `marital_status` int(4) DEFAULT NULL COMMENT '婚姻状况 0-未婚 1-已婚 2-离异 3-丧偶',
  `live_status` int(4) DEFAULT NULL COMMENT '居住状况 0-独自居住 1-子女同住 2-其它',
  `address` varchar(500) DEFAULT NULL COMMENT '家庭地址',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `country` varchar(50) DEFAULT NULL COMMENT '县',
  `town` varchar(50) DEFAULT NULL COMMENT '乡',
  `community` varchar(50) DEFAULT NULL COMMENT '社区',
  `agency_id` varchar(100) DEFAULT NULL COMMENT '机构组织ID',
  `emergency_contact` varchar(100) DEFAULT NULL COMMENT '紧急联系人',
  `emergency_phone` varchar(50) DEFAULT NULL COMMENT '紧急联系电话',
  `provide_state` int(4) DEFAULT NULL COMMENT '养老类型：0未知；1机构养老；2以房养老',
  `verify_state` int(4) DEFAULT NULL COMMENT '审核状态：0未知；1通过；2拒绝',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

# 家庭关系表
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

# 体征测量表
DROP TABLE IF EXISTS `t_body_sign`;
CREATE TABLE `t_body_sign` (
                             `id` varchar(100) NOT NULL COMMENT '主键 ID',
                             `user_id` varchar(100) NOT NULL COMMENT '用户 ID',
                             `body_sign` varchar(50) NULL DEFAULT NULL COMMENT '体征项，breathRate(呼吸率)；bp(血压，单位mmHg)；heartRate(心率，单位次/分)；spo2(血氧饱和度，单位SaO2)；sleepTime(睡眠时间，单位min)；bg(血糖，单位mmol/L)；temperature(体温，单位°C)；uricAcid(尿酸，单位umol/L)；bloodFat(血脂，单位mmol/L)；weight(体重，单位Kg)；height(身高，单位cm)；waist(腰围，单位cm)；hip(臀围，单位cm)；',
                             `content` varchar(50) NULL DEFAULT NULL COMMENT '体征值内容',
                             `description` varchar(100) NULL DEFAULT NULL COMMENT '健康描述信息',
                             `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                             `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 体征告警设置表
DROP TABLE IF EXISTS `t_body_sign_alarm`;
CREATE TABLE `t_body_sign_alarm` (
                                   `id` varchar(100) NOT NULL COMMENT '主键 ID',
                                   `doctor_id` varchar(100) NOT NULL COMMENT '用户 ID',
                                   `breath_rate_max` int(8) NULL DEFAULT NULL COMMENT '呼吸率',
                                   `breath_rate_min` int(8) NULL DEFAULT NULL COMMENT '呼吸率',
                                   `systolic_max` int(8) NULL DEFAULT NULL COMMENT '收缩压，单位mmHg',
                                   `systolic_min` int(8) NULL DEFAULT NULL COMMENT '收缩压，单位mmHg',
                                   `diastolic_max` int(8) NULL DEFAULT NULL COMMENT '舒张压，单位mmHg',
                                   `diastolic_min` int(8) NULL DEFAULT NULL COMMENT '舒张压，单位mmHg',
                                   `heart_rate_max` int(8) NULL DEFAULT NULL COMMENT '心率，单位次/分',
                                   `heart_rate_min` int(8) NULL DEFAULT NULL COMMENT '心率，单位次/分',
                                   `spo2_max` int(8) NULL DEFAULT NULL COMMENT '血氧饱和度，单位SaO2',
                                   `spo2_min` int(8) NULL DEFAULT NULL COMMENT '血氧饱和度，单位SaO2',
                                   `sleep_time_max` int(8) NULL DEFAULT NULL COMMENT '睡眠时间，单位min',
                                   `sleep_time_min` int(8) NULL DEFAULT NULL COMMENT '睡眠时间，单位min',
                                   `bg_max` int(8) NULL DEFAULT NULL COMMENT '血糖，单位mmol/L',
                                   `bg_min` int(8) NULL DEFAULT NULL COMMENT '血糖，单位mmol/L',
                                   `temperature_max` int(8) NULL DEFAULT NULL COMMENT '体温，单位°C',
                                   `temperature_min` int(8) NULL DEFAULT NULL COMMENT '体温，单位°C',
                                   `uric_acid_max` int(8) NULL DEFAULT NULL COMMENT '尿酸，单位umol/L',
                                   `uric_acid_min` int(8) NULL DEFAULT NULL COMMENT '尿酸，单位umol/L',
                                   `blood_fat_max` int(8) NULL DEFAULT NULL COMMENT '血脂，单位mmol/L',
                                   `blood_fat_min` int(8) NULL DEFAULT NULL COMMENT '血脂，单位mmol/L',
                                   `weight_max` int(8) NULL DEFAULT NULL COMMENT '体重，单位Kg',
                                   `weight_min` int(8) NULL DEFAULT NULL COMMENT '体重，单位Kg',
                                   `waist_max` int(8) NULL DEFAULT NULL COMMENT '腰围，单位cm',
                                   `waist_min` int(8) NULL DEFAULT NULL COMMENT '腰围，单位cm',
                                   `hip_max` int(8) NULL DEFAULT NULL COMMENT '臀围，单位cm',
                                   `hip_min` int(8) NULL DEFAULT NULL COMMENT '臀围，单位cm',
                                   `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                                   `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 健康档案表
DROP TABLE IF EXISTS `t_health_document`;
CREATE TABLE `t_health_document` (
                                   `id` varchar(100) NOT NULL COMMENT '主键 ID',
                                   `user_id` varchar(100) NOT NULL COMMENT '主键 ID',
                                   `path` varchar(200) NOT NULL COMMENT '健康档案的路径信息',
                                   `content` varchar(5000) NOT NULL COMMENT '健康档案的内容',
                                   `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                                   `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 医生表
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

# 签约表
DROP TABLE IF EXISTS `t_contract`;
CREATE TABLE `t_contract` (
                            `id` varchar(100) NOT NULL COMMENT '主键 ID',
                            `user_id` varchar(100) NOT NULL COMMENT '用户 ID',
                            `doctor_id` varchar(100) NOT NULL COMMENT '医生 ID',
                            `state` int(4) NULL DEFAULT NULL COMMENT '状态，0表示审核中，1表示已签约',
                            `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                            `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 用户咨询表
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

# 医生预约表
DROP TABLE IF EXISTS `t_reservation`;
CREATE TABLE `t_reservation` (
                               `id` varchar(100) NOT NULL COMMENT '主键 ID',
                               `user_id` varchar(100) NOT NULL COMMENT '用户 ID',
                               `doctor_id` varchar(100) NOT NULL COMMENT '医生 ID',
                               `instructions` varchar(1000) NULL DEFAULT NULL COMMENT '就诊说明',
                               `state` int(4) NULL DEFAULT NULL COMMENT '预约状态，1表示预约中，2表示预约成功，3表示预约失败',
                               `reserve_time` datetime(0) NULL DEFAULT NULL COMMENT '预约时间',
                               `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                               `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 处方表
DROP TABLE IF EXISTS `t_prescription`;
CREATE TABLE `t_prescription` (
                                `id` varchar(100) NOT NULL COMMENT '主键 ID',
                                `user_id` varchar(100) NOT NULL COMMENT '用户 ID',
                                `doctor_id` varchar(100) NOT NULL COMMENT '医生 ID',
                                `title` varchar(100) NOT NULL COMMENT '处方标题',
                                `content` varchar(1000) NOT NULL COMMENT '处方内容',
                                `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                                `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 处方模板表
DROP TABLE IF EXISTS `t_prescription_model`;
CREATE TABLE `t_prescription_model` (
                                `id` varchar(100) NOT NULL COMMENT '主键 ID',
                                `doctor_id` varchar(100) NOT NULL COMMENT '医生 ID',
                                `title` varchar(100) NOT NULL COMMENT '处方标题',
                                `content` varchar(1000) NOT NULL COMMENT '处方内容',
                                `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                                `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 医生回访表
DROP TABLE IF EXISTS `t_revisit`;
CREATE TABLE `t_revisit` (
                           `id` varchar(100) NOT NULL COMMENT '主键 ID',
                           `user_id` varchar(100) NOT NULL COMMENT '被回访老年人 ID',
                           `doctor_id` varchar(100) NOT NULL COMMENT '医生ID',
                           `method` varchar(100) NULL DEFAULT NULL COMMENT '回访方式',
                           `content` varchar(500) NULL DEFAULT NULL COMMENT '回访内容',
                           `visit_time` datetime(0) NOT NULL COMMENT '回访时间',
                           `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                           `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 设备表
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

# 提醒管理表
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

# 关联老人表
DROP TABLE IF EXISTS `t_association`;
/*CREATE TABLE `t_association` (
                               `id` varchar(100) NOT NULL COMMENT '主键 ID',
                               `user_id` varchar(100) NOT NULL COMMENT '用户 ID',
                               `associate_user_id` varchar(100) NOT NULL COMMENT '被关联的老人ID',
                               `state` int(4) NULL DEFAULT NULL COMMENT '关联状态',
                               `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                               `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;*/

CREATE TABLE `t_association` (
  `id` varchar(100) NOT NULL COMMENT '主键 ID',
  `user_id` varchar(100) NOT NULL COMMENT '用户 ID',
  `associate_user_id` varchar(100) NOT NULL COMMENT '被关联的老人ID',
  `associate_role` tinyint(255) NOT NULL DEFAULT '0' COMMENT '关联人的角色 0-子女 1-老人',
  `state` int(4) DEFAULT '0' COMMENT '关联状态 0-待审核 1-同意 2-拒绝',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

# 服务管家
/*DROP TABLE IF EXISTS `t_manager`;
CREATE TABLE `t_manager` (
                           `id` varchar(100) NOT NULL COMMENT '主键 ID',
                           `phone` varchar(50) NOT NULL COMMENT '电话号码',
                           `pwd` varchar(100) NOT NULL COMMENT '登陆密码',
                           `identity` varchar(50) NULL DEFAULT NULL COMMENT '身份证',
                           `name` varchar(100) NULL DEFAULT NULL COMMENT '名字',
                           `birth` int(8) NULL DEFAULT NULL COMMENT '生日',
                           `sex` int(4) NULL DEFAULT NULL COMMENT '性别',
                           `image` varchar(500) NULL DEFAULT NULL COMMENT '头像 url',
                           `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                           `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;*/

DROP TABLE IF EXISTS `t_manager`;
CREATE TABLE `t_manager`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键 ID',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '电话号码',
  `pwd` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登陆密码',
  `identity` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名字',
  `birth` int(8) NULL DEFAULT NULL COMMENT '生日',
  `sex` int(4) NULL DEFAULT NULL COMMENT '性别',
  `image` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像 url',
  `province` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '省份',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '城市',
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '县',
  `town` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '乡',
  `community` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '社区',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '家庭地址',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 贴心服务
/*DROP TABLE IF EXISTS `t_service_center`;
CREATE TABLE `t_service_center` (
                                  `id` varchar(100) NOT NULL COMMENT '主键 ID',
                                  `user_id` varchar(100) NOT NULL COMMENT '用户 ID',
                                  `type` varchar(100) NOT NULL COMMENT '服务类型',
                                  `state` int(4) NULL DEFAULT NULL COMMENT '状态，0表示待接收，1表示进行中，2表示已完成，3表示已取消',
                                  `requirement` varchar(500) NOT NULL COMMENT '服务要求',
                                  `price` varchar(50) NOT NULL COMMENT '服务报酬价格',
                                  `service_time` datetime(0) NOT NULL COMMENT '服务时间',
                                  `manager_id` varchar(100) NOT NULL COMMENT '服务管家 ID',
                                  `service_star` int(4) NULL DEFAULT NULL COMMENT '服务星级',
                                  `finish_time` datetime(0) NOT NULL COMMENT '服务完成时间',
                                  `recordUrls` varchar(500) NOT NULL COMMENT '服务记录Url列表',
                                  `evaluate_star` int(4) NULL DEFAULT NULL COMMENT '评分星级',
                                  `evaluate_content` varchar(100) NOT NULL COMMENT '服务类型',
                                  `evaluate_time` datetime(0) NOT NULL COMMENT '评论时间',
                                  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                                  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;*/
DROP TABLE IF EXISTS `t_service_center`;
CREATE TABLE `t_service_center` (
                                  `id` varchar(100) NOT NULL COMMENT '主键 ID',
                                  `user_id` varchar(100) NOT NULL COMMENT '用户 ID',
                                  `help_type` tinyint(4) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000 COMMENT '0-贴心服务 1-帮扶',
                                  `type` varchar(100) NOT NULL COMMENT '服务类型',
                                  `state` int(4) NULL DEFAULT NULL COMMENT '状态，0表示待接收，1表示进行中，2表示已完成，3表示已取消',
                                  `requirement` varchar(500) NOT NULL COMMENT '服务要求',
                                  `price` varchar(50) NOT NULL COMMENT '服务报酬价格',
                                  `service_time` datetime(0) NOT NULL COMMENT '服务时间',
                                  `manager_id` varchar(100) NOT NULL COMMENT '服务管家 ID',
                                  `service_star` int(4) NULL DEFAULT NULL COMMENT '服务星级',
                                  `finish_time` datetime(0) NOT NULL COMMENT '服务完成时间',
                                  `record_url` varchar(500) NOT NULL DEFAULT '' COMMENT '服务记录Url列表',
                                  `evaluate_star` int(4) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000 COMMENT '评分星级',
                                  `evaluate_content` varchar(100) NOT NULL COMMENT '服务类型',
                                  `evaluate_time` datetime(0) NULL DEFAULT NULL COMMENT '评论时间',
                                  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                                  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 机构
DROP TABLE IF EXISTS `t_agency`;
CREATE TABLE `t_agency` (
                          `id` varchar(100) NOT NULL COMMENT '主键 ID',
                          `name` varchar(100) NULL DEFAULT NULL COMMENT '名字',
                          `type` varchar(100) NULL DEFAULT NULL COMMENT '类型',
                          `address` varchar(500) NULL DEFAULT NULL COMMENT '地址',
                          `province` varchar(50) NULL DEFAULT NULL COMMENT '省份',
                          `city` varchar(50) NULL DEFAULT NULL COMMENT '城市',
                          `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                          `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 医院
DROP TABLE IF EXISTS `t_hospital`;
CREATE TABLE `t_hospital` (
                            `id` varchar(100) NOT NULL COMMENT '主键 ID',
                            `name` varchar(100) NULL DEFAULT NULL COMMENT '名字',
                            `type` varchar(100) NULL DEFAULT NULL COMMENT '类型',
                            `address` varchar(500) NULL DEFAULT NULL COMMENT '地址',
                            `province` varchar(50) NULL DEFAULT NULL COMMENT '省份',
                            `city` varchar(50) NULL DEFAULT NULL COMMENT '城市',
                            `country` varchar(50) NULL DEFAULT NULL COMMENT '县',
                            `town` varchar(50) NULL DEFAULT NULL COMMENT '乡',
                            `community` varchar(50) NULL DEFAULT NULL COMMENT '社区',
                            `introduction` varchar(1000) NULL DEFAULT NULL COMMENT '介绍',
                            `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                            `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 事件处理日志
DROP TABLE IF EXISTS `t_event`;
CREATE TABLE `t_event` (
                         `id` varchar(100) NOT NULL COMMENT '主键 ID',
                         `operator` varchar(100) NULL DEFAULT NULL COMMENT '操作人',
                         `role` varchar(50) NULL DEFAULT NULL COMMENT '角色',
                         `content` varchar(500) NULL DEFAULT NULL COMMENT '内容',
                         `state` varchar(50) NULL DEFAULT NULL COMMENT '状态',
                         `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                         `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 投诉管理
DROP TABLE IF EXISTS `t_complaint`;
CREATE TABLE `t_complaint` (
                             `id` varchar(100) NOT NULL COMMENT '主键 ID',
                             `complainant` varchar(100) NULL DEFAULT NULL COMMENT '投诉人',
                             `content` varchar(500) NULL DEFAULT NULL COMMENT '内容',
                             `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                             `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 关怀
DROP TABLE IF EXISTS `t_care`;
CREATE TABLE `t_care` (
                        `id` varchar(100) NOT NULL COMMENT '主键 ID',
                        `title` varchar(100) NULL DEFAULT NULL COMMENT '标题',
                        `content` varchar(500) NULL DEFAULT NULL COMMENT '内容',
                        `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                        `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 通知公告
DROP TABLE IF EXISTS `t_notify`;
CREATE TABLE `t_notify` (
                          `id` varchar(100) NOT NULL COMMENT '主键 ID',
                          `content` varchar(500) NULL DEFAULT NULL COMMENT '内容',
                          `area` varchar(100) NULL DEFAULT NULL COMMENT '推送区域',
                          `state` varchar(50) NULL DEFAULT NULL COMMENT '状态',
                          `notify_time` datetime(0) NOT NULL COMMENT '推送时间',
                          `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                          `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

# 咨询
DROP TABLE IF EXISTS `t_new`;
CREATE TABLE `t_new` (
                       `id` varchar(100) NOT NULL COMMENT '主键 ID',
                       `title` varchar(100) NULL DEFAULT NULL COMMENT '标题',
                       `content` varchar(5000) NULL DEFAULT NULL COMMENT '内容',
                       /*`type` varchar(50) NULL DEFAULT NULL COMMENT '类型',*/
                       `type` tinyint(4) unsigned zerofill NOT NULL DEFAULT '0000' COMMENT '类型 0-工作动态 1-全民健康 2-孝感天地',
                       /*`notify_time` datetime(0) NOT NULL COMMENT '推送时间',*/
                       `notify_time` datetime DEFAULT NULL COMMENT '推送时间',
                       `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                       `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;
