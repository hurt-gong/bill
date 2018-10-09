package cn.yu2.baomihua.manage.module;

import java.util.Date;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.dto.StudentTaskRecordDTO;
import cn.yu2.baomihua.manage.entity.StudentParent;
import cn.yu2.baomihua.manage.entity.TaskStatistics;
import cn.yu2.baomihua.manage.enums.TaskStatusEnum;
import cn.yu2.baomihua.manage.service.IStudentParentService;
import cn.yu2.baomihua.manage.service.IStudentTaskRecordService;
import cn.yu2.baomihua.manage.service.ITaskStatisticsService;

/**
 * 个人中心(区管理员，老师，学生)
 */
public class PersonalCenterModuleImpl extends AbstractModule implements IPersonalCenterModule {

	@Resource(name = "studentTaskRecordServiceImpl")
	private IStudentTaskRecordService studentTaskRecordServiceImpl;

	@Resource(name = "studentParentServiceImpl")
	private IStudentParentService studentParentServiceImpl;

	@Resource(name = "taskStatisticsServiceImpl")
	private ITaskStatisticsService taskStatisticsServiceImpl;


	@Override
	public Response<Page<StudentTaskRecordDTO>> getStudentTask( Page<StudentTaskRecordDTO> page,
			long studentId,
			Integer isFinished,
			Integer taskType ) {
		page.setRecords(studentTaskRecordServiceImpl.getStudentTask(page, studentId, isFinished, taskType));
		return success(page);
	}


	@Override
	public Response<Page<StudentTaskRecordDTO>> getStuTaskByParentId( Page<StudentTaskRecordDTO> page,
			long parentId,
			Integer isFinished,
			Integer taskType ) {
		StudentParent studentParent = new StudentParent();
		studentParent.setParentId(parentId);
		StudentParent stuParent = studentParentServiceImpl.selectOne(studentParent);
		if ( null != stuParent ) page.setRecords(
			studentTaskRecordServiceImpl.getStudentTask(page, stuParent.getStudentId(), isFinished, taskType));
		return success(page);
	}


	@Override
	public Response<Double> getTaskNum( Long studentId, Date crDate ) {
		Integer all = studentTaskRecordServiceImpl.getTaskNum(studentId, null, crDate);
		Integer finish = studentTaskRecordServiceImpl.getTaskNum(studentId, TaskStatusEnum.FINISHED.key(), crDate);
		if ( null != finish && null != all && 0 != all ) {
			return success(Double.valueOf(finish * 100 / all));
		}
		return success(0D);
	}


	@Override
	public Response<Integer> getTaskNumByParent( Long parentId, Date crDate ) {
		StudentParent studentParent = new StudentParent();
		studentParent.setParentId(parentId);
		StudentParent stuParent = studentParentServiceImpl.selectOne(studentParent);
		if ( null != stuParent ) {
			Integer all = studentTaskRecordServiceImpl.getTaskNum(stuParent.getStudentId(), null, crDate);
			Integer finish = studentTaskRecordServiceImpl.getTaskNum(stuParent.getStudentId(),
				TaskStatusEnum.FINISHED.key(), crDate);
			if ( null != finish && null != all && 0 != all ) {
				return success(finish / all * 100);
			}
		}
		return success(0);
	}


	@Override
	public Response<Page<TaskStatistics>> getTeacherTask( Page<TaskStatistics> page,
			long teacherId,
			Integer taskType ) {
		TaskStatistics taskStatistics = new TaskStatistics();
		taskStatistics.setTeacherId(teacherId);
		taskStatistics.setTaskType(taskType);
		Page<TaskStatistics> taskPage = taskStatisticsServiceImpl.selectPage(page, taskStatistics);
		return success(taskPage);
	}

}
