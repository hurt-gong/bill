package cn.yu2.baomihua.manage.module;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.dto.SchoolAdminDTO;
import cn.yu2.baomihua.manage.dto.SchoolDTO;
import cn.yu2.baomihua.manage.entity.School;
import cn.yu2.baomihua.manage.entity.SchoolAdmin;
import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.manage.service.ISchoolAdminService;
import cn.yu2.baomihua.manage.service.ISchoolService;
import cn.yu2.baomihua.manage.service.IUserService;

/** 
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月26日 下午5:14:53 
 */
public class SchoolModuleImpl extends AbstractModule implements ISchoolModule {
	
	private static final Logger logger = LoggerFactory.getLogger(SchoolModuleImpl.class);
	
	@Autowired
	private ISchoolService schoolService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ISchoolAdminService schoolAdminService;	

	@Override
	public Response<Page<SchoolDTO>> getSchoolByName(Page<SchoolDTO> page, String name) {
		page = schoolService.getSchoolByName(page, name);
		logger.warn(this.getClass().getName()+ ",name:"+name);
		for(SchoolDTO dto : page.getRecords()){
			long schoolId = dto.getId();
			List<SchoolAdminDTO> admins =schoolAdminService.getAdmins(schoolId);
			dto.setAdmins(admins);
		}
		return success(page);
	}

	@Override
	public Response<User> insertAdmin(Long userId) {
		User u = new User();
		u.setId(userId);
		userService.updateSelectiveById(u);
		
		return success(userService.selectById(userId));
	}

	@Override
	public Response<Boolean> deleteAdmin(Long id) {
		
		return success(schoolAdminService.deleteById(id));
	}

	@Override
	public Response<Set<Long>> getAdminIds(Long schoolId) {
		SchoolAdmin schoolAdmin = new SchoolAdmin();
		schoolAdmin.setSchoolId(schoolId);
		List<SchoolAdmin> schoolAdmins = schoolAdminService.selectList(schoolAdmin);
		Set<Long> adminIds = new HashSet<>();
		for(SchoolAdmin sa : schoolAdmins){
			adminIds.add(sa.getUserId());	
		}
		return success(adminIds);
	}

	@Override
	public Response<Boolean> insertAdmins(List<SchoolAdmin> schoolAdmins) {
		
		return success(schoolAdminService.insertBatch(schoolAdmins));
	}

	@Override
	public Response<Boolean> deleteAdmins(Long schoolId) {
		SchoolAdmin param = new SchoolAdmin();
		param.setSchoolId(schoolId);
		
		return success(schoolAdminService.deleteSelective(param));
	}

	@Override
	public Response<List<SchoolAdminDTO>> getAdmins(Long schoolId) {
		return success(schoolAdminService.getAdmins(schoolId));
	}

	@Override
	public School getSchoolById(Long id) {
		return schoolService.getSchoolById(id);
	}

	@Override
	public List<School> getSchoolList() {
		return schoolService.getSchoolList();
	}

}
