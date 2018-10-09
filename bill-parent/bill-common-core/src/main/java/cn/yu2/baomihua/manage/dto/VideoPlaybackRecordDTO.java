package cn.yu2.baomihua.manage.dto;

import cn.yu2.baomihua.manage.entity.VideoPlaybackRecord;

public class VideoPlaybackRecordDTO extends VideoPlaybackRecord {

	private static final long serialVersionUID = -5860622817638012797L;

	private String date;


	public String getDate() {
		return date;
	}


	public void setDate( String date ) {
		this.date = date;
	}
}
