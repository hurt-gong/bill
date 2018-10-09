package cn.yu2.baomihua.manage.service;

import cn.yu2.baomihua.manage.entity.Knowledge;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.framework.service.ISuperService;

/**
 *
 * Knowledge 表数据服务层接口
 *
 */
public interface IKnowledgeService extends ISuperService<Knowledge> {

	public List<Knowledge> getKnowledgeList(@Param("phaseId") Long phaseId,@Param("subjectId") Long subjectId);
	
	public List<Knowledge> getKnowledgeListByPsrentId(@Param("parentId") Long parentId);

}