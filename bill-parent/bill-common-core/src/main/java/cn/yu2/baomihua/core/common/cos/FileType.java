package cn.yu2.baomihua.core.common.cos;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 根据头文件判断类型
 * 
 * <p>
 * 文件MIMETYPE类型，与文件类型的对应关系请参考 http://t.cn/z8ZnFny
 * </p>
 * 
 * @author hubin
 *
 */
public class FileType {

	private final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();


	protected FileType() {

	}


	static {
		/**
		 * 初始化文件类型信息
		 */
		initAllFileType();
	}


	/**
	 * 
	 * 目前获取文件头 3 个字节判断
	 * 
	 * <p>
	 * 注意！文件头 3字节重复率很高，结合上传文件后缀联合判断。
	 * </p>
	 * 
	 */
	private static void initAllFileType() {

		/* 图片文件 */
		FILE_TYPE_MAP.put("ffd8ff", "jpg");
		FILE_TYPE_MAP.put("89504e", "png");
		FILE_TYPE_MAP.put("424d2e", "bmp");//256色位图(bmp)
		FILE_TYPE_MAP.put("424d1e", "bmp");//24色位图(bmp)
		FILE_TYPE_MAP.put("424dc6", "bmp");//16色位图(bmp)
		FILE_TYPE_MAP.put("474946", "gif");
		FILE_TYPE_MAP.put("49492a", "tif");
		FILE_TYPE_MAP.put("384250", "psd");

		/* 视频文件 */
		FILE_TYPE_MAP.put("464c56", "flv");
		FILE_TYPE_MAP.put("000000", "mp4");
		FILE_TYPE_MAP.put("1a45df", "mkv");
		FILE_TYPE_MAP.put("2e524d", "rmvb");
		FILE_TYPE_MAP.put("524946", "avi");
		FILE_TYPE_MAP.put("4d5a50", "exe");

		/* 音频文件 */
		FILE_TYPE_MAP.put("232141", "amr");
		
		/* 文档文件 */
		FILE_TYPE_MAP.put("255044", "pdf");
		FILE_TYPE_MAP.put("d0cf11", "xls");
		FILE_TYPE_MAP.put("504b03", "xlsx");
		
	}


	/** 
	 * 得到上传文件的文件头 
	 * @param src 
	 * @return 
	 */
	private static String bytesToHexString( byte[] src ) {
		StringBuilder stringBuilder = new StringBuilder();
		if ( null == src || src.length <= 0 ) {
			return null;
		}
		for ( int i = 0 ; i < src.length ; i++ ) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if ( hv.length() < 2 ) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}


	/**
	 * 获取文件类型
	 * @param file
	 * 			文件 {@link File}
	 * @return
	 */
	public static String getFileType( File file ) throws Exception {
		return getFileType(new FileInputStream(file));
	}


	/**
	 * 获取文件类型
	 * @param FileInputStream
	 * 					文件输入流  {@link InputStream}
	 * @return
	 */
	public static String getFileType( InputStream in ) throws Exception {
		if ( in == null ) {
			throw new IllegalArgumentException("InputStream reference cannot be null.");
		}
		if ( !in.markSupported() ) {
			throw new IllegalArgumentException("InputStream must support the mark() and reset() methods.");
		}
		byte[] data = new byte[3];
		in.mark(3);
		in.read(data, 0, data.length);
		in.reset();
		return getFileType(data);
	}


	/**
	 * 获取文件类型
	 * @param FileInputStream
	 * 					文件输入流  {@link InputStream}
	 * @return
	 */
	public static String getFileType( byte[] data ) throws Exception {
		return FILE_TYPE_MAP.get(bytesToHexString(data));
	}

}
