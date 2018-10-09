package cn.yu2.baomihua.web.controller.ucenter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.kisso.common.encrypt.MD5;

import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.web.controller.BaseController;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/ucenter")
public class UcenterController extends BaseController {
	
	/**
	 * 头像设置
	 */
	
	@RequestMapping("/setAvatar")
	public String setAvatar(HttpServletRequest request, Model model){
		return "/ucenter/set_avatar";
	}
	
	@ResponseBody
	@RequestMapping("/uploadAvatar")
	public String uploadAvatar(HttpServletRequest request, Model model){
		if(this.isPost()){
			String avatarUrl = request.getParameter("avatarUrl");
			if(StringUtils.isNotBlank(avatarUrl)){
				boolean rlt = userLoginModuleImpl.setHeadUrl(getCurrentUserId(), avatarUrl).getBody();
				if(rlt){
					return callbackSuccess(null);
				}
			}
		}
		return callbackFail(null);
	} 
	
	/**
	 * 修改密码
	 */
	@RequestMapping("/setPwd")
	public String setPwd(HttpServletRequest request, Model model){
		String tipMsg ="";
		if(this.isPost()){
			tipMsg = "修改密码失败！";
			String oldPwd = request.getParameter("oldPwd");
			String newPwd = request.getParameter("newPwd");
			if(StringUtils.isNotBlank(oldPwd) && StringUtils.isNotBlank(newPwd)){
				User user = this.getUser();
				if(user.getPassword().equals(MD5.toMD5(oldPwd))){
					boolean success = userLoginModuleImpl.setPassword(user.getId(),MD5.toMD5(newPwd)).getBody();
					if(success){
						tipMsg = "修改密码成功！";
						
					}
				}else{
					tipMsg = "当前密码错误！";
				}
				
			}
			
		}
		model.addAttribute("tipMsg", tipMsg);
		
		return "/ucenter/set_pwd";
	}

}
