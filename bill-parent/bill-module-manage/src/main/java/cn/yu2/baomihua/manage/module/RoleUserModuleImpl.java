package cn.yu2.baomihua.manage.module;

import java.util.List;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.User;

/** 
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月27日 下午3:21:37 
 */
public class RoleUserModuleImpl extends AbstractModule implements IRoleUserModule {

	@Override
	public Response<List<User>> getUserByRoleId(long id) {

		return null;
	}
}
