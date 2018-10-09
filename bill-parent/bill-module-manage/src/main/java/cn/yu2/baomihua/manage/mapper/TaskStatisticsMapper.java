package cn.yu2.baomihua.manage.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.manage.dto.HomeworkStatisticsDTO;
import cn.yu2.baomihua.manage.dto.TaskStatisticsDTO;
import cn.yu2.baomihua.manage.dto.VideoStatisticsDTO;
import cn.yu2.baomihua.manage.entity.TaskStatistics;

/**
 *
 * TaskStatistics 表数据库控制层接口
 *
 */
public interface TaskStatisticsMapper extends AutoMapper<TaskStatistics> {

	/**
	 * 获取任务统计信息
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param sort 		排序字段
	 * @return List<TaskStatisticsDTO>
	 */
	public List<TaskStatisticsDTO> getTaskQty( @Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("sort") String sort );


	/**
	 * 获取任务统计信息图表
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @return List<TaskStatisticsDTO>
	 */
	public List<TaskStatisticsDTO> getTaskQtyChart( @Param("startDate") Date startDate,
			@Param("endDate") Date endDate );


	/**全区任务大排名*/
	public List<TaskStatistics> getTaskRanking( Page<TaskStatistics> page );


	/**
	 * 获取作业统计信息
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param sort 		排序字段
	 * @return List<HomeworkStatisticsDTO>
	 */
	public List<HomeworkStatisticsDTO> getHomwkQty( @Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("sort") String sort );


	/**
	 * 获取作业统计信息图表
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @return List<HomeworkStatisticsDTO>
	 */
	public List<HomeworkStatisticsDTO> getHomwkQtyChart( @Param("startDate") Date startDate,
			@Param("endDate") Date endDate );


	/**
	 * 获取视频统计信息
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param sort 		排序字段
	 * @return List<LoginStatistics>
	 */
	public List<VideoStatisticsDTO> getVideoCount( @Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("sort") String sort );


	/**
	 * 获取视频统计信息图表
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @return List<LoginStatistics>
	 */
	public List<VideoStatisticsDTO> getVideoCountChart( @Param("startDate") Date startDate,
			@Param("endDate") Date endDate );


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
	 */
	public List<VideoStatisticsDTO> getSchoolVideoCount( @Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("schoolId") Long schoolId,
			@Param("gradeId") Long gradeId,
			@Param("subjectId") Long subjectId,
			@Param("sort") String sort );


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
	public List<VideoStatisticsDTO> getSchoolVideoCountChart( @Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("schoolId") Long schoolId,
			@Param("gradeId") Long gradeId,
			@Param("subjectId") Long subjectId,
			@Param("sort") String sort );


	/**
	 * 获取校作业任务统计信息
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param sort 		排序字段
	 * @return List<HomeworkStatisticsDTO>
	 */
	public List<HomeworkStatisticsDTO> getSchoolHomwkAndTask( @Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("taskType") Integer taskType,
			@Param("schoolId") Long schoolId,
			@Param("gradeId") Long gradeId,
			@Param("subjectId") Long subjectId,
			@Param("sort") String sort,
			@Param("groupByteacherId") boolean groupByteacherId );


	/**
	 * 获取校作业任务统计信息图表数据
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param sort 		排序字段
	 * @return List<HomeworkStatisticsDTO>
	 */
	public List<HomeworkStatisticsDTO> getSchoolHomwkAndTaskChart( @Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("taskType") Integer taskType,
			@Param("schoolId") Long schoolId,
			@Param("gradeId") Long gradeId,
			@Param("subjectId") Long subjectId,
			@Param("sort") String sort );
}