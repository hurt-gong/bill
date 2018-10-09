package cn.yu2.baomihua.manage.mapper;

import cn.yu2.baomihua.manage.entity.Permission;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;

/**
 *
 * Permission 表数据库控制层接口
 *
 */
public interface PermissionMapper extends AutoMapper<Permission> {


	List<Permission> selectBatchCodes(@Param("str")String string);

	Permission selectBatchCode(@Param("str")String str);

	/**
	 * 获取权限
	 * @param userId
	 * @return
	 */
	public Set<String> getPermission(@Param("userId")Long userId);

}