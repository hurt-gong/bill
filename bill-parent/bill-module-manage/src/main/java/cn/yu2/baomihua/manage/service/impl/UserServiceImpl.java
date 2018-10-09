package cn.yu2.baomihua.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.manage.dto.UserDTO;
import cn.yu2.baomihua.manage.entity.StudentParent;
import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.manage.mapper.StudentParentMapper;
import cn.yu2.baomihua.manage.mapper.UserMapper;
import cn.yu2.baomihua.manage.service.IUserService;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	private StudentParentMapper studentParentMapper;
	
	@Autowired
	private UserMapper UserMapper;


	@Override
	public List<User> getUserList( Page<User> page, User user ) {
		return baseMapper.getUserList(page, user);
	}


	@Override
	public List getUserList( Page<UserDTO> page, Map<String, Object> paramMap, User user ) {
		return baseMapper.getUserDtoList(page, paramMap, user);
	}


	@Override
	public List getUserList( Map<String, Object> paramMap, User user ) {
		return baseMapper.getUserDtoList(paramMap, user);
	}


	@Override
	public User getStudentParent( Long studentId ) {
		User user = new User();
		StudentParent parent = new StudentParent();
		parent.setStudentId(studentId);
		StudentParent stuParent = studentParentMapper.selectOne(parent);
		if ( stuParent != null ) {
			user = baseMapper.selectById(stuParent.getParentId());
		}
		return user;
	}


	@Override
	public List<User> getUsersByIds(List<Long> ids) {
		
		return UserMapper.selectBatchIds(ids);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}