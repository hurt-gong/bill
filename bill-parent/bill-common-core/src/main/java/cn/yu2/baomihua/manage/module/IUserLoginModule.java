/**
 * IUserLoginModule.java cn.yu2.baomihua.manage.module Copyright (c) 2016,
 * 北京微课创景教育科技有限公司版权所有.
 */

package cn.yu2.baomihua.manage.module;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.User;

/**
 * 
 * <p>
 *
 * @author   ShiBin
 * @date	 2016年6月1日 
 * @version  1.0.0	 
 */
public interface IUserLoginModule {

	Response<User> getUser( User user );


	Response<User> getCurrentUser( long userId );


	Response<Boolean> setPassword( Long id, String password );


	Response<Boolean> setHeadUrl( Long currentUserId, String avatarUrl );


	/**保存用户登录统计信息*/
	Response<Boolean> addLoginStatistics( Long userId, Long schoolId, String loginIp );
}

