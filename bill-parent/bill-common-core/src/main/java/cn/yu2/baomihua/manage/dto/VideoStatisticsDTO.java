package cn.yu2.baomihua.manage.dto;

import java.io.Serializable;

import cn.yu2.baomihua.manage.entity.TaskStatistics;

/** 视频点播上传评论统计 */
public class VideoStatisticsDTO extends TaskStatistics implements Serializable {

	private static final long serialVersionUID = 488617453932559009L;

	private Long videoId;

	private String videoName;

	/** 视频上传数量 */
	private int videoUploadQty;


	public String getVideoName() {
		return videoName;
	}


	public void setVideoName( String videoName ) {
		this.videoName = videoName;
	}


	public Long getVideoId() {
		return videoId;
	}


	public void setVideoId( Long videoId ) {
		this.videoId = videoId;
	}


	public int getVideoUploadQty() {
		return videoUploadQty;
	}


	public void setVideoUploadQty( int videoUploadQty ) {
		this.videoUploadQty = videoUploadQty;
	}

}
