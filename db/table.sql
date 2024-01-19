/*
 user表
 */
CREATE TABLE `user` (
                        `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户主键',
                        `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
                        `pass_word` varchar(36) DEFAULT NULL COMMENT '密码',
                        `ctime` bigint DEFAULT NULL COMMENT '创建时间',
                        `mtime` bigint DEFAULT NULL COMMENT '更新时间',
                        `deleted` tinyint(1) DEFAULT '0' COMMENT '软删除字段0=未删除，1=已删除',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户表';