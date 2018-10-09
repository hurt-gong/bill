package cn.yu2.baomihua.web.controller.manage.distric;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yu2.baomihua.annotation.Permission;
import cn.yu2.baomihua.constant.PermiConstant;
import cn.yu2.baomihua.manage.dto.RoleTeacherDTO;
import cn.yu2.baomihua.manage.entity.Role;
import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.manage.enums.RoleTypeEnum;
import cn.yu2.baomihua.manage.module.IDeptModule;
import cn.yu2.baomihua.manage.module.IUserModule;
import cn.yu2.baomihua.web.controller.BaseController;

@Controller
@RequestMapping("/manage/distric/role/")
public class RoleController extends BaseController {

	@Autowired
	private IDeptModule deptModule;
	@Autowired
	private IUserModule userModule;

	/**
	 * 角色名称列表
	 * 
	 * @return
	 */
	@Permission(PermiConstant.DISTRIC_DEPT)
	@RequestMapping("list")
	public String list(Model model, Long id) {
		Long checkedRoleId = null;
		List<RoleTeacherDTO> roleTeachers = null;
		List<Role> roles = deptModule.getDepts(getCurrentSchoolId(), RoleTypeEnum.ROLE.key()).getBody();
		if (roles!=null && roles.size()>0 && id == null) {
			Role firstRole = roles.get(0);
			checkedRoleId = firstRole.getId();
			roleTeachers = deptModule.getRoleTeachers(checkedRoleId).getBody();
		} else {
			checkedRoleId = id;
			roleTeachers = deptModule.getRoleTeachers(id).getBody();
		}
		model.addAttribute("checkedRoleId", checkedRoleId);
		model.addAttribute("roleTeachers", roleTeachers);
		model.addAttribute("roles", roles);
		return "manage/distric/role/list";
	}

	/**
	 * 添加角色名称
	 */
	@ResponseBody
	@RequestMapping("add")
	public String add(String name) {
		Role role = new Role();
		role.setType(RoleTypeEnum.ROLE.key());
		role.setName(name);
		role.setPid(0L);
		role.setSchoolId(0L);
		deptModule.addDept(role);
		
		role = deptModule.getDept(role).getBody().get(0);

		return callbackSuccess(role);
	}
	
	/**
	 * 修改角色
	 */
	@ResponseBody
	@RequestMapping("update")
	public String update(Long id,String name){
		Role role = deptModule.getDept(id).getBody();
		role.setName(name);
		return callbackSuccess(deptModule.updateById(role).getBody());
	}
	

	/**
	 * 删除角色
	 */
	@Permission(PermiConstant.DISTRIC_DEPT)
	@RequestMapping("del")
	public String del(Long id){
		deptModule.deleteById(id).getBody();
		return redirectTo("/manage/distric/role/list.html");
	}
	
	/**
	 * 删除角色人员
	 */
	@Permission(PermiConstant.DISTRIC_DEPT)
	@RequestMapping("delroleteacher")
	public String delroleteacher(Long id){
		return callbackSuccess(deptModule.deleteRoleTeacherById(id).getBody());
		
	}
	
	/**
	 * 角色人员弹出
	 */
	@ResponseBody
	@RequestMapping("users")
	public String getUsers( Model model,@RequestParam("deptId") Long deptId) {
		Map<Character, List<User>> teacherTreeMap = userModule.getTeacherTreeMap(0L).getBody();
		Set<Long> userIds = deptModule.getUserIds(deptId).getBody();
		model.addAttribute("teacherTreeMap", teacherTreeMap);
		model.addAttribute("userIds", userIds);
		model.addAttribute("deptId", deptId);
		String html = mail.getHtmltext("manage/distric/dept/ajax_user.vm", model);
		return callbackSuccess(html);
	}

}
