DROP TABLE IF EXISTS `mst_group`;
DROP TABLE IF EXISTS `mst_japan`;
DROP TABLE IF EXISTS `tbl_detail_user_japan`;
DROP TABLE IF EXISTS `tbl_user`;


CREATE TABLE `mst_group` (
  `group_id` NUMERIC(11) NOT NULL AUTO_INCREMENT,
  `group_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`group_id`)
);

CREATE TABLE `mst_japanse` (
  `code_level` VARCHAR(15) NOT NULL,
  `name_level` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`code_level`)
);

CREATE TABLE `tbl_user` (
  `user_id` NUMERIC(11) NOT NULL AUTO_INCREMENT,
  `group_id` NUMERIC(11) ,
  `login_name` VARCHAR(15) ,
  `password` VARCHAR(255) ,
  `full_name` VARCHAR(255) ,
  `full_name_kana` VARCHAR(255) DEFAULT NULL,
  `email` VARCHAR(255) ,
  `tel` VARCHAR(15) ,
  `birthday` timestamp ,
  `rule` VARCHAR(255) ,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FK_USER_GROUP` FOREIGN KEY (`group_id`) REFERENCES `mst_group` (`group_id`)
);

CREATE TABLE `tbl_detail_user_japan` (
  `detail_user_japan_id` NUMERIC(11) NOT NULL AUTO_INCREMENT,
  `user_id` NUMERIC(11) ,
  `code_level` VARCHAR(15) ,
  `start_date` timestamp ,
  `end_date` timestamp ,
  `total` NUMERIC(11) ,
  PRIMARY KEY (`detail_user_japan_id`),
  CONSTRAINT `FK_DETAIL_USER_JAPANSE` FOREIGN KEY (`code_level`) REFERENCES `mst_japanse` (`code_level`),
  CONSTRAINT `FK_DETAIL_USER_USER` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`)
);
