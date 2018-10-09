package cn.yu2.baomihua.core.common.upload;

import javax.servlet.http.HttpServletResponse;

public class AjaxUtil {

	/**
	 * 允许跨域 js 请求
	 */
	public static void allowJsCrossdoamin( HttpServletResponse response ) {
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, POST, PUT, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setHeader("Access-Control-Max-Age", "3600");
	}
}
