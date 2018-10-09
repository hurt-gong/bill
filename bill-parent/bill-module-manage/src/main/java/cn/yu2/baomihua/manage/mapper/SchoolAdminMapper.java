package cn.yu2.baomihua.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;

import cn.yu2.baomihua.manage.dto.SchoolAdminDTO;
import cn.yu2.baomihua.manage.entity.SchoolAdmin;

/**
 *
 * SchoolAdmin 表数据库控制层接口
 *
 */
public interface SchoolAdminMapper extends AutoMapper<SchoolAdmin> {

	public List<SchoolAdminDTO> getAdmins(@Param("schoolId")Long schoolId);
}