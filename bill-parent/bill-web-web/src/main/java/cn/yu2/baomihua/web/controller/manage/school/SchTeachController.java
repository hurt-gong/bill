package cn.yu2.baomihua.web.controller.manage.school;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.framework.controller.AjaxResult;
import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.annotation.Permission;
import cn.yu2.baomihua.constant.PermiConstant;
import cn.yu2.baomihua.manage.dto.BaseCodeDTO;
import cn.yu2.baomihua.manage.dto.UserDTO;
import cn.yu2.baomihua.manage.entity.Grade;
import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.manage.enums.KlassTeacherTypeEnum;
import cn.yu2.baomihua.manage.enums.StatusEnum;
import cn.yu2.baomihua.manage.enums.UserTypeEnum;
import cn.yu2.baomihua.manage.module.ITeachModule;
import cn.yu2.baomihua.web.controller.BaseController;

/**
 * 校任教管理
 * @author lizhixiao
 * @version 1.0 
 * @date 2016-06-28
 */
@Controller
@RequestMapping("/manage/school/teach/")
public class SchTeachController extends BaseController {

	@Resource(name = "teachModuleImpl")
	private ITeachModule teachModuleImpl;


	/**
	 * 任课教师管理
	 */
	@Permission(PermiConstant.SCHOOL_TEACH)
	@RequestMapping("teacher")
	public String teacher( Model model, @RequestParam(value = "phaseId", required = false) Long phaseId,
			@RequestParam(value = "gradeId", required = false) Long gradeId,
			@RequestParam(value = "subjectId", required = false) Long subjectId ) {
		BaseCodeDTO baseCode = teachModuleImpl.getBaseCode(StatusEnum.ON.key(), getCurrentSchoolId()).getBody();
		if ( phaseId == null && baseCode.getPhaseList().size() > 0 ) {
			phaseId = baseCode.getPhaseList().get(0).getId();
		}
		if ( gradeId == null && baseCode.getGradeList().size() > 0 ) {
			gradeId = baseCode.getGradeList().get(0).getId();
		}
		if ( subjectId == null && baseCode.getSubjectList().size() > 0 ) {
			subjectId = baseCode.getSubjectList().get(0).getId();
		}
		Map<String, Object> paramMap = new HashMap<String, Object>(3);
		paramMap.put("phaseId", phaseId);
		paramMap.put("gradeId", gradeId);
		paramMap.put("subjectId", subjectId);
		paramMap.put("schoolId", getCurrentSchoolId());
		paramMap.put("type", KlassTeacherTypeEnum.TEACHER.key());
		Page<UserDTO> page = getPage(15);
		Page<UserDTO> userDto = teachModuleImpl.getTeachTeacher(page, paramMap).getBody();
		model.addAttribute("paramMap", paramMap);
		model.addAttribute("baseCode", baseCode);
		model.addAttribute("userDto", userDto);
		return "manage/school/teach/teacher";
	}


	/**
	 * 任课教师管理
	 */
	@Permission(PermiConstant.SCHOOL_TEACH)
	@RequestMapping("teacherByName")
	public String teacherByName( Model model, @RequestParam("name") String name ) {
		if ( null == name || "".equals(name.trim()) ) {
			return redirectTo("/manage/school/teach/teacher.html");
		}
		BaseCodeDTO baseCode = teachModuleImpl.getBaseCode(StatusEnum.ON.key(), getCurrentSchoolId()).getBody();
		User user = new User();
		user.setType(UserTypeEnum.TEACHER.key());
		user.setStatus(StatusEnum.ON.key());
		user.setSchoolId(getCurrentSchoolId());
		user.setName(name);
		Page<UserDTO> page = getPage(15);
		Map<String, Object> paramMap = new HashMap<String, Object>(3);
		paramMap.put("name", name);
		Page<UserDTO> userDto = teachModuleImpl.getTeacher(page, paramMap, user).getBody();
		model.addAttribute("name", name);
		model.addAttribute("baseCode", baseCode);
		model.addAttribute("userDto", userDto);
		return "manage/school/teach/teacher";
	}


	@Permission(PermiConstant.SCHOOL_TEACH)
	@RequestMapping("headTeacherByName")
	public String headTeacherByName( Model model, @RequestParam("name") String name ) {
		if ( null == name || "".equals(name.trim()) ) {
			return redirectTo("/manage/school/teach/headTeacher.html");
		}
		BaseCodeDTO baseCode = teachModuleImpl.getBaseCode(StatusEnum.ON.key(), getCurrentSchoolId()).getBody();
		User user = new User();
		user.setType(UserTypeEnum.TEACHER.key());
		user.setStatus(StatusEnum.ON.key());
		user.setSchoolId(getCurrentSchoolId());
		user.setName(name);
		Page<UserDTO> page = getPage(15);
		Map<String, Object> paramMap = new HashMap<String, Object>(3);
		paramMap.put("name", name);
		Page<UserDTO> userDto = teachModuleImpl.getTeacher(page, paramMap, user).getBody();
		model.addAttribute("name", name);
		model.addAttribute("userDto", userDto);
		model.addAttribute("baseCode", baseCode);
		return "manage/school/teach/head_teacher";
	}


	/**
	 * 班主任主页面
	 */
	@Permission(PermiConstant.SCHOOL_TEACH)
	@RequestMapping("headTeacher")
	public String courseManage( Model model, @RequestParam(value = "phaseId", required = false) Long phaseId,
			@RequestParam(value = "gradeId", required = false) Long gradeId ) {

		BaseCodeDTO baseCode = teachModuleImpl.getBaseCode(StatusEnum.ON.key(), getCurrentSchoolId()).getBody();
		if ( phaseId == null && baseCode.getPhaseList().size() > 0 ) {
			phaseId = baseCode.getPhaseList().get(0).getId();
		}
		if ( gradeId == null && baseCode.getGradeList().size() > 0 ) {
			gradeId = baseCode.getGradeList().get(0).getId();
		}
		Map<String, Object> paramMap = new HashMap<String, Object>(3);
		paramMap.put("phaseId", phaseId);
		paramMap.put("gradeId", gradeId);
		paramMap.put("schoolId", getCurrentSchoolId());
		paramMap.put("type", KlassTeacherTypeEnum.HEADMASTER.key());
		Page<UserDTO> page = getPage(15);
		Page<UserDTO> userDto = teachModuleImpl.getTeachTeacher(page, paramMap).getBody();
		model.addAttribute("paramMap", paramMap);
		model.addAttribute("baseCode", baseCode);
		model.addAttribute("userDto", userDto);
		return "manage/school/teach/head_teacher";
	}


	/**
	 * 班级列表
	 */
	@ResponseBody
	@RequestMapping("getGradeList")
	public String getGradeList( Model model, Long phaseId ) {
		List<Grade> gradeList = teachModuleImpl.getGradeList(phaseId, StatusEnum.ON.key()).getBody();
		model.addAttribute("gradeList", gradeList);
		String html = mail.getHtmltext("/manage/school/teach/grade_ajax.vm", model);
		return callbackSuccess(new AjaxResult(html));
	}
}
