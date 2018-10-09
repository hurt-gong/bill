/**
 * ISMSService.java cn.bjjh.comment.service Copyright (c) 2015,
 * 北京微课创景教育科技有限公司版权所有.
 */

package cn.yu2.baomihua.manage.service;

import cn.yu2.baomihua.manage.entity.FileMd5;


/**
 * <p>
 * 文件md5值资源对应关系
 * </p>
 * 
 * @author   ShiBin
 */
public interface IFileMd5Service {

	/**
	 * 添加文件资源
	 * 
	 * @param fileMd5
	 * 				文件MD5对象
	 * @return
	 */
	int addFileMd5( FileMd5 fileMd5 );


	/**
	 * 根据 md5 值查看文件资源
	 * 
	 * @param md5
	 * 			文件md5值
	 * @return
	 */
	FileMd5 selectByMd5( String md5 );

}
