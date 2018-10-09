package cn.yu2.baomihua.core.common.cos;

/**
 * 
 * 上传编码
 * 
 * @author hubin
 *
 */
public enum UploadCode {

	NORMAL(0, " upload success. "), ILLEGAL_EXT(1, " upload illegal file ext. "), EXCEPTION(3, " upload exception. ");

	private final int key;

	private final String desc;

	private UploadCode(int key, String desc) {
		this.key = key;
		this.desc = desc;
	}

	public int key() {
		return key;
	}

	public String desc() {
		return desc;
	}

}
