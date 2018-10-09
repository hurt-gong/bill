package cn.yu2.baomihua.manage.module;

import java.util.List;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.User;

/** 
 * @Description:角色人员module
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月27日 下午3:03:10 
 */
public interface IRoleUserModule {

	public Response<List<User>> getUserByRoleId(long id);
}
