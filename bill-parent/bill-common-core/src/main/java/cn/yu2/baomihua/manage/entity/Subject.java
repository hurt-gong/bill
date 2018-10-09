package cn.yu2.baomihua.manage.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 学科表
 *
 */
@TableName(value = "GL_SUBJECT")
public class Subject implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId
	private Long id;

	/** 名称 */
	private String name;

	/** 类型,1:基础,2:自定义 */
	private Integer type;

	/** 状态,1:启用,2:删除 */
	private Integer status;

	/** 所属学校 */
	private Long schoolId;

	/** 创建时间 */
	private Date crTime;

	/** 创建人 */
	private Long crUserId;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Date getCrTime() {
		return this.crTime;
	}

	public void setCrTime(Date crTime) {
		this.crTime = crTime;
	}

	public Long getCrUserId() {
		return this.crUserId;
	}

	public void setCrUserId(Long crUserId) {
		this.crUserId = crUserId;
	}

}
