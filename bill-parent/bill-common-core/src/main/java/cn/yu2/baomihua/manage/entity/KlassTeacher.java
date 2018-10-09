package cn.yu2.baomihua.manage.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 班级教师
 *
 */
@TableName(value = "GL_KLASS_TEACHER")
public class KlassTeacher implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** ID */
	@TableId
	private Long id;

	/** 班级ID */
	private Long klassId;

	/** 教师id */
	private Long teacherId;

	/** 学科id */
	private Long subjectId;

	/** 类型 */
	private Integer type;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getKlassId() {
		return this.klassId;
	}

	public void setKlassId(Long klassId) {
		this.klassId = klassId;
	}

	public Long getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
