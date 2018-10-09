package cn.yu2.baomihua.web.controller.ucenter;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.manage.dto.StudentTaskRecordDTO;
import cn.yu2.baomihua.manage.dto.VideoPlaybackRecordDTO;
import cn.yu2.baomihua.manage.enums.TaskStatusEnum;
import cn.yu2.baomihua.manage.module.IPersonalCenterModule;
import cn.yu2.baomihua.manage.module.IStatisticsModule;
import cn.yu2.baomihua.util.DateUtils;
import cn.yu2.baomihua.web.controller.BaseController;

/**
 * 家长个人中心
 * 
 * @author lizhixiao
 * @date 2016-11-04
 * @version 1.0.0
 */
@Controller
@RequestMapping("/ucenter/parent")
public class ParentController extends BaseController {

	@Resource(name = "statisticsModuleImpl")
	private IStatisticsModule statisticsModuleImpl;

	@Resource(name = "personalCenterModuleImpl")
	private IPersonalCenterModule personalCenterModuleImpl;


	/**家长个人中心*/
	@RequestMapping("/index")
	public String parentIndex( Model model ) {
		Page<VideoPlaybackRecordDTO> page = getPage(8);
		model.addAttribute("videoPlayPage",
			statisticsModuleImpl.getVideoPlayBackByParent(page, getCurrentUserId()).getBody());
		Page<StudentTaskRecordDTO> taskPage = getPage(6);
		model.addAttribute("stuTaskPage", personalCenterModuleImpl
				.getStuTaskByParentId(taskPage, getCurrentUserId(), TaskStatusEnum.UNFINISHED.key(), null).getBody());
		model.addAttribute("finishNum",
			personalCenterModuleImpl.getTaskNumByParent(getCurrentUserId(), DateUtils.getMondayOfThisWeek()).getBody());
		return "/ucenter/parent/parentIndex";
	}


	/**学习任务*/
	@RequestMapping("/stuTask")
	public String stuTask( Model model,
			@RequestParam(value = "isFinished", defaultValue = "0", required = false) Integer isFinished,
			@RequestParam(value = "taskType", defaultValue = "1", required = false) Integer taskType ) {
		model.addAttribute("isFinished", isFinished);
		model.addAttribute("taskType", taskType);
		Page<StudentTaskRecordDTO> taskPage = getPage(10);
		model.addAttribute("stuTaskPage", personalCenterModuleImpl
				.getStuTaskByParentId(taskPage, getCurrentUserId(), isFinished, taskType).getBody());
		return "/ucenter/parent/stuTask";
	}


	/**视频播放记录*/
	@RequestMapping("/playBackRecord")
	public String playBackRecord( Model model ) {
		Page<VideoPlaybackRecordDTO> page = getPage(12);
		model.addAttribute("videoPlayPage",
			statisticsModuleImpl.getVideoPlayBackByParent(page, getCurrentUserId()).getBody());
		return "/ucenter/parent/playBackRecord";
	}
}
