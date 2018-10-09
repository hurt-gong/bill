package cn.yu2.baomihua.manage.enums;

import com.baomidou.framework.common.IEnum;

/**
 * @Description:用户类型
 * 
 * @author wanglulu
 * @version 1.0
 * @date 2016年4月27日 上午10:12:30
 */
public enum UserTypeEnum implements IEnum {
	STUDENT(1, "学生"), TEACHER(2, "教师"), PARENT(3, "家长");

	// 枚举值
	private final int key;

	// 枚举描述
	private final String desc;


	private UserTypeEnum( int key, String desc ) {
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
