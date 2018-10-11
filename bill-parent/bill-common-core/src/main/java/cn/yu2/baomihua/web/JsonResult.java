package cn.yu2.baomihua.web;
 
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 页面返回数据JSON格式封装对象，封装了一些 状态信息（成功，失败的标志位或信息）及数据
 * @author Tony_tian
 * @Date 2015年11月12日10:57:54
 */
public class JsonResult {
	
	/** 请求处理成功 */
	public static final int CODE_SUCCESS = 200;
	/** 服务器代码出错*/
	public static final int CODE_ERROR = 500;
	/** 用户没有登录，请求不合法*/
	public static final int CODE_UNAUTHORIZED = 401;
	/** 请求参数为空或不正确*/
	public static final int CODE_PARAMETER_ERROR=402;


	/**请求返回状态码*/
	private int code;
	/**返回数据提示信息*/
	private String msg;
	/**封装返回数据,注解：data为空的话,序列化不会在json中体现*/
	@JsonInclude(Include.NON_NULL)
	private Object data;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public JsonResult(int code) {
		this.code = code;
	}

	public JsonResult() {
		super();
	}
}