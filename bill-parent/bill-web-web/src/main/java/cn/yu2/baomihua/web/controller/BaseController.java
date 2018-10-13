package cn.yu2.baomihua.web.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.framework.controller.SuperController;
import com.baomidou.framework.mail.MailHelper;

import cn.yu2.baomihua.core.exception.WebException;
import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.manage.module.IUserLoginModule;
import cn.yu2.baomihua.web.JsonResult;

/**
 * 
 * <p>
 * 基础控制器
 * </p>
 * 
 * @author hubin
 * @Date 2016-04-18
 */
public class BaseController extends SuperController {

	@Autowired
	protected MailHelper mail;

	@Resource(name = "userLoginModuleImpl")
	protected IUserLoginModule userLoginModuleImpl;
	
 
	/**
	 * 用户对象获取
	 */
	protected User getUser() {
		return getCurrentUser(getCurrentUserId());
	}


	/**
	 * 获取学校ID
	 */
	protected Long getCurrentSchoolId() {
		return getUser().getSchoolId();
	}


	/**
	 * 根据用户ID 获取当前用户信息
	 */
	protected User getCurrentUser( long userId ) {
		User user = userLoginModuleImpl.getCurrentUser(userId).getBody();
		/** 异常提示 */
		if ( user == null ) {
			throw new WebException("用户不存在,请重新登录");
		}
		return user;
	}
	
	
	/**
	 * 
	 * <p>Title: retsuccess</p>  
	 * <p>Description: </p>  
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	protected JsonResult retsuccess(int code,String msg,Object data){
		JsonResult js = new JsonResult();
		js.setCode(code);
		js.setData(data);
		js.setMsg(msg);
		return js;
	}
}
