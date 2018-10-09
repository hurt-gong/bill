package cn.yu2.baomihua.web.controller.manage.distric;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yu2.baomihua.annotation.Permission;
import cn.yu2.baomihua.constant.PermiConstant;
import cn.yu2.baomihua.manage.dto.ChartDto;
import cn.yu2.baomihua.manage.entity.School;
import cn.yu2.baomihua.manage.enums.AppEnum;
import cn.yu2.baomihua.manage.module.ISchoolModule;
import cn.yu2.baomihua.manage.module.IStatisticsModule;
import cn.yu2.baomihua.util.DateUtils;
import cn.yu2.baomihua.web.controller.BaseController;

/*
 * 资源使用统计
 * 
 * @author lizhixiao
 * 
 * @date 2016-10-29
 */

@Controller
@RequestMapping("/manage/distric/statistics/")
public class StatisticsController extends BaseController {

	@Autowired
	private IStatisticsModule statisticsModuleImpl;

	@Autowired
	private ISchoolModule schoolModuleImpl;


	/**
	 * 获取登录统计数据
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param sel1		查询条件(1按学校,2按应用)
	 * @param sel2		二级查询条件
	 * @param sort 		排序字段(指标)
	 */
	@Permission(PermiConstant.DISTRIC_STATISTICS)
	@RequestMapping("/listResource")
	public String ListResource( Model model,
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam(value = "sel1", defaultValue = "1", required = false) Integer sel1,
			@RequestParam(value = "sel2", required = false) Long sel2,
			@RequestParam(value = "sort", defaultValue = "1", required = false) Integer sort ) {
		if ( null == startDate ) startDate = DateUtils.addCalendarDate(-7);
		if ( null == endDate ) endDate = DateUtils.addCalendarDate(-1);
		String _sort = getSortVal(sel1, sel2, sort); //获取排序
		if ( sel1 == AppEnum.SelType.SELECT_SCHOOL.key() ) {
			List<School> schoolList = schoolModuleImpl.getSchoolList();
			model.addAttribute("schoolList", schoolList);
			Map<Integer, String> LoginQuota = AppEnum.getEnumMap(AppEnum.LoginQuota.class);
			model.addAttribute("selectQuota", LoginQuota);
			model.addAttribute("loginStatistics",
				statisticsModuleImpl.getLoginCount(startDate, endDate, sel2, _sort).getBody());
		} else if ( sel1 == AppEnum.SelType.SELECT_APP.key() ) {
			if ( null == sel2 ) sel2 = 0L;
			Map<Integer, String> appTypeMap = AppEnum.getEnumMap(AppEnum.AppType.class);
			model.addAttribute("appTypeMap", appTypeMap);
			if ( AppEnum.AppType.HOMEWORK_SYSTEM.key() == sel2 ) {
				Map<Integer, String> homeworkMap = AppEnum.getEnumMap(AppEnum.HomworkSystem.class);
				model.addAttribute("selectQuota", homeworkMap);
				model.addAttribute("homeworkStatistics",
					statisticsModuleImpl.getHomeworkQty(startDate, endDate, _sort).getBody());
			} else if ( AppEnum.AppType.STUDY_TASK.key() == sel2 ) {
				Map<Integer, String> studyTaskMap = AppEnum.getEnumMap(AppEnum.StudyTask.class);
				model.addAttribute("selectQuota", studyTaskMap);
				model.addAttribute("taskStatistics",
					statisticsModuleImpl.getTaskQty(startDate, endDate, _sort).getBody());
			} else {
				Map<Integer, String> publicCourseMap = AppEnum.getEnumMap(AppEnum.PublicCource.class);
				model.addAttribute("selectQuota", publicCourseMap);
				model.addAttribute("videoStatistics",
					statisticsModuleImpl.getVideoCount(startDate, endDate, _sort).getBody());
			}
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		paramMap.put("sel1", sel1);
		paramMap.put("sel2", sel2);
		paramMap.put("sort", sort);
		model.addAttribute("paramMap", paramMap);

		return "manage/distric/statistics/listResource";
	}


	/**
	 * 获取登录统计图表
	 */
	@ResponseBody
	@RequestMapping("getLoginStaChart")
	public String getLoginStaChart(
			@RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam("sel1") Integer sel1,
			@RequestParam(value = "sel2", defaultValue = "0") Long sel2,
			@RequestParam("sort") Integer sort ) {
		if ( null == startDate ) startDate = DateUtils.addCalendarDate(-7);
		if ( null == endDate ) endDate = DateUtils.addCalendarDate(-1);
		String _sort = getSortVal(sel1, sel2, sort);
		ChartDto chartDto = null;
		if ( sel1 == AppEnum.SelType.SELECT_SCHOOL.key() ) {
			chartDto = statisticsModuleImpl.getLoginCountChart(startDate, endDate, sel2, _sort).getBody();
		} else {
			if ( AppEnum.AppType.HOMEWORK_SYSTEM.key() == sel2 ) {
				chartDto = statisticsModuleImpl.getHomeworkQtyChart(startDate, endDate, _sort).getBody();
			} else if ( AppEnum.AppType.STUDY_TASK.key() == sel2 ) {
				chartDto = statisticsModuleImpl.getTaskQtyChart(startDate, endDate, _sort).getBody();
			} else {
				chartDto = statisticsModuleImpl.getVideoCountChart(startDate, endDate, _sort).getBody();
			}
		}
		return callbackSuccess(chartDto);
	}


	/**
	 * 获取学校列表
	 * @return ajax school
	 */
	@ResponseBody
	@RequestMapping("getSchoolList")
	public String getSchoolList( Model model ) {
		model.addAttribute("school", schoolModuleImpl.getSchoolList());
		String html = mail.getHtmltext("manage/distric/statistics/ajax_getSchool.vm", model);
		return callbackSuccess(html);
	}


	private String getSortVal( int sel1, Long sel2, int sort ) {
		if ( sel1 == AppEnum.SelType.SELECT_SCHOOL.key() ) {
			if ( sort == AppEnum.LoginQuota.LOGIN_IP.key() ) {
				return "loginIp";
			} else if ( sort == AppEnum.LoginQuota.LOGIN_COUNT.key() ) {
				return "loginCount";
			} else {
				return "pageView";
			}
		} else {
			if ( null == sel2 ) sel2 = 0L;
			if ( AppEnum.AppType.STUDY_TASK.key() == sel2 ) {
				if ( sort == AppEnum.StudyTask.PUBLISH_TASK.key() ) {
					return "publishTaskQty";
				} else {
					return "finishStudentQty";
				}
			} else if ( AppEnum.AppType.HOMEWORK_SYSTEM.key() == sel2 ) {
				if ( sort == AppEnum.HomworkSystem.HOMEWORK_UPLOAD_QTY.key() ) {
					return "homwkUploadQty";
				} else {
					return "homwkFinishQty";
				}
			} else {
				if ( sort == AppEnum.PublicCource.VIDEO_COMMENT_QTY.key() ) {
					return "videoCommentQty";
				} else if ( sort == AppEnum.PublicCource.VIDEO_UPLOAD_QTY.key() ) {
					return "videoUploadQty";
				} else {
					return "videoDibbleQty";
				}
			}
		}
	}
}
