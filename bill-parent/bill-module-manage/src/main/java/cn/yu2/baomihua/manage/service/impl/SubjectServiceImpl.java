package cn.yu2.baomihua.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.manage.entity.GradeSubject;
import cn.yu2.baomihua.manage.entity.Subject;
import cn.yu2.baomihua.manage.mapper.GradeSubjectMapper;
import cn.yu2.baomihua.manage.mapper.SubjectMapper;
import cn.yu2.baomihua.manage.service.ISubjectService;

/**
 *
 * Subject 表数据服务层接口实现类
 *
 */
@Service
public class SubjectServiceImpl extends BaseServiceImpl<SubjectMapper, Subject>implements ISubjectService {

	@Autowired
	private GradeSubjectMapper gradeSubjectMapper;

	@Autowired
	private SubjectMapper subjectMapper;

	@Override
	public List<Subject> listSubject(int status, Long phaseId, Long schoolId) {
		List<Subject> subList = baseMapper.getSubjectByPhase(status, phaseId, schoolId);
		return subList;
	}

	@Override
	public List<Subject> getSubjectByGradeId(Long phaseId, Long schoolId) {
		return baseMapper.getSubjectByGradeId(phaseId, schoolId);
	}

	@Override
	public int deleteSubject(Subject sub) {
		GradeSubject graSub = new GradeSubject();
		graSub.setSubjectId(sub.getId());
		gradeSubjectMapper.deleteSelective(graSub);
		return baseMapper.updateSelectiveById(sub);
	}

	@Override
	public List<Subject> getSubjectNoSel(Long gradeId, int status, Long schoolId) {
		return baseMapper.getSubjectNoSel(gradeId, status, schoolId);
	}

	@Override
	public Subject getBookToName(String name) {
		return this.baseMapper.getBookToName(name);
	}

	@Override
	public List<Subject> getSubjectList() {
		return baseMapper.selectList(null);
	}

	@Override
	public List<Subject> findSubjectByGradeId(Long gradeId) {
		return subjectMapper.findSubjectByGradeId(gradeId);
	}

	@Override
	public List<Subject> getSubjectBySchoolId(Long schoolId) {
		return subjectMapper.getSubjectBySchoolId(schoolId);
	}

	@Override
	public List<Subject> getAllSubject() {
		return baseMapper.selectList(null);
	}
}