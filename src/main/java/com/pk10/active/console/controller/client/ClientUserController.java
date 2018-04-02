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

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk10.active.console.common.bean.CodeMsg;
import com.pk10.active.console.common.bean.Result;
import com.pk10.active.console.common.constant.Constant;
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
			HttpServletRequest request) {
		User user = new User();
		BeanUtils.copyProperties(loginVo, user);
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		user = userService.queryOne(user);
		if (user != null) {
			request.getSession().setAttribute(Constant.SESSION_KEY, user);
		} else {
			return Result.error(CodeMsg.LOGIN_ERROR);
		}
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
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		userService.saveSelective(user);
		return Result.success(null);
	}

}
