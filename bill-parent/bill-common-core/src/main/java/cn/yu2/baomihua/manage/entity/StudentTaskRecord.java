package cn.yu2.baomihua.manage.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 学生任务完成记录
 *
 */
@TableName(value = "TJ_STUDENT_TASK_RECORD")
public class StudentTaskRecord implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId
	private Long id;

	/** 任务统计id */
	private Long taskStatisticsId;

	/** 是否完成(0=否,1=是) */
	private Integer isFinished;

	/** 学生id */
	private Long studentId;

	/** 当前日期 */
	private Date crDate;


	public Long getId() {
		return this.id;
	}


	public void setId( Long id ) {
		this.id = id;
	}


	public Long getTaskStatisticsId() {
		return this.taskStatisticsId;
	}


	public void setTaskStatisticsId( Long taskStatisticsId ) {
		this.taskStatisticsId = taskStatisticsId;
	}


	public Integer getIsFinished() {
		return this.isFinished;
	}


	public void setIsFinished( Integer isFinished ) {
		this.isFinished = isFinished;
	}


	public Long getStudentId() {
		return this.studentId;
	}


	public void setStudentId( Long studentId ) {
		this.studentId = studentId;
	}


	public Date getCrDate() {
		return this.crDate;
	}


	public void setCrDate( Date crDate ) {
		this.crDate = crDate;
	}

}
