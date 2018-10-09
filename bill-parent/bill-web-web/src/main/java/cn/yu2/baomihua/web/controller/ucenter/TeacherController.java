package cn.yu2.baomihua.web.controller.ucenter;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.manage.dto.VideoPlaybackRecordDTO;
import cn.yu2.baomihua.manage.entity.School;
import cn.yu2.baomihua.manage.entity.TaskStatistics;
import cn.yu2.baomihua.manage.enums.TaskTypeEnum;
import cn.yu2.baomihua.manage.module.IPersonalCenterModule;
import cn.yu2.baomihua.manage.module.ISchoolModule;
import cn.yu2.baomihua.manage.module.IStatisticsModule;
import cn.yu2.baomihua.web.controller.BaseController;

/**
 * 教师个人中心
 * 
 * @author lizhixiao
 * @date 2016-11-04
 * @version 1.0.0
 */
@Controller
@RequestMapping("/ucenter/teacher")
public class TeacherController extends BaseController {

	@Resource(name = "statisticsModuleImpl")
	private IStatisticsModule statisticsModuleImpl;

	@Resource(name = "schoolModule")
	private ISchoolModule schoolModuleImpl;

	@Resource(name = "personalCenterModuleImpl")
	private IPersonalCenterModule personalCenterModuleImpl;


	/**教师个人中心*/
	@RequestMapping("/index")
	public String teacherIndex( Model model ) {
		School school = schoolModuleImpl.getSchoolById(getCurrentSchoolId());
		Page<TaskStatistics> page = getPage(10);
		page = statisticsModuleImpl.getTaskRanking(page).getBody();
		Page<VideoPlaybackRecordDTO> videoPage = getPage(8);
		model.addAttribute("school", school);
		model.addAttribute("taskRankingPage", page);
		model.addAttribute("videoPlayPage",
			statisticsModuleImpl.getVideoPlayBackRecord(videoPage, getCurrentUserId()).getBody());
		return "/ucenter/teacher/teacherIndex";
	}


	/**视频播放记录*/
	@RequestMapping("/videoPlayBackRecord")
	public String getVideoPlayBackRecord( Model model ) {
		Page<VideoPlaybackRecordDTO> page = getPage(12);
		model.addAttribute("videoPlayPage",
			statisticsModuleImpl.getVideoPlayBackRecord(page, getCurrentUserId()).getBody());
		return "/ucenter/teacher/playBackRecord";
	}


	/**全区大排名*/
	@RequestMapping("/ranking")
	public String ranking( Model model ) {
		Page<TaskStatistics> page = getPage(10);
		page = statisticsModuleImpl.getTaskRanking(page).getBody();
		model.addAttribute("taskRankingPage", page);
		return "/ucenter/teacher/taskRanking";
	}


	/**获取教师发布的任务*/
	@RequestMapping("/getTeacherTask")
	public String getTeacherTask( @RequestParam(value = "taskType", required = false) Integer taskType, Model model ) {
		if ( null == taskType ) taskType = TaskTypeEnum.VIDEO.key();
		Page<TaskStatistics> page = getPage(10);
		model.addAttribute("taskSta",
			personalCenterModuleImpl.getTeacherTask(page, getCurrentUserId(), taskType).getBody());
		model.addAttribute("taskType", taskType);
		return "/ucenter/teacher/teacherTask";
	}
}
