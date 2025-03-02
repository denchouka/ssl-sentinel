CREATE DATABASE `ssl_sentinel` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';
USE ssl_sentinel;


/* 用户表 */
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录密码',
  `nickname` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `secret_key` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'MFA密钥',
  `create_time` DATETIME(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

insert into ssl_sentinel.user (username, password, nickname, secret_key)
values ('denchouka', '$2a$10$RN6VBTQdT9Ywp8HDWDF8IuLZ1Kh5c18wpF4kMBhJ.FoedsOUk8B02', 'Admin', 'A5I2UH6L2K22MJU5');

/* 任务表 */
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `domain_name` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '域名',
  `application_platform` VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '申请平台',
  `usage_platform` VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '使用平台',
  `purpose` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用途',
  `ddl` DATE NOT NULL COMMENT '过期日期',
  `date` DATE NOT NULL COMMENT '提醒日期',
  `email` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱地址',
  `content` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '提醒内容',
  `remark` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '备注',
  `status` INT(1) NOT NULL DEFAULT 0 COMMENT '状态（1:未执行、2:执行中、3:执行完成）',
  `create_time` DATETIME(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务表' ROW_FORMAT = DYNAMIC;

/* 历史表 */
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `task_id` BIGINT(20) NOT NULL COMMENT '任务id',
  `execute_time` DATETIME(0) NOT NULL COMMENT '任务执行时间',
  `create_time` DATETIME(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '历史表' ROW_FORMAT = DYNAMIC;