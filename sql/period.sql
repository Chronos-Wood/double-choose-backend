USE double_choose;
SET NAMES utf8mb4;
CREATE TABLE semester(
  `id` INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `description` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '描述',
  `begin` DATETIME NOT NULL DEFAULT NOW() COMMENT '开始时间',
  `end` DATETIME NOT NULL DEFAULT NOW() COMMENT '结束时间',
  `create_time` DATETIME(4) NOT NULL DEFAULT CURRENT_TIMESTAMP(4) COMMENT '创建日期',
  `update_time` DATETIME(4) NOT NULL DEFAULT CURRENT_TIMESTAMP(4) ON UPDATE CURRENT_TIMESTAMP(4) COMMENT '更新日期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='双选时期表'