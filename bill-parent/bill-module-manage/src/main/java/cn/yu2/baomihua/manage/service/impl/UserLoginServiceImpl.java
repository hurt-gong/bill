package cn.yu2.baomihua.manage.service.impl;

import org.springframework.stereotype.Service;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.manage.mapper.UserMapper;
import cn.yu2.baomihua.manage.service.IUserLoginService;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserLoginServiceImpl extends BaseServiceImpl<UserMapper, User>implements IUserLoginService { 

	@Override
	public User getUser( User user ) {
		return baseMapper.selectOne(user);
	}


	@Override
	public User getCurrentUser( long userId ) {

		return baseMapper.selectById(userId);

	}


	@Override
	public int updateUser(User user) {
		
		return baseMapper.updateSelectiveById(user);
	}





}