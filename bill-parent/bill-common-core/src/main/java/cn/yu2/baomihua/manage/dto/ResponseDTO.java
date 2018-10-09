package cn.yu2.baomihua.manage.dto;

import java.io.Serializable;

/**
 * response返回信息
 */
public class ResponseDTO implements Serializable {

	private static final long serialVersionUID = -3229621908095826462L;

	//返回代码
	private int code;

	//返回详情
	private String massage;


	public int getCode() {
		return code;
	}


	public void setCode( int code ) {
		this.code = code;
	}


	public String getMassage() {
		return massage;
	}


	public void setMassage( String massage ) {
		this.massage = massage;
	}

}
