drop database if exists `vercode`;
create database `vercode`;
use `vercode`;

drop table if exists `phone`;
create table `phone` (
    `id` int unsigned not null auto_increment,
	`user_id` int unsigned not null,
    `number` varchar(50) not null,
	`status` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '0代表未使用, 1代表正在等待验证码, 2代表已输入验证码, 3代表已使用',
	`code` varchar(50) not null DEFAULT '',
    `create_time` timestamp not null default now(),
	`use_time` timestamp not null default '0000-00-00 00:00:00',
    primary key(`id`),
	index idx_user_id(`user_id`),
    unique key `uniq_number` (`number`)
) engine=InnoDB default charset utf8;

drop table if exists `user`;
create table `user` (
    `id` int unsigned not null auto_increment,
    `name` varchar(50) not null,
	`token` varchar(50) not null,
	`type` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '0表示号码商, 1表示验证码获取方',
    `create_time` timestamp not null default now(),
    primary key(`id`),
    unique key `uniq_token` (`token`)
) engine=InnoDB default charset utf8;