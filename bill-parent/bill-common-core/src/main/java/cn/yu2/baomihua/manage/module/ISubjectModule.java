package cn.yu2.baomihua.manage.module;

import java.util.List;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.Subject;

/**
 * @Description:学科module
 * 
 * @author wanglulu
 * @version 1.0
 * @date 2016年4月27日 下午3:05:16
 */
public interface ISubjectModule {

	public Response<List<Subject>> listSubject(int status, Long phaseId, Long schoolId);

	public Subject getBookToName(String name);

	public List<Subject> getSubjectList();

	public List<Subject> findSubjectByGradeId(Long gradeId);

	public Subject getSubjectById(Long Id);

	public List<Subject> getSubjectBySchoolId(Long schoolId);

	public List<Subject> getAllSubject();

}
