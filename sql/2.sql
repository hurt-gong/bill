/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.25-enterprise-commercial-advanced : Database - hdedu_group
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hdedu_group` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `hdedu_group`;

/*Table structure for table `BR_SOURCE` */

DROP TABLE IF EXISTS `BR_SOURCE`;

CREATE TABLE `BR_SOURCE` (
  `id` bigint(20) NOT NULL,
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `storeUrl` varchar(200) DEFAULT NULL COMMENT '存储地址',
  `preUrl` varchar(200) DEFAULT NULL COMMENT '预览地址',
  `format` tinyint(4) DEFAULT NULL COMMENT '格式',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `subjectId` bigint(20) DEFAULT NULL COMMENT '所属学科id',
  `subjectName` varchar(50) DEFAULT NULL COMMENT '所属学科名称',
  `userId` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `crTime` datetime DEFAULT NULL COMMENT '创建时间',
  `schoolId` bigint(20) DEFAULT NULL COMMENT '学校Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学案';

/*Table structure for table `BR_SOURCE_TAG` */

DROP TABLE IF EXISTS `BR_SOURCE_TAG`;

CREATE TABLE `BR_SOURCE_TAG` (
  `id` bigint(20) NOT NULL,
  `sourceId` bigint(20) DEFAULT NULL COMMENT '学案Id',
  `klassId` bigint(20) DEFAULT NULL COMMENT '班级id',
  `klassName` varchar(50) DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学案TAG';

/*Table structure for table `PJ_EVALUATION` */

DROP TABLE IF EXISTS `PJ_EVALUATION`;

CREATE TABLE `PJ_EVALUATION` (
  `id` bigint(20) NOT NULL,
  `content` varchar(300) DEFAULT NULL COMMENT '评价内容',
  `star` int(11) DEFAULT NULL COMMENT '评价星星',
  `crTime` datetime DEFAULT NULL COMMENT '评价时间',
  `studentId` bigint(20) DEFAULT NULL COMMENT '学生id',
  `studentName` varchar(50) DEFAULT NULL COMMENT '学生姓名',
  `klassId` bigint(20) DEFAULT NULL COMMENT '学生所在班级id',
  `klassName` varchar(50) DEFAULT NULL COMMENT '学生所在班级名称',
  `teacherId` bigint(20) DEFAULT NULL COMMENT '教师id',
  `teacherName` varchar(50) DEFAULT NULL COMMENT '教师姓名',
  `subjetName` varchar(50) DEFAULT NULL COMMENT '任教学科名称',
  `schoolId` bigint(20) DEFAULT NULL COMMENT '学校id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评价';

/*Table structure for table `Q_GROUP` */

DROP TABLE IF EXISTS `Q_GROUP`;

CREATE TABLE `Q_GROUP` (
  `klassId` bigint(20) DEFAULT NULL,
  `headUrl` varchar(200) DEFAULT NULL COMMENT 'ͷ��',
  `quotations` varchar(200) DEFAULT NULL COMMENT '��¼',
  `notice` varchar(200) DEFAULT NULL COMMENT '����',
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��Ⱥ��';

/*Table structure for table `Q_GROUP_USER` */

DROP TABLE IF EXISTS `Q_GROUP_USER`;

CREATE TABLE `Q_GROUP_USER` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `groupId` bigint(20) DEFAULT NULL COMMENT 'Ⱥid',
  `userId` bigint(20) DEFAULT NULL COMMENT 'Ⱥ�û�',
  `tag` tinyint(4) DEFAULT NULL COMMENT '��ǩ.1:����Ա,2:����',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��Ⱥ�û�';

/*Table structure for table `Q_TOPIC` */

DROP TABLE IF EXISTS `Q_TOPIC`;

CREATE TABLE `Q_TOPIC` (
  `id` bigint(20) NOT NULL COMMENT '帖子id',
  `klassId` bigint(20) DEFAULT NULL COMMENT '班级Id',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `preContent` varchar(300) DEFAULT NULL COMMENT '预览内容',
  `isCommend` bit(1) DEFAULT NULL COMMENT '是否推荐',
  `isDigest` bit(1) DEFAULT NULL COMMENT '是否精华',
  `isLearn` bit(1) DEFAULT NULL COMMENT '是否学习帖',
  `isTop` bit(1) DEFAULT NULL COMMENT '是否置顶',
  `isReply` bit(1) DEFAULT NULL COMMENT '是否可回复',
  `isVote` bit(1) DEFAULT NULL COMMENT '是否投票',
  `ip` varchar(60) DEFAULT NULL COMMENT '发帖ip',
  `pid` bigint(20) DEFAULT NULL COMMENT '父id',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `userName` varchar(60) DEFAULT NULL COMMENT '用户名',
  `headUrl` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `crTime` datetime DEFAULT NULL COMMENT '发帖时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态,1;正常,2:删除',
  `replayCount` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帖子';

/*Table structure for table `Q_VOTE` */

DROP TABLE IF EXISTS `Q_VOTE`;

CREATE TABLE `Q_VOTE` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `groupId` bigint(20) DEFAULT NULL COMMENT 'Ⱥid',
  `title` varchar(300) DEFAULT NULL COMMENT '����',
  `optionType` tinyint(4) DEFAULT NULL COMMENT 'ѡ��',
  `topicId` bigint(20) DEFAULT NULL COMMENT '��id',
  `endDate` datetime DEFAULT NULL COMMENT '��������',
  `type` tinyint(4) DEFAULT NULL COMMENT '�ɼ�����',
  `userId` bigint(20) DEFAULT NULL COMMENT '�û�id',
  `userName` varchar(60) DEFAULT NULL COMMENT '�û���',
  `headUrl` varchar(100) DEFAULT NULL COMMENT '�û�ͷ��',
  `crTime` datetime DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ͶƱ';

/*Table structure for table `Q_VOTE_OPTION` */

DROP TABLE IF EXISTS `Q_VOTE_OPTION`;

CREATE TABLE `Q_VOTE_OPTION` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `voteId` bigint(20) DEFAULT NULL COMMENT 'ͶƱid',
  `content` varchar(200) DEFAULT NULL COMMENT '����',
  `orderNum` tinyint(4) DEFAULT NULL COMMENT '����',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ͶƱѡ��';

/*Table structure for table `Q_VOTE_USER` */

DROP TABLE IF EXISTS `Q_VOTE_USER`;

CREATE TABLE `Q_VOTE_USER` (
  `id` bigint(20) NOT NULL,
  `voteId` bigint(20) DEFAULT NULL COMMENT 'ͶƱid',
  `optionId` bigint(20) DEFAULT NULL COMMENT 'ѡ��id',
  `userId` bigint(20) DEFAULT NULL COMMENT '�û�id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ͶƱ�û�';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
