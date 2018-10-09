package cn.yu2.baomihua.manage.enums;

import com.baomidou.framework.common.IEnum;

/**
 * @Description:用户状态
 * 
 * @author lizhixiao
 * @version 1.0
 * @date 2016-06-15
 */
public enum ResponseHeadCodeEnum implements IEnum {
	processed(10, "处理中"), SUCCESS(20, "处理成功"), WAIT(30, "等待下一步处理指令"), REQUEST_ERROR(40, "请求错误"), ERROR(50, "处理失败");

	// 枚举值
	private final int key;

	// 枚举描述
	private final String desc;


	private ResponseHeadCodeEnum( int key, String desc ) {
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
