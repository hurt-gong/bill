package cn.yu2.baomihua.web.controller.manage.distric;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.yu2.baomihua.constant.PermiConstant;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.Permission;
import cn.yu2.baomihua.manage.entity.Role;
import cn.yu2.baomihua.manage.enums.RoleTypeEnum;
import cn.yu2.baomihua.manage.module.IDeptModule;
import cn.yu2.baomihua.manage.module.IPermissionModule;
import cn.yu2.baomihua.web.controller.BaseController;

@Controller
@RequestMapping("/manage/distric/permission/user/")
public class RolePermissionController extends BaseController {
	
	@Autowired
	private IDeptModule deptModule;
	
	@Autowired
	private IPermissionModule permissionModule;
	
	@cn.yu2.baomihua.annotation.Permission(PermiConstant.DISTRIC_PERMI)
	@RequestMapping("roleList")
	public String roleList(Model model, Long roleId){
		 List<Role> roles = deptModule.getDepts(getCurrentSchoolId(), RoleTypeEnum.ROLE.key()).getBody(); 
		if((roleId == null || roleId==0L) && roles.size()>0){
			Role firstRole = roles.get(0);
			roleId  = firstRole.getId();	
		}
		/**
		 * 根据部门ID查所有权限 GL_ROLE_PERMISSION
		 */
		List<String> permissionCodeList=permissionModule.selectPermissionCode(roleId);
		
		/**
		 * 根据permissionCode 去查GL_PERMISSION
		 */
		if(permissionCodeList!=null && permissionCodeList.size()>0){
			List<Permission> permissionList=permissionModule.selectPermission(permissionCodeList);
			model.addAttribute("permissionList", permissionList);
		}
		/**
		 * 查询所有权限
		 */
		List<Permission> permissionAllList=permissionModule.selectPermissionAllByType(true);
		
		model.addAttribute("permissionAllList", permissionAllList);
		model.addAttribute("roleId", roleId);
		model.addAttribute("roles", roles);
		
		return "manage/distric/permission/user/roleList";
	}
	/**
	 * 为部门配置权限
	 * @param perCode
	 * @param deptId
	 * @param deptPId
	 * @return
	 */
	@cn.yu2.baomihua.annotation.Permission(PermiConstant.DISTRIC_PERMI)
	@RequestMapping("addRolePermissions")
	public String addPermissions(@RequestParam(required=false,value="perCode")List<String> perCode ,@RequestParam("roleId")Long roleId){
		if(perCode !=null){
			Response<Boolean> num=permissionModule.addPermissions(perCode,roleId);
			}else{
				permissionModule.deletePermissionByRoleId(roleId);
			}
		
		
		return redirectTo("roleList.html?roleId="+roleId);
	}
}
