package cn.yu2.baomihua.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.manage.enums.UserTypeEnum;
import cn.yu2.baomihua.manage.module.IStatisticsModule;


/**
 * 基础控制器
 * 
 * @author hubin
 * @Date 2016-04-18
 */
@Controller
public class IndexController extends BaseController {

	@Resource(name = "statisticsModuleImpl")
	private IStatisticsModule statisticsModuleImpl;


	@RequestMapping("/index")
	public String index() {
		return super.redirectTo("/account/login");
	}


	@RequestMapping("/idx")
	public String idx() {
		String returnUrl = "/index.html";
		User u = getUser();
		Long currentSchoolId = u.getSchoolId();
		Integer type = u.getType();
		if ( 0 == currentSchoolId ) {
			returnUrl = "/ucenter/distric/index.html";
		} else {
			if ( type == UserTypeEnum.STUDENT.key() ) {
				returnUrl = "/ucenter/student/index.html";
			} else if ( type == UserTypeEnum.TEACHER.key() ) {
				returnUrl = "/ucenter/teacher/index.html";
			} else if ( type == UserTypeEnum.PARENT.key() ) {
				returnUrl = "/ucenter/parent/index.html";
			}
		}
		return redirectTo(returnUrl);
	}


	@RequestMapping("/poly/upload")
	public String aaa() {
		return "/index/poly";
	}
}
