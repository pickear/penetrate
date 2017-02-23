--
--    Copyright 2015-2016 the original author or authors.
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.
--
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `device_type` varchar(255) DEFAULT NULL,
  `protocol_type` varchar(255) DEFAULT NULL,
  `auth_token` varchar(255) DEFAULT NULL,
  `bind_addr` varchar(255) DEFAULT NULL,
  `listen_port` varchar(255) DEFAULT NULL,
  `custom_domains` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `common`;
CREATE TABLE `common` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bind_addr` varchar(255) DEFAULT NULL,
  `bind_port` varchar(255) DEFAULT NULL,
  `vhost_http_port` varchar(255) DEFAULT NULL,
  `vhost_https_port` varchar(255) DEFAULT NULL,
  `dashboard_port` varchar(255) DEFAULT NULL,
  `dashboard_user` varchar(255) DEFAULT NULL,
  `dashboard_pwd` varchar(255) DEFAULT NULL,
  `log_file` varchar(255) DEFAULT '',
  `log_level` varchar(255) DEFAULT '',
  `log_max_days` int(11) DEFAULT NULL,
  `privilege_mode` tinyint(4) DEFAULT NULL,
  `privilege_token` varchar(255) DEFAULT NULL,
  `privilege_allow_ports` varchar(255) DEFAULT NULL,
  `max_pool_count` int(11) DEFAULT NULL,
  `authentication_timeout` int(11) DEFAULT NULL,
  `subdomain_host` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- ----------------------------
-- Table structure for `user`
-- ----------------------------
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(20) NOT NULL COMMENT '用户名',
  `nick_name` varchar(20) NOT NULL COMMENT '用户昵称',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `salt` varchar(50) NOT NULL COMMENT '加密盐',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `login_count` INT(10) COMMENT '登录次数',
  `create_time` varchar(20) NOT NULL COMMENT '注册时间',
  `login_time` varchar(20) COMMENT '最近一次登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `code` varchar(50) NOT NULL COMMENT '角色编码',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  KEY `u_key` (`user_id`),
  KEY `r_key` (`role_id`),
  CONSTRAINT `r_key` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `u_key` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO user(id,name,nick_name,password,salt,email,login_count,create_time,login_time) VALUES (1,'admin','manager','ff709232640c89dbfa7e60efa2ad80cd','b3e7cdbc601dfb86f1298be0d075c0ec','pickear@gmail.com',1,'2017-02-20 12:00:00','2017-02-20 12:00:00');

INSERT INTO `role` VALUES ('1', 'ADMIN', '管理员');

INSERT INTO `user_role` VALUES ('1', '1', '1');