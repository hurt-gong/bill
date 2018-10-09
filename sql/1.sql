/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.25-enterprise-commercial-advanced : Database - hdedu_course
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hdedu_course` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `hdedu_course`;

/*Table structure for table `TJ_TASK_STATISTICS` */

DROP TABLE IF EXISTS `TJ_TASK_STATISTICS`;

CREATE TABLE `TJ_TASK_STATISTICS` (
  `id` bigint(20) NOT NULL COMMENT '本表id',
  `taskId` bigint(20) DEFAULT NULL COMMENT '任务id(视频id，作业id，学案id)',
  `taskName` varchar(100) DEFAULT NULL,
  `taskType` smallint(2) DEFAULT NULL COMMENT '任务类型(1=视频,2=作业,3=学案)',
  `taskStudentQty` int(11) DEFAULT NULL COMMENT '任务认识',
  `finishStudentQty` int(11) DEFAULT NULL COMMENT '任务完成人数',
  `finishRate` decimal(3,2) DEFAULT NULL COMMENT '完成率',
  `firstStudentId` bigint(20) DEFAULT NULL COMMENT '首名完成任务同学Id',
  `firstStudentName` varchar(50) DEFAULT NULL COMMENT '首名完成任务同学名',
  `firstStudentKlassName` varchar(50) DEFAULT NULL COMMENT '首名完成任务同学班级',
  `teacherId` bigint(20) DEFAULT NULL,
  `teacherName` varchar(50) DEFAULT NULL,
  `schoolId` bigint(20) DEFAULT NULL COMMENT '学校Id',
  `schoolName` varchar(50) DEFAULT NULL COMMENT '学校名称',
  `crDate` double DEFAULT NULL COMMENT '当前日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学习任务统计';

/*Table structure for table `WK_COMMENT` */

DROP TABLE IF EXISTS `WK_COMMENT`;

CREATE TABLE `WK_COMMENT` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `content` varchar(100) DEFAULT NULL COMMENT '内容',
  `schoolId` bigint(20) DEFAULT NULL COMMENT '学校id',
  `klassId` bigint(20) DEFAULT NULL COMMENT '班级Id',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `title` varchar(100) DEFAULT NULL COMMENT '用户title',
  `pid` bigint(20) DEFAULT NULL COMMENT '父id',
  `isLeaf` tinyint(1) DEFAULT NULL COMMENT '是否叶子节点',
  `objId` bigint(20) DEFAULT NULL COMMENT '评论对象id',
  `type` tinyint(4) DEFAULT NULL COMMENT '评论类型,1：课程互动,2:我的答疑,3课程评价',
  `isTop` bit(1) DEFAULT NULL COMMENT '是否置顶',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `schoolName` varchar(100) DEFAULT NULL,
  `userName` varchar(100) DEFAULT NULL,
  `klassName` varchar(100) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `headUrl` varchar(100) DEFAULT NULL,
  `crTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论';

/*Table structure for table `WK_EXAM_CONTENT` */

DROP TABLE IF EXISTS `WK_EXAM_CONTENT`;

CREATE TABLE `WK_EXAM_CONTENT` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '本表主键',
  `type` int(11) NOT NULL COMMENT '枚举类型',
  `content` blob NOT NULL COMMENT '内容',
  `analysis` blob NOT NULL COMMENT '解析',
  `answer` blob NOT NULL COMMENT '答案',
  `option1` blob,
  `option2` blob,
  `option3` blob,
  `option4` blob,
  `option5` blob,
  `option6` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='试题表';

/*Table structure for table `WK_EXAM_EXAM` */

DROP TABLE IF EXISTS `WK_EXAM_EXAM`;

CREATE TABLE `WK_EXAM_EXAM` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '本表主键',
  `bigId` bigint(20) NOT NULL DEFAULT '0' COMMENT '作业id',
  `smallId` bigint(20) NOT NULL DEFAULT '0' COMMENT '试题id',
  `orderNum` int(10) NOT NULL COMMENT '题型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='习题和试题关系';

/*Table structure for table `WK_HOMEWORK` */

DROP TABLE IF EXISTS `WK_HOMEWORK`;

CREATE TABLE `WK_HOMEWORK` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '本表主键',
  `title` varchar(10) NOT NULL COMMENT '作业名称',
  `klass` bigint(20) NOT NULL DEFAULT '0' COMMENT '关联微课程表主键',
  `subjectId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课时id',
  `startDate` date NOT NULL COMMENT '发作业时间',
  `endDate` date NOT NULL COMMENT '交作业时间',
  `examNum` int(11) NOT NULL DEFAULT '0' COMMENT '试题数量',
  `answerNum` int(11) NOT NULL DEFAULT '0' COMMENT '答题人数',
  `klassNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师发布的作业';

