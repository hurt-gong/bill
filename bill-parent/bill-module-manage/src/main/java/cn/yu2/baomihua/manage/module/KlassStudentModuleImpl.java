/**
 * KlassStudentModuleImpl.java
 * cn.yu2.baomihua.manage.module
 * Copyright (c) 2016, 北京微课创景教育科技有限公司版权所有.
*/

package cn.yu2.baomihua.manage.module;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;



import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.KlassStudent;
import cn.yu2.baomihua.manage.service.IKlassStudentService;

/**
 * 
 * <p>
 *
 * @author   李新迎
 * @date	 2016年6月28日
 * @version  1.0.0
 */

public class KlassStudentModuleImpl extends AbstractModule implements IKlassStudentModule{
	
	@Autowired
	private IKlassStudentService klassStudentService;
	
	
	public Response<Integer> getStudentSumById(Long id){
		
		
		return success(klassStudentService.getStudentSumById(id));
	}

	/** 通过klassId查询学生id */
	@Override
	public Response<List<Long>> getStudentByKlassId(Long klassId) {

		List<Long> ids = klassStudentService.getStudentByKlassId(klassId);
		
		return success(ids);
	}
	
	/** 通过studentId查询学生的klassIdList  */
	@Override
	public Response<List<Long>> getklassIdListByUserId(Long userId) {
		
		KlassStudent klassStudent = new KlassStudent();
		klassStudent.setStudentId(userId);
		List<KlassStudent> klassStudents = klassStudentService.selectList(klassStudent);
		List<Long> ids = new ArrayList<>(); 
		for (KlassStudent student : klassStudents) {
			ids.add(student.getKlassId());
		}
		return success(ids);
	}

	/** 通过studentId查询学生姓名 */
	@Override
	public Long getKlassIdByStudnetId(Long id) {
		KlassStudent klassStudent = new KlassStudent();
		klassStudent.setStudentId(id);
		klassStudent = klassStudentService.selectOne(klassStudent);
		if(klassStudent!=null){
			return klassStudent.getKlassId();
		}else{
			return null;
		}
		
	}
	
	
	public KlassStudent getKlassStudentByStudentId(Long id){
		KlassStudent klassStudent = new KlassStudent();
		klassStudent.setStudentId(id);
		return klassStudentService.selectOne(klassStudent);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
