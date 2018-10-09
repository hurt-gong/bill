package cn.yu2.baomihua.web.controller.manage.school;

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
import cn.yu2.baomihua.manage.dto.GradeSubjectDTO;
import cn.yu2.baomihua.manage.entity.LoginStatistics;
import cn.yu2.baomihua.manage.enums.AppEnum;
import cn.yu2.baomihua.manage.enums.TaskTypeEnum;
import cn.yu2.baomihua.manage.enums.AppEnum.LoginQuota;
import cn.yu2.baomihua.manage.enums.AppEnum.SchoolSttQuota;
import cn.yu2.baomihua.manage.enums.AppEnum.StatisticsType;
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

@Controller(value = "scStaController")
@RequestMapping("/manage/school/statistics/")
public class StatisticsController extends BaseController {

	@Autowired
	private IStatisticsModule statisticsModuleImpl;


	/**
	 * 获取登录统计数据
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param selType	查询条件(1学习任务,2基本统计)
	 * @param phaseId	学段
	 * @param gradeId 	年级
	 * @param subjectId 学科
	 * @param sort 		排序字段(指标)
	
	@Permission(PermiConstant.SCHOOL_STATISTICS)
	@RequestMapping("/listResource")
	public String ListResource( Model model,
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam(value = "selType", required = false, defaultValue = "1") Integer selType,
			@RequestParam(value = "gradeId", required = false) Long gradeId,
			@RequestParam(value = "subjectId", required = false) Long subjectId,
			@RequestParam(value = "sort", defaultValue = "1", required = false) Integer sort ) {
		if ( null == startDate ) startDate = DateUtils.addCalendarDate(-7);
		if ( null == endDate ) endDate = DateUtils.addCalendarDate(-1);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		paramMap.put("selType", selType);
		paramMap.put("gradeId", gradeId);
		paramMap.put("subjectId", subjectId);
		paramMap.put("sort", sort);
		model.addAttribute("paramMap", paramMap);
		GradeSubjectDTO gradeSubject = statisticsModuleImpl.getGradeSubjectByGradeId(getCurrentSchoolId(), gradeId)
				.getBody();
		String _sort = getSortVal(selType, sort);
		if ( StatisticsType.STADY_TASK.key() == selType ) {
			model.addAttribute("SchoolSttQuota", AppEnum.getEnumMap(SchoolSttQuota.class));
			if ( sort == SchoolSttQuota.VIDEO_DIBBLE_QTY.key()
					|| sort == AppEnum.SchoolSttQuota.VIDEO_COMMENT_QTY.key() ) {
				model.addAttribute("videoCount",
					statisticsModuleImpl
							.getSchoolVideoCount(startDate, endDate, getCurrentSchoolId(), gradeId, subjectId, _sort)
							.getBody());
			} else if ( sort == AppEnum.SchoolSttQuota.HOMEWORK_FINISH_QTY.key()
					|| sort == AppEnum.SchoolSttQuota.HOMEWORK_UPLOAD_QTY.key() ) {
				boolean groupByteacherId = false;
				if ( sort == AppEnum.SchoolSttQuota.HOMEWORK_UPLOAD_QTY.key() ) {
					groupByteacherId = true;
				}
				model.addAttribute("homwkSta",
					statisticsModuleImpl.getSchoolHomwkAndTask(startDate, endDate, TaskTypeEnum.HOMEWORK.key(),
						getCurrentSchoolId(), gradeId, subjectId, _sort, groupByteacherId).getBody());
			} else {
				_sort = "homwkFinishQty";
				model.addAttribute("homwkSta", statisticsModuleImpl.getSchoolHomwkAndTask(startDate, endDate, null,
					getCurrentSchoolId(), gradeId, subjectId, _sort, false).getBody());
			}
		} else {
			List<LoginStatistics> loginStatistics = statisticsModuleImpl
					.getSchoolLoginCount(startDate, endDate, getCurrentSchoolId(), gradeId, _sort).getBody();
			model.addAttribute("loginStatistics", loginStatistics);
		}
		model.addAttribute("gradeSubject", gradeSubject);
		return "manage/school/statistics/listResource";
	}
 */

	/**
	 * 获取登录统计图表
	 */
	@ResponseBody
	@RequestMapping("getLoginStaChart")
	public String getLoginStaChart(
			@RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam(value = "selType", required = false, defaultValue = "1") Integer selType,
			@RequestParam(value = "gradeId", required = false) Long gradeId,
			@RequestParam(value = "subjectId", required = false) Long subjectId,
			@RequestParam("sort") Integer sort ) {
		if ( null == startDate ) startDate = DateUtils.addCalendarDate(-7);
		if ( null == endDate ) endDate = DateUtils.addCalendarDate(-1);
		String _sort = getSortVal(selType, sort);
		ChartDto chartDto = null;
		if ( StatisticsType.STADY_TASK.key() == selType ) {
			if ( sort == SchoolSttQuota.VIDEO_DIBBLE_QTY.key()
					|| sort == AppEnum.SchoolSttQuota.VIDEO_COMMENT_QTY.key() ) {
				chartDto = statisticsModuleImpl
						.getSchoolVideoCountChart(startDate, endDate, getCurrentSchoolId(), gradeId, subjectId, _sort)
						.getBody();
			} else if ( sort == AppEnum.SchoolSttQuota.HOMEWORK_FINISH_QTY.key()
					|| sort == AppEnum.SchoolSttQuota.HOMEWORK_UPLOAD_QTY.key() ) {
				chartDto = statisticsModuleImpl.getSchoolHomwkAndTaskChart(startDate, endDate,
					TaskTypeEnum.HOMEWORK.key(), getCurrentSchoolId(), gradeId, subjectId, _sort).getBody();
			} else {
				_sort = "homwkFinishQty";
				chartDto = statisticsModuleImpl.getSchoolHomwkAndTaskChart(startDate, endDate, null,
					getCurrentSchoolId(), gradeId, subjectId, _sort).getBody();
			}
		} else {
			chartDto = statisticsModuleImpl
					.getSchoolLoginCountChart(startDate, endDate, getCurrentSchoolId(), gradeId, _sort).getBody();
		}

		return callbackSuccess(chartDto);
	}


	private String getSortVal( int sel1, int sort ) {
		if ( sel1 == AppEnum.StatisticsType.BASE_STATISTICS.key() ) {
			if ( sort == LoginQuota.LOGIN_COUNT.key() ) {
				return "loginCount";
			} else {
				return "pageView";
			}
		} else {
			if ( sort == SchoolSttQuota.VIDEO_DIBBLE_QTY.key() ) {
				return "videoDibbleQty";
			} else if ( sort == AppEnum.SchoolSttQuota.VIDEO_COMMENT_QTY.key() ) {
				return "videoCommentQty";
			} else if ( sort == AppEnum.SchoolSttQuota.HOMEWORK_FINISH_QTY.key() ) {
				return "homwkFinishQty";
			} else if ( sort == AppEnum.SchoolSttQuota.HOMEWORK_UPLOAD_QTY.key() ) {
				return "homwkFinishQty";
			} else {
				return "finishTaskQty";
			}
		}
	}
}
