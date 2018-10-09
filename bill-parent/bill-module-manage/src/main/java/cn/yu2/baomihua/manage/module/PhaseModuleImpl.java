package cn.yu2.baomihua.manage.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.manage.entity.Phase;
import cn.yu2.baomihua.manage.service.IPhaseService;

/** 
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月27日 下午3:20:31 
 */
public class PhaseModuleImpl extends AbstractModule implements IPhaseModule {
	
	@Autowired
	private IPhaseService phaseService;	

	@Override
	public Phase getPhase(String name) {
		return phaseService.getPhase(name);
	}

	@Override
	public List<Phase> getPhaseList() {
		
		return phaseService.getPhaseList();
	}

	@Override
	public Phase getPhaseById(Long id) {
		return phaseService.getPhaseById(id);
	}

}
