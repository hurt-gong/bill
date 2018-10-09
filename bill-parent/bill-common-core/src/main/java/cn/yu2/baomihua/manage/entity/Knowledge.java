package cn.yu2.baomihua.manage.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 知识点表
 *
 */
@TableName(value = "GL_KNOWLEDGE")
public class Knowledge implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 本表主键 */
	@TableId
	private Long id;

	/** 标签名称 */
	private String name;

	/** 标签类型，见相关枚举类 */
	private Integer type;

	/** 标签显示顺序 */
	private Integer orderNum;

	/** 查询条件之一 */
	private Long k1;

	/** 查询条件之二 */
	private Long k2;

	/** 父ID */
	private Long parentId;

	/** 标记此标签是用户自定义还是系统录入，参见枚举类 */
	private Integer category;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Long getK1() {
		return this.k1;
	}

	public void setK1(Long k1) {
		this.k1 = k1;
	}

	public Long getK2() {
		return this.k2;
	}

	public void setK2(Long k2) {
		this.k2 = k2;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

}
