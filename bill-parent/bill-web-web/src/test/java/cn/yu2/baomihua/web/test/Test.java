package cn.yu2.baomihua.web.test;

public class Test {
	
	
	
	/** 
     * 将Office文档转换为PDF. 运行该函数需要用到OpenOffice, OpenOffice下载地址为 
     * http://www.openoffice.org/ 
     *  
     * <pre> 
     * 方法示例: 
     * String sourcePath = "C:\\Users\\Administrator\\Desktop\office\\source.doc"; 
     * String destFile = "C:\\Users\\Administrator\\Desktop\pdf\\dest.doc"; 
     * Converter.office2PDF(sourcePath, destFile); 
     * </pre> 
     *  
     * @param sourceFile 
     *            源文件, 绝对路径. 可以是Office2003-2007全部格式的文档, Office2010的没测试. 包括.doc, 
     *            .docx, .xls, .xlsx, .ppt, .pptx等. 示例: F:\\office\\source.doc 
     * @param destFile 
     *            目标文件. 绝对路径. 示例: F:\\pdf\\dest.pdf 
     * @return 操作成功与否的提示信息. 如果返回 -1, 表示找不到源文件, 或url.properties配置错误; 如果返回 0, 
     *         则表示操作成功; 返回1, 则表示转换失败 
     */  
	
    /*@org.junit.Test
    public String pdf2swf(){
    	
    	SwfToolsHelper.convertPDF2SWF(sourcePath, destFile)
    	
    	return null;
    }*/
}
