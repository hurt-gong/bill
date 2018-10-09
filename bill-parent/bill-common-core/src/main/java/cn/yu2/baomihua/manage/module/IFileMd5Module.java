package cn.yu2.baomihua.manage.module;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.FileMd5;

/**
 * 
 * 文件MD5值资源相关
 *
 * @author  hubin
 * 
 */
public interface IFileMd5Module {

	/**
	 * 添加文件资源
	 * 
	 * @param fileMd5
	 * 				文件MD5对象
	 * @return
	 */
	Response<Boolean> addFileMd5( FileMd5 fileMd5 );


	/**
	 * 根据 md5 值查看文件资源
	 * 
	 * @param md5
	 * 			文件md5值
	 * @return
	 */
	Response<FileMd5> getFileMd5ByMd5( String md5 );

}
