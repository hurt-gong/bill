/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.25-enterprise-commercial-advanced : Database - hdedu_tiku
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hdedu_tiku` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `hdedu_tiku`;

/*Table structure for table `TK_ANALYSIS` */

DROP TABLE IF EXISTS `TK_ANALYSIS`;

CREATE TABLE `TK_ANALYSIS` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `examId` bigint(20) DEFAULT NULL COMMENT '试题内容id',
  `videoName` varchar(50) DEFAULT NULL COMMENT '视频解析名称  ',
  `videoCode` char(10) DEFAULT NULL COMMENT '视频编码',
  `type` tinyint(4) DEFAULT NULL COMMENT '解析类型(1:文本解析;2：图文解析;3:视频解析)',
  `videoCover` varchar(200) DEFAULT NULL COMMENT '视频解析封面',
  `content` blob COMMENT '解析内容',
  PRIMARY KEY (`id`),
  KEY `idx_examId` (`examId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='试题解析';

/*Table structure for table `TK_BASE_CODE` */

DROP TABLE IF EXISTS `TK_BASE_CODE`;

CREATE TABLE `TK_BASE_CODE` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '本表主键',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '数据名称',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '标记数据类型，数据类型见枚举',
  `orderNum` tinyint(4) NOT NULL DEFAULT '0' COMMENT '标记数据显示的顺序',
  PRIMARY KEY (`id`),
  KEY `idx_type` (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='基础配置表';

/*Table structure for table `TK_BASE_CODE_REL` */

DROP TABLE IF EXISTS `TK_BASE_CODE_REL`;

CREATE TABLE `TK_BASE_CODE_REL` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '本表主键',
  `latoId` bigint(20) NOT NULL DEFAULT '0' COMMENT '查询对象类型1的ID，若为一维条件查询，那么此字段首选为非空',
  `lattId` bigint(20) NOT NULL DEFAULT '0' COMMENT '查询对象类型2的ID，若为二维条件查询，此字段非空',
  `toId` bigint(20) NOT NULL DEFAULT '0' COMMENT '被查询类型ID',
  `toName` varchar(20) NOT NULL DEFAULT '' COMMENT '被查询对象名称',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '标记被查询对象的类型，例如学段和学科的关系，这个类型就是学科的类型',
  `orderNum` smallint(6) NOT NULL DEFAULT '0' COMMENT '被查询类型对象的显示顺序',
  PRIMARY KEY (`id`),
  KEY `idx_latoId_lattId` (`latoId`,`lattId`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=432 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='存储基础配置表之间数据的关系，支持多维存储。';

/*Table structure for table `TK_EXAM` */

DROP TABLE IF EXISTS `TK_EXAM`;

CREATE TABLE `TK_EXAM` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `content` blob NOT NULL COMMENT '试题内容',
  `answer` blob NOT NULL COMMENT '答案内容',
  `source` varchar(50) NOT NULL COMMENT '试题来源',
  `analysis` blob NOT NULL COMMENT '试题的默认解析,文本解析',
  `option1` blob COMMENT '选项1',
  `option2` blob COMMENT '选项2',
  `option3` blob COMMENT '选项3',
  `option4` blob COMMENT '选项4',
  `option5` blob COMMENT '选项5',
  `option6` blob COMMENT '选项6',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='试题内容和答案表';

/*Table structure for table `TK_EXAM_KNOWLEDGE` */

DROP TABLE IF EXISTS `TK_EXAM_KNOWLEDGE`;

CREATE TABLE `TK_EXAM_KNOWLEDGE` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `examId` bigint(20) DEFAULT NULL COMMENT '试题内容id',
  `kId` bigint(20) DEFAULT NULL COMMENT '知识点id',
  `type` tinyint(4) DEFAULT NULL COMMENT '知识点类型(14一级,15二级,16三级)',
  `kName` varchar(100) DEFAULT NULL COMMENT '知识点名称',
  PRIMARY KEY (`id`),
  KEY `idx_examId_type` (`examId`,`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='试题和知识点关系';

/*Table structure for table `TK_EXAM_SEARCH` */

DROP TABLE IF EXISTS `TK_EXAM_SEARCH`;

CREATE TABLE `TK_EXAM_SEARCH` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `examId` bigint(10) DEFAULT NULL COMMENT '试题内容id',
  `code` bigint(20) DEFAULT NULL COMMENT '唯一码',
  `phaseId` bigint(20) DEFAULT NULL COMMENT '学段',
  `subjectId` bigint(20) DEFAULT NULL COMMENT '学科',
  `typeId` bigint(20) DEFAULT NULL COMMENT '题型',
  `difficultyId` bigint(20) DEFAULT NULL COMMENT '难度',
  `isObjectivie` bit(1) DEFAULT NULL COMMENT '是否客观题',
  `optionCount` int(10) DEFAULT NULL COMMENT '选项个数',
  `isVideoResolve` bit(1) DEFAULT NULL COMMENT '否有视频解析(true是，false否)',
  `typeName` varchar(10) DEFAULT NULL COMMENT '题型名称',
  `subjectName` varchar(10) DEFAULT NULL COMMENT '学科名',
  `diffName` varchar(10) DEFAULT NULL COMMENT '难度名称',
  `phaseName` varchar(10) DEFAULT NULL COMMENT '学段名称',
  `category` tinyint(4) DEFAULT '1' COMMENT '试题分类1系统第一版2用户上传',
  `schoolId` bigint(20) DEFAULT NULL COMMENT '上传的学校id',
  `teamGroupId` bigint(20) DEFAULT NULL COMMENT '上传的备课组id',
  `userId` bigint(20) DEFAULT NULL COMMENT '上传的用户id',
  `crTime` datetime DEFAULT NULL COMMENT '上传的时间',
  `areaId` bigint(20) DEFAULT NULL COMMENT '区域id',
  `editionId` bigint(20) DEFAULT NULL COMMENT '版本id',
  `creditLineId` bigint(20) DEFAULT NULL COMMENT '题注id',
  `creditLineName` varchar(50) DEFAULT NULL COMMENT '题注名称',
  `areaName` varchar(50) DEFAULT NULL COMMENT '地区名称',
  `editionName` varchar(50) DEFAULT NULL COMMENT '版本名称',
  PRIMARY KEY (`id`),
  KEY `idx_examId` (`examId`) USING BTREE,
  KEY `idx_index` (`phaseId`,`subjectId`,`typeId`,`creditLineId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='试题查询表';

/*Table structure for table `TK_PAPER` */

DROP TABLE IF EXISTS `TK_PAPER`;

CREATE TABLE `TK_PAPER` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `phaseId` bigint(20) DEFAULT NULL COMMENT '学段Id',
  `subjectId` bigint(20) DEFAULT NULL COMMENT '学科Id',
  `userId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `type` int(11) DEFAULT NULL COMMENT '1教师组卷2学生组卷',
  `examNum` int(11) DEFAULT NULL COMMENT '试题数量',
  `timeLimit` int(11) DEFAULT NULL COMMENT '限时',
  `knowlegeName` varchar(100) DEFAULT NULL COMMENT '知识点集合',
  `typeName` varchar(100) DEFAULT NULL COMMENT '试题类型集合',
  `status` int(11) DEFAULT NULL COMMENT '状态1未发布2发布',
  `purpose` int(11) DEFAULT NULL COMMENT '目的1在线做2下载',
  `crTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `TK_PAPER_EXAM` */

DROP TABLE IF EXISTS `TK_PAPER_EXAM`;

CREATE TABLE `TK_PAPER_EXAM` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `paperId` bigint(20) DEFAULT NULL COMMENT '试卷ID',
  `examId` bigint(20) DEFAULT NULL COMMENT '试题ID',
  `orderNum` int(11) DEFAULT NULL COMMENT '排序',
  `typeId` bigint(20) DEFAULT NULL COMMENT '题型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `TK_XX_CODE` */

DROP TABLE IF EXISTS `TK_XX_CODE`;

CREATE TABLE `TK_XX_CODE` (
  `id` bigint(20) NOT NULL,
  `type` varchar(50) DEFAULT NULL COMMENT '数据类型',
  `name` varchar(20) DEFAULT NULL COMMENT '数据项名称',
  `value` int(11) DEFAULT NULL COMMENT '数据项值',
  `orderNum` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `TK_XX_EXAM_CONTENT` */

DROP TABLE IF EXISTS `TK_XX_EXAM_CONTENT`;

CREATE TABLE `TK_XX_EXAM_CONTENT` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `optionCount` int(11) DEFAULT NULL COMMENT '选项个数',
  `content` text COMMENT '试题内容',
  `analysis` text COMMENT '试题解析',
  `answer` varchar(255) DEFAULT NULL COMMENT '答案内容',
  `crName` varchar(10) DEFAULT NULL COMMENT '创建者',
  `crTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='试题内容表';

/*Table structure for table `TK_XX_EXAM_INFO` */

DROP TABLE IF EXISTS `TK_XX_EXAM_INFO`;

CREATE TABLE `TK_XX_EXAM_INFO` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `number` varchar(255) DEFAULT NULL COMMENT '编号信息',
  `contentId` bigint(20) DEFAULT NULL COMMENT '试题内容主键',
  `subjectId` bigint(20) DEFAULT NULL COMMENT '科目',
  `gradeId` bigint(20) DEFAULT NULL COMMENT '年级',
  `typeId` bigint(20) DEFAULT NULL COMMENT '题型',
  `versionId` bigint(20) DEFAULT NULL COMMENT '版本',
  `difficultyId` bigint(20) DEFAULT NULL COMMENT '难度',
  `crName` varchar(10) DEFAULT NULL COMMENT '创建者',
  `crTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='试题信息';

/*Table structure for table `TK_XX_EXAM_KNOWLEDGE` */

DROP TABLE IF EXISTS `TK_XX_EXAM_KNOWLEDGE`;

CREATE TABLE `TK_XX_EXAM_KNOWLEDGE` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `knowledgeId` bigint(20) DEFAULT NULL COMMENT '知识点id',
  `examId` bigint(20) DEFAULT NULL COMMENT '试题id',
  PRIMARY KEY (`id`),
  KEY `idx_examId` (`examId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='知识点与试题关联表';

/*Table structure for table `TK_XX_KNOWLEDGE` */

DROP TABLE IF EXISTS `TK_XX_KNOWLEDGE`;

CREATE TABLE `TK_XX_KNOWLEDGE` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '知识点名',
  `level` int(2) DEFAULT NULL COMMENT '级别',
  `parentId` bigint(20) DEFAULT NULL COMMENT '父级知识点Id',
  `orderNum` int(11) DEFAULT NULL COMMENT '排序',
  `gradeId` bigint(20) DEFAULT NULL COMMENT '年级',
  `subjectId` bigint(20) DEFAULT NULL COMMENT '学科',
  `versionId` bigint(20) DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='知识点信息';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
