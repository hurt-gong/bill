package cn.yu2.baomihua.core.common.cos;

import java.io.File;
import java.util.HashMap;

/**
 * Cos上传文件信息
 * 
 * @author hubin
 *
 */
public class CosFile {

	/* 名称 */
	private String filename;

	/* 后缀名 */
	private String suffix;

	/* 上传文件目录 */
	private String dir;

	/* 上传名称 */
	private String original;

	/* 上传类型 */
	private String type;

	/* 文件字节数 */
	private long size;

	/* 文件上传码 */
	private UploadCode uploadCode = UploadCode.NORMAL;

	/* 上传携带参数 */
	private HashMap<String, String> paramParts;


	/**
	 * 是否上传成功
	 */
	public boolean isSuccess() {
		if ( UploadCode.NORMAL == getUploadCode() ) {
			return true;
		}
		return false;
	}


	/**
	 * 是否为视频
	 */
	public boolean isVideo() {
		if ( suffix != null ) {
			if ( suffix.contains("mp4") || suffix.contains("flv") ) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 是否为音频
	 */
	public boolean isAudio() {
		if ( suffix != null ) {
			if ( suffix.contains("amr") ) {
				return true;
			}
		}
		return false;
	}


	/**
	 * 是否为图片
	 */
	public boolean isImage() {
		if ( suffix != null ) {
			if ( suffix.contains("jpg")
					|| suffix.contains("png") || suffix.contains("bmp") || suffix.contains("gif") ) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 是否为 PDF 文档文件
	 */
	public boolean isPDF() {
		if ( suffix != null ) {
			if ( suffix.equalsIgnoreCase("pdf") ) {
				return true;
			}
		}
		return false;
	}


	/**
	 * 文件路径
	 */
	public String getFileUrl() {
		if ( dir == null || filename == null ) {
			return null;
		} else {
			return dir + File.separator + filename;
		}
	}


	/**
	 * 上传文件
	 */
	public File getFile() {
		if ( dir == null || filename == null ) {
			return null;
		} else {
			return new File(dir + File.separator + filename);
		}
	}


	/**
	 * 删除上传文件
	 */
	public boolean delFile() {
		File file = getFile();
		if ( file != null ) {
			return file.delete();
		}
		return false;
	}


	public String getContentType() {
		return type;
	}


	public String getFilesystemName() {
		return filename;
	}


	public String getOriginalFileName() {
		return original;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename( String filename ) {
		this.filename = filename;
	}


	public String getSuffix() {
		return suffix;
	}


	public void setSuffix( String suffix ) {
		this.suffix = suffix;
	}


	public String getDir() {
		return dir;
	}


	public void setDir( String dir ) {
		this.dir = dir;
	}


	public String getOriginal() {
		return original;
	}


	public void setOriginal( String original ) {
		this.original = original;
	}


	public String getType() {
		return type;
	}


	public void setType( String type ) {
		this.type = type;
	}


	public long getSize() {
		return size;
	}


	public void setSize( long size ) {
		this.size = size;
	}


	public UploadCode getUploadCode() {
		return uploadCode;
	}


	public void setUploadCode( UploadCode uploadCode ) {
		this.uploadCode = uploadCode;
	}


	public HashMap<String, String> getParamParts() {
		return paramParts;
	}


	public void setParamParts( HashMap<String, String> paramParts ) {
		this.paramParts = paramParts;
	}

}
