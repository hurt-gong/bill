package cn.yu2.baomihua.web.controller.manage.school;

import java.util.ArrayList;
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
import cn.yu2.baomihua.manage.entity.RoleTeacher;
import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.manage.enums.RoleTypeEnum;
import cn.yu2.baomihua.manage.module.IDeptModule;
import cn.yu2.baomihua.manage.module.IUserModule;
import cn.yu2.baomihua.web.controller.BaseController;

@Controller
@RequestMapping("/manage/school/dept/")
public class SchoolDeptController extends BaseController{
	
	@Autowired
	private IDeptModule deptModule;
	
	@Autowired
	private IUserModule userModule;
	
	/**
	 * 部门列表
	 */
	@Permission(PermiConstant.SCHOOL_DEPT)
	@RequestMapping("list")
	public String list(Model model,Long id){
		Long checkedDeptId = null;
		Long checkedChildId = null;
		List<Role> childDepts = null;
		List<RoleTeacherDTO> roleTeachers = null;
		List<Role> depts =  deptModule.getDepts(getCurrentSchoolId(),RoleTypeEnum.DEPT.key()).getBody();
		
		if(id !=null) {
			Role role = deptModule.getDept(id).getBody();
			Long pid = role.getPid();
			if(pid==0L){
				checkedDeptId=id;
				childDepts = deptModule.getChildDepts(id).getBody();
				if(childDepts !=null && childDepts.size()>0) {
					checkedChildId = childDepts.get(0).getId();
				} else {
					childDepts =null;
				}
			}else {
				checkedDeptId=pid;
				checkedChildId=id;
				childDepts = deptModule.getChildDepts(pid).getBody();
			};
		} else {
			if(depts != null && depts.size() > 0){
				Role r = depts.get(0);
				checkedDeptId=r.getId();
				childDepts = deptModule.getChildDepts(r.getId()).getBody();
				if(childDepts !=null && childDepts.size()>0) {
					checkedChildId = childDepts.get(0).getId();
				} else {
					childDepts =null;
				}
			}
		}
		if(checkedChildId !=null) {
			roleTeachers = deptModule.getRoleTeachers(checkedChildId).getBody();
		}
		
		model.addAttribute("checkedDeptId", checkedDeptId);
		model.addAttribute("checkedChildId", checkedChildId);
		model.addAttribute("depts", depts);
		model.addAttribute("childDepts", childDepts);
		model.addAttribute("roleTeachers", roleTeachers);
		return "manage/school/dept/list";
		
	}
	
	/**
	 * 添加部门、岗位
	 */
	@ResponseBody
	@RequestMapping("add")
	public String add(String name,long pid) {
		Role role = new Role();
		role.setType(RoleTypeEnum.DEPT.key());
		role.setName(name);
		role.setPid(pid);
		role.setSchoolId(getCurrentSchoolId());
		role.setCrUserId(0L);
		deptModule.addDept(role);
		
		List<Role> roles = deptModule.getDept(role).getBody();
		
		if(roles !=null && !roles.isEmpty()) {
			return callbackSuccess(roles.get(0));
		}
		return callbackFail("fail");
	}
	
	/**
	 * 删除部门、岗位
	 */
	@Permission(PermiConstant.SCHOOL_DEPT)
	@RequestMapping("del")
	public String del(long id) {
		String url = "/manage/school/dept/list.html";
		Role role = deptModule.getDept(id).getBody();
		Long pid = role.getPid();
		deptModule.deleteById(id);
		if(pid != 0L){
			url = "/manage/school/dept/list.html?id="+pid;
		}
		return redirectTo(url);
	}
	
	@ResponseBody
	@RequestMapping("update")
	public String update(Long id,String name){
		
		Role role = deptModule.getDept(id).getBody();
		role.setName(name);
		return callbackSuccess(deptModule.updateById(role).getBody());
	}
	
	/**
	 * 部门人员弹出
	 */
	@ResponseBody
	@RequestMapping("users")
	public String getUsers( Model model,@RequestParam("deptId") Long deptId) {
		Map<Character, List<User>> teacherTreeMap = userModule.getTeacherTreeMap(getCurrentSchoolId()).getBody();
		Set<Long> userIds = deptModule.getUserIds(deptId).getBody();
		model.addAttribute("teacherTreeMap", teacherTreeMap);
		model.addAttribute("userIds", userIds);
		model.addAttribute("deptId", deptId);
		String html = mail.getHtmltext("manage/distric/dept/ajax_user.vm", model);
		return callbackSuccess(html);
	}
	
	/**
	 * 岗位人员修改
	 */
	@ResponseBody
	@RequestMapping("updateUsers")
	public String updateUsers(Model model,@RequestParam("deptId")Long deptId,@RequestParam(value="userIds[]", required = false) List<Long> userIds) {
		List<RoleTeacher> rts = null;
		deptModule.deleteRoleTeacher(deptId);
		if(userIds !=null && !userIds.isEmpty()) {
			rts = new ArrayList<>();
			for(Long userId : userIds) {
				RoleTeacher rt = new RoleTeacher();
				rt.setRoleId(deptId);
				rt.setTeacherId(userId);
				rt.setType(RoleTypeEnum.DEPT.key());
				rts.add(rt);
			}
			deptModule.insertRoleTeacher(rts);
		}
		List<RoleTeacherDTO> roleTeachers = deptModule.getRoleTeachers(deptId).getBody();
		model.addAttribute("roleTeachers", roleTeachers);
		String html = mail.getHtmltext("manage/distric/dept/ajax_roleteachers.vm", model);
		return callbackSuccess(html);
	}
	
	/**
	 * 删除岗位人员
	 */
	@ResponseBody
	@RequestMapping("delroleteacher")
	public String delroleteacher(Long id) {
		return callbackSuccess(deptModule.deleteRoleTeacherById(id));
	}
}
