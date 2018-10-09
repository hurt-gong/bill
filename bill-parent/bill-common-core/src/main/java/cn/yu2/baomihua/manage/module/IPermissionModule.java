package cn.yu2.baomihua.manage.module;

import java.util.List;
import java.util.Set;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.Permission;

/** 
 * @Description:权限管理
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月27日 下午3:14:44 
 */
public interface IPermissionModule {
	
	/**
	 * 根据部门ID查询该部门权限
	 * @param deptId
	 * @return
	 */
	List<String> selectPermissionCode(Long deptId);

	/**
	 * 根据部门下的权限Code查询对应的权限
	 * @param permissionCodeList
	 * @return
	 */
	List<Permission> selectPermission(List<String> permissionCodeList);
	/**
	 * 查询所有权限
	 * @return
	 */
	List<Permission> selectPermissionAll();
	
	List<Permission> selectPermissionAllByType(Boolean type);

	Response<Boolean> addPermissions(List<String> perCode, Long deptId);

	/**
	 * 根据roleId删除权限
	 * @param deptId
	 * @return
	 */
	Response<Boolean> deletePermissionByRoleId(Long deptId);
	
	/**
	 * 获取用户权限值
	 * @param userId
	 * @return
	 */
	public Response<Set<String>> getPermission(Long userId);

}
