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
@TableName(value = "GL_KLASS_STUDENT")
public class KlassStudent implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** ID */
	@TableId
	private Long ID;

	/**  */
	private Long klassId;

	/** ѧ */
	private Long studentId;

	public Long getID() {
		return this.ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public Long getKlassId() {
		return this.klassId;
	}

	public void setKlassId(Long klassId) {
		this.klassId = klassId;
	}

	public Long getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

}
