package cn.yu2.baomihua.manage.module;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.dto.BaseCodeDTO;
import cn.yu2.baomihua.manage.dto.UserDTO;
import cn.yu2.baomihua.manage.entity.Dict;
import cn.yu2.baomihua.manage.entity.Klass;
import cn.yu2.baomihua.manage.entity.User;

/** 
 * @Description:
 * 		区人员管理interface
 * @author lizhixiao
 * @version 1.0 
 * @date  2016-06-12
 */
public interface IStaffModule {

	/**
	 * 分页查询用户列表
	 * @param name   用户名
	 * @param type		用户类型
	 * @param status	用户状态
	 * @return listPage
	 */
	public Response<Page<User>> getUserList( Page<User> page, User user );


	/**
	 * 添加用户
	 * @param user	用户实体
	 * @return Boolean 操作结果
	 */
	public Response<Boolean> addUser( User user );


	/**
	 * 更新用户
	 * @param user	用户实体
	 * @return Boolean 操作结果
	 */
	public Response<Boolean> updateUserById( User user );


	/**
	 * 删除用户
	 * @param user	用户实体
	 * @return Boolean 操作结果
	 */
	public Response<Boolean> delUserById( Long id );


	/**
	 * 根据条件查询用户列表
	 * @param type		用户类型
	 * @param status	用户状态
	 * @return list
	 */
	public Response<List<User>> getUserList( User user );


	/**
	 * 根据用户Id批量删除
	 * @param idList	用户Id List
	 * @return 影响记录条数
	 */
	public Response<Integer> delUserList( List<Long> idList );


	/**
	 * 批量添加用户
	 * @param userList	用户实体List
	 * @return Boolean 操作结果
	 */
	public Response<Boolean> importUserList( List<User> userList );


	/**
	 * 查询学生
	 * @param userList	用户实体List
	 * @return Boolean 操作结果
	 */
	public Response<Page<UserDTO>> getStudentList( Page<UserDTO> page, Map<String, Object> paramMap, User user );


	/**获取年级班级列表*/
	public Response<BaseCodeDTO> getBaseCode( Long gradeId, Long schoolId );


	/**获取班级列表*/
	public Response<List<Klass>> getKlassList( Long gradeId, Long schoolId );


	/**根据参数获取用户列表*/
	public Response<List<UserDTO>> getStudentList( Map<String, Object> paramMap, User user );


	public Response<Page<UserDTO>> getTeacherList( Page<UserDTO> page, Map<String, Object> paramMap, User user );


	/**根据类型获取字典列表*/
	public Response<List<Dict>> getDictList( int type );


	/**获取学生详细信息*/
	public Response<UserDTO> getUserDTO( Long studentId );


	/**重置密码*/
	public Response<Boolean> resetPass( Long id );


}
