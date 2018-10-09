package cn.yu2.baomihua.manage.module;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.yu2.baomihua.manage.entity.Knowledge;

/**
 * 知识点module
 * @author YFT
 */
public interface IKnowledgeModule {

	public List<Knowledge> getKnowledgeList(Long phaseId,Long subjectId);
	
	public List<Knowledge> getKnowledgeListByPsrentId(Long parentId);
}
