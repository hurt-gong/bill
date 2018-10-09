package cn.yu2.baomihua.core.common.upload;

import com.baomidou.kisso.SSOConfig;

/**
 * 
 * 上传配置
 * 
 * @author hubin
 *
 */
public class UploadConfig {

	/**
	 * 上传保存地址
	 */
	public static String getSaveDir() {
		return SSOConfig.getSSOProperties().get("upload.savedir");
	}


	/**
	 * 上传返回地址
	 * 
	 * @param url
	* 				FastDfs 返回地址
	 * @return
	 */
	public static String getUploadUrl( String url ) {
		return SSOConfig.getSSOProperties().get("upload.domain") + url;
	}


	/**
	 * 上传地址类型
	 * 
	 * @param url
	* 				FastDfs 返回地址
	 * @return
	 */
	public static String getUploadType() {
		return SSOConfig.getSSOProperties().get("upload.type");
	}


	/**
	 * 上传上传
	 * 
	 * @param url
	 * 				FastDfs 返回地址
	 * @return
	 */
	public static String getUploadWebUrl() {
		return SSOConfig.getSSOProperties().get("upload.web.url");
	}

}