/*Table structure for table `WK_HOMEWORK_CLASS` */

DROP TABLE IF EXISTS `WK_HOMEWORK_CLASS`;

CREATE TABLE `WK_HOMEWORK_CLASS` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '本表主键',
  `hwId` bigint(20) NOT NULL DEFAULT '0' COMMENT '作业id',
  `classId` bigint(20) NOT NULL DEFAULT '0' COMMENT '班级id',
  `subjectId` bigint(20) NOT NULL DEFAULT '0' COMMENT '学科id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='作业和班级关系表';

/*Table structure for table `WK_HOMEWORK_EXAM` */

DROP TABLE IF EXISTS `WK_HOMEWORK_EXAM`;

CREATE TABLE `WK_HOMEWORK_EXAM` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '本表主键',
  `hwId` bigint(20) NOT NULL DEFAULT '0' COMMENT '作业id',
  `examId` bigint(20) NOT NULL DEFAULT '0' COMMENT '试题id',
  `type` tinyint(2) NOT NULL COMMENT '题型',
  `sourceType` tinyint(1) DEFAULT NULL,
  `orderNum` int(3) NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='作业和试题关系';

/*Table structure for table `WK_HOMEWORK_EXAM_NEW` */

DROP TABLE IF EXISTS `WK_HOMEWORK_EXAM_NEW`;

CREATE TABLE `WK_HOMEWORK_EXAM_NEW` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '本表主键',
  `hwId` bigint(20) NOT NULL DEFAULT '0' COMMENT '作业id',
  `examId` bigint(20) NOT NULL DEFAULT '0' COMMENT '习题id',
  `type` bigint(20) DEFAULT NULL COMMENT '题型',
  `orderNum` int(3) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='作业和习题关系表';

/*Table structure for table `WK_HOMEWORK_NEW` */

DROP TABLE IF EXISTS `WK_HOMEWORK_NEW`;

CREATE TABLE `WK_HOMEWORK_NEW` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '本表主键',
  `title` varchar(10) NOT NULL COMMENT '作业名称',
  `subjectId` bigint(20) NOT NULL DEFAULT '0' COMMENT '学科id',
  `creator` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `startDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发作业时间',
  `endDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '交作业时间',
  `examCount` int(11) NOT NULL DEFAULT '0' COMMENT '试题数量',
  `answerCount` int(11) NOT NULL DEFAULT '0' COMMENT '答题人数',
  `studentCount` int(11) DEFAULT '0' COMMENT '本版总人数',
  `phaseId` bigint(20) DEFAULT NULL COMMENT '学段id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='作业表';

/*Table structure for table `WK_HOMEWORK_STATISTICS` */

DROP TABLE IF EXISTS `WK_HOMEWORK_STATISTICS`;

CREATE TABLE `WK_HOMEWORK_STATISTICS` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '本表主键',
  `hwId` bigint(20) NOT NULL DEFAULT '0' COMMENT '作业id',
  `examId` bigint(20) NOT NULL COMMENT '作业名称',
  `rightNum` int(11) DEFAULT NULL COMMENT '排序',
  `wrongNum` int(11) DEFAULT NULL,
  `rightRate` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='作业正确率';

/*Table structure for table `WK_MOOC` */

DROP TABLE IF EXISTS `WK_MOOC`;

CREATE TABLE `WK_MOOC` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '本表主键',
  `title` varchar(100) DEFAULT '' COMMENT '课程名称',
  `cover` varchar(100) DEFAULT '' COMMENT '课程封面(url)',
  `finishedNum` int(11) DEFAULT '0' COMMENT '已经更新的课程数量',
  `content` varchar(500) DEFAULT '0' COMMENT '课程介绍',
  `keyword` varchar(500) DEFAULT NULL COMMENT '关键词',
  `isOpen` bit(1) DEFAULT b'0' COMMENT '是否全区公开 0:否 | 1:是 ',
  `isTop` bit(1) DEFAULT NULL COMMENT '是否置顶',
  `schoolId` bigint(20) DEFAULT NULL COMMENT '学校ID',
  `teacherId` bigint(20) DEFAULT '0' COMMENT '关联用户表主键',
  `teacherName` varchar(100) DEFAULT '' COMMENT '老师名称',
  `isCommend` bit(1) DEFAULT NULL COMMENT '是否为区推荐课程：1为是，0为否',
  `status` int(11) DEFAULT '0' COMMENT '课程状态，1:,正常,2::删除',
  `crTime` datetime DEFAULT NULL COMMENT '创建时间',
  `schoolName` varchar(100) DEFAULT NULL,
  `checkNum` int(100) DEFAULT NULL,
  `commentNum` int(100) DEFAULT NULL,
  `score` int(10) DEFAULT '0',
  `isHide` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='老师微课程表';

