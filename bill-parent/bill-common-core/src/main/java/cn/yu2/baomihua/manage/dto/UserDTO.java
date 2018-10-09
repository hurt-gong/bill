package cn.yu2.baomihua.manage.dto;

import java.io.Serializable;

import cn.yu2.baomihua.manage.entity.User;

/**
 * @Description:班级学科
 * 
 */
/**
 * @author Administrator
 *
 */
public class UserDTO extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String gradeName;

	//职务
	private String postionName;

	//班级
	private String KlassName;

	//父母
	private User parent;

	//政治面貌
	private String political;


	public String getGradeName() {
		return gradeName;
	}


	public void setGradeName( String gradeName ) {
		this.gradeName = gradeName;
	}


	public String getKlassName() {
		return KlassName;
	}


	public void setKlassName( String klassName ) {
		KlassName = klassName;
	}


	public User getParent() {
		return parent;
	}


	public void setParent( User parent ) {
		this.parent = parent;
	}


	public String getPolitical() {
		return political;
	}


	public void setPolitical( String political ) {
		this.political = political;
	}


	public String getPostionName() {
		return postionName;
	}


	public void setPostionName( String postionName ) {
		this.postionName = postionName;
	}

}
