package cn.yu2.baomihua.manage.module;

import java.util.List;

import javax.annotation.Resource;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.Subject;
import cn.yu2.baomihua.manage.service.ISubjectService;

/**
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0
 * @date 2016年4月27日 下午3:22:28
 */
public class SubjectModuleImpl extends AbstractModule implements ISubjectModule {
	@Resource(name = "subjectServiceImpl")
	private ISubjectService subjectServiceImpl;

	@Override
	public Response<List<Subject>> listSubject(int status, Long phaseId, Long schoolId) {
		Subject sub = new Subject();
		sub.setStatus(status);
		return success(subjectServiceImpl.listSubject(status, phaseId, schoolId));
	}

	@Override
	public Subject getBookToName(String name) {
		return this.subjectServiceImpl.getBookToName(name);
	}

	@Override
	public List<Subject> getSubjectList() {
		return subjectServiceImpl.getSubjectList();
	}

	@Override
	public List<Subject> findSubjectByGradeId(Long gradeId) {
		return subjectServiceImpl.findSubjectByGradeId(gradeId);
	}

	@Override
	public Subject getSubjectById(Long Id) {
		return subjectServiceImpl.selectById(Id);
	}

	@Override
	public List<Subject> getSubjectBySchoolId(Long schoolId) {
		return subjectServiceImpl.getSubjectBySchoolId(schoolId);
	}

	@Override
	public List<Subject> getAllSubject() {
		return subjectServiceImpl.getAllSubject();
	}

}
