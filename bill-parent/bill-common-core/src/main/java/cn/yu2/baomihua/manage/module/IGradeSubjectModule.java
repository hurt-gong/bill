package cn.yu2.baomihua.manage.module;

import java.util.List;

import cn.yu2.baomihua.manage.entity.GradeSubject;

/** 
 * @Description:年级配课module
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月27日 下午3:06:41 
 */
public interface IGradeSubjectModule {
	
	/**查*/
	public List<GradeSubject> getGradeSubjectListByEntiey(GradeSubject gradeSubject);
}
