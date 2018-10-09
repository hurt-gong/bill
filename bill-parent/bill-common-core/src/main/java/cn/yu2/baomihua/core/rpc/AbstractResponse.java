package cn.yu2.baomihua.core.rpc;

/**
 * 所有Module和Service层的父类需要继承的类
 * <p>
 * 封装了Module层和Service需要公用到的方法等
 * 
 * @author   malei
 * @date	 2014-11-20 
 * @version  1.0.0	 
 */
public abstract class AbstractResponse {


	/**
	 * 正常返回情况下的头部封装
	 */
	public ResponseHead successHead() {
		return new ResponseHead(CodeConstants.SUCCESS, "正常返回");
	}


	/**
	 * 正常情况下的一站式返回结果
	 * 
	 * @param 	body 应答体
	 * @return	返回数据结构
	 */
	public <T> Response<T> success( T body ) {
		return new Response<T>(successHead(), body);
	}


	/**
	 * 程序发生异常时可调用此方法返回数据体
	 * @param <T>
	 * 
	 * @param code 错误代码
	 * @param desc 错误描述
	 * @return 返回错误时数据体
	 */
	public <T> Response<T> fail( final String code, final String desc ) {
		return new Response<T>(new ResponseHead(code, desc), null);
	}


	/**
	 * 针对返回boolean类型时true的情况
	 * 
	 * @return 返回true类型的数据对象
	 */
	public Response<Boolean> successOfBoolean() {
		return new Response<Boolean>(successHead(), true);
	}


	/**
	 * 针对返回boolean类型时false的情况
	 * 
	 * @return 返回false类型的数据对象
	 */
	public Response<Boolean> failOfBoolean() {
		return new Response<Boolean>(successHead(), false);
	}


	/**
	 * 针对修改或删除时需要返回执行成功结果时
	 * 
	 * @param  col 执行操作影响的行数
	 * @return 根据col判断执行操作结果
	 */
	public Response<Boolean> result( final int col ) {
		return (col > 0) ? successOfBoolean() : failOfBoolean();
	}

}
