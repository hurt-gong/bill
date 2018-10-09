package cn.yu2.baomihua.manage.dto;

import java.io.Serializable;

/** 
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年6月7日 下午1:48:20 
 */
public class SchoolAdminDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long schoolId;

	private Long userId;
	
	private String name;
	
	private String loginName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	
}