/*Table structure for table `WK_MOOC_TAG` */

DROP TABLE IF EXISTS `WK_MOOC_TAG`;

CREATE TABLE `WK_MOOC_TAG` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '本表主键',
  `moocId` bigint(20) DEFAULT '0' COMMENT '关联慕课表主键',
  `objType` int(11) DEFAULT '0' COMMENT '存储类型，1：课程标签，2：知识点标签',
  `phaseId` bigint(20) DEFAULT '0' COMMENT '学段Id',
  `gradeId` bigint(20) DEFAULT '0' COMMENT '年级id',
  `subjectId` bigint(20) DEFAULT '0' COMMENT '学科Id',
  `versionId` bigint(20) DEFAULT '0' COMMENT '教材Id',
  `key1Id` bigint(20) DEFAULT '0' COMMENT '知识点1或者章',
  `key2Id` bigint(20) DEFAULT '0' COMMENT '知识点2或者节',
  `key1Name` varchar(100) DEFAULT NULL COMMENT '知识点1或者章名称',
  `key2Name` varchar(100) DEFAULT NULL COMMENT '知识点2或者节名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储微课程的课程、知识点标签的数据结构关系';

/*Table structure for table `WK_PAPER` */

DROP TABLE IF EXISTS `WK_PAPER`;

