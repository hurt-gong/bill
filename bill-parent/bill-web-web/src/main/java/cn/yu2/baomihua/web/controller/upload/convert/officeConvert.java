package cn.yu2.baomihua.web.controller.upload.convert;

import java.io.File;
import java.io.FileNotFoundException;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/***
 * 		操作成功与否的提示信息. 如果返回 -1, 表示找不到源文件, 或url.properties配置错误; 如果返回 0,  
 *      则表示操作成功; 返回1, 则表示转换失败  
 */
public class officeConvert {
	
	 public int convert(String sourceFile) {  
	        Process pro = null;  
	        OpenOfficeConnection connection = null;  
	        try {  
	            File inputFile = new File(sourceFile);  
	            if (!inputFile.exists()) {  
	                return -1;//文件不存在  
	            }  
	  
	            //文件夹不存在创建目录  
	            File outputFile = new File(sourceFile.substring(0,sourceFile.lastIndexOf("."))+".pdf");  
	            if (!outputFile.getParentFile().exists()) {  
	                outputFile.getParentFile().mkdirs();  
	            }  
	  
	            String OpenOffice_HOME = "D:\\OpenOffice";  
	            if (OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '\\') {  
	                OpenOffice_HOME += "\\";  
	            }  
	            // 启动OpenOffice的服务    
	            String command = OpenOffice_HOME  
	                    + "program\\soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\" -nofirststartwizard";  
	            pro = Runtime.getRuntime().exec(command);  
	            // connect to an OpenOffice.org instance running on port 8100  
	            connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);  
	            connection.connect();  
	  
	            // convert  
	            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);  
	           
	            
	            converter.convert(inputFile, outputFile);  
	  
	            return 0;  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	            return 1;  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }finally{  
	            if(connection!=null){  
	                // close the connection  
	                connection.disconnect();  
	            }  
	             // 关闭OpenOffice服务的进程    
	            if(pro!=null){  
	                pro.destroy();  
	            }  
	        }  
	        return 1;  
	    } 
	
}
