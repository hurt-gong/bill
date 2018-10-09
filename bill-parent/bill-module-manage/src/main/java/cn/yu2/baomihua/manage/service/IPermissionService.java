package cn.yu2.baomihua.manage.service;

import java.util.List;
import java.util.Set;

import com.baomidou.framework.service.ISuperService;

import cn.yu2.baomihua.manage.entity.Permission;
import cn.yu2.baomihua.manage.entity.RolePermission;

/**
 *
 * Permission 表数据服务层接口
 *
 */
public interface IPermissionService extends ISuperService<Permission> {



	List<Permission> selectPermission(List<String> permissionCodeList);

	List<String> selectPermissionCode(Long deptId);


	List<Permission> selectPermissionAll();

	int addPermissions(List<RolePermission> list,Long deptId);
	
	int deletePermissionByRoleId(Long roleId);

	List<Permission> selectPermissionAllByType(Boolean type);

	/**
	 * 获取用户权限值
	 * @param userId
	 * @return
	 */
	public Set<String> getPermission(Long userId);

}