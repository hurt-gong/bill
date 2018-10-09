package cn.yu2.baomihua.manage.mapper;

import cn.yu2.baomihua.manage.entity.KlassStudent;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;

/**
 *
 * KlassStudent 表数据库控制层接口
 *
 */
public interface KlassStudentMapper extends AutoMapper<KlassStudent> {


	public int getStudentSumById(@Param("id") Long id);
}