package cn.yu2.baomihua.manage.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.manage.enums.UserTypeEnum;
import cn.yu2.baomihua.manage.service.IUserService;

/** 
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月27日 下午3:22:52 
 */
public class UserModuleImpl extends AbstractModule implements IUserModule {

	@Autowired
	private IUserService userService;


	@Override
	public Response<Map<Character, List<User>>> getTeacherTreeMap( Long schoolId ) {
		User param = new User();
		param.setSchoolId(schoolId);
		param.setType(UserTypeEnum.TEACHER.key());
		List<User> usrList = userService.selectList(param);
		Map<Character, List<User>> userMap = new TreeMap<Character, List<User>>();
		for ( User user : usrList ) {
			if ( null == user.getSpell() || user.getSpell().length() < 1 ) {
				continue;
			}
			Character ch = user.getSpell().toUpperCase().charAt(0);
			List<User> userList = userMap.get(ch);
			if ( userList == null ) {
				userList = new ArrayList<>();
				userMap.put(ch, userList);
			}
			userList.add(user);
		}
		return success(userMap);
	}


	@Override
	public Response<List<User>> getUsersByIds(List<Long> ids,Long schoolId) {
		
		User user = new User();
		List<User> userList = new ArrayList<>();
		for (Long id : ids) {
			user.setId(id);
			User user2 = userService.selectOne(user);
			if (user2!=null) {
				if (schoolId!=null && user2.getSchoolId() == schoolId) {
						userList.add(user2);
				} 
			}
		}
		return success(userList);
	}

	/** 通过id查询user*/
	public User getUserById(Long userId){
		return userService.selectById(userId);
	}


	@Override
	public List<User> getUserByEntity(User user) {
		
		return userService.selectList(user);
	}
}
