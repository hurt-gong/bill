package cn.yu2.baomihua.core.common.cos;

/**
 * 
 * 上传信息
 * 
 * @author hubin
 *
 */
public class UploadMsg {

	/**
	 * 是否成功
	 */
	private boolean success = false;

	/* 文件字节数 */
	private long size;

	/* 文件保存路径 */
	private String url;

	/**
	 * 提示信息
	 */
	private String msg = "upload failed.";


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess( boolean success ) {
		this.success = success;
	}


	public long getSize() {
		return size;
	}


	public void setSize( long size ) {
		this.size = size;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl( String url ) {
		this.url = url;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg( String msg ) {
		this.msg = msg;
	}

}
