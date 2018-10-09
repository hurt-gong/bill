package cn.yu2.baomihua.manage.enums;

import com.baomidou.framework.common.IEnum;

/**
 * @Description:数据状态
 * 
 * @author lizhixiao
 * @version 1.0
 * @date 2016-06-12
 */
public enum SubjectTypeEnum implements IEnum {
	BASE(1, "基础"), CUSTOM(2, "自定义");

	// 枚举值
	private final int key;

	// 枚举描述
	private final String desc;


	private SubjectTypeEnum( int key, String desc ) {
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
