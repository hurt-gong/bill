package cn.yu2.baomihua.manage.dto;

import java.io.Serializable;

import cn.yu2.baomihua.manage.entity.StudentTaskRecord;

public class StudentTaskRecordDTO extends StudentTaskRecord implements Serializable {

	private static final long serialVersionUID = -5190880276309586394L;


	/** 任务id */
	private Long taskId;

	/** 任务名称 */
	private String TaskName;

	/** 任务类型(1=视频，2=作业，3=学案) */
	private Integer taskType;

	/**预览地址*/
	private String preUrl;

	/**群组Id*/
	private Long groupId;


	public String getTaskName() {
		return TaskName;
	}


	public void setTaskName( String taskName ) {
		TaskName = taskName;
	}


	public Long getTaskId() {
		return taskId;
	}


	public void setTaskId( Long taskId ) {
		this.taskId = taskId;
	}


	public Integer getTaskType() {
		return taskType;
	}


	public void setTaskType( Integer taskType ) {
		this.taskType = taskType;
	}


	public String getPreUrl() {
		return preUrl;
	}


	public void setPreUrl( String preUrl ) {
		this.preUrl = preUrl;
	}


	public Long getGroupId() {
		return groupId;
	}


	public void setGroupId( Long groupId ) {
		this.groupId = groupId;
	}


}
