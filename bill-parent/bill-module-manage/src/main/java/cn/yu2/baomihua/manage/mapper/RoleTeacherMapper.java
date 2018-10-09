package cn.yu2.baomihua.manage.mapper;

import cn.yu2.baomihua.manage.dto.RoleTeacherDTO;
import cn.yu2.baomihua.manage.entity.RoleTeacher;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.mapper.AutoMapper;

/**
 *
 * RoleTeacher 表数据库控制层接口
 *
 */
public interface RoleTeacherMapper extends AutoMapper<RoleTeacher> {

	public List<RoleTeacherDTO> getRoleTeachers(@RequestParam()Long roleId);

}