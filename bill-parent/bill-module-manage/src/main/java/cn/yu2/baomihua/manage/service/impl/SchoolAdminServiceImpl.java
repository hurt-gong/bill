package cn.yu2.baomihua.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.manage.dto.SchoolAdminDTO;
import cn.yu2.baomihua.manage.entity.RoleTeacher;
import cn.yu2.baomihua.manage.entity.SchoolAdmin;
import cn.yu2.baomihua.manage.mapper.RoleTeacherMapper;
import cn.yu2.baomihua.manage.mapper.SchoolAdminMapper;
import cn.yu2.baomihua.manage.service.ISchoolAdminService;

/**
 *
 * SchoolAdmin 表数据服务层接口实现类
 *
 */
@Service
public class SchoolAdminServiceImpl extends BaseServiceImpl<SchoolAdminMapper, SchoolAdmin> implements ISchoolAdminService {
	
	@Autowired
	private RoleTeacherMapper roleTeacherMapper;

	@Override
	public List<SchoolAdminDTO> getAdmins(Long schoolId) {
		
		return baseMapper.getAdmins(schoolId);
	}

	@Override
	public boolean insertBatch(List<SchoolAdmin> entityList) {
		List<RoleTeacher> roleTeachers = new ArrayList<RoleTeacher>();
		for(SchoolAdmin schoolAdmin : entityList) {
			RoleTeacher roleTeacher = new RoleTeacher();
			roleTeacher.setRoleId(2L);
			roleTeacher.setTeacherId(schoolAdmin.getUserId());
			roleTeacher.setType(2);
			roleTeachers.add(roleTeacher);
		}
		roleTeacherMapper.insertBatch(roleTeachers);
		return super.insertBatch(entityList);
	}

	@Override
	public boolean deleteById(Long id) {
		SchoolAdmin schoolAdmin = baseMapper.selectById(id);
		RoleTeacher param = new RoleTeacher();
		param.setRoleId(2L);
		param.setTeacherId(schoolAdmin.getUserId());
		//删除用户校超级管理员角色
		roleTeacherMapper.deleteSelective(param);
		return super.deleteById(id);
	}

	@Override
	public boolean deleteSelective(SchoolAdmin entity) {
		List<SchoolAdmin> admins = baseMapper.selectList(new EntityWrapper<SchoolAdmin>(entity));
		if(admins != null && admins.size()>0){
			for(SchoolAdmin schoolAdmin : admins) {
				RoleTeacher param = new RoleTeacher();
				param.setRoleId(2L);
				param.setTeacherId(schoolAdmin.getUserId());
				//删除用户校超级管理员角色
				roleTeacherMapper.deleteSelective(param);
			}
		}
		return super.deleteSelective(entity);
	}


}