package cn.yu2.baomihua.manage.dto;

import java.io.Serializable;
import java.util.List;

import cn.yu2.baomihua.manage.entity.Role;

/** 
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年6月12日 下午5:54:12 
 */
public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private List<Role> childrens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Role> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Role> childrens) {
		this.childrens = childrens;
	}
	
	
}
