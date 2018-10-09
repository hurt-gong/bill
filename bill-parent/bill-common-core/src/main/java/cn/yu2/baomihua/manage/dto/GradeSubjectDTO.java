package cn.yu2.baomihua.manage.dto;

import java.io.Serializable;
import java.util.List;

import cn.yu2.baomihua.manage.entity.Grade;
import cn.yu2.baomihua.manage.entity.Subject;

/** 
 * @Description:班级学科
 * 
 */
public class GradeSubjectDTO implements Serializable {

	private static final long serialVersionUID = 7237889219309382772L;

	private Grade grade;

	private List<Grade> gradeList;

	//基础学科
	private List<Subject> baseSubjectList;

	//自定义学科
	private List<Subject> subjectList;


	public Grade getGrade() {
		return grade;
	}


	public void setGrade( Grade grade ) {
		this.grade = grade;
	}


	public List<Subject> getSubjectList() {
		return subjectList;
	}


	public void setSubjectList( List<Subject> subjectList ) {
		this.subjectList = subjectList;
	}


	public List<Subject> getBaseSubjectList() {
		return baseSubjectList;
	}


	public void setBaseSubjectList( List<Subject> baseSubjectList ) {
		this.baseSubjectList = baseSubjectList;
	}


	public List<Grade> getGradeList() {
		return gradeList;
	}


	public void setGradeList( List<Grade> gradeList ) {
		this.gradeList = gradeList;
	}


}
