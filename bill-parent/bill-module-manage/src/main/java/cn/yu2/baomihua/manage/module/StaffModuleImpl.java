package cn.yu2.baomihua.manage.module;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.baomidou.kisso.common.encrypt.MD5;
import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.constant.ManageConstant;
import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.dto.BaseCodeDTO;
import cn.yu2.baomihua.manage.dto.UserDTO;
import cn.yu2.baomihua.manage.entity.Dict;
import cn.yu2.baomihua.manage.entity.Grade;
import cn.yu2.baomihua.manage.entity.Klass;
import cn.yu2.baomihua.manage.entity.StudentParent;
import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.manage.enums.StatusEnum;
import cn.yu2.baomihua.manage.enums.UserTypeEnum;
import cn.yu2.baomihua.manage.service.IDictService;
import cn.yu2.baomihua.manage.service.IGradeService;
import cn.yu2.baomihua.manage.service.IKlassService;
import cn.yu2.baomihua.manage.service.IStudentParentService;
import cn.yu2.baomihua.manage.service.IUserService;

/** 
 * @Description:
 * 		区教学管理module
 * @author lizhixiao
 * @version 1.0 
 * @date  2016-06-12
 */
public class StaffModuleImpl extends AbstractModule implements IStaffModule {

	@Resource(name = "userServiceImpl")
	private IUserService userServiceImpl;

	@Resource(name = "klassServiceImpl")
	private IKlassService klassServiceImpl;

	@Resource(name = "gradeServiceImpl")
	private IGradeService gradeServiceImpl;

	@Resource(name = "dictServiceImpl")
	private IDictService dictServiceImpl;

	@Resource(name = "studentParentServiceImpl")
	private IStudentParentService studentParentServiceImpl;


	@Override
	public Response<Page<User>> getUserList( Page<User> page, User user ) {
		page.setRecords(userServiceImpl.getUserList(page, user));
		return success(page);
	}


	@Override
	public Response<Boolean> addUser( User user ) {
		//		user.setSpell(Pinyin4jHelper.converterToSpell(user.getName()));
		return success(userServiceImpl.insert(user));
	}


	@Override
	public Response<Boolean> updateUserById( User user ) {
		return success(userServiceImpl.updateSelectiveById(user));
	}


	@Override
	public Response<Boolean> delUserById( Long id ) {
		return success(userServiceImpl.deleteById(id));
	}


	@Override
	public Response<List<User>> getUserList( User user ) {
		return success(userServiceImpl.selectList(user));
	}


	@Override
	public Response<Integer> delUserList( List<Long> idList ) {
		int num = 0;
		for ( int i = 0 ; i < idList.size() ; i++ ) {
			Boolean flag = userServiceImpl.deleteById(idList.get(i));
			if ( flag ) {
				num++;
			}
		}
		return success(num);
	}


	@Override
	public Response<Boolean> importUserList( List<User> userList ) {
		return success(userServiceImpl.insertBatch(userList));
	}


	@Override
	public Response<Page<UserDTO>> getStudentList( Page<UserDTO> page, Map<String, Object> paramMap, User user ) {
		List<UserDTO> studentDTOList = userServiceImpl.getUserList(page, paramMap, user);
		for ( int i = 0 ; i < studentDTOList.size() ; i++ ) {
			User parent = userServiceImpl.getStudentParent(studentDTOList.get(i).getId());
			if ( parent != null ) {
				studentDTOList.get(i).setParent(parent);
			}
		}
		page.setRecords(studentDTOList);
		return success(page);
	}


	@Override
	public Response<List<UserDTO>> getStudentList( Map<String, Object> paramMap, User user ) {
		List<UserDTO> studentDTOList = userServiceImpl.getUserList(paramMap, user);
		for ( int i = 0 ; i < studentDTOList.size() ; i++ ) {
			User parent = userServiceImpl.getStudentParent(studentDTOList.get(i).getId());
			if ( parent != null ) {
				studentDTOList.get(i).setParent(parent);
			}
		}
		return success(studentDTOList);
	}


