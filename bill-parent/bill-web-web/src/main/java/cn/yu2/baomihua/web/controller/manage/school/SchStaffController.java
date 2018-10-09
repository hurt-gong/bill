package cn.yu2.baomihua.web.controller.manage.school;


import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.framework.controller.AjaxResult;
import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.annotation.Permission;
import cn.yu2.baomihua.constant.ManageConstant;
import cn.yu2.baomihua.constant.PermiConstant;
import cn.yu2.baomihua.core.common.util.POIUtil;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.dto.BaseCodeDTO;
import cn.yu2.baomihua.manage.dto.ResponseDTO;
import cn.yu2.baomihua.manage.dto.UserDTO;
import cn.yu2.baomihua.manage.entity.Dict;
import cn.yu2.baomihua.manage.entity.Klass;
import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.manage.enums.DictTypeEnum;
import cn.yu2.baomihua.manage.enums.StatusEnum;
import cn.yu2.baomihua.manage.enums.UserTypeEnum;
import cn.yu2.baomihua.manage.module.IStaffModule;
import cn.yu2.baomihua.web.controller.BaseController;

/**
 * 校人员管理
 * @author lizhixiao
 * @version 1.0 
 * @date 2016-06-22
 */
@Controller
@RequestMapping("/manage/school/staff/")
public class SchStaffController extends BaseController {

	@Resource(name = "staffModuleImpl")
	private IStaffModule staffModuleImpl;


	/**
	 * 学生管理主页面
	 */
	@Permission(PermiConstant.SCHOOL_USER)
	@RequestMapping("studentManage")
	public String studentManage( Model model, @RequestParam(value = "gradeId", required = false) Long gradeId,
			@RequestParam(value = "klassId", required = false) Long klassId,
			@RequestParam(value = "name", required = false) String name ) {
		Map<String, Object> paramMap = new HashMap<String, Object>(3);
		paramMap.put("gradeId", gradeId);
		paramMap.put("klassId", klassId);
		paramMap.put("name", name);
		BaseCodeDTO baseCode = staffModuleImpl.getBaseCode(gradeId, getCurrentSchoolId()).getBody();
		Page<UserDTO> page = getPage(15);
		User user = new User();
		user.setType(UserTypeEnum.STUDENT.key());
		user.setStatus(StatusEnum.ON.key());
		user.setSchoolId(getCurrentSchoolId());
		Page<UserDTO> studentList = staffModuleImpl.getStudentList(page, paramMap, user).getBody();
		model.addAttribute("paramMap", paramMap);
		model.addAttribute("baseCode", baseCode);
		model.addAttribute("studentList", studentList);
		return "manage/school/staff/studentManage";
	}


	/**
	 * 教师管理主页面
	 */
	@Permission(PermiConstant.SCHOOL_USER)
	@RequestMapping("teacherManage")
	public String teacherManage( Model model, @RequestParam(value = "positionId", required = false) Long positionId,
			@RequestParam(value = "name", required = false) String name ) {
		if ( "".equals(name) ) {
			name = null;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>(3);
		paramMap.put("positionId", positionId);
		paramMap.put("name", name);
		User user = new User();
		user.setType(UserTypeEnum.TEACHER.key());
		user.setStatus(StatusEnum.ON.key());
		user.setSchoolId(getCurrentSchoolId());
		Page<UserDTO> page = getPage(15);
		Page<UserDTO> teacherList = staffModuleImpl.getTeacherList(page, paramMap, user).getBody();
		List<Dict> dictList = staffModuleImpl.getDictList(DictTypeEnum.POSTION.key()).getBody();
		model.addAttribute("dictList", dictList);
		model.addAttribute("paramMap", paramMap);
		model.addAttribute("teacherList", teacherList);
		return "manage/school/staff/teacherManage";
	}


	/**
	 * 班级列表
	 */
	@ResponseBody
	@RequestMapping("getKlassList")
	public String getKlassList( Model model, Long gradeId ) {
		List<Klass> klassList = staffModuleImpl.getKlassList(gradeId, getCurrentSchoolId()).getBody();
		model.addAttribute("klassList", klassList);
		String html = mail.getHtmltext("/manage/school/staff/student_klass.vm", model);
		return callbackSuccess(new AjaxResult(html));
	}


	/**学生详细信息*/
	@ResponseBody
	@RequestMapping("getStudentDtail")
	public String getStudentDtail( Model model, Long studentId ) {
		UserDTO userDto = staffModuleImpl.getUserDTO(studentId).getBody();
		model.addAttribute("userDto", userDto);
		String html = mail.getHtmltext("/manage/school/staff/student_detail_ajax.vm", model);
		return callbackSuccess(new AjaxResult(html));
	}


	/**重置密码*/
	@ResponseBody
	@RequestMapping("resetPass")
	public String resetPass( Model model, Long id ) {
		Response<Boolean> flag = staffModuleImpl.resetPass(id);
		return callbackSuccess(flag.getBody());
	}


	/**
	 * 学生导出
	 */
	@Permission(PermiConstant.SCHOOL_USER)
	@RequestMapping("exportStudent")
	public String exportStudent( HttpServletRequest request, HttpServletResponse response,
			@RequestParam("klassId") Long klassId, @RequestParam("name") String name ) {
		response = POIUtil.excelResponse(request, response, ManageConstant.SCHOOL_STUDENT_EXCEL);
		try {
			HSSFWorkbook workbook = POIUtil.createWorkbook();
			HSSFSheet sheet = POIUtil.createSheet(workbook, ManageConstant.SCHOOL_STUDENT_EXCEL,
				ManageConstant.SCHOOL_STUDENT_EXCEL_TITLES);
			User user = new User();
			user.setType(UserTypeEnum.STUDENT.key());
			user.setStatus(StatusEnum.ON.key());
			user.setSchoolId(getCurrentSchoolId());

			Map<String, Object> paramMap = new HashMap<String, Object>(2);
			paramMap.put("klassId", klassId);
			paramMap.put("name", name);
			List<UserDTO> studentList = staffModuleImpl.getStudentList(paramMap, user).getBody();
			writeStaffWithExcel(sheet, studentList);
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			workbook.close();
			out.close();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return null;
	}


	/**人员信息写入excel*/
	private ResponseDTO writeStaffWithExcel( HSSFSheet sheet, List<UserDTO> studentList ) {
		Integer row = 1;
		for ( UserDTO student : studentList ) {
			Map<Integer, String> map = new HashMap<Integer, String>();
			map.put(0, row.toString());
			map.put(1, null == student.getStudentCode() ? "" : student.getStudentCode());
			map.put(2, null == student.getName() ? "" : student.getName());
			map.put(3, null == student.getGender() ? "" : (1 == student.getGender() ? "男" : "女"));
			map.put(4, null == student.getGradeName() ? "" : student.getGradeName());
			map.put(5, null == student.getKlassName() ? "" : student.getKlassName());
			map.put(6, null == student.getNation() ? "" : student.getNation());
			map.put(7, null == student.getParent()
				? "" : (null == student.getParent().getName() ? "" : student.getParent().getName()));
			map.put(8, null == student.getParent()
				? "" : (null == student.getParent().getMobile() ? "" : student.getParent().getMobile()));
			for ( Entry<Integer, String> entry : map.entrySet() ) {
				POIUtil.writeCellValue(sheet, row, entry.getKey(), String.valueOf(entry.getValue()));
			}
			row++;
		}
		return null;
	}
}