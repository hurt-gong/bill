package cn.yu2.baomihua.web.controller.ucenter;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.manage.dto.VideoPlaybackRecordDTO;
import cn.yu2.baomihua.manage.entity.TaskStatistics;
import cn.yu2.baomihua.manage.module.IStatisticsModule;
import cn.yu2.baomihua.web.controller.BaseController;

/**
 * 区人员个人中心
 * 
 * @author lizhixiao
 * @date 2016-11-04
 * @version 1.0.0
 */
@Controller
@RequestMapping("/ucenter/distric")
public class districController extends BaseController {

	@Resource(name = "statisticsModuleImpl")
	private IStatisticsModule statisticsModuleImpl;


	/*区人员个人中心*/
	@RequestMapping("/index")
	public String districIndex( Model model ) {
		Page<TaskStatistics> page = getPage(6);
		page = statisticsModuleImpl.getTaskRanking(page).getBody();
		Page<VideoPlaybackRecordDTO> videoPage = getPage(8);
		model.addAttribute("videoPlayPage",
			statisticsModuleImpl.getVideoPlayBackRecord(videoPage, getCurrentUserId()).getBody());
		model.addAttribute("taskRankingPage", page);
		return "/ucenter/distric/districIndex";
	}


	/**全区大排名*/
	@RequestMapping("/ranking")
	public String ranking( Model model ) {
		Page<TaskStatistics> page = getPage(10);
		page = statisticsModuleImpl.getTaskRanking(page).getBody();
		model.addAttribute("taskRankingPage", page);
		return "/ucenter/distric/taskRanking";
	}


	/**视频播放记录*/
	@RequestMapping("/playBackRecord")
	public String playbackRecord( Model model ) {
		Page<VideoPlaybackRecordDTO> page = getPage(12);
		model.addAttribute("videoPlayPage",
			statisticsModuleImpl.getVideoPlayBackRecord(page, getCurrentUserId()).getBody());
		return "/ucenter/distric/playBackRecord";
	}

}
