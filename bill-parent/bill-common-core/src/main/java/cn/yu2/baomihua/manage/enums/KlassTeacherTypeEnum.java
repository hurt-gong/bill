package cn.yu2.baomihua.manage.enums;

import com.baomidou.framework.common.IEnum;

/**
 * @Description:班级教师类型
 * 
 * @author lizhixixao
 * @version 1.0
 * @date 2016年6月29日
 */
public enum KlassTeacherTypeEnum implements IEnum {
	TEACHER(1, "任课教师"), HEADMASTER(2, "班主任");

	// 枚举值
	private final int key;

	// 枚举描述
	private final String desc;


	private KlassTeacherTypeEnum( int key, String desc ) {
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
