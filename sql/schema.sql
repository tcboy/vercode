drop database if exists `vercode`;
create database `vercode`;
use `vercode`;

drop table if exists `phone`;
create table `phone` (
    `id` int unsigned not null auto_increment,
    `number` varchar(50) not null,
	`status` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '0代表未使用, 1代表正在等待验证码, 2代表已输入验证码, 3代表已使用验证码',
	`code` varchar(50) not null DEFAULT '',
    `create_time` timestamp not null default now(),
	`use_time` timestamp not null default '0000-00-00 00:00:00',
    primary key(`id`),
    unique key `uniq_number` (`number`)
) engine=InnoDB default charset utf8;