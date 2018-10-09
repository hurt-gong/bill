package cn.yu2.baomihua.manage.enums;

import com.baomidou.framework.common.IEnum;

/**
 * @Description:任务类型
 * 
 * @author lizhixiao
 * @version 1.0
 * @date 2016-11-03
 */
public enum TaskTypeEnum implements IEnum {
	VIDEO(1, "视频"), HOMEWORK(2, "作业"), SOURCE(3, "学案"), TOPIC(4, "帖子");

	// 枚举值
	private final int key;

	// 枚举描述
	private final String desc;


	private TaskTypeEnum( int key, String desc ) {
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
