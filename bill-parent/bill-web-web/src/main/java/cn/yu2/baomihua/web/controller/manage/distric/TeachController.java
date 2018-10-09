package cn.yu2.baomihua.web.controller.manage.distric;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.framework.controller.AjaxResult;

import cn.yu2.baomihua.annotation.Permission;
import cn.yu2.baomihua.constant.PermiConstant;
import cn.yu2.baomihua.manage.dto.GradeSubjectDTO;
import cn.yu2.baomihua.manage.entity.Grade;
import cn.yu2.baomihua.manage.entity.GradeSubject;
import cn.yu2.baomihua.manage.entity.Phase;
import cn.yu2.baomihua.manage.entity.Subject;
import cn.yu2.baomihua.manage.enums.StatusEnum;
import cn.yu2.baomihua.manage.enums.SubjectTypeEnum;
import cn.yu2.baomihua.manage.module.ITeachModule;
import cn.yu2.baomihua.web.controller.BaseController;
  
/**
 * 区教学设置
 * @author lizhixiao
 * @version 1.0 
 * @date 2016-06-12
 */
@Controller
@RequestMapping("/manage/distric/teach/")
public class TeachController extends BaseController {

	@Resource(name = "teachModuleImpl")
	private ITeachModule teachModuleImpl;


	/**
	 * 年级设置主页面
	 */
	@Permission(PermiConstant.DISTRIC_EDU)
	@RequestMapping("list")
	public String list( Model model ) {
		List<Phase> phaseList = teachModuleImpl.listPhase(StatusEnum.ON.key()).getBody();
		model.addAttribute("phaseList", phaseList);
		if ( phaseList.size() > 0 ) {
			Grade grade = new Grade();
			grade.setPhaseId(phaseList.get(0).getId());
			grade.setStatus(StatusEnum.ON.key());
			List<Grade> gradeList = teachModuleImpl.getGradeByPhaseId(grade).getBody();
			model.addAttribute("gradeList", gradeList);
		}
		return "manage/distric/teach/list";

	}


	/**
	 * 课程管理主页面
	 */
	@Permission(PermiConstant.DISTRIC_EDU)
	@RequestMapping("courseManage")
	public String courseManage( Model model ) {
		Subject baseSubject = new Subject();
		baseSubject.setType(SubjectTypeEnum.BASE.key());
		List<Subject> baseSubjectList = teachModuleImpl.getSubjectList(baseSubject).getBody();
		Subject customSubject = new Subject();
		customSubject.setType(SubjectTypeEnum.CUSTOM.key());
		customSubject.setSchoolId(getCurrentSchoolId());
		customSubject.setStatus(StatusEnum.ON.key());
		List<Subject> customSubjectList = teachModuleImpl.getSubjectList(customSubject).getBody();
		model.addAttribute("baseSubjectList", baseSubjectList);
		model.addAttribute("customSubjectList", customSubjectList);
		return "manage/distric/teach/courseManage";

	}


	/**
	 * 年级配课主页面
	 */
	@Permission(PermiConstant.DISTRIC_EDU)
	@RequestMapping("gradeSubject")
	public String gradeCourse( Model model ) {
		List<GradeSubjectDTO> graSubDto = teachModuleImpl.getGradeSubject(getCurrentSchoolId()).getBody();
		model.addAttribute("graSubDto", graSubDto);
		return "manage/distric/teach/gradeSubject";

	}


	/**查询年级*/
	@ResponseBody
	@RequestMapping("getGradeByPhaseId")
	public String getGradeByPhaseId( Model model, Long phaseId ) {
		Grade grade = new Grade();
		grade.setPhaseId(phaseId);
		grade.setStatus(StatusEnum.ON.key());
		List<Grade> gradeList = teachModuleImpl.getGradeByPhaseId(grade).getBody();
		model.addAttribute("gradeList", gradeList);
		String html = mail.getHtmltext("manage/distric/teach/grade_ajax.vm", model);
		return callbackSuccess(new AjaxResult(html));
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


	/**批量修改学科*/
	@Permission(PermiConstant.DISTRIC_EDU)
	@RequestMapping("updateSubjectList")
	public String updateSubjectList( Model model, GradeSubjectDTO subjectDto ) {
		teachModuleImpl.updateSubjectList(subjectDto.getSubjectList()).getBody();
		return redirectTo("/manage/distric/teach/courseManage.html");
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
