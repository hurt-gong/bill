package cn.yu2.baomihua.manage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.manage.dto.StudentTaskRecordDTO;
import cn.yu2.baomihua.manage.entity.StudentTaskRecord;
import cn.yu2.baomihua.manage.mapper.StudentTaskRecordMapper;
import cn.yu2.baomihua.manage.service.IStudentTaskRecordService;

/**
 *
 * StudentTaskRecord 表数据服务层接口实现类
 *
 */
@Service
public class StudentTaskRecordServiceImpl extends BaseServiceImpl<StudentTaskRecordMapper, StudentTaskRecord>
		implements IStudentTaskRecordService {

	@Override
	public List<StudentTaskRecordDTO> getStudentTask( Page<StudentTaskRecordDTO> page,
			long studentId,
			Integer isFinished,
			Integer taskType ) {
		List<StudentTaskRecordDTO> stuTaskPage = baseMapper.getStudentTask(page, studentId, isFinished, taskType);
		return stuTaskPage;
	}


	@Override
	public Integer getTaskNum( long studentId, Integer isFinished, Date crDate ) {
		return baseMapper.getTaskNum(studentId, isFinished, crDate);
	}
}