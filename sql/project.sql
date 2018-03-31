USE double_choose;
SET NAMES utf8mb4;
CREATE TABLE project(
  `id` INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` VARCHAR(128) DEFAULT '' NOT NULL COMMENT '研究项目名',
  `preview_iamge` VARCHAR(1024) DEFAULT '' NOT NULL COMMENT '预览图',
  `description` VARCHAR(128) NOT NULL  DEFAULT '' COMMENT '项目简介',
  `director_id` INT UNSIGNED DEFAULT 0 NOT NULL COMMENT '项目负责人id, 即director表中的主键',
  `period_id` INT UNSIGNED DEFAULT 0 NOT NULL COMMENT '时期ID',
  `begin` DATETIME NOT NULL DEFAULT NOW() COMMENT '课题开始时间',
  `end` DATETIME NOT NULL DEFAULT NOW() COMMENT '课题结束时间',
  `create_time` DATETIME(4) NOT NULL DEFAULT CURRENT_TIMESTAMP(4) COMMENT '创建日期',
  `update_time` DATETIME(4) NOT NULL DEFAULT CURRENT_TIMESTAMP(4) ON UPDATE CURRENT_TIMESTAMP(4) COMMENT '更新日期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课题信息表';