package cn.yu2.baomihua.web.handler;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.framework.spring.SpringHelper;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.baomidou.kisso.annotation.Login;

import cn.yu2.baomihua.manage.module.IStatisticsModule;
import cn.yu2.baomihua.util.DateUtils;


public class PageViewRecordInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler )
		throws Exception {
		if ( handler instanceof HandlerMethod ) {
			String userUrl = request.getRequestURI();
			if ( userUrl.startsWith("/500.html")
					|| userUrl.startsWith("/403.html") || userUrl.startsWith("/404.html") ) {
				return true;
			}
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			Login login = method.getAnnotation(Login.class);
			//判断是否登录并不是ajax请求
			if ( login == null && request.getHeader("x-requested-with") == null ) {
				//如果是ajax请求响应头会有，x-requested-with  ) {
				SSOToken st = (SSOToken) SSOHelper.attrToken(request);
				new Thread(new insertPageViewRecord(st)).start();
			}
		}
		return true;
	}


	@Override
	public void postHandle( HttpServletRequest request,
			HttpServletResponse response,
			Object handler,
			ModelAndView modelAndView )
		throws Exception {
	}


	@Override
	public void afterCompletion( HttpServletRequest request,
			HttpServletResponse response,
			Object handler,
			Exception ex )
		throws Exception {
	}


	class insertPageViewRecord implements Runnable {


		private SSOToken st;


		public insertPageViewRecord( SSOToken st ) {
			super();
			this.st = st;
		}


		@Override
		public void run() {
			IStatisticsModule statisticsModuleImpl = SpringHelper.getBean(IStatisticsModule.class);
			if ( null != st ) statisticsModuleImpl
					.updateLoginStatistics(st.getId(), st.getIp(), DateUtils.parseyyyyMMddDate("")).getBody();
		}
	}

}
