package cn.yu2.baomihua.manage.dto;

import java.io.Serializable;
import java.util.List;

/** 
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月26日 下午4:58:41 
 */
public class SchoolDTO implements Serializable{

	private static final long serialVersionUID = 7237889219309382771L;

	private Long id;

	private String name;

	private String address;
	
	private List<SchoolAdminDTO> admins; //管理员列表
	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<SchoolAdminDTO> getAdmins() {
		return admins;
	}

	public void setAdmins(List<SchoolAdminDTO> admins) {
		this.admins = admins;
	}
	
	
}
