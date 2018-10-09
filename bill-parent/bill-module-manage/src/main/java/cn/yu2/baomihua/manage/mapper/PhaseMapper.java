package cn.yu2.baomihua.manage.mapper;

import cn.yu2.baomihua.manage.entity.Phase;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;

/**
 *
 * Phase 表数据库控制层接口
 *
 */
public interface PhaseMapper extends AutoMapper<Phase> {
	public Phase getPhase(@Param("name") String name);
}