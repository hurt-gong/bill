package cn.yu2.baomihua.manage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.manage.dto.SchoolDTO;
import cn.yu2.baomihua.manage.entity.School;
import cn.yu2.baomihua.manage.mapper.SchoolMapper;
import cn.yu2.baomihua.manage.service.ISchoolService;

/**
 *
 * School 表数据服务层接口实现类
 *
 */
@Service
public class SchoolServiceImpl extends BaseServiceImpl<SchoolMapper, School> implements ISchoolService {

	
	@Override
	public Page<SchoolDTO> getSchoolByName(Page<SchoolDTO> page, String name) {
		List<SchoolDTO> data = baseMapper.getSchoolByName(page, name);
		page.setRecords(data);
		return page;
	}

	@Override
	public School getSchoolById(Long id) {
		School school = baseMapper.selectById(id);
		return school;
	}

	@Override
	public List<School> getSchoolList() {
		return baseMapper.selectList(null);
	}


}