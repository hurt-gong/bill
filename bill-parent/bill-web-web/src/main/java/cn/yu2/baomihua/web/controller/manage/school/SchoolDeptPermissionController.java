package cn.yu2.baomihua.web.controller.manage.school;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.yu2.baomihua.constant.PermiConstant;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.dto.RoleDTO;
import cn.yu2.baomihua.manage.entity.Permission;
import cn.yu2.baomihua.manage.entity.Role;
import cn.yu2.baomihua.manage.enums.RoleTypeEnum;
import cn.yu2.baomihua.manage.module.IDeptModule;
import cn.yu2.baomihua.manage.module.IPermissionModule;
import cn.yu2.baomihua.web.controller.BaseController;

@Controller
@RequestMapping("/manage/school/permission/")
public class SchoolDeptPermissionController extends BaseController {
	
	@Autowired
	private IDeptModule deptModule; 
	
	@Autowired
	private IPermissionModule permissionModule; 
	
	@cn.yu2.baomihua.annotation.Permission(PermiConstant.SCHOOL_PERMI)
	@RequestMapping("list")
	public String list(Model model,Long deptId,Long deptPId){
		List<RoleDTO> roleDTOs = null; 
		List<Role> roles = deptModule.getDepts(getCurrentSchoolId(), RoleTypeEnum.DEPT.key()).getBody();
		if(roles != null && !roles.isEmpty()) {
			roleDTOs = new ArrayList<>();
			for(Role role : roles) {
				RoleDTO dto = new RoleDTO();
				BeanUtils.copyProperties(role, dto);
				List<Role> childrens = deptModule.getChildDepts(role.getId()).getBody();
				dto.setChildrens(childrens);
				roleDTOs.add(dto);
			}
		}
		if((deptId==null || deptId==0L) && null!=roleDTOs && roleDTOs.get(0).getChildrens().size()>0){
			deptId=roleDTOs.get(0).getChildrens().get(0).getId();
		}
		if((deptPId==null || deptPId==0L) && null!=roleDTOs){
			deptPId=roleDTOs.get(0).getId();
		}
		//查询权限值
		//1,拿部门ID查所有权限 GL_ROLE_PERMISSION
		List<String> permissionCodeList=permissionModule.selectPermissionCode(deptId);
		//2，根据permissionCode 去查GL_PERMISSION
		if(permissionCodeList!=null && permissionCodeList.size()>0){
			List<Permission> permissionList=permissionModule.selectPermission(permissionCodeList);
			model.addAttribute("permissionList", permissionList);
		}
		//3,查询所有权限
		List<Permission> permissionAllList=permissionModule.selectPermissionAllByType(false);
		model.addAttribute("permissionAllList", permissionAllList);
		model.addAttribute("roleDTOs", roleDTOs);
		model.addAttribute("deptId", deptId);
		model.addAttribute("deptPId", deptPId);
		return "manage/school/permission/deptList";
	}
	/**
	 * 为部门配置权限
	 * @param perCode
	 * @param deptId
	 * @param deptPId
	 * @return
	 */
	@cn.yu2.baomihua.annotation.Permission(PermiConstant.SCHOOL_PERMI)
	@RequestMapping("addPermissions")
	public String addPermissions(@RequestParam(required=false,value="perCode")List<String> perCode ,@RequestParam("deptId")Long deptId,@RequestParam("deptPId")Long deptPId){
		if(perCode != null){
			Response<Boolean> num=permissionModule.addPermissions(perCode,deptId);			
		}else{
			permissionModule.deletePermissionByRoleId(deptId);
		}
		return redirectTo("list.html?deptId="+deptId+"&deptPId="+deptPId);
	}
	

}

