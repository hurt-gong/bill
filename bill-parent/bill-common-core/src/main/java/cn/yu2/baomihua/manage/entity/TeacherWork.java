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
@TableName(value = "GL_TEACHER_WORK")
public class TeacherWork implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId
	private Long id;

	/**  */
	private Long teacherId;

	/**  */
	private Long teachId;

	/**  */
	private Integer type;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getTeachId() {
		return this.teachId;
	}

	public void setTeachId(Long teachId) {
		this.teachId = teachId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
