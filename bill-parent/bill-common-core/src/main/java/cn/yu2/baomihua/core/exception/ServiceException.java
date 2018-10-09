package cn.yu2.baomihua.core.exception;


/**
 * 应用层只要是异常,切记统一抛此异常
 * <p>
 *
 * @author   malei
 * @date	 2014-11-19 
 * @version  1.0.0	 
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 2585591242322597769L;

	// 异常代码
	private String code;

	// 异常说明
	private String desc;


	public ServiceException() {
		super();
	}


	public ServiceException( String message ) {
		super(message);
		this.desc = message;
	}


	public ServiceException( Throwable cause ) {
		super(cause);
	}


	public ServiceException( String code, String desc ) {
		this.code = code;
		this.desc = desc;
	}


	public ServiceException( String code, String desc, Throwable cause ) {
		super(cause);
		this.code = code;
		this.desc = desc;
	}


	public ServiceException( String code, String desc, String message ) {
		super(message);
		this.code = code;
		this.desc = desc;
	}


	@Override
	public synchronized Throwable fillInStackTrace() {
		return null;
	}


	public String getCode() {
		return code;
	}


	public String getDesc() {
		return desc;
	}


	@Override
	public String getMessage() {
		if ( super.getMessage() == null ) {
			return desc;
		}
		return super.getMessage();

	}

}
