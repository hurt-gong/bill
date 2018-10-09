package cn.yu2.baomihua.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;

import cn.yu2.baomihua.manage.entity.Subject;

/**
 *
 * Subject 表数据库控制层接口
 *
 */
public interface SubjectMapper extends AutoMapper<Subject> {

	public List<Subject> getSubjectByPhase(@Param("status") Integer status, @Param("phaseId") Long phaseId,
			@Param("schoolId") Long schoolId);

	public List<Subject> getSubjectByGradeId(@Param("gradeId") Long gradeId, @Param("schoolId") Long schoolId);

	public List<Subject> getSubjectNoSel(@Param("gradeId") Long gradeId, @Param("status") int status,
			@Param("schoolId") Long schoolId);

	public Subject getBookToName(@Param("name") String name);

	public List<Subject> findSubjectByGradeId(Long gradeId);

	public List<Subject> getSubjectBySchoolId(@Param("schoolId") Long schoolId);
}