CREATE TABLE `qrtz_job_details` (
  `oid` varchar(50) NOT NULL,
  `job_name` varchar(200) DEFAULT NULL,
  `job_group` varchar(200) DEFAULT NULL,
  `job_class_name` varchar(200) DEFAULT NULL COMMENT '根据class名字区分job的类型',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `qrtz_triggers` (
  `oid` varchar(50) DEFAULT NULL,
  `job_oid` varchar(50) NOT NULL,
  `trigger_name` varchar(200) DEFAULT NULL,
  `cron_expression` varchar(200) DEFAULT NULL,
  KEY `job_oid` (`job_oid`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`job_oid`) REFERENCES `qrtz_job_details` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

