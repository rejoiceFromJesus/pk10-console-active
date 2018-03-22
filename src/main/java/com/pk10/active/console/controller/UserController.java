/**
 * 系统项目名称
 * com.pk10.active.console.controller
 * UserController.java
 * 
 * 2017年6月8日-下午5:38:42
 *  2017金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pk10.active.console.common.bean.CodeMsg;
import com.pk10.active.console.common.bean.Result;
import com.pk10.active.console.common.constant.Constant;
import com.pk10.active.console.entity.RechargeRecord;
import com.pk10.active.console.entity.User;
import com.pk10.active.console.handler.InvalidParamException;
import com.pk10.active.console.service.UserService;
import com.pk10.active.console.vo.LoginVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *
 * UserController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月8日 下午5:38:42
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User,UserService>{

	@PutMapping("/recharge")
	public Result<Boolean> changePwd(@RequestBody RechargeRecord rechargeRecord){
		this.getService().recharge(rechargeRecord.getMobile(),rechargeRecord.getMoney());
		return Result.success(true);
	}
	@PutMapping("/change-pwd")
	public Result<Boolean> changePwd(@RequestBody User user){
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		this.getService().updateByIdSelective(user);
		return Result.success(true);
	}
	
	@PostMapping("login")
	public ModelAndView login(User user,HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		user = this.getService().queryOne(user);
		if(user != null){
			request.getSession().setAttribute(Constant.SESSION_KEY, user);
			model.setViewName("redirect:/page/home.html");
		}else{
			model.setViewName("login"); 
			model.addObject("error", "username and password not match");
		}
		return model;
	}
	

	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		request.getSession().removeAttribute(Constant.SESSION_KEY);
		model.setViewName("redirect:/page/login.html");
		return model;
	}
	
	
}
