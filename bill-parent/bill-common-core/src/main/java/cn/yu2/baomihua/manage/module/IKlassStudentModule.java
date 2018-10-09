/**
 * IKlassStudentModule.java
 * cn.yu2.baomihua.manage.module
 * Copyright (c) 2016, 北京微课创景教育科技有限公司版权所有.
*/

package cn.yu2.baomihua.manage.module;

import java.util.List;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.KlassStudent;

/**
 * 
 * <p>
 *
 * @author   李新迎
 * @date	 2016年6月28日
 * @version  1.0.0
 */

public interface IKlassStudentModule {

	
	public Response<Integer> getStudentSumById(Long id);
	
	/** 通过kalssId 查询学生 */
	public Response<List<Long>> getStudentByKlassId(Long klassId);
	
	/** 通过studentId查询学生的klassIdList  */
	public Response<List<Long>> getklassIdListByUserId(Long userId);
	
	/** 通过studentId查询学生姓名 */
	public Long getKlassIdByStudnetId(Long id);
	
	public KlassStudent getKlassStudentByStudentId(Long id);
}
