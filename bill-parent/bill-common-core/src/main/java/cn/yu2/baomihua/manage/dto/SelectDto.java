package cn.yu2.baomihua.manage.dto;

/** 
 *  下拉选项
 * 
 * @author lizhixiao
 * @version 1.0 
 * @date 2016-11-01
 */
public class SelectDto {

	private long id;

	private String name;


	public long getId() {
		return id;
	}


	public void setId( long id ) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName( String name ) {
		this.name = name;
	}


	public SelectDto() {
		super();
	}


	public SelectDto( long id, String name ) {
		super();
		this.id = id;
		this.name = name;
	}

}
