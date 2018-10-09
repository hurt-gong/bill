package cn.yu2.baomihua.manage.module;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.dto.ChartDto;
import cn.yu2.baomihua.manage.dto.GradeSubjectDTO;
import cn.yu2.baomihua.manage.dto.HomeworkStatisticsDTO;
import cn.yu2.baomihua.manage.dto.TaskStatisticsDTO;
import cn.yu2.baomihua.manage.dto.VideoPlaybackRecordDTO;
import cn.yu2.baomihua.manage.dto.VideoStatisticsDTO;
import cn.yu2.baomihua.manage.entity.LoginStatistics;
import cn.yu2.baomihua.manage.entity.TaskStatistics;

/**
 * 资源使用统计
 * 
 * @author lizhixiao
 * @version 1.0
 * @date 2016-10-31
 */
public interface IStatisticsModule {

	/**
	 * 获取登录次数页面浏览量
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param schoolId	学校Id
	 * @param sort		排序字段
	 * @return Page		Response<List<LoginStatistics>>
	 */
	public Response<List<LoginStatistics>> getLoginCount( Date startDate, Date endDate, Long schoolId, String sort );


	/**
	 * 获取登录次数页面浏览量
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param schoolId	学校Id
	 * @param sort		排序字段
	 * @return Page		Response<List<LoginStatistics>>
	 */
	public Response<ChartDto> getLoginCountChart( Date startDate, Date endDate, Long schoolId, String sort );


	/**
	 * 获取视频统计信息图表
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @return List<LoginStatistics>
	 */
	public Response<List<VideoStatisticsDTO>> getVideoCount( Date startDate, Date endDate, String sort );


	/**
	 * 获取视频统计信息
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param sort 		排序字段
	 * @return List<LoginStatistics>
	 */
	public Response<ChartDto> getVideoCountChart( Date startDate, Date endDate, String sort );


	/**
	 * 获取作业统计信息
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param sort 		排序字段
	 * @return List<HomeworkStatisticsDTO>
	 */
	public Response<List<HomeworkStatisticsDTO>> getHomeworkQty( Date startDate, Date endDate, String sort );


	/**
	 * 获取作业统计信息图表
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param sort 		排序字段
	 * @return List<HomeworkStatisticsDTO>
	 */
	public Response<ChartDto> getHomeworkQtyChart( Date startDate, Date endDate, String sort );


	/**
	 * 获取任务统计信息
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param sort 		排序字段
	 * @return List<TaskStatisticsDTO>
	 */
	public Response<List<TaskStatisticsDTO>> getTaskQty( Date startDate, Date endDate, String sort );


	/**
	 * 获取任务统计信息图表
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param sort 		排序字段
	 * @return List<TaskStatisticsDTO>
	 */
	public Response<ChartDto> getTaskQtyChart( Date startDate, Date endDate, String sort );


	/**全区任务大排名*/
	public Response<Page<TaskStatistics>> getTaskRanking( Page<TaskStatistics> page );


	/**获取视频播放记录*/
	public Response<Page<VideoPlaybackRecordDTO>> getVideoPlayBackRecord( Page<VideoPlaybackRecordDTO> page,
			Long studentId );


	/**根据家长获取学生视频播放记录*/
	public Response<Page<VideoPlaybackRecordDTO>> getVideoPlayBackByParent( Page<VideoPlaybackRecordDTO> page,
			Long parentId );


	/**
	 * 获取校登录次数页面浏览量
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param schoolId	学校Id
	 * @param klassId	年级Id
	 * @param sort 		排序字段
	 * @return List<LoginStatistics>
	 */
	public Response<List<LoginStatistics>> getSchoolLoginCount( Date startDate,
			Date endDate,
			Long schoolId,
			Long gradeId,
			String sort );


	/**
	 * 获取校登录次数页面浏览量图表
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param schoolId	学校Id
	 * @param klassId	年级Id
	 * @param sort 		排序字段
	 * @return List<LoginStatistics>
	 */
	public Response<ChartDto> getSchoolLoginCountChart( Date startDate,
			Date endDate,
			Long schoolId,
			Long gradeId,
			String sort );


	/**获取年级学科*/
	public Response<GradeSubjectDTO> getGradeSubjectByGradeId( Long schoolId, Long gradeId );


	/**
	 * 获取学校视频统计信息
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param schoolId	学校Id
	 * @param gradeId	班级ID
	 * @param subjectId	学科
	 * @param sort 		排序字段
	 * @return List<LoginStatistics>
	 
	public Response<List<VideoStatisticsDTO>> getSchoolVideoCount( @Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("schoolId") Long schoolId,
			@Param("gradeId") Long gradeId,
			@Param("subjectId") Long subjectId,
			@Param("sort") String sort );
*/

	/**
	 * 获取学校视频统计图表信息
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param schoolId	学校Id
	 * @param gradeId	班级ID
	 * @param subjectId	学科
	 * @param sort 		排序字段
	 * @return List<LoginStatistics>
	 */
	public Response<ChartDto> getSchoolVideoCountChart( @Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("schoolId") Long schoolId,
			@Param("gradeId") Long gradeId,
			@Param("subjectId") Long subjectId,
			@Param("sort") String sort );


	/**保存用户登录次数*/
	public Response<Boolean> updateLoginStatistics( long userId, String ip, Date date );


	/**
	 * 获取校作业任务统计信息
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param sort 		排序字段
	 * @return List<HomeworkStatisticsDTO>
	
	public Response<List<HomeworkStatisticsDTO>> getSchoolHomwkAndTask( Date startDate,
			Date endDate,
			Integer taskType,
			Long schoolId,
			Long gradeId,
			Long subjectId,
			String sort,
			boolean groupByteacherId );
 */

	/**
	 * 获取校作业任务统计信息图表数据
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param sort 		排序字段
	 * @return List<HomeworkStatisticsDTO>
	 */
	public Response<ChartDto> getSchoolHomwkAndTaskChart( Date startDate,
			Date endDate,
			Integer taskType,
			Long schoolId,
			Long gradeId,
			Long subjectId,
			String sort );

}
