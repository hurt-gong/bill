package cn.yu2.baomihua.manage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.manage.dto.HomeworkStatisticsDTO;
import cn.yu2.baomihua.manage.dto.TaskStatisticsDTO;
import cn.yu2.baomihua.manage.dto.VideoStatisticsDTO;
import cn.yu2.baomihua.manage.entity.Klass;
import cn.yu2.baomihua.manage.entity.KlassStudent;
import cn.yu2.baomihua.manage.entity.StudentTaskRecord;
import cn.yu2.baomihua.manage.entity.TaskStatistics;
import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.manage.enums.TaskStatusEnum;
import cn.yu2.baomihua.manage.enums.TaskTypeEnum;
import cn.yu2.baomihua.manage.enums.UserTypeEnum;
import cn.yu2.baomihua.manage.mapper.KlassMapper;
import cn.yu2.baomihua.manage.mapper.KlassStudentMapper;
import cn.yu2.baomihua.manage.mapper.SchoolMapper;
import cn.yu2.baomihua.manage.mapper.StudentTaskRecordMapper;
import cn.yu2.baomihua.manage.mapper.TaskStatisticsMapper;
import cn.yu2.baomihua.manage.mapper.UserMapper;
import cn.yu2.baomihua.manage.service.ITaskStatisticsService;

/**
 *
 * TaskStatistics 表数据服务层接口实现类
 *
 */
