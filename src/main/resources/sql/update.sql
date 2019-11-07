CREATE TABLE `wwd_banner` (
  `id` varchar(32) NOT NULL,
  `title` varchar(255) NOT NULL COMMENT '标题',
  `title_url` varchar(255) NOT NULL COMMENT '标题图URL',
  `redirect_url` varchar(255) DEFAULT NULL COMMENT '跳转链接Url',
  `type` varchar(50) DEFAULT NULL COMMENT '类型: 用户，活动，页面',
  `status` varchar(255) DEFAULT 'Y' COMMENT '可用状态：Y/N',
  `descp` varchar(255) DEFAULT NULL COMMENT '描述',
  `data_user_id` varchar(32) DEFAULT NULL COMMENT '数据归属用户id',
  `data_office_id` varchar(32) DEFAULT NULL COMMENT '数据归属机构id',
  `data_type` varchar(255) DEFAULT NULL COMMENT '数据类型，分类',
  `data_area_id` varchar(32) DEFAULT NULL COMMENT '数据归属区域id',
  `del_flag` char(1) NOT NULL DEFAULT 'N' COMMENT '删除标记，N=未删除；Y=已删除',
  `create_at` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `update_at` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(32) NOT NULL COMMENT '修改人',
  `modified_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '标记本条数据的更新时间，不使用程序更新',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ROW_FORMAT=COMPACT COMMENT='轮播图';

ALTER TABLE `wwd_activity_user_mutual_election`
ADD COLUMN `LEVEL`  char(1)  NULL DEFAULT NULL COMMENT '互选级别：A-B-C...' AFTER `SELECTED_WWD__USER_ID`;

