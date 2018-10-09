package cn.yu2.baomihua.manage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.manage.entity.Permission;
import cn.yu2.baomihua.manage.entity.RolePermission;
import cn.yu2.baomihua.manage.mapper.PermissionMapper;
import cn.yu2.baomihua.manage.mapper.RolePermissionMapper;
import cn.yu2.baomihua.manage.service.IPermissionService;

/**
 *
 * Permission 表数据服务层接口实现类
 *
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper, Permission> implements IPermissionService {

	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	@Override
	public List<Permission> selectPermission(List<String> permissionCodeList) {
		/*StringBuffer sb = new StringBuffer();
		if(permissionCodeList!=null &&permissionCodeList.size()>0){
			for(int i=0;i<permissionCodeList.size();i++){
				if(i==0){
					sb.append("'"+permissionCodeList.get(i)+"'");
				}else{
					sb.append(","+"'"+permissionCodeList.get(i)+"'");
				}
		}
		}*/
		//return baseMapper.selectBatchCodes(sb.toString());
		List<Permission> list = new ArrayList<Permission>();
		for(int i=0;i<permissionCodeList.size();i++){
			Permission permission=baseMapper.selectBatchCode(permissionCodeList.get(i));
			list.add(permission);
		}
		return list;
	}

	@Override
	public List<String> selectPermissionCode(Long deptId) {	
		return rolePermissionMapper.selectPermissionCode(deptId);
	}

	@Override
	public List<Permission> selectPermissionAll() {
		return baseMapper.selectList(null);
	}

	@Override
	public int addPermissions(List<RolePermission> list,Long deptId) {
		rolePermissionMapper.deleteByDeptId(deptId);
		return rolePermissionMapper.insertBatch(list);
	}

	@Override
	public int deletePermissionByRoleId(Long roleId) {
		return rolePermissionMapper.deleteByDeptId(roleId);
	}

	@Override
	public List<Permission> selectPermissionAllByType(Boolean type) {
		Permission p = new Permission();
		p.setType(type);
		return baseMapper.selectList(new EntityWrapper(p));
	}

	@Override
	public Set<String> getPermission(Long userId) {
		
		return baseMapper.getPermission(userId);
	}






	
}