package cn.yu2.baomihua.manage.module;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.dto.BaseCodeDTO;
import cn.yu2.baomihua.manage.dto.GradeSubjectDTO;
import cn.yu2.baomihua.manage.dto.UserDTO;
import cn.yu2.baomihua.manage.entity.Grade;
import cn.yu2.baomihua.manage.entity.GradeSubject;
import cn.yu2.baomihua.manage.entity.Phase;
import cn.yu2.baomihua.manage.entity.Subject;
import cn.yu2.baomihua.manage.entity.User;

/** 
 * @Description:
 * 		区教学管理interface
 * @author lizhixiao
 * @version 1.0 
 * @date  2016-06-12
 */
public interface ITeachModule {

	/**
	 * 查询所有学段
	 */
	public Response<List<Phase>> listPhase( int status );


	/**
	 * 查询年级
	 * @param grade  年级
	 * @return List<Grade>
	 */
	public Response<List<Grade>> getGradeByPhaseId( Grade grade );


	/**
	 * 查询课程
	 * @param subject  学科
	 * @return List<Subject>
	 */
	public Response<List<Subject>> getSubjectList( Subject subject );


	/**修改学科*/
	public Response<Boolean> updateSubject( Long id, String name );


	/**添加学科*/
	public Response<Boolean> addSubject( Subject subject );


	/**删除学科*/
	public Response<Boolean> delSubject( Long id );


	/**获取年级学科*/
	public Response<List<GradeSubjectDTO>> getGradeSubject( Long schoolId );


	/**删除年级配课*/
	public Response<Boolean> deleteGradeSubject( GradeSubject gradeSubject );


	/**批量修改学科*/
	public Response<Boolean> updateSubjectList( List<Subject> subjectlist );


	/**查询未选择年级的学科
	 * @param long1 */
	public Response<List<Subject>> getSubjectNoSel( Long gradeId, int status, Long schoolId );


	/**添加年级学科对应关系*/
	public Response<Boolean> addGradeSubject( GradeSubject graSub );


	/**获取基本配置*/
	public Response<BaseCodeDTO> getBaseCode( int status, Long schoolId );


	/**获取年级列表*/
	public Response<List<Grade>> getGradeList( Long phaseId, int status );


	/**获教师教学列表*/
	public Response<Page<UserDTO>> getTeachTeacher( Page<UserDTO> page, Map<String, Object> paramMap );


	public Response<Page<UserDTO>> getTeacher( Page<UserDTO> page, Map<String, Object> paramMap, User user );
	
	
	
	
	
	
}
