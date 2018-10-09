package cn.yu2.baomihua.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;

import cn.yu2.baomihua.manage.entity.Klass;

/**
 *
 * Klass 表数据库控制层接口
 *
 */
public interface KlassMapper extends AutoMapper<Klass> {

	public Klass getKlassName( @Param("id") Long id );

	public List<Klass> getKlassByIds(@Param("gradeId") Long gradeId,@Param("schoolId") Long schoolId,@Param("crUserId") Long crUserId);
}