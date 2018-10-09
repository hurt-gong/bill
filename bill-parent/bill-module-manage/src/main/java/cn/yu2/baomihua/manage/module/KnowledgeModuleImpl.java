package cn.yu2.baomihua.manage.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.manage.entity.Knowledge;
import cn.yu2.baomihua.manage.service.IKnowledgeService;

public class KnowledgeModuleImpl extends AbstractModule implements IKnowledgeModule {

	@Autowired
	private IKnowledgeService knowledgeService;
	
	@Override
	public List<Knowledge> getKnowledgeList(Long phaseId, Long subjectId) {
		return knowledgeService.getKnowledgeList(phaseId,subjectId);
	}

	@Override
	public List<Knowledge> getKnowledgeListByPsrentId(Long parentId) {
		return knowledgeService.getKnowledgeListByPsrentId(parentId);
	}

}
