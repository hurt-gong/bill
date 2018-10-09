package cn.yu2.baomihua.manage.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.Permission;
import cn.yu2.baomihua.manage.entity.RolePermission;
import cn.yu2.baomihua.manage.service.IPermissionService;

/** 
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月27日 下午3:20:06 
 */
public class PermissionModuleImpl extends AbstractModule implements IPermissionModule {
	
	@Autowired
	private IPermissionService permissionService;
	
	@Override
	public List<String> selectPermissionCode(Long deptId) {
		return permissionService.selectPermissionCode(deptId);
	}

	@Override
	public List<Permission> selectPermission(List<String> permissionCodeList) {
		
		return permissionService.selectPermission(permissionCodeList);
	}

	@Override
	public List<Permission> selectPermissionAll() {
		return permissionService.selectPermissionAll();
	}

	@Override
	public Response<Boolean> addPermissions(List<String> perCode, Long deptId) {
		List<RolePermission> list = new ArrayList<RolePermission>();
		for(String str:perCode){
			RolePermission rolePermission = new RolePermission();
			rolePermission.setPermissionCode(str);
			rolePermission.setRoleId(deptId);
			list.add(rolePermission);
		}
		return result(permissionService.addPermissions(list,deptId));
	}

	@Override
	public Response<Boolean> deletePermissionByRoleId(Long roleId) {
		permissionService.deletePermissionByRoleId(roleId);
		return success(true);
	}

	@Override
	public List<Permission> selectPermissionAllByType(Boolean type) {
		return permissionService.selectPermissionAllByType(type);
	}

	@Override
	public Response<Set<String>> getPermission(Long userId) {

		return success(permissionService.getPermission(userId));
	}






}
