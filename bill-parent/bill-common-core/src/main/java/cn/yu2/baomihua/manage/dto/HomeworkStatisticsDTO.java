package cn.yu2.baomihua.manage.dto;

import cn.yu2.baomihua.manage.entity.TaskStatistics;


public class HomeworkStatisticsDTO extends TaskStatistics {

	private static final long serialVersionUID = 1564337632838723942L;

	/** 作业上传数量 */
	private Integer homwkUploadQty;

	/** 作业完成数量 */
	private Integer homwkFinishQty;


	public Integer getHomwkUploadQty() {
		return homwkUploadQty;
	}


	public void setHomwkUploadQty( Integer homwkUploadQty ) {
		this.homwkUploadQty = homwkUploadQty;
	}


	public Integer getHomwkFinishQty() {
		return homwkFinishQty;
	}


	public void setHomwkFinishQty( Integer homwkFinishQty ) {
		this.homwkFinishQty = homwkFinishQty;
	}

}
