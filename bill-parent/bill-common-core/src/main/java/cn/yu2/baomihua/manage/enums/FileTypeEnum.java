package cn.yu2.baomihua.manage.enums;

import com.baomidou.framework.common.IEnum;

/**
 * @Description:
 * 
 * @author ShiBin
 * @version 1.0
 * @date 2016年4月27日 上午10:12:30
 */
public enum FileTypeEnum implements IEnum {
	IMAGE(1, "图片"), VIDEO(2, "视频"), UNKNOWN(3, "未知文件");

	// 枚举值
	private final int key;

	// 枚举描述
	private final String desc;


	private FileTypeEnum( int key, String desc ) {
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
