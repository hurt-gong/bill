package cn.yu2.baomihua.manage.service;

import cn.yu2.baomihua.manage.entity.Phase;

import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * Phase 表数据服务层接口
 *
 */
public interface IPhaseService extends ISuperService<Phase> {

	public boolean isExist( Phase phase );

	public boolean addPhase( Phase phase );
	
	public Phase getPhase(String name);
	
	public List<Phase> getPhaseList();
	
	public Phase getPhaseById(Long id);
}