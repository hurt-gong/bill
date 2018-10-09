package cn.yu2.baomihua.manage.mapper;

import cn.yu2.baomihua.manage.entity.Knowledge;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;

/**
 *
 * Knowledge 表数据库控制层接口
 *
 */
public interface KnowledgeMapper extends AutoMapper<Knowledge> {


	public List<Knowledge> getKnowledgeList(@Param("phaseId") Long phaseId,@Param("subjectId") Long subjectId);
	
	public List<Knowledge> getKnowledgeListByPsrentId(@Param("parentId") Long parentId);
}