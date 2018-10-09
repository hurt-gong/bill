package cn.yu2.baomihua.manage.enums;

import com.baomidou.framework.common.IEnum;

/**
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0
 * @date 2016年4月27日 上午10:12:30
 */
public enum RoleTypeEnum implements IEnum {
	DEPT(1, "部门岗位"), ROLE(2, "角色");
	;
	// 枚举值
	private final int key;

	// 枚举描述
	private final String desc;

	private RoleTypeEnum( int key, String desc ) {
		this.key = key;
		this.desc = desc;
	}

	@Override
	public int key() {
		return key;
	}

	@Override
	public String desc() {
		return desc;
	}

}
