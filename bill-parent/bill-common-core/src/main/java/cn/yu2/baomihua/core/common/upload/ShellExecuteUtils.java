package cn.yu2.baomihua.core.common.upload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.kisso.common.util.EnvUtil;

public class ShellExecuteUtils {

	protected static Logger logger = LoggerFactory.getLogger(ShellExecuteUtils.class);

	/**
	 * 执行控制台命令
	 * @param shell
	 * @return
	 */
	public static synchronized String runCommand( String shell ) {
		Runtime run = Runtime.getRuntime();
		String result = null;
		try {
			String t = "Execute : " + shell;
			logger.debug(t);
			Process process = null;
			if ( EnvUtil.isLinux() ) {
				process = run.exec(new String[ ] { "/bin/sh", "-c", shell });

			} else {
				process = run.exec(new String[ ] { "cmd ", "/c", shell });
			}
			result = inputStream2String(process.getInputStream());
			logger.debug("执行结果为：" + result);


		} catch ( IOException e ) {
			logger.debug("执行 出错!", e);	
		}

		return result;
	}


	/**
	 * 从InputStream流中读取字符串
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String inputStream2String( InputStream is ) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String line = null;
		StringBuffer buffer = new StringBuffer();
		while ( (line = in.readLine()) != null ) {
			buffer.append(line).append("\n");
		}
		//remove last \n		
		if ( buffer.length() > 0 ) {
			buffer.deleteCharAt(buffer.length() - 1);
		}
		return buffer.toString();
	}

}
