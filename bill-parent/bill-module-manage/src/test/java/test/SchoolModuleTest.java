package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.dto.SchoolDTO;
import cn.yu2.baomihua.manage.module.ISchoolModule;

/** 
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月27日 上午10:56:48 
 */
public class SchoolModuleTest extends BaseTest {
	
	protected static Logger logger = LoggerFactory.getLogger(SchoolModuleTest.class);

	public static void main(String[] args) {
		ISchoolModule schoolModule = (ISchoolModule) getContext().getBean("schoolModule");
		
		Page<SchoolDTO> page = new Page<SchoolDTO>(1, 2);
		Response<Page<SchoolDTO>> resp = schoolModule.getSchoolByName(page, "十一");
		if(resp.isSuccess()) {
			page = resp.getBody();
			for(SchoolDTO dto : page.getRecords()) {
				logger.debug(dto.getName()+"\t" + dto.getAddress() + "\t" + dto.getAdmins().size());
			}
		}
	}
}
