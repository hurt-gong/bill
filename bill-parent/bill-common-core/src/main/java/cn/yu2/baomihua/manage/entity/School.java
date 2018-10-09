package cn.yu2.baomihua.manage.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * ѧУ
 *
 */
@TableName(value = "GL_SCHOOL")
public class School implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** ID */
	@TableId
	private Long ID;

	/**  */
	private String name;

	/**  */
	private String address;

	/** logo */
	private String logo;

	/** ״̬ */
	private Integer status;

	/**  */
	private Date crTime;

	/**  */
	private Long crUserId;

	public Long getID() {
		return this.ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
