package cn.yu2.baomihua.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.manage.dto.SchoolDTO;
import cn.yu2.baomihua.manage.entity.Grade;
import cn.yu2.baomihua.manage.mapper.GradeMapper;
import cn.yu2.baomihua.manage.service.IGradeService;
import cn.yu2.baomihua.core.service.BaseServiceImpl;

/**
 *
 * Grade 表数据服务层接口实现类
 *
 */
@Service
public class GradeServiceImpl extends BaseServiceImpl<GradeMapper, Grade> implements IGradeService {

	@Autowired
	private GradeMapper gradeMapper;
	
	
	public Page<Grade> getGradeList(Page<Grade> page) {
		List<Grade> data = baseMapper.getGradeList(page);
		page.setRecords(data);
		return page;
	}

	@Override
	public List<Grade> getGradeList() {
		
		return baseMapper.selectList(null);
	}

	@Override
	public List<Grade> getGradeListByPhaseId(Long phaseId) {
		
		return gradeMapper.getGradeListByPhaseId(phaseId);
	}

	@Override
	public Grade getGradeById(Long id) {
		return baseMapper.selectById(id);
	}
}