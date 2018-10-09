package cn.yu2.baomihua.web.controller.upload.convert;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.intarsys.pdf.cos.COSDocument;
import de.intarsys.pdf.pd.PDDocument;
import de.intarsys.pdf.st.STDocument;
import de.intarsys.tools.locator.FileLocator;

public class PdfConverter implements Converter {

	private static final Logger logger = LoggerFactory.getLogger(PdfConverter.class);

	private String swftoolsCommand;

	private String middleCommand;


	@Override
	public void convert( String source ) throws Exception {
		String target = getTarget(source);
		if ( new File(target).exists() ) {
			return;
		}
		//���ܴ�����ʱ�ļ�
		String newPdf = removeEncryption(source);
		String command = swftoolsCommand + newPdf + middleCommand + target;
//		String command = "pdf2swf -s languagedir=/usr/local/xpdf-chinese-simplified -T 9 /usrdata/hdedu/up/mooc/458YbsiZEuBLKrJsK6vYuA.pdf -o /usrdata/hdedu/up/mooc/458YbsiZEuBLKrJsK6vYuA.swf";
		logger.error(command);
		//		String[] cmd = {"/bin/sh", "-c",command};
		Process result = Runtime.getRuntime().exec(command);
		//��������ֹ
		//		if(result.exitValue() !=0 ) {
		//			command = "pdf2swf -s languagedir=/usr/local/xpdf-chinese-simplified -T 9 -s poly2bitmap" + newPdf + middleCommand + target;
		//			logger.error(command);
		//			Runtime.getRuntime().exec(command);
		//		}

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


	private static String getTarget( String source ) {
		return FilenameUtils.getFullPath(source)
				+ FilenameUtils.getName(source).split("\\.")[0].replace("_de", "") + ".swf";
	}


	private static String getTargetPdf_de( String source ) {
		return FilenameUtils.getFullPath(source) + FilenameUtils.getName(source).split("\\.")[0] + "_de.pdf";
	}


	public String getSwftoolsCommand() {
		return swftoolsCommand;
	}


	public void setSwftoolsCommand( String swftoolsCommand ) {
		this.swftoolsCommand = swftoolsCommand;
	}


	public String getMiddleCommand() {
		return middleCommand;
	}


	public void setMiddleCommand( String middleCommand ) {
		this.middleCommand = middleCommand;
	}

}
