-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int NOT NULL auto_increment COMMENT '主键',
  `code` varchar(32) NOT NULL unique COMMENT '编码',
  `name` varchar(64) default NULL COMMENT '名称',
  `supplier_code` varchar(32) default NULL COMMENT '供应商编码',
  `receive_code` varchar(32) character set utf8 collate utf8_bin default NULL COMMENT '接收方编码',
  PRIMARY KEY  (`id`)
) comment='公司' ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `cost_center`;
CREATE TABLE `cost_center` (
  `id` int NOT NULL auto_increment COMMENT '主键',
  `code` varchar(32) NOT NULL unique COMMENT '编码',
  `name` varchar(64) default NULL COMMENT '名称',
  PRIMARY KEY  (`id`)
)comment='成本中心'  ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int NOT NULL auto_increment COMMENT '主键',
  `code` varchar(32) NOT NULL unique COMMENT '编码',
  `name` varchar(64) default NULL COMMENT '名称',
  `department_type` varchar(4) default NULL COMMENT '类型见数据字典',
  `cost_centerid` varchar(4) default NULL COMMENT '成本中心',
  `parent_id` varchar(4) default NULL COMMENT '上级机构',
  PRIMARY KEY  (`id`)
)comment='部门'  ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `employer`;
CREATE TABLE `employer` (
  `id` int NOT NULL auto_increment COMMENT '主键',
  `code` varchar(32) NOT NULL unique COMMENT '编码',
  `name` varchar(64) default NULL COMMENT '名称',
  `department_id` varchar(4) default NULL COMMENT '部门编号',
  `cost_centerid` varchar(4) default NULL COMMENT '岗位编码',
  PRIMARY KEY  (`id`)
)comment='人员'  ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `id` int NOT NULL auto_increment COMMENT '主键',
  `code` varchar(32) NOT NULL unique COMMENT '编码',
  `name` varchar(64) default NULL COMMENT '名称',
  PRIMARY KEY  (`id`)
)comment='岗位'  ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` int NOT NULL auto_increment COMMENT '主键',
  `code` varchar(32) NOT NULL unique COMMENT '编码',
  `name` varchar(64) default NULL COMMENT '名称',
  `supplier_type` varchar(8) default NULL COMMENT '供应商类型见数据字典',
  PRIMARY KEY  (`id`)
)comment='供应商'  ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int NOT NULL auto_increment COMMENT '主键',
  `code` varchar(32) NOT NULL unique COMMENT '编码',
  `name` varchar(64) default NULL COMMENT '名称',
  `customer_type` varchar(8) default NULL COMMENT '客户类型见数据字典',
  PRIMARY KEY  (`id`)
)comment='客户'  ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment` (
  `id` int NOT NULL auto_increment COMMENT '主键',
  `code` varchar(32) NOT NULL unique COMMENT '编码',
  `name` varchar(64) default NULL COMMENT '名称',
  PRIMARY KEY  (`id`)
)comment='设备'  ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `mould`;
CREATE TABLE `mould` (
  `id` int NOT NULL auto_increment COMMENT '主键',
  `code` varchar(32) NOT NULL unique COMMENT '编码',
  `name` varchar(64) default NULL COMMENT '名称',
  `equipment_id` varchar(32) default NULL COMMENT '所属设备编码',
  PRIMARY KEY  (`id`)
)comment='模具' ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product_capacity`;
CREATE TABLE `product_capacity` (
  `id` int NOT NULL auto_increment COMMENT '主键',
  `code` varchar(32) NOT NULL unique COMMENT '编码',
  `name` varchar(64) default NULL COMMENT '名称',
  `resource_type` varchar(8) default NULL COMMENT '资源类型见数据字典',
  `work_ amount` int default NULL COMMENT '标准产能件数',
  `work_hour` float(5,2) default NULL COMMENT '标准产能工时',
  `efficiency` float(5,2) default NULL COMMENT '效率',
  PRIMARY KEY  (`id`)
)comment='资源' ENGINE=INNODB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `work_calendar`;
CREATE TABLE `work_calendar` (
  `id` int NOT NULL auto_increment COMMENT '主键',
  `date` date NOT NULL unique COMMENT '编码',
  `is_work` tinyint(1) default NULL COMMENT '名称',
  PRIMARY KEY  (`id`)
)comment='工作日历' ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `order_class`;
CREATE TABLE `order_class` (
  `id` int NOT NULL auto_increment COMMENT '主键',
  `code` varchar(32) NOT NULL unique COMMENT '编码',
  `name` varchar(64) default NULL COMMENT '名称',
  `time_interval_total` int default NULL COMMENT '总时段',
  `start_time` timestamp default NULL COMMENT '开始时间',
  `end_time` timestamp default NULL COMMENT '结束时间',
  PRIMARY KEY  (`id`)
)comment='班次' ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `id` int NOT NULL COMMENT '主键Id',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级类型Id',
  `code` varchar(64) unique DEFAULT NULL COMMENT '对照码',
  `value` varchar(8) DEFAULT NULL COMMENT '对照值',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sys字典类型表';
