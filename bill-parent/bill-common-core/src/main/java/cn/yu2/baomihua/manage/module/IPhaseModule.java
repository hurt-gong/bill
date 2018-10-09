package cn.yu2.baomihua.manage.module;

import java.util.List;

import cn.yu2.baomihua.manage.entity.Phase;

/** 
 * @Description:学段module
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月27日 下午3:04:14 
 */
public interface IPhaseModule {
	
	public Phase getPhase(String name);
	
	public List<Phase> getPhaseList();
	
	public Phase getPhaseById(Long id);
}
