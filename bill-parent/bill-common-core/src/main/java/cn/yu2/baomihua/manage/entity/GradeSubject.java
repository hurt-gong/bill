package cn.yu2.baomihua.manage.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 
 *
 */
@TableName(value = "GL_GRADE_SUBJECT")
public class GradeSubject implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** ID */
	@TableId
	private Long ID;

	/**  */
	private Long gradeId;

	/** ѧ */
	private Long subjectId;

	/** ѧУID */
	private Long schoolId;

	public Long getID() {
		return this.ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public Long getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Long getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

}
