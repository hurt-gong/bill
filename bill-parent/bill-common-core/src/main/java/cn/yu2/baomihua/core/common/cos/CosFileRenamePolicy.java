package cn.yu2.baomihua.core.common.cos;

import java.io.File;

import com.baomidou.framework.upload.multipart.FileRenamePolicy;

import cn.yu2.baomihua.util.Base58;

/**  
 * Description: 自定义COS命名策略文件
 * 
 * @author hubin
 */
public class CosFileRenamePolicy implements FileRenamePolicy {

	/* 后缀名 */
	private String suffix;


	/**
	 * 文件重命名
	 */
	public File rename( File file ) {
		StringBuffer pathName = new StringBuffer();
//		pathName.append(cn.yu2.baomihua.core.common.upload.FileUtil.getOnlyFileName());
		pathName.append(Base58.compressedUUID());
		pathName.append(".");
		pathName.append(getSuffix());
		file = new File(file.getParent(), pathName.toString());
		return file;
	}


	public String getSuffix() {
		return suffix;
	}


	public void setSuffix( String suffix ) {
		this.suffix = suffix;
	}
}
