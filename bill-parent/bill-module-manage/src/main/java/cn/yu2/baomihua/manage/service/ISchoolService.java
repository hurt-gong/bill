package cn.yu2.baomihua.manage.service;

import cn.yu2.baomihua.manage.dto.SchoolDTO;
import cn.yu2.baomihua.manage.entity.School;

import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * School 表数据服务层接口
 *
 */
public interface ISchoolService extends ISuperService<School> {

	Page<SchoolDTO> getSchoolByName(Page<SchoolDTO> page, String name);
	
	public School getSchoolById(Long id);
	
	public List<School> getSchoolList();
}