/**
 * IUserLoginService.java cn.yu2.baomihua.manage.service.impl Copyright (c) 2016,
 *           .
 */

package cn.yu2.baomihua.manage.service;

import cn.yu2.baomihua.manage.entity.User;

/**
 * 
 * <p>
 *
 * @author   ShiBin
 * @date	 2016年6月1日 
 * @version  1.0.0	 
 */
public interface IUserLoginService {

	User getUser( User user );


	User getCurrentUser( long userId );


	int updateUser(User user);

}

