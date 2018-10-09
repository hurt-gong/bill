package cn.yu2.baomihua.manage.enums;

import com.baomidou.framework.common.IEnum;

/**
 * @Description:状态
 * 
 * @author wanglulu
 * @version 1.0
 * @date 2016年4月27日 上午10:12:30
 */
public enum StatusEnum implements IEnum {
	ON(1, "启用"), OFF(2, "下线或删除");

	// 枚举值
	private final int key;

	// 枚举描述
	private final String desc;


	private StatusEnum( int key, String desc ) {
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
