package cn.yu2.baomihua.core.rpc;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yu2.baomihua.core.exception.ServiceException;


/**
 * 这里是所有MODULE实现都需要继承的父类
 * <p>
 * 在这里你可以定义很多便于使用的抽象方法实现

 * @author   malei
 * @date	 2014-11-19 
 * @version  1.0.0	 
 */
public abstract class AbstractModule extends AbstractResponse {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());


	/**
	 * 合并字符串
	 * <p>
	 * @param list
	 * @return 
	*/
	protected String combineString( List<String> list ) {
		StringBuilder str = new StringBuilder();
		boolean dot = false;
		for ( String string : list ) {
			if ( dot ) {
				str.append(",");
			}
			if ( string != null ) {
				str.append(string);
				dot = true;
			}
		}
		return str.toString();
	}


	/**
	 * 创建应用异常
	 * @param desc
	 * 				异常说明
	 * @return ServiceException
	 */
	protected ServiceException errorServiceException( String desc ) {
		return new ServiceException(CodeConstants.PARAM_ERROR, desc);
	}
}
