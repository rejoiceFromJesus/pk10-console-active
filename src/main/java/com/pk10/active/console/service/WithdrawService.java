/**
 * 系统项目名称
 * com.pk10.active.console.service
 * WithdrawService.java
 * 
 * 2018年4月8日-上午11:24:18
 *  2018金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.service;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk10.active.console.common.constant.Constant;
import com.pk10.active.console.common.constant.WithdrawStatusEnum;
import com.pk10.active.console.entity.User;
import com.pk10.active.console.entity.Withdraw;

/**
 *
 * WithdrawService
 * 
 * @author rejoice 948870341@qq.com
 * @date 2018年4月8日 上午11:24:18
 * 
 * @version 1.0.0
 *
 */
@Service
public class WithdrawService extends BaseService<Withdraw> {
	
	@Autowired
	UserService userService;

	/**
	 * apply(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param user
	 * @param money
	 * void
	*/
	public void apply(User user, BigDecimal money) {
		Withdraw withdraw = new Withdraw();
		withdraw.setMobile(user.getMobile());
		withdraw.setMoney(money);
		withdraw.setApplyTime(DateTime.now().toString(Constant.DATE_FORMAT_PATTERN2));
		withdraw.setStatus(WithdrawStatusEnum.APPLY.value());
		this.saveSelective(withdraw);
		User userCons = new User();
		userCons.setMobile(user.getMobile());
		userCons.setBalance(user.getBalance());
		userService.updateByMobileSelective(userCons);
	}

}
