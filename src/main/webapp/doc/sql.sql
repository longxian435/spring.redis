CREATE TABLE `essay` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(100) collate utf8_unicode_ci NOT NULL,
  `content` varchar(500) collate utf8_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `creator_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL auto_increment,
  `user_name` varchar(32) collate utf8_unicode_ci NOT NULL,
  `pass_word` varchar(128) collate utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update` datetime NOT NULL,
  `status` int(11) NOT NULL default '1' COMMENT '0:禁用,1:启用',
  PRIMARY KEY  (`id`),
  KEY `Index_1` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;





CREATE TABLE `sys_oper_logs` (
  `id` bigint(20) NOT NULL auto_increment,
  `userid` bigint(20) NOT NULL,
  `username` varchar(30) collate utf8_unicode_ci NOT NULL,
  `opera_type` varchar(20) collate utf8_unicode_ci NOT NULL,
  `opera_des` varchar(100) collate utf8_unicode_ci default NULL,
  `url` varchar(100) collate utf8_unicode_ci default NULL,
  `oldvalue` text collate utf8_unicode_ci,
  `newvalue` text collate utf8_unicode_ci,
  `create_time` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26766 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;





