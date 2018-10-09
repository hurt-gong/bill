package cn.yu2.baomihua.web.controller.manage.school;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.framework.controller.AjaxResult;

import cn.yu2.baomihua.annotation.Permission;
import cn.yu2.baomihua.constant.ManageConstant;
import cn.yu2.baomihua.constant.PermiConstant;
import cn.yu2.baomihua.manage.dto.GradeSubjectDTO;
import cn.yu2.baomihua.manage.entity.GradeSubject;
import cn.yu2.baomihua.manage.entity.Subject;
import cn.yu2.baomihua.manage.enums.StatusEnum;
import cn.yu2.baomihua.manage.enums.SubjectTypeEnum;
import cn.yu2.baomihua.manage.module.ITeachModule;
import cn.yu2.baomihua.web.controller.BaseController;

/**
 * 校课程管理
 * @author lizhixiao
 * @version 1.0 
 * @date 2016-06-22
 */
@Controller
@RequestMapping("/manage/school/course/")
public class CourseController extends BaseController {

	//默认数据状态
	@Resource(name = "teachModuleImpl")
	private ITeachModule teachModuleImpl;


	/**
	 * 课程管理主页面
	 */
	@Permission(PermiConstant.SCHOOL_COURSE)
	@RequestMapping("list")
	public String list( Model model ) {
		Subject baseSubject = new Subject();
		baseSubject.setSchoolId(ManageConstant.DISTRIC_SCHOOLID);
		baseSubject.setStatus(StatusEnum.ON.key());
		List<Subject> baseSubjectList = teachModuleImpl.getSubjectList(baseSubject).getBody();
		Subject customSubject = new Subject();
		customSubject.setType(SubjectTypeEnum.CUSTOM.key());
		customSubject.setSchoolId(getCurrentSchoolId());
		customSubject.setStatus(StatusEnum.ON.key());
		List<Subject> customSubjectList = teachModuleImpl.getSubjectList(customSubject).getBody();
		model.addAttribute("baseSubjectList", baseSubjectList);
		model.addAttribute("customSubjectList", customSubjectList);
		return "manage/school/course/list";

	}


	/**
	 * 年级配课主页面
	 */
	@Permission(PermiConstant.SCHOOL_COURSE)
	@RequestMapping("gradeSubject")
	public String gradeCourse( Model model ) {
		List<GradeSubjectDTO> graSubDto = teachModuleImpl.getGradeSubject(getCurrentSchoolId()).getBody();
		model.addAttribute("graSubDto", graSubDto);
		return "manage/school/course/gradeSubject";

	}


	/**添加学科*/
	@ResponseBody
	@RequestMapping("addSubject")
	public String addSubject( Model model, String name ) {
		Subject subject = new Subject();
		subject.setName(name);
		subject.setSchoolId(getCurrentSchoolId());
		return callbackSuccess(teachModuleImpl.addSubject(subject).getBody());
	}


	/**修改学科*/
	@ResponseBody
	@RequestMapping("updateSubject")
	public String updateSubject( Model model, Long id, String name ) {
		return callbackSuccess(teachModuleImpl.updateSubject(id, name).getBody());
	}


	/**删除学科*/
	@ResponseBody
	@RequestMapping("delSubject")
	public String delSubject( Model model, Long id ) {
		return callbackSuccess(teachModuleImpl.delSubject(id).getBody());
	}


	/**查询未选择的学科*/
	@ResponseBody
	@RequestMapping("getSubjectNoSel")
	public String getSubjectNoSel( Model model, Long gradeId ) {
		List<Subject> subjectList = teachModuleImpl.getSubjectNoSel(gradeId, StatusEnum.ON.key(), getCurrentSchoolId())
				.getBody();
		model.addAttribute("subjectList", subjectList);
		String html = mail.getHtmltext("manage/distric/teach/subject_ajax.vm", model);
		return callbackSuccess(new AjaxResult(html));
	}


	/**添加年级配课*/
	@ResponseBody
	@RequestMapping("addGradeSubject")
	public String addGradeSubject( Model model, Long gradeId, Long subjectId ) {
		GradeSubject graSub = new GradeSubject();
		graSub.setGradeId(gradeId);
		graSub.setSubjectId(subjectId);
		graSub.setSchoolId(getCurrentSchoolId());
		return callbackSuccess(teachModuleImpl.addGradeSubject(graSub).getBody());
	}


	/**年级配课*/
	@ResponseBody
	@RequestMapping("delGradeSubject")
	public String delGradeSubject( Model model, Long gradeId, Long subjectId ) {
		GradeSubject graSub = new GradeSubject();
		graSub.setGradeId(gradeId);
		graSub.setSubjectId(subjectId);
		graSub.setSchoolId(getCurrentSchoolId());
		return callbackSuccess(teachModuleImpl.deleteGradeSubject(graSub).getBody());
	}
}
