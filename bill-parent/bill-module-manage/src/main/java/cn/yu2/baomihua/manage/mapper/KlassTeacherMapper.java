package cn.yu2.baomihua.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.manage.dto.UserDTO;
import cn.yu2.baomihua.manage.entity.KlassTeacher;

/**
 *
 * KlassTeacher 表数据库控制层接口
 *
 */
public interface KlassTeacherMapper extends AutoMapper<KlassTeacher> {

	public List<KlassTeacher> getTeachTeacher( Page<UserDTO> page, @Param("paramMap") Map<String, Object> paramMap );


}