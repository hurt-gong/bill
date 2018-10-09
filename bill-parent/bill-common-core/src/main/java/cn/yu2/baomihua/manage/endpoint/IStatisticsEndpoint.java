package cn.yu2.baomihua.manage.endpoint;

 
import cn.yu2.baomihua.manage.entity.VideoPlaybackRecord;
 

/**
 * 保存统计信息接口
 * @author lizhixiao
 */
public interface IStatisticsEndpoint {

	 

	/**更新学生任务记录*/
	public void updateStudentTask( Long studentId, Long taskId, int type );


	/**更新视频任务统计，保存视频播放记录*/
	public void updateVideoStatistics( VideoPlaybackRecord videoPlay, Long userId );


	/**更新视频评论*/
	public void updateVideoTask( Long videoId );


	/**删除任务*/
	public void deletTask( Long taskId, int taskType );
}
