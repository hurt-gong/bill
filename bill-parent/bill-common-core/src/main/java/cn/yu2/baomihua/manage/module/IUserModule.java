package cn.yu2.baomihua.manage.module;

import java.util.List;
import java.util.Map;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.User;

/** 
 * @Description:用户module
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月27日 下午3:11:10 
 */
public interface IUserModule {

	public Response<Map<Character, List<User>>> getTeacherTreeMap(Long schoolId);
	/** 都不能为null*/
	public Response<List<User>> getUsersByIds(List<Long> ids,Long schoolId);

	/** 通过id查询user*/
	public User getUserById(Long userId);
	
	/** 通过entity查询用户 */
	public List<User> getUserByEntity(User user);
}
