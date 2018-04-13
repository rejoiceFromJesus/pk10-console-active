/**
 * 系统项目名称
 * com.pk10.active.console.controller.client
 * ClientUserController.java
 * 
 * 2018年3月22日-上午9:09:07
 *  2018金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.controller.client;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pk10.active.console.common.bean.CodeMsg;
import com.pk10.active.console.common.bean.Result;
import com.pk10.active.console.common.constant.Constant;
import com.pk10.active.console.common.util.CookieUtils;
import com.pk10.active.console.entity.User;
import com.pk10.active.console.handler.InvalidParamException;
import com.pk10.active.console.service.UserService;
import com.pk10.active.console.vo.LoginVo;

/**
 *
 * ClientUserController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2018年3月22日 上午9:09:07
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("/client/user")
@Api(tags = "用户模块")
public class ClientUserController {
	
	
	@Autowired
	UserService userService;
	
	@PostMapping("login")
	@ApiOperation(value = "用户登录", notes = "使用手机号和密码进行登录")
	@ApiImplicitParams({ @ApiImplicitParam(name = "loginVo", value = "用户登录vo", dataType = "LoginVo", required = true) })
	public Result<User> login(@RequestBody LoginVo loginVo,
			HttpServletRequest request,HttpServletResponse response) {
		User user = new User();
		BeanUtils.copyProperties(loginVo, user);
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		user = userService.queryOne(user);
		if (user != null) {
			request.getSession().setAttribute(Constant.SESSION_KEY, user);
		} else {
			return Result.error(CodeMsg.LOGIN_ERROR);
		}
		String token  = user.getMobile()+"-"+user.getPassword();
		CookieUtils.setCookie(request, response, Constant.COOKIE_TOKEN, token , 7*24*60*60);
		return Result.success(user);
	}
	
	@PostMapping("auto-login")
	@ApiOperation(value = "自动登录")
	public Result<User> autoLogin(HttpServletRequest request,HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String token = "";
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals(Constant.COOKIE_TOKEN)){
				token = cookie.getValue();
				break;
			}
		}
		if(StringUtils.isBlank(token)){
			Result.paramError("token不存在");
		}
		String[] tokenArr = token.split("-");
		User userCons = new User();
		userCons.setMobile(tokenArr[0]);
		userCons.setPassword(tokenArr[1]);
		User user = userService.queryOne(userCons);
		if (user != null) {
			request.getSession().setAttribute(Constant.SESSION_KEY, user);
		} else {
			return Result.error(CodeMsg.LOGIN_ERROR);
		}
		CookieUtils.setCookie(request, response, "token", token , 7*24*60*60);
		return Result.success(user);
	}
	
	
	@GetMapping("/mobile/{mobile}")
	@ApiOperation(value = "用户查询", notes = "根据手机号查询")
	public Result<User> findByMobile(@PathVariable("mobile") String mobile){
		User user = new User();
		user.setMobile(mobile);
		return Result.success(userService.queryOne(user));
	}

	@PostMapping("/register")
	@ApiOperation(value = "用户注册", notes = "手机号和密码必填")
	@ApiImplicitParams({ @ApiImplicitParam(name = "user", value = "用户实体", dataType = "User", required = true) })
	public Result<Object> register(@RequestBody User user) {
		if (StringUtils.isBlank(user.getMobile())) {
			throw new InvalidParamException("mobile is blank");
		}
		if (StringUtils.isBlank(user.getPassword())) {
			throw new InvalidParamException("password is blank");
		}
		//validate if mobile exists
		User userExists = new User();
		userExists.setMobile(user.getMobile());
		userExists = userService.queryOne(userExists);
		if (userExists != null) {
			return Result.error(CodeMsg.MOBILE_EXIST);
		}
		user.setUsername(user.getMobile());
		userService.saveSelective(user);
		return Result.success(null);
	}
	
	@DeleteMapping("/logout")
	@ApiOperation(value = "退出登录")
	public Result<Void> logout(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute(Constant.SESSION_KEY);
		CookieUtils.deleteCookie(request, response, Constant.COOKIE_TOKEN);
		return Result.success(null);
	}

}
