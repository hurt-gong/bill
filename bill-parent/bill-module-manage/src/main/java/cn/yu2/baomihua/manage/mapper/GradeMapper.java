package cn.yu2.baomihua.manage.mapper;

import cn.yu2.baomihua.manage.entity.Grade;

import java.util.List;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * Grade 表数据库控制层接口
 *
 */
public interface GradeMapper extends AutoMapper<Grade> {
	
	public List<Grade> getGradeList(Page<Grade> page);
	
	public List<Grade> getGradeListByPhaseId(Long phaseId);
	
	Long getGradeByKlassId(Long klassId);
}