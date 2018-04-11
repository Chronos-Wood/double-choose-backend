USE double_choose;
SET NAMES utf8mb4;
CREATE TABLE will(
  `id` INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `student_id` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '学生ID',
  `project_id` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '课题ID',
  `accepted` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '志愿是否被接收 0否 1是',
  `period_id` INT UNSIGNED DEFAULT 0 NOT NULL COMMENT '时期ID',
  `precedence` INT UNSIGNED DEFAULT 1 NOT NULL COMMENT '志愿优先级 即第一志愿，第二志愿，第三志愿',
  `create_time` DATETIME(4) NOT NULL DEFAULT CURRENT_TIMESTAMP(4) COMMENT '创建日期',
  `update_time` DATETIME(4) NOT NULL DEFAULT CURRENT_TIMESTAMP(4) ON UPDATE CURRENT_TIMESTAMP(4) COMMENT '更新日期',
  UNIQUE INDEX uniq_idx_studentid_projectid(`student_id`, `project_id`, `period_id`, `precedence`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='志愿表';