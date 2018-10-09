package cn.yu2.baomihua.manage.dto;

import java.io.Serializable;
import java.util.List;

import cn.yu2.baomihua.manage.entity.Grade;
import cn.yu2.baomihua.manage.entity.Klass;
import cn.yu2.baomihua.manage.entity.Phase;
import cn.yu2.baomihua.manage.entity.Subject;

/** 
 * @Description:基本配置
 * @author lizhixiao
 */
public class BaseCodeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	//学段List
	private List<Phase> phaseList;

	//年级List
	private List<Grade> gradeList;

	//班级List
	private List<Klass> klassList;

	//学科
	private List<Subject> subjectList;


	public List<Phase> getPhaseList() {
		return phaseList;
	}


	public void setPhaseList( List<Phase> phaseList ) {
		this.phaseList = phaseList;
	}


	public List<Grade> getGradeList() {
		return gradeList;
	}


	public void setGradeList( List<Grade> gradeList ) {
		this.gradeList = gradeList;
	}


	public List<Klass> getKlassList() {
		return klassList;
	}


	public void setKlassList( List<Klass> klassList ) {
		this.klassList = klassList;
	}


	public List<Subject> getSubjectList() {
		return subjectList;
	}


	public void setSubjectList( List<Subject> subjectList ) {
		this.subjectList = subjectList;
	}


}
