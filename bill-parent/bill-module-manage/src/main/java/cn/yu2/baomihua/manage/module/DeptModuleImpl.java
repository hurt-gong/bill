package cn.yu2.baomihua.manage.module;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.dto.RoleTeacherDTO;
import cn.yu2.baomihua.manage.entity.Role;
import cn.yu2.baomihua.manage.entity.RoleTeacher;
import cn.yu2.baomihua.manage.service.IRoleService;
import cn.yu2.baomihua.manage.service.IRoleTeacherService;

/** 
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月27日 下午3:17:17 
 */
public class DeptModuleImpl extends AbstractModule implements IDeptModule {

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IRoleTeacherService roleTeacherService;
	
	@Override
	public Response<List<Role>> getDepts(Long schoolId, Integer type) {
		Role role = new Role();
		role.setSchoolId(schoolId);
		role.setType(type);
		role.setPid(0L);
		List<Role> roles = roleService.selectList(role);
		return success(roles);
	}

	@Override
	public Response<List<Role>> getChildDepts(Long pid) {
		Role role = new Role();
		role.setPid(pid);
		List<Role> roles = roleService.selectList(role);
		return success(roles);
	}

	@Override
	public Response<Boolean> addDept(Role role) {
		
		return success(roleService.insert(role));
	}
	
	@Override
	public Response<Boolean> updateById(Role role) {
		
		return success(roleService.updateById(role));
	}

	@Override
	public Response<Role> getDept(Long id) {
		return success(roleService.selectById(id));
	}

	@Override
	public Response<Boolean> deleteById(Long id) {
		return success(roleService.deleteById(id));
	}

	@Override
	public Response<List<RoleTeacherDTO>> getRoleTeachers(Long deptId) {

		return success(roleTeacherService.getRoleTeachers(deptId));
	}
	
	@Override
	public Response<Set<Long>> getUserIds(Long deptId) {
		List<RoleTeacherDTO> roleTeachers = roleTeacherService.getRoleTeachers(deptId);
		Set<Long> userIds = new HashSet<>();
		for(RoleTeacherDTO rtd : roleTeachers){
			userIds.add(rtd.getTeacherId());	
		}
		return success(userIds);
	}

	@Override
	public Response<List<Role>> getDept(Role role) {
		
		return success(roleService.selectList(role));
	}

	@Override
	public Response<Boolean> deleteRoleTeacher(Long deptId) {

		RoleTeacher param = new RoleTeacher();
		param.setRoleId(deptId);
		return success(roleTeacherService.deleteSelective(param));
	}

	@Override
	public Response<Boolean> insertRoleTeacher(List<RoleTeacher> roleTeachers) {

		return success(roleTeacherService.insertBatch(roleTeachers));
	}

	@Override
	public Response<Boolean> deleteRoleTeacherById(Long id) {

		return success(roleTeacherService.deleteById(id));
	}

}
