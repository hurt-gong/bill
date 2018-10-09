package cn.yu2.baomihua.manage.module;

import java.util.Date;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.dto.StudentTaskRecordDTO;
import cn.yu2.baomihua.manage.entity.TaskStatistics;

/**
 * 个人中心(区管理员，老师，学生)
 */
public interface IPersonalCenterModule {

	/**
	 * 获取学生任务
	 * @param studentId	 学生id
	 * @param isFinished 是否完成
	 * @param taskType	任务类型
	 * @return List<StudentTaskRecordDTO>
	 */
	public Response<Page<StudentTaskRecordDTO>> getStudentTask( Page<StudentTaskRecordDTO> page,
			long studentId,
			Integer isFinished,
			Integer taskType );


	/**
	 * 根据家长获取学生任务
	 * @param parentId	 家长id
	 * @param isFinished 是否完成
	 * @param taskType	任务类型
	 * @return List<StudentTaskRecordDTO>
	 */
	public Response<Page<StudentTaskRecordDTO>> getStuTaskByParentId( Page<StudentTaskRecordDTO> page,
			long parentId,
			Integer isFinished,
			Integer taskType );


	public Response<Double> getTaskNum( Long studentId, Date crDate );


	/**根据家长获取学生完成学习任务*/
	public Response<Integer> getTaskNumByParent( Long parentId, Date crDate );


	/**获取教师发布的任务*/
	public Response<Page<TaskStatistics>> getTeacherTask( Page<TaskStatistics> page, long teacherId, Integer taskType );
}
