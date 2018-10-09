package cn.yu2.baomihua.manage.service;

import java.util.List;

import com.baomidou.framework.service.ISuperService;

import cn.yu2.baomihua.manage.entity.Subject;

/**
 *
 * Subject 表数据服务层接口
 *
 */
public interface ISubjectService extends ISuperService<Subject> {

	public List<Subject> listSubject(int status, Long phaseId, Long schoolId);

	/** 根据年级获取年级学科 */
	public List<Subject> getSubjectByGradeId(Long phaseId, Long schoolId);

	public int deleteSubject(Subject sub);

	public List<Subject> getSubjectNoSel(Long gradeId, int status, Long schoolId);

	public Subject getBookToName(String name);

	public List<Subject> getSubjectList();

	public List<Subject> findSubjectByGradeId(Long gradeId);

	public List<Subject> getSubjectBySchoolId(Long schoolId);

	public List<Subject> getAllSubject();
}