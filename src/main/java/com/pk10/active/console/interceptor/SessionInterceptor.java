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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.CharSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.pk10.active.console.common.bean.CodeMsg;
import com.pk10.active.console.common.bean.Result;
import com.pk10.active.console.common.constant.Constant;
import com.pk10.active.console.common.util.JsonUtil;
import com.pk10.active.console.entity.User;
import com.pk10.active.console.handler.UnauthorizedException;
import com.pk10.active.console.handler.UncertificatedException;

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
		User user = (User) request.getSession().getAttribute(Constant.SESSION_KEY);
		String requestURI = request.getRequestURI();
		if(request.getSession().getAttribute(Constant.SESSION_KEY) != null){
			if(user.getIsAdmin() == 0){
				if(requestURI.startsWith("/client")){
					return true;
				}else{
					 response.setCharacterEncoding("UTF-8");
					 response.getWriter().write(JsonUtil.toJson(Result.error(CodeMsg.UNAUTHORIZED)));
					 response.setStatus(HttpStatus.UNAUTHORIZED.value());
					 response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
					 return false;
				}
			}
		}else{
			if(ArrayUtils.contains(Constant.PASS_PATHS, requestURI)){
				return true;
			}
			if(requestURI.startsWith("/client")){
				 response.setCharacterEncoding("UTF-8");
				 response.getWriter().write(JsonUtil.toJson(Result.error(CodeMsg.UNCERTIFICATED)));
				 response.setStatus(HttpStatus.UNAUTHORIZED.value());
				 response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
				 return false;
			}else{
				response.sendRedirect("/page/login.html");
				return false;
			}
		}
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
