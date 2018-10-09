package cn.yu2.baomihua.manage.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.manage.dto.StudentTaskRecordDTO;
import cn.yu2.baomihua.manage.entity.StudentTaskRecord;

/**
 *
 * StudentTaskRecord 表数据库控制层接口
 *
 */
public interface StudentTaskRecordMapper extends AutoMapper<StudentTaskRecord> {

	public List<StudentTaskRecordDTO> getStudentTask( Page<StudentTaskRecordDTO> page,
			@Param("studentId") long studentId,
			@Param("isFinished") Integer isFinished,
			@Param("taskType") Integer taskType );


	public Integer getTaskNum( @Param("studentId") long studentId,
			@Param("isFinished") Integer isFinished,
			@Param("crDate") Date crDate );


}