package cn.yu2.baomihua.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.manage.entity.Phase;
import cn.yu2.baomihua.manage.mapper.PhaseMapper;
import cn.yu2.baomihua.manage.service.IPhaseService;

/**
 *
 * Phase 表数据服务层接口实现类
 *
 */
@Service
public class PhaseServiceImpl extends BaseServiceImpl<PhaseMapper, Phase> implements IPhaseService {

	@Resource(name = "phaseMapper")
	private PhaseMapper phaseMapper;


	@Override
	public boolean isExist( Phase phase ) {
		Phase phases = phaseMapper.selectOne(phase);
		if ( phases != null ) {
			return true;
		}
		return false;
	}


	@Override
	public boolean addPhase( Phase phase ) {
		int count = phaseMapper.insertSelective(phase);
		if ( count > 0 ) {
			return true;
		}
		return false;
	}


	public Phase selectById( Long id ) {
		return phaseMapper.selectById(id);
	}


	@Override
	public Phase getPhase(String name) {
		return phaseMapper.getPhase(name);
	}


	@Override
	public List<Phase> getPhaseList() {
		List<Phase> list = baseMapper.selectList(null);
		return list;
	}


	@Override
	public Phase getPhaseById(Long id) {
		return baseMapper.selectById(id);
	}

}