package cn.yu2.baomihua.manage.module;

import java.util.List;
import java.util.Set;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.dto.RoleTeacherDTO;
import cn.yu2.baomihua.manage.entity.Role;
import cn.yu2.baomihua.manage.entity.RoleTeacher;

/** 
 * @Description:部门module
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月27日 下午3:01:12 
 */
public interface IDeptModule {

	/**
	 * 查询部门列表
	 * @param schoolId
	 * @return
	 */
	public Response<List<Role>> getDepts(Long schoolId,Integer type);
	
	/**
	 * 查询岗位列表
	 * @param pid
	 * @return
	 */
	public Response<List<Role>> getChildDepts(Long pid);

	/**
	 * 添加部门 岗位
	 * @param role
	 * @return
	 */
	public Response<Boolean> addDept(Role role);
	
	/**
	 * 修改部门、岗位
	 * @param role
	 * @return
	 */
	public Response<Boolean> updateById(Role role);
	
	/**
	 * 查询部门岗位信息
	 * @param id
	 * @return
	 */
	public Response<Role> getDept(Long id);

	/**
	 * 删除部门岗位
	 * @param id
	 * @return
	 */
	public Response<Boolean> deleteById(Long id);
	
	/**
	 * 获取部门人员列表
	 * @param deptId
	 * @return
	 */
	public Response<List<RoleTeacherDTO>> getRoleTeachers(Long deptId);
	
	/**
	 * 获取用户ids
	 * @param deptId
	 * @return
	 */
	public Response<Set<Long>> getUserIds(Long deptId);
	
	/**
	 * 查询部门信息
	 * @param role
	 * @return
	 */
	public Response<List<Role>> getDept(Role role);
	
	/**
	 * 删除部门人员
	 * @param deptId
	 * @return
	 */
	public Response<Boolean> deleteRoleTeacher(Long deptId);
	
	/**
	 * 更新部门人员
	 * @param roleTeachers
	 * @return
	 */
	public Response<Boolean> insertRoleTeacher(List<RoleTeacher> roleTeachers);

	/**
	 * 删除部门人员
	 * @param id
	 */
	public Response<Boolean> deleteRoleTeacherById(Long id);
	
}
