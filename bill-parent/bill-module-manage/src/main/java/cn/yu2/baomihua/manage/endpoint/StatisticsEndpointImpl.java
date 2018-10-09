package cn.yu2.baomihua.manage.endpoint;

import javax.annotation.Resource;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.manage.endpoint.IStatisticsEndpoint;
import cn.yu2.baomihua.manage.entity.TaskStatistics;
import cn.yu2.baomihua.manage.entity.VideoPlaybackRecord;
import cn.yu2.baomihua.manage.enums.TaskTypeEnum;
import cn.yu2.baomihua.manage.service.IKlassService;
import cn.yu2.baomihua.manage.service.IKlassStudentService;
import cn.yu2.baomihua.manage.service.ISchoolService;
import cn.yu2.baomihua.manage.service.ITaskStatisticsService;
import cn.yu2.baomihua.manage.service.IUserService;
import cn.yu2.baomihua.manage.service.IVideoPlaybackRecordService;

public class StatisticsEndpointImpl extends AbstractModule implements IStatisticsEndpoint {

	@Resource(name = "taskStatisticsServiceImpl")
	private ITaskStatisticsService taskStatisticsServiceImpl;

	@Resource(name = "klassServiceImpl")
	private IKlassService klassServiceImpl;

	@Resource(name = "klassStudentServiceImpl")
	private IKlassStudentService klassStudentServiceImpl;

	@Resource(name = "schoolServiceImpl")
	private ISchoolService schoolServiceImpl;

	@Resource(name = "userServiceImpl")
	private IUserService userServiceImpl;

	@Resource(name = "videoPlaybackRecordServiceImpl")
	private IVideoPlaybackRecordService videoPlaybackRecordServiceImpl;


	 


	@Override
	public void updateStudentTask( Long studentId, Long homeworkId, int type ) {
		taskStatisticsServiceImpl.updateStudentTask(studentId, homeworkId, type);
	}


	 


	@Override
	public void updateVideoStatistics( VideoPlaybackRecord videoPlay, Long userId ) {
		videoPlaybackRecordServiceImpl.insert(videoPlay);
		taskStatisticsServiceImpl.updateStudentTask(userId, videoPlay.getVideoId(), TaskTypeEnum.VIDEO.key());
	}


	@Override
	public void updateVideoTask( Long videoId ) {
		TaskStatistics task = new TaskStatistics();
		task.setTaskId(videoId);
		task.setTaskType(TaskTypeEnum.VIDEO.key());
		TaskStatistics _task = taskStatisticsServiceImpl.selectOne(task);
		if ( null != _task ) {
			_task.setVideoCommentQty(_task.getVideoCommentQty() + 1);
			taskStatisticsServiceImpl.updateById(_task);
		}
	}


	 


	 


	@Override
	public void deletTask( Long taskId, int taskType ) {
		TaskStatistics task = new TaskStatistics();
		task.setTaskId(taskId);
		task.setTaskType(taskType);
		TaskStatistics myTask = taskStatisticsServiceImpl.selectOne(task);
		if ( null != myTask ) {
			taskStatisticsServiceImpl.deleteTask(myTask);
		}
	}

}
