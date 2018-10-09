package cn.yu2.baomihua.manage.module;

import org.springframework.beans.factory.annotation.Autowired;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.manage.entity.StudentParent;
import cn.yu2.baomihua.manage.service.IStudentParentService;

/**
 * 
 * @author jiabing
 *
 */
public class StudentParentModuleImpl extends AbstractModule implements IStudentParentModule {

	@Autowired
	private IStudentParentService studentParentService;
	
	/** 通过parentId获取studentId*/
	@Override
	public Long getStudentIdByparentId(Long id) {
		StudentParent studentParent = new StudentParent();
		studentParent.setParentId(id);
		return studentParentService.selectOne(studentParent).getStudentId();
	}
	
	@Override
	public StudentParent getStudentParentByParentId(Long parentId){
		
		StudentParent studentParent = new StudentParent();
		studentParent.setParentId(parentId);
		return studentParentService.selectOne(studentParent);
		
	}
}
