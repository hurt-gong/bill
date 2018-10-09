/**
 * CaptchaController.java net.yunxiaoyuan.sso.controller Copyright (c) 2014,
 * 北京微课创景教育科技有限公司版权所有.
 */

package cn.yu2.baomihua.web.controller.ucenter;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yu2.baomihua.core.common.EhcacheCaptcha;
import cn.yu2.baomihua.web.controller.BaseController;

/**
 * <p>
 * 验证码控制器
 * </p>
 * @author   hubin
 * @date	 2016-06-27 
 */
@Controller
@RequestMapping(value = "/captcha")
public class CaptchaController extends BaseController {

	/**
	 * 获取验证码
	 */
	@ResponseBody
	@RequestMapping("/checkcode")
	public void image( String token ) {
		try {
			EhcacheCaptcha.getInstance().generate(request, response.getOutputStream(), token);
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
}
