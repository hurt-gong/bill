package cn.yu2.baomihua.manage.service;

import cn.yu2.baomihua.manage.entity.Grade;

import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * Grade 表数据服务层接口
 *
 */
public interface IGradeService extends ISuperService<Grade> {

	public Page<Grade> getGradeList(Page<Grade> page);
	
	public List<Grade> getGradeList();
	
	public List<Grade> getGradeListByPhaseId(Long phaseId);
	
	public Grade getGradeById(Long id);
	
}