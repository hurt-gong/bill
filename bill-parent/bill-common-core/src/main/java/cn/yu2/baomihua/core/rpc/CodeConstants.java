package cn.yu2.baomihua.core.rpc;

/**
 * <p>
 * 这里定义所有的返回异常的代码
 * </p>
 * 
 * @author hubin
 * @Date 2016-04-18
 */
public class CodeConstants {

	/* 请求成功 */
	public static String SUCCESS = "000000";

	/* 执行失败 */
	public static String FAIL = "000001";

	/* 参数校验未通过 */
	public static String PARAM_ERROR = "000002";

	/* 系统异常(包括执行SQL所产生的一切异常信息) */
	public static String SYSTEM_ERROR = "000003";


}
