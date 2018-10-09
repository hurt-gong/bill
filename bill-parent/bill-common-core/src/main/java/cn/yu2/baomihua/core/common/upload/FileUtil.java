package cn.yu2.baomihua.core.common.upload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.common.util.EnvUtil;
import com.baomidou.kisso.common.util.RandomUtil;

/**
 * 文件工具类
 * 
 * @author hubin
 */
public class FileUtil {

	/**
	 * 获得不同环境的文件目录
	 * 
	 * @param dir
	 * 			文件目录
	 * @return
	 */
	public static String getSystemDir( String dir ) {
		if ( EnvUtil.isLinux() ) {
			return dir;
		}

		File file = new File(System.getProperty("user.dir") + dir);
//		File file = new File(SSOConfig.getSSOProperties().get("upload.basedir")+dir);
		if ( !file.exists() ) {
			file.mkdirs();
		}
		return file.getPath() + File.separator;
	}


	/**
	 * 获取文件路径名
	 * 
	 * @param filePath
	 * 				文件路径
	 * @param fileSuffix
	 * 				文件后缀
	 * @return
	 */
	public static String getPathName( String filePath, String fileSuffix ) {
		StringBuffer pathName = new StringBuffer();
		pathName.append(filePath);
		pathName.append(getOnlyFileName());
		pathName.append(".");
		pathName.append(fileSuffix);
		return pathName.toString();
	}


	/**
	 * 获取唯一文件名
	 */
	public static synchronized String getOnlyFileName() {
		StringBuffer fileName = new StringBuffer();
		fileName.append(System.currentTimeMillis());
		fileName.append("-");
		fileName.append(RandomUtil.getCharacterAndNumber(5));
		fileName.append("-");
		fileName.append(UUID.randomUUID());
		return fileName.toString();
	}


	/** 
	 * 清理指定文件
	 */
	public static void delete( String pathname ) {
		File file = new File(pathname);
		if ( file != null && file.exists() ) {
			file.delete();
		}
	}


	/** 
	 * 获得指定文件的byte数组 
	 */
	public static byte[] getBytes( File file ) {
		byte[] buffer = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ( (n = fis.read(b)) != -1 ) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch ( FileNotFoundException e ) {
			e.printStackTrace();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return buffer;
	}

}
