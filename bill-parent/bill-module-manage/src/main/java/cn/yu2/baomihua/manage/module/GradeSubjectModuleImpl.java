package cn.yu2.baomihua.manage.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.manage.entity.GradeSubject;
import cn.yu2.baomihua.manage.service.IGradeSubjectService;

/** 
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月27日 下午3:19:40 
 */
public class GradeSubjectModuleImpl extends AbstractModule implements IGradeSubjectModule {
	
	@Autowired
	private IGradeSubjectService GradeSubjectModuleImpl;
	
	@Override
	public List<GradeSubject> getGradeSubjectListByEntiey(GradeSubject gradeSubject) {
		
		return GradeSubjectModuleImpl.selectList(gradeSubject);
	}

}
