package cn.yu2.baomihua.manage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.yu2.baomihua.manage.dto.RoleTeacherDTO;
import cn.yu2.baomihua.manage.entity.RoleTeacher;
import cn.yu2.baomihua.manage.mapper.RoleTeacherMapper;
import cn.yu2.baomihua.manage.service.IRoleTeacherService;
import cn.yu2.baomihua.core.service.BaseServiceImpl;

/**
 *
 * RoleTeacher 表数据服务层接口实现类
 *
 */
@Service
public class RoleTeacherServiceImpl extends BaseServiceImpl<RoleTeacherMapper, RoleTeacher> implements IRoleTeacherService {

	@Override
	public List<RoleTeacherDTO> getRoleTeachers(Long roleId) {
		
		return baseMapper.getRoleTeachers(roleId);
	}


}