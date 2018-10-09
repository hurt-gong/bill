package cn.yu2.baomihua.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.manage.dto.SchoolDTO;
import cn.yu2.baomihua.manage.entity.School;

/**
 *
 * School 表数据库控制层接口
 *
 */
public interface SchoolMapper extends AutoMapper<School> {

	public List<SchoolDTO> getSchoolByName(Page<SchoolDTO> page, @Param("name")String name);
}