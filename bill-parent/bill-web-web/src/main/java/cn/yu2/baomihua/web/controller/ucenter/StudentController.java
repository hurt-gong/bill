package cn.yu2.baomihua.web.controller.ucenter;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.manage.dto.StudentTaskRecordDTO;
import cn.yu2.baomihua.manage.dto.VideoPlaybackRecordDTO;
import cn.yu2.baomihua.manage.entity.School;
import cn.yu2.baomihua.manage.entity.TaskStatistics;
import cn.yu2.baomihua.manage.enums.TaskStatusEnum;
import cn.yu2.baomihua.manage.module.IKlassModule;
import cn.yu2.baomihua.manage.module.IPersonalCenterModule;
import cn.yu2.baomihua.manage.module.ISchoolModule;
import cn.yu2.baomihua.manage.module.IStatisticsModule;
import cn.yu2.baomihua.util.DateUtils;
import cn.yu2.baomihua.web.controller.BaseController;

/**
 * 学生个人中心
 * 
 * @author lizhixiao
 * @date 2016-11-04
 * @version 1.0.0
 */
@Controller
@RequestMapping("/ucenter/student")
public class StudentController extends BaseController {

	@Resource(name = "statisticsModuleImpl")
	private IStatisticsModule statisticsModuleImpl;

	@Resource(name = "personalCenterModuleImpl")
	private IPersonalCenterModule personalCenterModuleImpl;

	@Resource(name = "schoolModule")
	private ISchoolModule schoolModuleImpl;

	@Resource(name = "klassModuleImpl")
	private IKlassModule klassModuleImpl;

	/** 学生个人中心 */
	@RequestMapping("/index")
	public String stuIndex(Model model) {
		School school = schoolModuleImpl.getSchoolById(getCurrentSchoolId());
		Page<TaskStatistics> rankPage = getPage(6);
		rankPage = statisticsModuleImpl.getTaskRanking(rankPage).getBody();
		long studentId = getCurrentUserId();
		Integer finished = TaskStatusEnum.UNFINISHED.key();
		Page<StudentTaskRecordDTO> taskPage = getPage(6);
		Page<VideoPlaybackRecordDTO> videoPage = getPage(8);
		model.addAttribute("videoPlayPage",
				statisticsModuleImpl.getVideoPlayBackRecord(videoPage, getCurrentUserId()).getBody());
		model.addAttribute("school", school);
		model.addAttribute("klass", klassModuleImpl.getKlassByStudentId(getCurrentUserId()).getBody());
		model.addAttribute("taskRankingPage", rankPage);
		Page<StudentTaskRecordDTO> stuPage = personalCenterModuleImpl
				.getStudentTask(taskPage, studentId, finished, null).getBody();
		model.addAttribute("stuTaskPage", stuPage);
		model.addAttribute("finishNum",
				personalCenterModuleImpl.getTaskNum(studentId, DateUtils.getMondayOfThisWeek()).getBody());
		return "/ucenter/student/stuIndex";
	}

	/** 学习任务 */
	@RequestMapping("/task")
	public String task(Model model) {
		long studentId = getCurrentUserId();
		Integer finished = TaskStatusEnum.UNFINISHED.key();
		Page<StudentTaskRecordDTO> taskPage = getPage(6);
		Page<StudentTaskRecordDTO> stuPage = personalCenterModuleImpl
				.getStudentTask(taskPage, studentId, finished, null).getBody();
		model.addAttribute("stuTaskPage", stuPage);
		return "/ucenter/student/task";
	}

	/** 全区大排名 */
	@RequestMapping("/ranking")
	public String ranking(Model model) {
		Page<TaskStatistics> page = getPage(10);
		page = statisticsModuleImpl.getTaskRanking(page).getBody();
		model.addAttribute("taskRankingPage", page);
		return "/ucenter/student/taskRanking";
	}

	/** 学生任务 */
	@RequestMapping("/getStuTask")
	public String getStuTask(Model model,
			@RequestParam(value = "isFinished", defaultValue = "0", required = false) Integer isFinished) {
		Page<StudentTaskRecordDTO> page = getPage(10);
		long studentId = getCurrentUserId();
		model.addAttribute("stuTaskPage",
				personalCenterModuleImpl.getStudentTask(page, studentId, isFinished, null).getBody());
		return "/ucenter/student/stuTask";
	}

	/** 播放记录 */
	@RequestMapping("/playbackRecord")
	public String playbackRecord(Model model) {
		Page<VideoPlaybackRecordDTO> page = getPage(12);
		model.addAttribute("videoPlayPage",
				statisticsModuleImpl.getVideoPlayBackRecord(page, getCurrentUserId()).getBody());
		return "/ucenter/student/playBackRecord";
	}
}
