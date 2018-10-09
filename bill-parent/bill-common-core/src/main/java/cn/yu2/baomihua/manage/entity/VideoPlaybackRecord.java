package cn.yu2.baomihua.manage.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 视频播放记录
 *
 */
@TableName(value = "TJ_VIDEO_PLAYBACK_RECORD")
public class VideoPlaybackRecord implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId
	private Long id;

	/** moocid */
	private Long moocId;

	/** 视频id */
	private Long videoId;

	/** 视频名 */
	private String videoName;

	/** 视频封面 */
	private String cover;

	/** 视频播放地址 */
	private String listenUrl;

	/** 主讲人，视频上传人 */
	private String speaker;

	/** 观看视频用户Id */
	private Long userId;

	/** 当前日期 */
	private Date crDate;


	public Long getId() {
		return this.id;
	}


	public void setId( Long id ) {
		this.id = id;
	}


	public Long getMoocId() {
		return this.moocId;
	}


	public void setMoocId( Long moocId ) {
		this.moocId = moocId;
	}


	public Long getVideoId() {
		return this.videoId;
	}


	public void setVideoId( Long videoId ) {
		this.videoId = videoId;
	}


	public String getVideoName() {
		return this.videoName;
	}


	public void setVideoName( String videoName ) {
		this.videoName = videoName;
	}


	public String getCover() {
		return this.cover;
	}


	public void setCover( String cover ) {
		this.cover = cover;
	}


	public String getListenUrl() {
		return this.listenUrl;
	}


	public void setListenUrl( String listenUrl ) {
		this.listenUrl = listenUrl;
	}


	public String getSpeaker() {
		return speaker;
	}


	public void setSpeaker( String speaker ) {
		this.speaker = speaker;
	}


	public Long getUserId() {
		return this.userId;
	}


	public void setUserId( Long userId ) {
		this.userId = userId;
	}


	public Date getCrDate() {
		return this.crDate;
	}


	public void setCrDate( Date crDate ) {
		this.crDate = crDate;
	}

}
