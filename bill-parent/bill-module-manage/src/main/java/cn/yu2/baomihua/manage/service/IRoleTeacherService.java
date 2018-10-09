package cn.yu2.baomihua.manage.service;

import java.util.List;

import com.baomidou.framework.service.ISuperService;

import cn.yu2.baomihua.manage.dto.RoleTeacherDTO;
import cn.yu2.baomihua.manage.entity.RoleTeacher;

/**
 *
 * RoleTeacher 表数据服务层接口
 *
 */
public interface IRoleTeacherService extends ISuperService<RoleTeacher> {

	public List<RoleTeacherDTO> getRoleTeachers(Long roleId);

}