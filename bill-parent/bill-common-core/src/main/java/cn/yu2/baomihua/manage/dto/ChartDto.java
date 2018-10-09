package cn.yu2.baomihua.manage.dto;

import java.io.Serializable;

/** 
 *  用户登录次数统计
 * 
 * @author lizhixiao
 * @version 1.0 
 * @date 2016-10-31
 */
@SuppressWarnings("serial")
public class ChartDto implements Serializable {

	/**图表标题*/
	private String title;

	/**图表图列*/
	private String name;

	/**日期*/
	private String[] categories;

	/**登录次数*/
	private int[] data;


	public String getTitle() {
		return title;
	}


	public void setTitle( String title ) {
		this.title = title;
	}


	public String getName() {
		return name;
	}


	public void setName( String name ) {
		this.name = name;
	}


	public String[] getCategories() {
		return categories;
	}


	public void setCategories( String[] categories ) {
		this.categories = categories;
	}


	public int[] getData() {
		return data;
	}


	public void setData( int[] data ) {
		this.data = data;
	}

}
