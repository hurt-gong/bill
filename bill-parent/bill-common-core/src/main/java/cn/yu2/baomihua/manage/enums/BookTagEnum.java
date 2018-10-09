package cn.yu2.baomihua.manage.enums;

import com.baomidou.framework.common.IEnum;

/**
 * @Description:用户状态
 * 
 * @author wanglulu
 * @version 1.0
 * @date 2016年4月27日 上午10:12:30
 */
public enum BookTagEnum implements IEnum {
	ON(1, "学段"), AGE (2, "年级"), OFF(3, "学科");
	;
	// 枚举值
	private final int key;

	// 枚举描述
	private final String desc;

	private BookTagEnum( int key, String desc ) {
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
