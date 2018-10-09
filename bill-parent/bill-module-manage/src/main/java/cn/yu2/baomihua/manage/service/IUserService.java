package cn.yu2.baomihua.manage.service;

import java.util.List;
import java.util.Map;

import com.baomidou.framework.service.ISuperService;
import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.manage.dto.UserDTO;
import cn.yu2.baomihua.manage.entity.User;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends ISuperService<User> {

	/**
	 * 分页查询用户列表
	 * @param user	用户
	 * @return listPage
	 */
	public List<User> getUserList( Page<User> page, User user );


	public List<UserDTO> getUserList( Page<UserDTO> page, Map<String, Object> paramMap, User user );


	/**
	 * 根据学生Id获取家长
	 */
	public User getStudentParent( Long studentId );


	public List<UserDTO> getUserList( Map<String, Object> paramMap, User user );
	
	public List<User> getUsersByIds(List<Long> ids);


}