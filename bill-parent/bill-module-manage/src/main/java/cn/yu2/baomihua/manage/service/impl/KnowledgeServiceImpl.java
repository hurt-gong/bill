package cn.yu2.baomihua.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yu2.baomihua.manage.entity.Knowledge;
import cn.yu2.baomihua.manage.mapper.KnowledgeMapper;
import cn.yu2.baomihua.manage.service.IKnowledgeService;
import cn.yu2.baomihua.core.service.BaseServiceImpl;

/**
 *
 * Knowledge 表数据服务层接口实现类
 *
 */
@Service
public class KnowledgeServiceImpl extends BaseServiceImpl<KnowledgeMapper, Knowledge> implements IKnowledgeService {

	@Autowired
	private KnowledgeMapper knowledgeMapper;
	
	@Override
	public List<Knowledge> getKnowledgeList(Long phaseId,Long subjectId) {
		return knowledgeMapper.getKnowledgeList(phaseId,subjectId);
	}

	@Override
	public List<Knowledge> getKnowledgeListByPsrentId(Long parentId) {
		return knowledgeMapper.getKnowledgeListByPsrentId(parentId);
	}


}