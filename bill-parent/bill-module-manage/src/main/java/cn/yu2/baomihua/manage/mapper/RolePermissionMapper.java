package cn.yu2.baomihua.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;

import cn.yu2.baomihua.manage.entity.RolePermission;

/**
 *
 * RolePermission 表数据库控制层接口
 *
 */
public interface RolePermissionMapper extends AutoMapper<RolePermission> {

	List<String> selectPermissionCode(@Param("deptId")Long deptId);

	int deleteByDeptId(@Param("deptId")Long deptId);


}