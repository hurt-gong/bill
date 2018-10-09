package cn.yu2.baomihua.web.controller.upload.convert;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.kisso.common.util.EnvUtil;

import de.intarsys.pdf.cos.COSDocument;
import de.intarsys.pdf.pd.PDDocument;
import de.intarsys.pdf.st.STDocument;
import de.intarsys.tools.locator.FileLocator;

/** 
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年11月7日 下午7:04:24 
 */
public class SwfToolsConverter implements Converter {

	private static final Logger logger = LoggerFactory.getLogger(SwfToolsConverter.class);

	private static final String CMD_WIN = "D:/soft/SWFTools/pdf2swf.exe -s flashversion=9 %s -o %s";

	private static final String CMD_LINUX = "pdf2swf -s languagedir=/usr/local/xpdf-chinese-simplified -T 9 -s zoom=150 -s flashversion=9 %s -o %s";


	@Override
	public void convert( String source ) throws Exception {
		String target = getTarget(source);
		if ( new File(target).exists() ) {
			return;
		}

		String newPdf = removeEncryption(source);
		/**
		 * 调用pdf2swf命令进行转换  
		 */
		String execCmd = String.format(EnvUtil.isLinux() ? CMD_LINUX : CMD_WIN, newPdf, target);
		logger.error(execCmd);
		Process pro = Runtime.getRuntime().exec(execCmd);

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
		while ( bufferedReader.readLine() != null )
			;
		//	    new DoOutput(pro.getInputStream()).start();
		//
		//        new DoOutput(pro.getErrorStream()).start();
		try {
			pro.waitFor();
		} catch (

		InterruptedException e ) {
			logger.error("SwfToolsHelper.convertPDF2SWF error.", e);
		}

	}


	private static String getTarget( String source ) {
		return FilenameUtils.getFullPath(source)
				+ FilenameUtils.getName(source).split("\\.")[0].replace("_de", "") + ".swf";
	}


	protected String removeEncryption( String source ) throws Exception {
		FileLocator locator = new FileLocator(source);
		PDDocument doc = PDDocument.createFromLocator(locator);
		COSDocument cosDoc = doc.cosGetDoc();
		String location = source;
		if ( cosDoc.isEncrypted() ) {
			STDocument stDoc = cosDoc.stGetDoc();
			stDoc.setSystemSecurityHandler(null);
			//�����ļ����λ��
			location = getTargetPdf_de(source);
			FileLocator locator_de = new FileLocator(getTargetPdf_de(source));
			doc.save(locator_de, null);
		}
		doc.close();
		return location;

	}


	private static String getTargetPdf_de( String source ) {
		return FilenameUtils.getFullPath(source) + FilenameUtils.getName(source).split("\\.")[0] + "_de.pdf";
	}

	private static class DoOutput extends Thread {

		public InputStream is;


		//构造方法

		public DoOutput( InputStream is ) {

			this.is = is;

		}


		@Override
		public void run() {

			BufferedReader br = new BufferedReader(new InputStreamReader(this.is));

			String str = null;

			try {

				//这里并没有对流的内容进行处理，只是读了一遍

				while ( (str = br.readLine()) != null )
					;

			} catch ( IOException e ) {

				e.printStackTrace();

			} finally {

				if ( br != null ) {

					try {

						br.close();

					} catch ( IOException e ) {

						e.printStackTrace();

					}

				}

			}

		}

	}
}
