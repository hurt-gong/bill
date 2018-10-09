package cn.yu2.baomihua.web.handler;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.framework.spring.SpringHelper;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;

import cn.yu2.baomihua.annotation.Permission;
import cn.yu2.baomihua.manage.module.IPermissionModule;

/** 
 * @Description:
 * 权限拦截器
 * @author wanglulu
 * @version 1.0 
 * @date  2016年11月9日 下午2:26:33 
 */
public class PermissionInterceptor implements HandlerInterceptor {

	protected static Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if ( handler instanceof HandlerMethod ) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Permission permission = handlerMethod.getMethod().getAnnotation(Permission.class);
			if ( permission == null ) {
				return true;
			}
			String permissionCode = permission.value();
			SSOToken st = (SSOToken) SSOHelper.attrToken(request);
			IPermissionModule permissionModuel = (IPermissionModule) SpringHelper
					.getBean(IPermissionModule.class);
			Set<String> permissionCodes = permissionModuel.getPermission(st.getId()).getBody();
			logger.warn(permissionCodes.toString());
			if ( permissionCodes != null && !permissionCodes.isEmpty() ) {
				if ( permissionCodes.contains(permissionCode) ) {
					return true;
				}
			}
		} else {
			return true;
		}

		logger.info("验证失败");
		response.sendError(403, "Forbidden");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
