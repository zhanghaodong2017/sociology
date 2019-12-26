-- 用户表
create table user_info
(
  guid        varchar(36) not null
    primary key,
  user_name   varchar(64) null,
  pass_word   varchar(64) null,
  salt        varchar(32) null,
  real_name   varchar(12) null,
  address     varchar(255) null,
  sex         int(3),
  email       varchar(32) null,
  level       int(10) null,
  create_time datetime null,
  update_time datetime null
);


INSERT INTO `zhd`.`user_info` (`guid`, `user_name`, `pass_word`, `salt`, `real_name`, `address`, `sex`, `email`,
                               `level`, `create_time`, `update_time`)
VALUES ('4fc9ff8f89594ec9a106c5dd1648460d', 'admin', '7d5c364260ffe068e34cd5119e3dbe36', 'a549d7efc3c0', '管理', 'NULL', 1, '', 0, '2019-12-25 12:12:39',
        '2019-12-25 12:12:43')