	@Override
	public Response<BaseCodeDTO> getBaseCode( Long gradeId, Long schoolId ) {
		BaseCodeDTO baseCode = new BaseCodeDTO();
		Grade grade = new Grade();
		grade.setStatus(StatusEnum.ON.key());
		List<Grade> gradeList = gradeServiceImpl.selectList(grade);
		baseCode.setGradeList(gradeList);
		if ( null == gradeId && gradeList.size() > 0 ) {
			gradeId = gradeList.get(0).getId();
		}
		Klass klass = new Klass();
		klass.setGradeId(gradeId);
		klass.setSchoolId(schoolId);
		klass.setStatus(StatusEnum.ON.key());
		List<Klass> klassList = klassServiceImpl.selectList(klass);
		baseCode.setKlassList(klassList);
		return success(baseCode);
	}


	@Override
	public Response<List<Klass>> getKlassList( Long gradeId, Long schoolId ) {
		Klass klass = new Klass();
		klass.setGradeId(gradeId);
		klass.setSchoolId(schoolId);
		klass.setStatus(StatusEnum.ON.key());
		List<Klass> klassList = klassServiceImpl.selectList(klass);
		return success(klassList);
	}


	@Override
	public Response<Page<UserDTO>> getTeacherList( Page<UserDTO> page, Map<String, Object> paramMap, User user ) {
		List<UserDTO> teacherDTOList = userServiceImpl.getUserList(page, paramMap, user);
		for ( int i = 0 ; i < teacherDTOList.size() ; i++ ) {
			if ( teacherDTOList.get(i).getPositionId() != null ) {
				Dict dict = dictServiceImpl.selectById(teacherDTOList.get(i).getPositionId());
				teacherDTOList.get(i).setPostionName(dict.getName());
			}
		}
		page.setRecords(teacherDTOList);
		return success(page);
	}


	@Override
	public Response<List<Dict>> getDictList( int type ) {
		Dict dict = new Dict();
		dict.setType(type);
		return success(dictServiceImpl.selectList(dict));
	}


	@Override
	public Response<UserDTO> getUserDTO( Long studentId ) {
		//=========待对接接口完善信息===========
		UserDTO userDto = new UserDTO();
		User userSource = userServiceImpl.selectById(studentId);
		BeanUtils.copyProperties(userSource, userDto);
		StudentParent studentParent = new StudentParent();
		studentParent.setStudentId(userDto.getId());
		StudentParent stuParent = studentParentServiceImpl.selectOne(studentParent);
		if ( null != stuParent ) {
			Dict dict = dictServiceImpl.selectById(stuParent.getRelationId());
			User user = userServiceImpl.selectById(stuParent.getParentId());
			if ( null != user ) {
				if ( null != dict ) {
					user.setName(user.getName() + "(" + dict.getName() + ")");
				}
				userDto.setParent(user);
			}
		}
		return success(userDto);
	}


	@Override
	public Response<Boolean> resetPass( Long id ) {
		User user = userServiceImpl.selectById(id);
		if ( user.getType() == UserTypeEnum.STUDENT.key() ) {
			String pass = user.getIdCardNo();
			if ( null == pass || pass.length() < 6 ) {
				pass = user.getStudentCode();
			}
			if ( pass != null && pass.length() > 5 ) {
				pass = pass.substring(pass.length() - 5, pass.length());
				user.setPassword(MD5.toMD5(pass));
				userServiceImpl.updateSelectiveById(user);
				return successOfBoolean();
			}
		} else {
			user.setPassword(MD5.toMD5(ManageConstant.DEFULT_PASS));
			userServiceImpl.updateSelectiveById(user);
			return successOfBoolean();
		}
		return failOfBoolean();
	}
}