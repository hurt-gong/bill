package cn.yu2.baomihua.manage.module;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.dto.SchoolAdminDTO;
import cn.yu2.baomihua.manage.dto.SchoolDTO;
import cn.yu2.baomihua.manage.entity.School;
import cn.yu2.baomihua.manage.entity.SchoolAdmin;
import cn.yu2.baomihua.manage.entity.User;

/** 
 * @Description:学校module
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月26日 下午4:56:08 
 */
public interface ISchoolModule {

	/**
	 * 分页查询学校信息
	 * @param page
	 * @param name
	 * @return
	 */
	public Response<Page<SchoolDTO>> getSchoolByName(Page<SchoolDTO> page, String name);
	
	/**
	 * 添加学校管理员
	 * @param userId
	 * @return
	 */
	public Response<User> insertAdmin(Long userId);
	
	/**
	 * 删除学校管理员
	 * @param userId
	 * @return
	 */
	public Response<Boolean> deleteAdmin(Long id);
	
	/**
	 * 查询学校管理员id
	 * @param schoolId
	 * @return
	 */
	public Response<Set<Long>> getAdminIds(Long schoolId);
	
	/**
	 * 查询学校管理员
	 * @param schoolId
	 * @return
	 */
	public Response<List<SchoolAdminDTO>> getAdmins(Long schoolId);
	
	/**
	 * 修改校管理员
	 * @param schoolAdmins
	 * @return
	 */
	public Response<Boolean> insertAdmins(List<SchoolAdmin> schoolAdmins);
	
	public Response<Boolean> deleteAdmins(Long schoolId);
	
	public School getSchoolById(Long id);
	
	public List<School> getSchoolList();
}
