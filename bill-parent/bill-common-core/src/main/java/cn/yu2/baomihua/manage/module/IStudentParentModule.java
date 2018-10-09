package cn.yu2.baomihua.manage.module;

import cn.yu2.baomihua.manage.entity.StudentParent;

/**
 * 
 * @author jiabing
 *
 */
public interface IStudentParentModule {
	
	
	/** 通过parentId获取studentId*/
	public Long getStudentIdByparentId(Long id);
	
	/** 通过parentId查询studentParent实体 */
	public StudentParent getStudentParentByParentId(Long parentId);
}
