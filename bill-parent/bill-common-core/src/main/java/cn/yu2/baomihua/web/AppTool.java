package cn.yu2.baomihua.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.tools.config.DefaultKey;

import com.baomidou.framework.spring.SpringHelper;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.Token;

import cn.yu2.baomihua.manage.entity.School;
import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.manage.module.ISchoolModule;
import cn.yu2.baomihua.manage.module.IUserLoginModule;

/**
 * <p>
 * veloctiy 视图标签工具类，例如： $app.current().name （获取当前登录用户名）
 * </p>
 * @author   hubin
 * @date	 2016-06-28
 */
@DefaultKey("app")
public class AppTool {

	/**
	 * 当前登录用户信息
	 */
	public User currentUser() {
		HttpServletRequest request = SpringHelper.getHttpServletRequest();
		Token token = SSOHelper.attrToken(request);
		if ( token == null ) {
			return null;
		}
		IUserLoginModule ulm = SpringHelper.getBean(IUserLoginModule.class);
		return ulm.getCurrentUser(token.getId()).getBody();
	}
	/**
	 * 当前学校
	 * @return
	 */
	public School currentSchool() {
		User u = currentUser();
		if(u == null) {
			return null;
		}
		ISchoolModule schoolModule = SpringHelper.getBean(ISchoolModule.class);
		return schoolModule.getSchoolById(u.getSchoolId());
	}
}
