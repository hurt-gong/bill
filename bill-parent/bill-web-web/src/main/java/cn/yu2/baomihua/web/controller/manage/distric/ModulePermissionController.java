package cn.yu2.baomihua.web.controller.manage.distric;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.yu2.baomihua.annotation.Permission;
import cn.yu2.baomihua.constant.PermiConstant;
import cn.yu2.baomihua.web.controller.BaseController;

@Controller
@RequestMapping("/manage/distric/permission/module/")
public class ModulePermissionController extends BaseController {
	
	@Permission(PermiConstant.DISTRIC_MODULE)
	@RequestMapping("list")
	public String list(){
		
	
	return "/manage/distric/permission/module/list";
	}
}
	
