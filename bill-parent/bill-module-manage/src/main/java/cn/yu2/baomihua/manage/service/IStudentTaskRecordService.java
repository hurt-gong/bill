package cn.yu2.baomihua.manage.service;

import java.util.Date;
import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.manage.dto.StudentTaskRecordDTO;
import cn.yu2.baomihua.manage.entity.StudentTaskRecord;

/**
 *
 * StudentTaskRecord 表数据服务层接口
 *
 */
public interface IStudentTaskRecordService extends ISuperService<StudentTaskRecord> {

	/**
	 * 获取学生任务
	 * @param studentId	 学生id
	 * @param isFinished 是否完成
	 * @param taskType	任务类型
	 * @return List<StudentTaskRecordDTO>
	 */
	public List<StudentTaskRecordDTO> getStudentTask( Page<StudentTaskRecordDTO> page,
			long studentId,
			Integer isFinished,
			Integer taskType );


	/**
	 * 获取学生任务统计
	 * @param isFinished 是否完成
	 * @param crDate 开始日期
	 */
	public Integer getTaskNum( long studentId, Integer isFinished, Date crDate );

}