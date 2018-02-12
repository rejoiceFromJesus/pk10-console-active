/**
 * 系统项目名称
 * com.pk10.active.console.interceptor
 * SessionInterceptor.java
 * 
 * 2017年6月10日-下午5:05:58
 *  2017金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.interceptor;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.pk10.active.console.common.constant.Constant;

/**
 *
 * SessionInterceptor
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月10日 下午5:05:58
 * 
 * @version 1.0.0
 *
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {
	
	private static Logger LOGGER = LoggerFactory.getLogger(SessionInterceptor.class);

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/*if(!Arrays.asList(Constant.PASS_PATHS).contains(request.getRequestURI().replace(request.getContextPath(), "")) 
				&& request.getSession().getAttribute(Constant.SESSION_KEY) == null){
			LOGGER.warn("visit path["+request.getRequestURI()+"]:no session, redirect to /login");
			response.sendRedirect(request.getContextPath()+"/page/login.html"); 
			return false;
		}  */
		return true; 
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
