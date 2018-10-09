package cn.yu2.baomihua.manage.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.framework.service.ISuperService;
import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.manage.dto.HomeworkStatisticsDTO;
import cn.yu2.baomihua.manage.dto.TaskStatisticsDTO;
import cn.yu2.baomihua.manage.dto.VideoStatisticsDTO;
import cn.yu2.baomihua.manage.entity.StudentTaskRecord;
import cn.yu2.baomihua.manage.entity.TaskStatistics;

/**
 *
 * TaskStatistics 表数据服务层接口
 *
 */
public interface ITaskStatisticsService extends ISuperService<TaskStatistics> {


	/**
	 * 获取任务统计信息
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param sort 		排序字段
	 * @return List<TaskStatisticsDTO>
	 */
	public List<TaskStatisticsDTO> getTaskQty( Date startDate, Date endDate, String sort );


	/**
	 * 获取任务统计信息图表
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @return List<TaskStatisticsDTO>
	 */
	public List<TaskStatisticsDTO> getTaskQtyChart( Date startDate, Date endDate );


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
	public List<HomeworkStatisticsDTO> getHomeworkQty( Date startDate, Date endDate, String sort );


	/**
	 * 获取作业统计信息图表
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @return List<HomeworkStatisticsDTO>
	 */
	public List<HomeworkStatisticsDTO> getHomeworkQtyChart( Date startDate, Date endDate );


	/**更新学生任务记录*/
	public void updateStudentTask( Long studentId, Long taskId, int type );


	/**保存学生视频任务记录*/
	public void saveTaskList( TaskStatistics task, List<StudentTaskRecord> stuTasklist );


	/**
	 * 获取视频统计信息
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param sort 		排序字段
	 * @return List<LoginStatistics>
	 */
	public List<VideoStatisticsDTO> getVideoCount( Date startDate, Date endDate, String sort );


	/**
	 * 获取视频统计信息图表
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @return List<LoginStatistics>
	 */
	public List<VideoStatisticsDTO> getVideoCountChart( Date startDate, Date endDate );


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
	public List<VideoStatisticsDTO> getSchoolVideoCount( Date startDate,
			Date endDate,
			Long schoolId,
			Long gradeId,
			Long subjectId,
			String sort );


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
	public List<VideoStatisticsDTO> getSchoolVideoCountChart( Date startDate,
			Date endDate,
			Long schoolId,
			Long gradeId,
			Long subjectId,
			String sort );


	/**
	 * 获取校作业任务统计信息
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param sort 		排序字段
	 * @return List<HomeworkStatisticsDTO>
	 */
	public List<HomeworkStatisticsDTO> getSchoolHomwkAndTask( @Param("startDate") Date startDate,
			Date endDate,
			Integer taskType,
			Long schoolId,
			Long gradeId,
			Long subjectId,
			String sort,
			boolean groupByteacherId );


	/**
	 * 获取校作业任务统计信息图表数据
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param sort 		排序字段
	 * @return List<HomeworkStatisticsDTO>
	 */
	public List<HomeworkStatisticsDTO> getSchoolHomwkAndTaskChart( @Param("startDate") Date startDate,
			Date endDate,
			Integer taskType,
			Long schoolId,
			Long gradeId,
			Long subjectId,
			String sort );


	/**删除任务*/
	public void deleteTask( TaskStatistics myTask );
}