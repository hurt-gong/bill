/**
 * IndexController.java cn.bjjh.controller.resource Copyright (c) 2015,
 * 北京微课创景教育科技有限公司版权所有.
 */

package cn.yu2.baomihua.web.controller.ucenter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.baomidou.kisso.Token;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.common.encrypt.MD5;
import com.baomidou.kisso.common.util.HttpUtil;
import com.baomidou.kisso.common.util.RandomUtil;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;

import cn.yu2.baomihua.core.common.EhcacheCaptcha;
import cn.yu2.baomihua.core.common.util.MatcherUtil;
import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.manage.module.IUserLoginModule;
import cn.yu2.baomihua.web.controller.BaseController;


/**
 * 账号相关
 * 
 * @author shibin
 * @date 2016年06月02日
 * @version 1.0.0
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {

	@Resource(name = "userLoginModuleImpl")
	protected IUserLoginModule userLoginModuleImpl;


	/**
	 * 登录
	 */
	@RequestMapping("/login")
	public String login( Model model ) {
		Token tk = SSOHelper.getToken(request);
		if ( tk != null ) {
			return redirectUrl(null);
		}
		model.addAttribute("token", RandomUtil.get32UUID());
		String returnURL = "/index";
		try {
			returnURL = URLDecoder.decode(request.getParameter("ReturnURL"), "utf-8");
		} catch ( UnsupportedEncodingException e ) {
			e.printStackTrace();
		}
		model.addAttribute("ReturnURL", returnURL);
		return "/login";
	}


	/**
	 * 首次登录
	 */
	@RequestMapping("/loginFirst")
	public String loginFirst( Model model ) {
		Token tk = SSOHelper.getToken(request);
		if ( tk != null ) {
			return redirectUrl(null);
		}
		if ( isPost() ) {
			String rlt = loginRedirectUrl(model, null);
			if ( rlt != null ) {
				return rlt;
			}
		}
		model.addAttribute("token", RandomUtil.get32UUID());
		return "/login_first";
	}


	/**
	 * 忘记密码
	 */
	@RequestMapping("/forgotPassword")
	public String forgotPassword( Model model ) {
		model.addAttribute("token", RandomUtil.get32UUID());
		return "/forgot_password";
	}


	@ResponseBody
	@RequestMapping(value = "/ajaxlogin", method = RequestMethod.POST)
	public String ajaxLogin( Model model ) {
		String retUrl = request.getParameter("ReturnURL");
		Token tk = SSOHelper.getToken(request);
		if ( tk != null ) {
			return callbackSuccess(retUrl);
		}
		String rlt = loginRedirectUrl(model, retUrl);
		if ( rlt != null ) {
			return callbackSuccess(retUrl);
		}
		model.addAttribute("token", RandomUtil.get32UUID());
		model.addAttribute("ReturnURL", retUrl);
		Map<String, Object> modelMap = model.asMap();
		String errorMsg = (String) modelMap.get("errorMsg");
		return callbackFail(errorMsg);
	}


	/**
	 * 退出登录
	 */
	@Login(action = Action.Skip)
	@RequestMapping("/logout")
	public String logout( HttpServletRequest request, HttpServletResponse response ) {
		SSOHelper.clearLogin(request, response);
		return redirectTo(getRetUrl(request, "/index.html"));
	}


	/**
	 * 登录重定向（执行登录逻辑）
	 */
	protected String loginRedirectUrl( Model model, String retUrl ) {
		String errorMsg = "登录密码错误";
		WafRequestWrapper req = new WafRequestWrapper(request);
		String username = req.getParameter("username");
		if ( StringUtils.isNotBlank(username) ) {
			String checkcode = request.getParameter("checkcode");
			if ( StringUtils.isNotBlank(checkcode) ) {
				String token = request.getParameter("token");
				boolean rlt = EhcacheCaptcha.getInstance().verification(request, token, checkcode);
				if ( rlt ) {
					String password = req.getParameter("password");
					if ( StringUtils.isNotBlank(password) ) {
						User user = new User();
						if ( MatcherUtil.isMobile(username) ) {
							user.setMobile(username);
						} else if ( MatcherUtil.isStudentNumber(username) ) {
							user.setStudentCode(username);
						} else {
							user.setLoginName(username);
						}
						User loginUser = userLoginModuleImpl.getUser(user).getBody();
						if ( loginUser != null ) {
							if ( loginUser.getPassword().equals(MD5.toMD5(password)) ) {
								/**
								 * 登录成功
								 */
								SSOToken st = new SSOToken(request);
								st.setId(loginUser.getId());
								SSOHelper.setSSOCookie(request, response, st, false);
								/*保存登录统计*/
								userLoginModuleImpl.addLoginStatistics(loginUser.getId(), loginUser.getSchoolId(),
									st.getIp());
								return redirectUrl(retUrl);
							}
						} else {
							errorMsg = "登录账号不存在";
						}
					} else {
						errorMsg = "登录密码为空";
					}
				} else {
					errorMsg = "请输入正确的验证码！";
				}
			} else {
				errorMsg = "验证码不能为空";
			}
			model.addAttribute("username", username);
		} else {
			errorMsg = "登录账号为空 ";
		}
		model.addAttribute("errorMsg", errorMsg);
		return null;
	}


	/**
	 * 返回地址
	 * 
	 * @param request
	 * 				当前请求
	 * @param url
	 * 				请求定向地址
	 * @return
	 */
	protected String getRetUrl( HttpServletRequest request, String url ) {
		String referer = request.getHeader("referer");
		if ( referer != null ) {
			SSOConfig config = SSOConfig.getInstance();
			if ( referer.contains(config.getCookieDomain()) ) {
				return HttpUtil.encodeRetURL(url, config.getParamReturl(), referer);
			}
		}
		return url;
	}


	/**
	 * 获取登录成功跳转地址
	 * <p>
	 * @param retUrl
	 * 				跳转地址
	 * @return String
	 */
	protected String redirectUrl( String retUrl ) {
		String toUrl = "/idx.html";
		if ( StringUtils.isNotBlank(retUrl) ) {
			try {
				toUrl = URLDecoder.decode(retUrl, "UTF-8");
			} catch ( Exception e ) {
				logger.error("sso login redirectTo error. {}", e.toString());
			}
		}
		return redirectTo(toUrl);
	}


}
