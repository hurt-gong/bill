package cn.yu2.baomihua.manage.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 学习任务统计
 *
 */
@TableName(value = "TJ_TASK_STATISTICS")
public class TaskStatistics implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 本表id */
	@TableId
	private Long id;

	/** 任务Id(视频，作业，学案) */
	private Long taskId;

	/** 任务名称 */
	private String taskName;

	/** 任务类型(1=视频，2=作业，3=学案) */
	private Integer taskType;

	/** 学校id */
	private Long schoolId;

	/** 学校名 */
	private String schoolName;

	/** 班级Id */
	private Long gradeId;

	/** 学科Id */
	private Long subjectId;

	/** 教师id */
	private Long teacherId;

	/** j教师名 */
	private String teacherName;

	/** 任务学生数量 */
	private Integer taskStudentQty;

	/** 完成学生数量 */
	private Integer finishStudentQty;

	/** 完成率 */
	private Double finishRate;

	/** 首次完成学生id */
	private Long firstStudentId;

	/** 首次完成学生姓名 */
	private String firstStudentName;

	/** 首次完成学生班级 */
	private String firstStudentKlass;

	/** 视频点播数 */
	private Integer videoDibbleQty;

	/** 视频评论数 */
	private Integer videoCommentQty;

	/** 预览地址 */
	private String preUrl;

	/** 当前日期 */
	private Date crDate;

	/** 班群id(帖子字段) */
	private Long groupId;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getTaskType() {
		return this.taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public Long getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return this.schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Long getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Long getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return this.teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getTaskStudentQty() {
		return this.taskStudentQty;
	}

	public void setTaskStudentQty(Integer taskStudentQty) {
		this.taskStudentQty = taskStudentQty;
	}

	public Integer getFinishStudentQty() {
		return this.finishStudentQty;
	}

	public void setFinishStudentQty(Integer finishStudentQty) {
		this.finishStudentQty = finishStudentQty;
	}

	public Double getFinishRate() {
		return this.finishRate;
	}

	public void setFinishRate(Double finishRate) {
		this.finishRate = finishRate;
	}

	public Long getFirstStudentId() {
		return this.firstStudentId;
	}

	public void setFirstStudentId(Long firstStudentId) {
		this.firstStudentId = firstStudentId;
	}

	public String getFirstStudentName() {
		return this.firstStudentName;
	}

	public void setFirstStudentName(String firstStudentName) {
		this.firstStudentName = firstStudentName;
	}

	public String getFirstStudentKlass() {
		return this.firstStudentKlass;
	}

	public void setFirstStudentKlass(String firstStudentKlass) {
		this.firstStudentKlass = firstStudentKlass;
	}

	public Integer getVideoDibbleQty() {
		return this.videoDibbleQty;
	}

	public void setVideoDibbleQty(Integer videoDibbleQty) {
		this.videoDibbleQty = videoDibbleQty;
	}

	public Integer getVideoCommentQty() {
		return this.videoCommentQty;
	}

	public void setVideoCommentQty(Integer videoCommentQty) {
		this.videoCommentQty = videoCommentQty;
	}

	public String getPreUrl() {
		return this.preUrl;
	}

	public void setPreUrl(String preUrl) {
		this.preUrl = preUrl;
	}

	public Date getCrDate() {
		return this.crDate;
	}

	public void setCrDate(Date crDate) {
		this.crDate = crDate;
	}

	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

}
