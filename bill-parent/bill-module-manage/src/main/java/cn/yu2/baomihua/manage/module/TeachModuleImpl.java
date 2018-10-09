package cn.yu2.baomihua.manage.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.constant.ManageConstant;
import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.CodeConstants;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.dto.BaseCodeDTO;
import cn.yu2.baomihua.manage.dto.GradeSubjectDTO;
import cn.yu2.baomihua.manage.dto.UserDTO;
import cn.yu2.baomihua.manage.entity.Grade;
import cn.yu2.baomihua.manage.entity.GradeSubject;
import cn.yu2.baomihua.manage.entity.KlassTeacher;
import cn.yu2.baomihua.manage.entity.Phase;
import cn.yu2.baomihua.manage.entity.Subject;
import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.manage.enums.StatusEnum;
import cn.yu2.baomihua.manage.enums.SubjectTypeEnum;
import cn.yu2.baomihua.manage.service.IGradeService;
import cn.yu2.baomihua.manage.service.IGradeSubjectService;
import cn.yu2.baomihua.manage.service.IKlassService;
import cn.yu2.baomihua.manage.service.IKlassTeacherService;
import cn.yu2.baomihua.manage.service.IPhaseService;
import cn.yu2.baomihua.manage.service.ISubjectService;
import cn.yu2.baomihua.manage.service.IUserService;

/** 
 * @Description:
 * 		区教学管理module
 * @author lizhixiao
 * @version 1.0 
 * @date  2016-06-12
 */
public class TeachModuleImpl extends AbstractModule implements ITeachModule {

	@Resource(name = "phaseServiceImpl")
	private IPhaseService phaseServiceImpl;

	@Resource(name = "gradeServiceImpl")
	private IGradeService gradeServiceImpl;

	@Resource(name = "gradeSubjectServiceImpl")
	private IGradeSubjectService gradeSubjectServiceImpl;

	@Resource(name = "subjectServiceImpl")
	private ISubjectService subjectServiceImpl;

	@Resource(name = "klassTeacherServiceImpl")
	private IKlassTeacherService klassTeacherServiceImpl;

	@Resource(name = "userServiceImpl")
	private IUserService userServiceImpl;

	@Resource(name = "klassServiceImpl")
	private IKlassService klassServiceImpl;


	@Override
	public Response<List<Phase>> listPhase( int status ) {
		Phase phase = new Phase();
		phase.setStatus(status);
		return success(phaseServiceImpl.selectList(phase));
	}


	@Override
	public Response<List<Grade>> getGradeByPhaseId( Grade grade ) {
		return success(gradeServiceImpl.selectList(grade));
	}


	/**查询学科*/
	@Override
	public Response<List<Subject>> getSubjectList( Subject sub ) {
		return success(subjectServiceImpl.selectList(sub));

	}


	/**添加学科*/
	@Override
	public Response<Boolean> addSubject( Subject subject ) {
		if ( subjectServiceImpl.selectList(subject).size() > 0 ) {
			return fail(CodeConstants.PARAM_ERROR, "名称已存在");
		} else {
			Subject baseSub = new Subject();
			baseSub.setName(subject.getName());
			baseSub.setSchoolId(ManageConstant.DISTRIC_SCHOOLID);
			baseSub.setStatus(StatusEnum.ON.key());
			if ( subjectServiceImpl.selectList(baseSub).size() > 0 ) {
				return fail(CodeConstants.PARAM_ERROR, "名称已存在");
			}
			subject.setType(SubjectTypeEnum.CUSTOM.key());
			subject.setStatus(StatusEnum.ON.key());
			subjectServiceImpl.insert(subject);
		}
		return successOfBoolean();

	}


	@Override
	public Response<Boolean> updateSubject( Long id, String name ) {
		Subject sub = new Subject();
		sub.setName(name);
		if ( subjectServiceImpl.selectList(sub).size() > 0 ) {
			return fail(CodeConstants.PARAM_ERROR, "名称已存在");
		} else {
			sub.setId(id);
			subjectServiceImpl.updateSelectiveById(sub);
		}
		return successOfBoolean();
	}


	@Override
	public Response<Boolean> delSubject( Long id ) {
		Subject sub = new Subject();
		sub.setId(id);
		sub.setStatus(StatusEnum.OFF.key());
		subjectServiceImpl.deleteSubject(sub);
		return successOfBoolean();

	}


