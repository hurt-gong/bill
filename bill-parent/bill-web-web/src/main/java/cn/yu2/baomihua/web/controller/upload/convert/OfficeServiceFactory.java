package cn.yu2.baomihua.web.controller.upload.convert;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.beans.factory.FactoryBean;

import com.baomidou.kisso.common.util.EnvUtil;

//import cn.vko.core.common.environment.EnvironmentDetect;

public class OfficeServiceFactory implements FactoryBean<OfficeManager> {

	private static final long TASK_EXECUTION_TIMEOUT = 1000 * 60 * 1L * 120;

	private static final long TASK_QUEUE_TIMEOUT = 1000 * 60 * 1L * 120;

	private String officeHome;

	private OfficeManager officeManager;


	public void init1() {
		DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
		if(!EnvUtil.isLinux()) {
			
			officeHome ="C:\\Program Files (x86)\\OpenOffice 4";
			//officeHome = "D:\\vko\\soft\\openOffice";
		} 
		config.setOfficeHome(officeHome);
		config.setTaskQueueTimeout(TASK_QUEUE_TIMEOUT);
		config.setTaskExecutionTimeout(TASK_EXECUTION_TIMEOUT);
//		if ( EnvironmentDetect.detectEnvironment().isDevelop() ) {
//			config.setPortNumbers(2001, 2002);
//		} else {
			config.setPortNumbers(2001, 2002, 2003);
//		}
		officeManager = config.buildOfficeManager();
		officeManager.start();
		
	}


	public void destroy() {
		officeManager.stop();
	}


	@Override
	public OfficeManager getObject() throws Exception {
		return officeManager;
	}


	@Override
	public Class<?> getObjectType() {
		return OfficeDocumentConverter.class;
	}


	@Override
	public boolean isSingleton() {
		return true;
	}


	public String getOfficeHome() {
		return officeHome;
	}


	public void setOfficeHome( String officeHome ) {
		this.officeHome = officeHome;
	}

}
