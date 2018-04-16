USE double_choose;
SET NAMES utf8mb4;
CREATE TABLE assignment(
  `id` INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `project_id` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '研究项目id',
  `project_name` VARCHAR(128) DEFAULT '' NOT NULL COMMENT '研究项目名',
  `student_id` INT UNSIGNED DEFAULT 0 NOT NULL COMMENT  '学生id',
  `student_name` VARCHAR(32) DEFAULT '' NOT NULL COMMENT '学生姓名',
  `director_id` INT UNSIGNED DEFAULT 0 NOT NULL COMMENT '项目负责人id, 即director表中的主键',
  `director_name` VARCHAR(32) DEFAULT '' NOT NULL COMMENT '负责人姓名',
  `period_id` INT UNSIGNED DEFAULT 0 NOT NULL COMMENT '时期ID',
  `precedence` INT UNSIGNED DEFAULT 0 NOT NULL COMMENT '志愿优先级',
  `create_time` DATETIME(4) NOT NULL DEFAULT CURRENT_TIMESTAMP(4) COMMENT '创建日期',
  `update_time` DATETIME(4) NOT NULL DEFAULT CURRENT_TIMESTAMP(4) ON UPDATE CURRENT_TIMESTAMP(4) COMMENT '更新日期',
  UNIQUE INDEX student_projectid_period(`student_id`, `project_id`,`period_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课题分配结果';