package cn.yu2.baomihua.web.controller.upload.convert;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.OfficeException;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocConverter implements Converter {

	protected static final Logger logger = LoggerFactory.getLogger(DocConverter.class);

	private OfficeManager officeManager;

	private FileConverterFactory fileConverterFactory;


	@Override
	public void convert( String source ) throws IOException {

		String targetPdfPath = getTarget(source, ".pdf");

		String targetSwfPath = getTarget(source, ".swf");

		File pdfFile = new File(targetPdfPath);
		File swfFile = new File(targetSwfPath);
		if ( swfFile.exists() ) {
			//pdfFile.delete();
			return;
		}
		
		OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);

		if ( !pdfFile.exists() ) {
			logger.error("开始转换office文档：{}",source);
			try {
				converter.convert(new File(source), pdfFile);
			} catch (OfficeException e) {
				//文件从未另存过,另存下
				if(!source.contains("_as.")) {
					String as = getTarget(source);
					converter.convert(new File(source), new File(as));
					String newSource = as;
					logger.error("转换另存文件:  "+newSource);
					converter.convert(new File(newSource), pdfFile);
				}
			}
		}
		
		if ( pdfFile.exists() ) {
			fileConverterFactory.doConvert(targetPdfPath);
			return;
		}

	}


	public static String getTarget( String source, String type ) {
		return FilenameUtils.getFullPath(source) + FilenameUtils.getName(source).split("\\.")[0] + type;
	}


	public static String getTarget( String source ) {
		String[] name = FilenameUtils.getName(source).split("\\.");
		return FilenameUtils.getFullPath(source) + name[0] + "_as." + name[1];
	}


	public FileConverterFactory getFileConverterFactory() {
		return fileConverterFactory;
	}


	public void setFileConverterFactory( FileConverterFactory fileConverterFactory ) {
		this.fileConverterFactory = fileConverterFactory;
	}


	public OfficeManager getOfficeManager() {
		return officeManager;
	}


	public void setOfficeManager( OfficeManager officeManager ) {
		this.officeManager = officeManager;
	}

}