CREATE TABLE `WK_PAPER` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '本表主键',
  `moocId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课时id',
  `periodId` bigint(20) NOT NULL DEFAULT '0' COMMENT '关联微课程表主键',
  `examNum` int(11) NOT NULL DEFAULT '0' COMMENT '试题数量',
  `answerNum` int(11) NOT NULL DEFAULT '0' COMMENT '答题人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课时下的习题';

/*Table structure for table `WK_PAPER_EXAM` */

DROP TABLE IF EXISTS `WK_PAPER_EXAM`;

CREATE TABLE `WK_PAPER_EXAM` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '本表主键',
  `paperId` bigint(20) NOT NULL DEFAULT '0' COMMENT '作业id',
  `examId` bigint(20) NOT NULL DEFAULT '0' COMMENT '试题id',
  `type` bigint(20) NOT NULL COMMENT '题型',
  `sourceType` tinyint(1) DEFAULT NULL,
  `orderNum` int(3) DEFAULT '0' COMMENT '排序',
  `rightNum` int(11) NOT NULL DEFAULT '0',
  `wrongNum` int(11) NOT NULL DEFAULT '0',
  `rightRate` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='习题和试题关系';

/*Table structure for table `WK_SCORE` */

DROP TABLE IF EXISTS `WK_SCORE`;

CREATE TABLE `WK_SCORE` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `commentId` bigint(20) DEFAULT NULL COMMENT '评论Id',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `score` int(11) DEFAULT NULL COMMENT '分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程评分';

/*Table structure for table `WK_SOURCE` */

DROP TABLE IF EXISTS `WK_SOURCE`;

CREATE TABLE `WK_SOURCE` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `moocId` bigint(20) DEFAULT NULL COMMENT 'mooc_id',
  `name` varchar(100) DEFAULT NULL COMMENT '文档名称',
  `url` varchar(100) DEFAULT NULL COMMENT '文档地址',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `downloadNum` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学案及课件';

/*Table structure for table `WK_STUDENT_HOMEWORK_ANSWER` */

DROP TABLE IF EXISTS `WK_STUDENT_HOMEWORK_ANSWER`;

CREATE TABLE `WK_STUDENT_HOMEWORK_ANSWER` (
  `id` bigint(20) NOT NULL COMMENT '本表主键',
  `userId` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `userName` varchar(20) DEFAULT NULL,
  `hwId` bigint(20) NOT NULL DEFAULT '0' COMMENT '作业id',
  `examId` bigint(20) NOT NULL DEFAULT '0' COMMENT '试题id',
  `userAnswer` varchar(100) NOT NULL COMMENT '用户答案',
  `rightRate` decimal(6,2) NOT NULL DEFAULT '0.00' COMMENT '正确率(正确100%错误0%)',
  `checked` tinyint(1) DEFAULT NULL,
  `klassId` bigint(20) NOT NULL COMMENT '学生班级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生作业答题详细信息';

/*Table structure for table `WK_STUDENT_HOMEWORK_IMAGE` */

DROP TABLE IF EXISTS `WK_STUDENT_HOMEWORK_IMAGE`;

CREATE TABLE `WK_STUDENT_HOMEWORK_IMAGE` (
  `id` bigint(20) NOT NULL COMMENT '本表主键',
  `userId` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `hwId` bigint(20) NOT NULL DEFAULT '0' COMMENT '作业id',
  `examId` bigint(20) DEFAULT NULL,
  `path` varchar(100) NOT NULL COMMENT '路径',
  `cover` varchar(100) NOT NULL DEFAULT '0' COMMENT '缩略图',
  `crTime` datetime DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='作业图片';

/*Table structure for table `WK_STUDENT_HOMEWORK_RECORD` */

DROP TABLE IF EXISTS `WK_STUDENT_HOMEWORK_RECORD`;

CREATE TABLE `WK_STUDENT_HOMEWORK_RECORD` (
  `id` bigint(20) NOT NULL COMMENT '本表主键',
  `userId` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `hwId` bigint(20) NOT NULL DEFAULT '0' COMMENT '作业id',
  `startTime` datetime NOT NULL COMMENT '本视频是否听完',
  `endTime` datetime NOT NULL COMMENT '结束时间',
  `elapse` int(11) NOT NULL DEFAULT '0' COMMENT '耗时',
  `rightNum` int(11) NOT NULL DEFAULT '0' COMMENT '正确题数',
  `wrongNum` int(11) NOT NULL DEFAULT '0' COMMENT '错误题数',
  `notDoNum` int(11) DEFAULT NULL,
  `rightRate` decimal(6,2) NOT NULL DEFAULT '0.00' COMMENT '正确率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生作业答题记录';

/*Table structure for table `WK_STUDENT_PAPER_ANSWER` */

DROP TABLE IF EXISTS `WK_STUDENT_PAPER_ANSWER`;

CREATE TABLE `WK_STUDENT_PAPER_ANSWER` (
  `id` bigint(20) NOT NULL COMMENT '本表主键',
  `userId` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `userName` varchar(20) DEFAULT NULL,
  `paperId` bigint(20) NOT NULL DEFAULT '0' COMMENT '作业id',
  `examId` bigint(20) NOT NULL DEFAULT '0' COMMENT '试题id',
  `userAnswer` varchar(100) NOT NULL COMMENT '用户答案',
  `rightRate` decimal(6,2) NOT NULL DEFAULT '0.00' COMMENT '正确率(正确100%错误0%)',
  `checked` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生习题答题详细信息';

/*Table structure for table `WK_STUDENT_PAPER_IMAGE` */

DROP TABLE IF EXISTS `WK_STUDENT_PAPER_IMAGE`;

CREATE TABLE `WK_STUDENT_PAPER_IMAGE` (
  `id` bigint(20) NOT NULL COMMENT '本表主键',
  `userId` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `习题Id` bigint(20) NOT NULL DEFAULT '0' COMMENT '作业id',
  `examId` bigint(20) DEFAULT NULL,
  `path` varchar(100) NOT NULL COMMENT '路径',
  `cover` varchar(100) NOT NULL DEFAULT '0' COMMENT '缩略图',
  `crTime` datetime DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='习题图片';

/*Table structure for table `WK_STUDENT_PAPER_RECORD` */

DROP TABLE IF EXISTS `WK_STUDENT_PAPER_RECORD`;

CREATE TABLE `WK_STUDENT_PAPER_RECORD` (
  `id` bigint(20) NOT NULL COMMENT '本表主键',
  `userId` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `paperId` bigint(20) NOT NULL DEFAULT '0' COMMENT '微课程ID',
  `rightNum` int(11) NOT NULL DEFAULT '0' COMMENT '正确题数',
  `wrongNum` int(11) NOT NULL DEFAULT '0' COMMENT '错误题数',
  `notDoNum` int(11) DEFAULT NULL,
  `rightRate` decimal(6,2) NOT NULL DEFAULT '0.00' COMMENT '正确率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生习题答题记录';

/*Table structure for table `WK_VIDEO` */

DROP TABLE IF EXISTS `WK_VIDEO`;

CREATE TABLE `WK_VIDEO` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '本表主键',
  `title` varchar(100) DEFAULT '0' COMMENT '视频名称',
  `courseNo` varchar(100) DEFAULT NULL COMMENT '视频唯一编号',
  `sourceUrl` varchar(100) DEFAULT NULL COMMENT '视频源文件地址',
  `listenUrl` varchar(100) DEFAULT NULL COMMENT '视频播放地址',
  `cover` varchar(100) DEFAULT '' COMMENT '视频封面',
  `duration` int(8) DEFAULT NULL COMMENT '视频时长(s)',
  `format` int(11) DEFAULT NULL COMMENT '视频格式',
  `status` int(11) DEFAULT '0' COMMENT '1转码成功2转码中',
  `crTime` datetime DEFAULT NULL COMMENT '创建时间',
  `checkNum` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储微课程下上传的视频';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