@Service
public class TaskStatisticsServiceImpl extends BaseServiceImpl<TaskStatisticsMapper, TaskStatistics>
		implements ITaskStatisticsService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SchoolMapper schoolMapper;

	@Autowired
	private KlassMapper klassMapper;

	@Autowired
	private KlassStudentMapper klassStudentMapper;

	@Autowired
	private StudentTaskRecordMapper studentTaskRecordMapper;

	@Override
	public List<TaskStatisticsDTO> getTaskQty(Date startDate, Date endDate, String sort) {
		return baseMapper.getTaskQty(startDate, endDate, sort);
	}

	@Override
	public List<TaskStatisticsDTO> getTaskQtyChart(Date startDate, Date endDate) {
		return baseMapper.getTaskQtyChart(startDate, endDate);
	}

	@Override
	public List<TaskStatistics> getTaskRanking(Page<TaskStatistics> page) {
		return baseMapper.getTaskRanking(page);
	}

 
	@Override
	public List<HomeworkStatisticsDTO> getHomeworkQty(Date startDate, Date endDate, String sort) {
		return baseMapper.getHomwkQty(startDate, endDate, sort);
	}

	@Override
	public List<HomeworkStatisticsDTO> getHomeworkQtyChart(Date startDate, Date endDate) {
		return baseMapper.getHomwkQtyChart(startDate, endDate);
	}

	@Override
	public void updateStudentTask(Long studentId, Long taskId, int type) {
		this.updateTask(studentId, taskId, type);
	}

	@Override
	public void saveTaskList(TaskStatistics task, List<StudentTaskRecord> stuTaskList) {
		baseMapper.insert(task);
		for (int i = 0; i < stuTaskList.size(); i++) {
			studentTaskRecordMapper.insert(stuTaskList.get(i));
		}
	}

	private void updateTask(Long studentId, Long taskId, int type) {
		TaskStatistics task = new TaskStatistics();
		task.setTaskId(taskId);
		task.setTaskType(type);
		TaskStatistics taskEntity = baseMapper.selectOne(task);
		if (null == taskEntity)
			return;
		if (type == TaskTypeEnum.VIDEO.key())
			taskEntity.setVideoDibbleQty(taskEntity.getVideoDibbleQty() + 1);
		User user = userMapper.selectById(studentId);
		if (UserTypeEnum.STUDENT.key() != user.getType()) {
			if (type == TaskTypeEnum.VIDEO.key())
				baseMapper.updateById(taskEntity);
			return;
		}
		if (0 == taskEntity.getFinishStudentQty()) {
			taskEntity.setFirstStudentId(studentId);
			if (null != user) {
				taskEntity.setFirstStudentName(user.getName());
				KlassStudent klassStudent = new KlassStudent();
				klassStudent.setStudentId(studentId);
				KlassStudent klassStu = klassStudentMapper.selectOne(klassStudent);
				if (null != klassStu) {
					Klass klassEntity = klassMapper.selectById(klassStu.getKlassId());
					taskEntity.setFirstStudentKlass(klassEntity.getName());
				}
			}
		}

		StudentTaskRecord stuTaskRecord = new StudentTaskRecord();
		stuTaskRecord.setStudentId(studentId);
		stuTaskRecord.setTaskStatisticsId(taskEntity.getId());
		stuTaskRecord.setIsFinished(TaskStatusEnum.UNFINISHED.key());
		StudentTaskRecord unFinishStuTaskRecord = studentTaskRecordMapper.selectOne(stuTaskRecord);
		if (null != unFinishStuTaskRecord) {
			unFinishStuTaskRecord.setIsFinished(TaskStatusEnum.FINISHED.key());
			studentTaskRecordMapper.updateById(unFinishStuTaskRecord);
			taskEntity.setFinishStudentQty(taskEntity.getFinishStudentQty() + 1);
			if (taskEntity.getTaskStudentQty() != 0) {
				double rate = taskEntity.getFinishStudentQty().doubleValue() / taskEntity.getTaskStudentQty();
				if (null == taskEntity.getFinishRate())
					taskEntity.setFinishRate(0d);
				rate = taskEntity.getFinishRate() >= 100 ? 100 : (rate * 100);
				taskEntity.setFinishRate(rate);
			}
		}
		baseMapper.updateById(taskEntity);

	}

	@Override
	public List<VideoStatisticsDTO> getVideoCount(Date startDate, Date endDate, String sort) {
		return baseMapper.getVideoCount(startDate, endDate, sort);
	}

	@Override
	public List<VideoStatisticsDTO> getVideoCountChart(Date startDate, Date endDate) {
		return baseMapper.getVideoCountChart(startDate, endDate);
	}

	@Override
	public List<VideoStatisticsDTO> getSchoolVideoCount(Date startDate, Date endDate, Long schoolId, Long gradeId,
			Long subjectId, String sort) {
		return baseMapper.getSchoolVideoCount(startDate, endDate, schoolId, gradeId, subjectId, sort);
	}

	@Override
	public List<VideoStatisticsDTO> getSchoolVideoCountChart(Date startDate, Date endDate, Long schoolId, Long gradeId,
			Long subjectId, String sort) {
		return baseMapper.getSchoolVideoCountChart(startDate, endDate, schoolId, gradeId, subjectId, sort);
	}

	@Override
	public List<HomeworkStatisticsDTO> getSchoolHomwkAndTask(Date startDate, Date endDate, Integer taskType,
			Long schoolId, Long gradeId, Long subjectId, String sort, boolean groupByteacherId) {
		return baseMapper.getSchoolHomwkAndTask(startDate, endDate, taskType, schoolId, gradeId, subjectId, sort,
				groupByteacherId);
	}

	@Override
	public List<HomeworkStatisticsDTO> getSchoolHomwkAndTaskChart(Date startDate, Date endDate, Integer taskType,
			Long schoolId, Long gradeId, Long subjectId, String sort) {
		return baseMapper.getSchoolHomwkAndTaskChart(startDate, endDate, taskType, schoolId, gradeId, subjectId, sort);
	}

	@Override
	public void deleteTask(TaskStatistics myTask) {
		StudentTaskRecord stuTaskRecord = new StudentTaskRecord();
		stuTaskRecord.setTaskStatisticsId(myTask.getId());
		studentTaskRecordMapper.deleteSelective(stuTaskRecord);
		baseMapper.deleteById(myTask.getId());
	}
}