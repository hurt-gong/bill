package cn.yu2.baomihua.manage.dto;

import cn.yu2.baomihua.manage.entity.TaskStatistics;


public class TaskStatisticsDTO extends TaskStatistics {

	private static final long serialVersionUID = 4934851647689987212L;

	/**发布任务数量*/
	private int publishTaskQty;


	public int getPublishTaskQty() {
		return publishTaskQty;
	}


	public void setPublishTaskQty( int publishTaskQty ) {
		this.publishTaskQty = publishTaskQty;
	}
}
