package cn.yu2.baomihua.manage.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 文件MD5值资源表
 * 
 * @author ShiBin
 *
 */
@TableName(value = "GL_FILE_MD5")
public class FileMd5 implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 文件MD5 */
	@TableId
	private String md5;

	/** 文件类型 0、图片 1、视频 2、PDF 3、未知文件 */
	private Integer type = 3;

	/** 文件地址 */
	private String sourceUrl;

	/** 截图地址*/
	private String convertUrl;

	/** 视频时长 */
	private Long duration;

	/** 创建时间 */
	private Date crTime;


	public String getMd5() {
		return md5;
	}


	public void setMd5( String md5 ) {
		this.md5 = md5;
	}


	public Integer getType() {
		return type;
	}


	public void setType( Integer type ) {
		this.type = type;
	}


	public Long getDuration() {
		return duration;
	}


	public void setDuration( Long duration ) {
		this.duration = duration;
	}


	public Date getCrTime() {
		return crTime;
	}


	public void setCrTime( Date crTime ) {
		this.crTime = crTime;
	}


	public String getSourceUrl() {
		return sourceUrl;
	}


	public void setSourceUrl( String sourceUrl ) {
		this.sourceUrl = sourceUrl;
	}


	public String getConvertUrl() {
		return convertUrl;
	}


	public void setConvertUrl( String convertUrl ) {
		this.convertUrl = convertUrl;
	}
}