	@Override
	public Response<List<GradeSubjectDTO>> getGradeSubject( Long schoolId ) {
		List<GradeSubjectDTO> subDtoList = new ArrayList<GradeSubjectDTO>();
		Grade grade = new Grade();
		grade.setStatus(StatusEnum.ON.key());
		List<Grade> gradeList = gradeServiceImpl.selectList(grade);
		for ( Grade grd : gradeList ) {
			GradeSubjectDTO subDto = new GradeSubjectDTO();
			if ( ManageConstant.DISTRIC_SCHOOLID != schoolId ) {
				List<Subject> baseSubjectList = subjectServiceImpl.getSubjectByGradeId(grd.getId(),
					ManageConstant.DISTRIC_SCHOOLID);
				subDto.setBaseSubjectList(baseSubjectList);
			}
			List<Subject> subjectList = subjectServiceImpl.getSubjectByGradeId(grd.getId(), schoolId);
			subDto.setSubjectList(subjectList);
			subDto.setGrade(grd);
			subDtoList.add(subDto);
		}
		return success(subDtoList);
	}


	@Override
	public Response<Boolean> deleteGradeSubject( GradeSubject gradeSubject ) {
		return success(gradeSubjectServiceImpl.deleteSelective(gradeSubject));
	}


	@Override
	public Response<Boolean> updateSubjectList( List<Subject> subjectlist ) {
		return success(subjectServiceImpl.updateBatchById(subjectlist));
	}


	@Override
	public Response<List<Subject>> getSubjectNoSel( Long gradeId, int status, Long schoolId ) {
		return success(subjectServiceImpl.getSubjectNoSel(gradeId, status, schoolId));
	}


	@Override
	public Response<Boolean> addGradeSubject( GradeSubject graSub ) {
		return success(gradeSubjectServiceImpl.insert(graSub));
	}


	@Override
	public Response<BaseCodeDTO> getBaseCode( int status, Long schoolId ) {
		BaseCodeDTO baseCodeDTO = new BaseCodeDTO();
		Phase phase = new Phase();
		phase.setStatus(status);
		List<Phase> phaseList = phaseServiceImpl.selectList(phase);
		baseCodeDTO.setPhaseList(phaseList);
		if ( phaseList.size() > 0 ) {
			Grade grade = new Grade();
			grade.setPhaseId(phaseList.get(0).getId());
			grade.setStatus(status);
			baseCodeDTO.setGradeList(gradeServiceImpl.selectList(grade));
		}
		Subject sub = new Subject();
		sub.setStatus(status);
		sub.setSchoolId(ManageConstant.DISTRIC_SCHOOLID);
		List<Subject> baseSubList = subjectServiceImpl.selectList(sub);
		Subject costomSub = new Subject();
		costomSub.setStatus(status);
		costomSub.setSchoolId(schoolId);
		List<Subject> costomSubList = subjectServiceImpl.selectList(costomSub);
		for ( int i = 0 ; i < costomSubList.size() ; i++ ) {
			baseSubList.add(costomSubList.get(i));
		}
		baseCodeDTO.setSubjectList(baseSubList);
		return success(baseCodeDTO);
	}


	@Override
	public Response<List<Grade>> getGradeList( Long phaseId, int status ) {
		Grade grade = new Grade();
		grade.setPhaseId(phaseId);
		grade.setStatus(status);
		return success(gradeServiceImpl.selectList(grade));
	}


	@Override
	public Response<Page<UserDTO>> getTeachTeacher( Page<UserDTO> page, Map<String, Object> paramMap ) {
		List<UserDTO> userList = klassTeacherServiceImpl.getTeachTeacher(page, paramMap);
		page.setRecords(userList);
		return success(page);
	}


	@Override
	public Response<Page<UserDTO>> getTeacher( Page<UserDTO> page, Map<String, Object> paramMap, User user ) {
		List<UserDTO> userDTOList = userServiceImpl.getUserList(page, paramMap, user);
		for ( int i = 0 ; i < userDTOList.size() ; i++ ) {
			KlassTeacher klaTeacher = new KlassTeacher();
			klaTeacher.setTeacherId(userDTOList.get(i).getId());
			KlassTeacher klaTeachers = klassTeacherServiceImpl.selectOne(klaTeacher);
			if ( null != klaTeachers ) {
				userDTOList.get(i).setKlassName(klassServiceImpl.selectById(klaTeachers.getKlassId()).getName());
			}
		}
		page.setRecords(userDTOList);
		return success(page);
	}
}
