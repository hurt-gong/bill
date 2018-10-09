package cn.yu2.baomihua.manage.dto;

import java.io.Serializable;

/** 
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年6月7日 下午6:13:05 
 */
public class RoleTeacherDTO implements Serializable {

	private static final long serialVersionUID = 4124859943086999964L;

	private Long id;

	private Long roleId;

	private Long teacherId;

	private Integer type;
	
	private String teacherName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	
}
