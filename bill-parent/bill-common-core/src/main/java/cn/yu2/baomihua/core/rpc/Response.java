package cn.yu2.baomihua.core.rpc;

import java.io.Serializable;

import cn.yu2.baomihua.core.exception.WebException;

/**
 * <p>
 * 所有返回数据的超级父类
 * </p>
 * 
 * @author hubin
 * @Date 2016-04-18
 */
public class Response<T> implements Serializable {

	private static final long serialVersionUID = -6906299699467228288L;

	/* 头信息 */
	private ResponseHead head;

	/* 数据区 */
	private T body;


	/*
	 * 用于构造包含head和body的返回数据
	 */
	public Response( ResponseHead head, T body ) {
		this.head = head;
		this.body = body;
	}


	/*
	 * 用于构造返回数据
	 */
	public Response( String statusCode, String msg, T body ) {
		head = new ResponseHead(statusCode, msg);
		this.body = body;
	}


	public ResponseHead getHead() {
		return head;
	}


	public String getStatusCode() {
		return head.getStatusCode();
	}


	public boolean isSuccess() {
		return CodeConstants.SUCCESS.equals(getStatusCode());
	}


	public void setHead( ResponseHead head ) {
		this.head = head;
	}


	public T getBody() {
		if ( !this.isSuccess() ) {
			throw new WebException(this.getStatusCode(), this.getHead().getDesc());
		}
		return body;
	}


	public void setBody( T body ) {
		this.body = body;
	}


	public T getNotSuccessBody() {

		return body;
	}

}
