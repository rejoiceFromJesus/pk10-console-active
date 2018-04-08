/**
 * 系统项目名称
 * com.pk10.active.console.controller.client
 * WithdrawController.java
 * 
 * 2018年4月8日-上午10:46:33
 *  2018金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.controller.client;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pk10.active.console.common.bean.CodeMsg;
import com.pk10.active.console.common.bean.Result;
import com.pk10.active.console.common.constant.Constant;
import com.pk10.active.console.entity.User;
import com.pk10.active.console.service.UserService;
import com.pk10.active.console.service.WithdrawService;

/**
 *
 * WithdrawController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2018年4月8日 上午10:46:33
 * 
 * @version 1.0.0
 *
 */

@RestController
@RequestMapping("/client/withdraw")
@Api(tags="提现模块")
public class ClientWithdrawController {

	@Autowired
	UserService userService;
	@Autowired
	WithdrawService withdrawService;
	
	@ApiOperation(value = "提现申请", notes = "提现申请，只需要传递提现金额")
	@PostMapping("/apply")
	public 	Result<BigDecimal> withdrawApply(@RequestParam("money")BigDecimal money, HttpSession session){
		if(money.compareTo(new BigDecimal(0)) != 1){
			return Result.paramError("提现金额必须大于0");
		}
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		user = userService.queryByID(user.getId());
		if(user.getBalance().compareTo(money) == -1){
			return Result.error(CodeMsg.WITHDRAW_INSUFFICIENT_BALANCE);
		}
		user.setBalance(user.getBalance().subtract(money));
		withdrawService.apply(user,money);
		return Result.success(user.getBalance());
	}
}
